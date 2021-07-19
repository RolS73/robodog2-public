package com.codecool.robodog2.dao.implementation.database;

import com.codecool.robodog2.dao.interfaces.PedigreeDAO;
import com.codecool.robodog2.dao.mapper.DogMapper;
import com.codecool.robodog2.dao.mapper.PedigreeMapper;
import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Pedigree;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Random;

@Repository
public class PedigreeJdbcDao implements PedigreeDAO {

    JdbcTemplate jdbcTemplate;


    public PedigreeJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addPedigree(Pedigree pedigree) {
        String query = "Insert into PEDIGREE (PUPPY_ID, MOM_ID, DAD_ID) Values(?, ?, ?)";
        this.jdbcTemplate.update(query, pedigree.getPuppyId(), pedigree.getMomId(), pedigree.getDadId());
    }

    @Override
    public List<Pedigree> listPedigrees() {
        String query = "SELECT * FROM PEDIGREE";
        return this.jdbcTemplate.query(query, new PedigreeMapper());
    }

    @Override
    public Pedigree getPedigree(long id) {
        String query = "SELECT * FROM PEDIGREE WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new PedigreeMapper(), id);
    }

    @Override
    public void updatePedigree(Pedigree pedigree, long id) {
        String query = "UPDATE PEDIGREE " +
                "SET PUPPY_ID = ?," +
                "MOM_ID = ?," +
                "DAD_ID = ?" +
                " WHERE id = ?";
        jdbcTemplate.update(query, pedigree.getPuppyId(), pedigree.getMomId(), pedigree.getDadId(), id);
    }

    @Override
    public void deletePedigree(long id) {
        String query = "DELETE FROM PEDIGREE WHERE id = ?";
        jdbcTemplate.update(query, id);
    }

    @Override
    public List<Dog> getFamilyOfDog(long dog_id) {
        String query = "SELECT * FROM Pedigree WHERE PUPPY_ID = ? OR MOM_ID = ? OR DAD_ID = ?";
        return jdbcTemplate.query(query, new DogMapper(), dog_id);
    }

    @Override
    public void setPedigree(Pedigree pedigree, long id) {
        String query = "INSERT INTO PEDIGREE VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(query, pedigree.getPuppyId(), pedigree.getMomId(), pedigree.getDadId());
    }

    @Override
    public Dog getPuppyMom(long id) {
        String query =
                "SELECT DOG.ID, DOG.BREED, DOG.AGE, DOG.NAME " +
                "FROM dog " +
                "INNER JOIN PEDIGREE ON " +
                "PEDIGREE.MOM_ID = DOG.ID AND PEDIGREE.PUPPY_ID = ?";
        return jdbcTemplate.queryForObject(query, new DogMapper(), id);
    }

    @Override
    public Dog getPuppyDad(long id) {
        String query =
                "SELECT DOG.ID, DOG.BREED, DOG.AGE, DOG.NAME " +
                "FROM dog " +
                "INNER JOIN PEDIGREE ON " +
                "PEDIGREE.DAD_ID = DOG.ID AND PEDIGREE.PUPPY_ID = ?";
        return jdbcTemplate.queryForObject(query, new DogMapper(), id);
    }

    @Override
    public Dog getRandomPuppyFromParents(long dadId, long momId) {
        Dog mom = jdbcTemplate.queryForObject("Select * FROM dog WHERE id = ?", new DogMapper(), momId);
        Dog dad = jdbcTemplate.queryForObject("Select * FROM dog WHERE id = ?", new DogMapper(), dadId);

        System.out.println(mom);
        System.out.println(dad);

        Dog puppy = new Dog();
        puppy.setName("Puppeh");
        puppy.setAge(0);

        if (mom != null && dad != null) {
            if (new Random().nextInt(2) == 0) {
                puppy.setBreed(mom.getBreed());
            } else {
                puppy.setBreed(dad.getBreed());
            }
        }


        return puppy;
    }

    @Override
    public List<Dog> getDogSiblings(long id) {
        Dog mom = getPuppyMom(id);
        Dog dad = getPuppyDad(id);

        String queryForMomChildren = "SELECT * FROM dog " +
                "INNER JOIN PEDIGREE ON " +
                "PEDIGREE.PUPPY_ID = DOG.ID " +
                "WHERE MOM_ID = ? AND DOG.ID != ? OR DAD_ID = ? AND DOG.ID != ?";
        List<Dog> childrenOfMom = jdbcTemplate.query(queryForMomChildren, new DogMapper(), mom.getId(), id, dad.getId(), id);
        /*String queryForDadChildren = "SELECT * FROM dog INNER JOIN PEDIGREE ON PEDIGREE.PUPPY_ID = DOG.ID WHERE DAD_ID = ? AND DOG.ID != ?";
        List<Dog> childrenOfDad = jdbcTemplate.query(queryForDadChildren, new DogMapper(), dad.getId(), id);*/

        /*for (Dog child : childrenOfDad) {
            if (!childrenOfMom.contains(child)) {
                childrenOfMom.add(child);
            }
        }*/

        return childrenOfMom;
    }
}

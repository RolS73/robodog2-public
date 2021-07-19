package com.codecool.robodog2.dao.implementation.database;

import com.codecool.robodog2.dao.interfaces.DogDAO;
import com.codecool.robodog2.dao.mapper.DogMapper;
import com.codecool.robodog2.model.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Random;

@Repository
public class DogJdbcDao implements DogDAO {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public DogJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addDog(Dog dog) {
        String query = "Insert into dog (BREED, AGE, NAME) Values (?, ?, ?)";
        this.jdbcTemplate.update(query, String.valueOf(dog.getBreed()), dog.getAge(), dog.getName());
    }

    @Override
    public List<Dog> listDogs() {
        String query = "SELECT * FROM dog";
        return jdbcTemplate.query(query, new DogMapper());
    }

    @Override
    public Dog getDog(long id) {
        String query = "SELECT * FROM dog WHERE id = ?";
        return this.jdbcTemplate.queryForObject(query, new DogMapper(), id);
    }

    @Override
    public void updateDog(Dog dog, long id) {
        String query = "UPDATE dog SET breed = ?," +
                " name = ?," +
                " age = ?" +
                " WHERE id = ?";
        jdbcTemplate.update(query, String.valueOf(dog.getBreed()), dog.getName(), dog.getAge(), id);
    }

    @Override
    public void deleteDog(long id) {
        String query = "DELETE FROM Dog WHERE id = ?";
        jdbcTemplate.update(query, id);
    }

    @Override
    public Dog addRandomDog(Dog dog) {
        String query = "INSERT INTO dog (BREED, AGE, NAME) Values(?, ?, ?)";
        this.jdbcTemplate.update(query, String.valueOf(dog.getBreed()), dog.getAge(), dog.getName());
        return dog;
    }

    @Override
    public long addDogAndReturnId(Dog dog) {
        String query = "Insert into dog Values(?, ?, ?, ?)";
        this.jdbcTemplate.update(query, String.valueOf(dog.getBreed()),dog.getAge(), dog.getName());
        return 0;
    }

    @Override
    public Dog postRandomPuppyFromParents(long dadId, long momId) {
        String queryForMom = "Select * FROM dog WHERE id = ?";
        Dog mom = jdbcTemplate.queryForObject(queryForMom, new DogMapper(), momId);
        Dog dad = jdbcTemplate.queryForObject(queryForMom, new DogMapper(), dadId);

        Random rand = new Random();

        Dog puppy = new Dog();

        puppy.setName("Puppeh");
        puppy.setAge(0);

        if (rand.nextInt(2) == 0) {
            assert mom != null;
            puppy.setBreed(mom.getBreed());
        } else {
            assert dad != null;
            puppy.setBreed(dad.getBreed());
        }

        return puppy;
    }
}

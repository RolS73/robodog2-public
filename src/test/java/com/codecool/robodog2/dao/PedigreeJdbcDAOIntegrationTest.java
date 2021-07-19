package com.codecool.robodog2.dao;

import com.codecool.robodog2.dao.implementation.database.PedigreeJdbcDao;
import com.codecool.robodog2.model.Breed;
import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Pedigree;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

//Use the schema built in the .sql file put in resources
@Sql(scripts = {"classpath:schema.sql","classpath:data.sql"})
@ComponentScan
@SpringBootTest
//As the context becomes dirty - database modifications happen, etc. - this method restores it to the original state
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PedigreeJdbcDAOIntegrationTest {

    Gson gson = new Gson();

    @Autowired
    JdbcTemplate jdbcTemplate;

    PedigreeJdbcDao pedigreeJdbcDao;

    @BeforeEach
    void init() {
        pedigreeJdbcDao = new PedigreeJdbcDao(jdbcTemplate);
    }


    @Test
    void getAllPedigreesByIdTest() {
        List<Pedigree> pedigreeList = new ArrayList<>();
        Pedigree granMaPg = new Pedigree();
        Pedigree grandPaPg = new Pedigree();
        Pedigree dad = new Pedigree(11L, 10L, 8L);
        Pedigree pup = new Pedigree();
        pup.setDadId(8L);
        granMaPg.setPuppyId(9L);
        grandPaPg.setPuppyId(9L);

        pedigreeList.add(grandPaPg);
        pedigreeList.add(granMaPg);
        pedigreeList.add(dad);
        pedigreeList.add(pup);

        Assertions.assertEquals(gson.toJson(pedigreeList), gson.toJson(pedigreeJdbcDao.listPedigrees()));
    }

    @Test
    void getMomIdOfDogTest() {
        Dog mom = new Dog(Breed.SPANIEL, "Lucy", 2);
        mom.setId(1);

        Assertions.assertEquals(gson.toJson(pedigreeJdbcDao.getPuppyMom(3)), gson.toJson(mom));
    }

    @Test
    void getDadIdOfDogTest() {
        Dog dad = new Dog(Breed.LABRADOR, "Molly", 1);
        dad.setId(2);

        Assertions.assertEquals(gson.toJson(pedigreeJdbcDao.getPuppyDad(3)), gson.toJson(dad));
    }

    @Test
    void getAllSiblingsOfDogTest() {
        List<Dog> siblingList = new ArrayList<>();
        Dog sibling1 = new Dog(Breed.LABRADOR, "Gel", 1);
        sibling1.setId(6);
        Dog sibling2 = new Dog(Breed.SPANIEL, "Boyo", 0);
        sibling2.setId(7);
        siblingList.add(sibling1);
        siblingList.add(sibling2);

        Assertions.assertEquals(gson.toJson(siblingList), gson.toJson(pedigreeJdbcDao.getDogSiblings(3)));
    }
}

package com.codecool.robodog2.dao;

import com.codecool.robodog2.dao.implementation.database.DogJdbcDao;
import com.codecool.robodog2.model.Breed;
import com.codecool.robodog2.model.Dog;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.util.Arrays;
import java.util.List;

//Use the schema built in this .sql file put in resources
@Sql(scripts = {"classpath:schema.sql"})
@ComponentScan
@SpringBootTest
//As the context becomes dirty - database modifications happen, etc. - this method restores it to the original state
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DogJdbcDAOIntegrationTest {

    Gson gson = new Gson();

    @Autowired
    JdbcTemplate jdbcTemplate;

    DogJdbcDao dogJdbcDaoTest;

    @BeforeEach
    void init() {
        dogJdbcDaoTest = new DogJdbcDao(jdbcTemplate);
    }

    @Test
    void addDogTest() {
        Dog testDog = new Dog();
        testDog.setId(1);
        testDog.setBreed(Breed.BULLDOG);
        testDog.setAge(2);
        testDog.setName("Buksi");
        dogJdbcDaoTest.addDog(testDog);

        Assertions.assertEquals(gson.toJson(testDog), gson.toJson(dogJdbcDaoTest.getDog(testDog.getId())));
    }


    @Test
    void listDogsTest() {
       Dog testDog1 = new Dog();
        testDog1.setId(1);
        testDog1.setBreed(Breed.BULLDOG);
        testDog1.setAge(2);
        testDog1.setName("Buksi");

        Dog testDog2 = new Dog();
        testDog2.setId(2);
        testDog2.setBreed(Breed.LABRADOR);
        testDog2.setAge(1);
        testDog2.setName("János");

        Dog testDog3 = new Dog();
        testDog3.setId(3);
        testDog3.setBreed(Breed.DACHSHUND);
        testDog3.setAge(3);
        testDog3.setName("Vauvau");

        List<Dog> dogList = Arrays.asList(testDog1, testDog2, testDog3);

        dogJdbcDaoTest.addDog(testDog1);
        dogJdbcDaoTest.addDog(testDog2);
        dogJdbcDaoTest.addDog(testDog3);

        Assertions.assertEquals(gson.toJson(dogList), gson.toJson(dogJdbcDaoTest.listDogs()));
    }

    @Test
    void getDog() {
        Dog testDog1 = new Dog();
        testDog1.setId(1);
        testDog1.setBreed(Breed.BULLDOG);
        testDog1.setAge(2);
        testDog1.setName("Buksi");

        dogJdbcDaoTest.addDog(testDog1);

        Assertions.assertEquals(gson.toJson(testDog1), gson.toJson(dogJdbcDaoTest.getDog(testDog1.getId())));
    }

    @Test
    void deleteDogTest() {
        Dog testDog1 = new Dog();
        testDog1.setId(1);
        testDog1.setBreed(Breed.BULLDOG);
        testDog1.setAge(2);
        testDog1.setName("Buksi");

        dogJdbcDaoTest.addDog(testDog1);

        Assertions.assertNotNull(dogJdbcDaoTest.getDog(testDog1.getId()));
        dogJdbcDaoTest.deleteDog(testDog1.getId());
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> dogJdbcDaoTest.getDog(testDog1.getId()));
    }

    @Test
    void updateDogTest() {
        Dog testDog1 = new Dog();
        testDog1.setId(1);
        testDog1.setBreed(Breed.BULLDOG);
        testDog1.setAge(2);
        testDog1.setName("Buksi");

        dogJdbcDaoTest.addDog(testDog1);

        Assertions.assertEquals(gson.toJson(testDog1), gson.toJson(dogJdbcDaoTest.getDog(testDog1.getId())));

        Dog testDogWithNewData = new Dog();
        testDogWithNewData.setId(1);
        testDogWithNewData.setBreed(Breed.LABRADOR);
        testDogWithNewData.setAge(3);
        testDogWithNewData.setName("Jolán");

        dogJdbcDaoTest.updateDog(testDogWithNewData, testDog1.getId());

        Assertions.assertEquals(gson.toJson(testDogWithNewData), gson.toJson(dogJdbcDaoTest.getDog(testDog1.getId())));
    }
}

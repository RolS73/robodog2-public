package com.codecool.robodog2.service;

import com.codecool.robodog2.dao.implementation.database.PedigreeJdbcDao;
import com.codecool.robodog2.dao.mapper.DogMapper;
import com.codecool.robodog2.model.Breed;
import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.service.services.PedigreeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
public class DogServiceTest {

    @InjectMocks
    PedigreeJdbcDao pedigreeJdbcDaoMock;

    @Mock
    JdbcTemplate jdbcTemplate;

    PedigreeService pedigreeService;

    @BeforeEach
    void init() {
        pedigreeService = new PedigreeService(pedigreeJdbcDaoMock);
    }

    /*@Test
    void parentsFoundFromMockedJdbcTemplateTest() {
        Assertions.assertNotNull(jdbcTemplate.queryForObject("Select * FROM dog WHERE id = ?", new DogMapper(), 1));
        System.out.println("Mom: " + jdbcTemplate.queryForObject("Select * FROM dog WHERE id = ?", new DogMapper(), 1));

        Assertions.assertNotNull(jdbcTemplate.queryForObject("Select * FROM dog WHERE id = ?", new DogMapper(), 2));
        System.out.println("Dad: " + jdbcTemplate.queryForObject("Select * FROM dog WHERE id = ?", new DogMapper(), 2));
    }*/

    @Test
    void generateRandomPuppyFromParentsTest_IsZeroYearsOld() {
        Assertions.assertEquals(0, pedigreeService.getRandomPuppyFromParents(2,1).getAge());
    }

    @Test
    void generateRandomPuppyFromParentsTest_IsBreedInheritedFromOneOfTheParents() throws NoSuchFieldException {
        Dog mom = new Dog(Breed.LABRADOR, "Monica", 6);
        Dog dad = new Dog(Breed.SPANIEL, "Darman", 7);
        mom.setId(1L);
        dad.setId(2L);


        Mockito.lenient().when(jdbcTemplate.queryForObject(
                Mockito.eq("Select * FROM dog WHERE id = ?"), Mockito.any(DogMapper.class), Mockito.eq(1)))
                .thenReturn(mom);

        Mockito.lenient().when(jdbcTemplate.queryForObject(
                Mockito.eq("Select * FROM dog WHERE id = ?"), Mockito.any(DogMapper.class), Mockito.eq(2)))
                .thenReturn(dad);

        List<Breed> validBreeds = Arrays.asList(mom.getBreed(), dad.getBreed());

        Assertions.assertTrue(validBreeds.contains(pedigreeService.getRandomPuppyFromParents(2,1).getBreed()));
    }
}

package com.codecool.robodog2.dao.interfaces;

import com.codecool.robodog2.model.Dog;

import java.util.List;

public interface DogDAO {

    void addDog(Dog dog);

    List<Dog> listDogs();

    Dog getDog(long id);

    void updateDog(Dog dog, long id);

    void deleteDog(long id);

    Dog addRandomDog(Dog dog);

    long addDogAndReturnId(Dog dog);

    Dog postRandomPuppyFromParents(long dadId, long momId);
}

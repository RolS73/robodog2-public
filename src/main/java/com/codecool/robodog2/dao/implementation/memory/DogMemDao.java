package com.codecool.robodog2.dao.implementation.memory;

import com.codecool.robodog2.dao.interfaces.DogDAO;
import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.service.DogStorage;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class DogMemDao implements DogDAO {

    DogStorage dogStorage;
    AtomicLong nextId;

    @Override
    public void addDog(Dog dog) {
        dog.setId(nextId.incrementAndGet());
        dogStorage.addDogToStorage(dog);
    }

    @Override
    public List<Dog> listDogs() {
        return dogStorage.getDogStorage();
    }

    @Override
    public Dog getDog(long id) {
        return dogStorage.getDogById(id);
    }

    @Override
    public void updateDog(Dog dog, long id) {
        dogStorage.updateDog(dog, id);
    }

    @Override
    public void deleteDog(long id) {
        dogStorage.removeDogFromStorage(id);
    }

    @Override
    public Dog addRandomDog(Dog dog) {
        dogStorage.addRandomDogToStorage();
        return dog;
    }

    @Override
    public long addDogAndReturnId(Dog dog) {
        //Dog dogToAdd = dog;
        addDog(dog);
        return dog.getId();
    }

    @Override
    public Dog postRandomPuppyFromParents(long dadId, long momId) {
        return null;
    }
}

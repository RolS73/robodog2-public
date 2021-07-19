package com.codecool.robodog2.service;

import com.codecool.robodog2.model.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class DogStorage {

    List<Dog> dogStorage = new ArrayList<>();
    AtomicLong nextId;

    @Autowired
    public DogStorage() {}

    public DogStorage(ArrayList<Dog> dogsToIncludeInStarterStorage) {
        dogStorage = dogsToIncludeInStarterStorage;
    }

    public void addDogToStorage(Dog dogToAdd) {
        dogToAdd.setId(nextId.incrementAndGet());
        dogStorage.add(dogToAdd);
    }

    public Dog getDogById(long id) {
        return dogStorage.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }

    public void addRandomDogToStorage() {
        Dog dogToAdd = DogCreator.createRandomDog();
        dogToAdd.setId(nextId.incrementAndGet());
        dogStorage.add(dogToAdd);

    }

    public List<Dog> getDogStorage() {
        return dogStorage;
    }

    public void updateDog(Dog inboundDogData, long id) {
        Dog dogToModify = dogStorage.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        if (dogToModify != null) {
            if (!dogToModify.getName().equals(inboundDogData.getName())) {
                dogToModify.setName(inboundDogData.getName());
            }
            if (dogToModify.getAge() != inboundDogData.getAge()) {
                dogToModify.setAge(inboundDogData.getAge());
            }
            if (dogToModify.getBreed() != inboundDogData.getBreed()) {
                dogToModify.setBreed(inboundDogData.getBreed());
            }
        }
    }

    public void removeDogFromStorage(long id) {
        Dog dogToRemove = getDogById(id);
        dogStorage.remove(dogToRemove);
    }
}

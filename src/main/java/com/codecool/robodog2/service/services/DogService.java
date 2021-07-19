package com.codecool.robodog2.service.services;

import com.codecool.robodog2.dao.interfaces.DogDAO;
import com.codecool.robodog2.model.Breed;
import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.DogNames;
import com.codecool.robodog2.service.DaoType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class DogService {

    private DaoType daoType = DaoType.MEMORY;
    private DogDAO activeDao;


    public DogService(DogDAO dogDAO) {
        this.activeDao = dogDAO;
    }

    public void addDog(Dog dog) {
        activeDao.addDog(dog);
    }

    public List<Dog> listDogs() {
        return activeDao.listDogs();
    }

    public Dog getDog(long id) {
        return activeDao.getDog(id);
    }

    public void updateDog(Dog dog, long id) {
        activeDao.updateDog(dog, id);
    }

    public void deleteDog(long id) {
        activeDao.deleteDog(id);
    }

    public long addDogAndReturnId(Dog dog) {
        return activeDao.addDogAndReturnId(dog);
    }

    public Dog addRandomDog() {
        return activeDao.addRandomDog(createRandomDog());
    }

    public Dog getRandomPuppyFromParents(long dadId, long momId) {
        return activeDao.postRandomPuppyFromParents(dadId, momId);
    }

    public Dog createRandomDog() {
        Random rand = new Random();

        return new Dog(Breed.values()[rand.nextInt(Breed.values().length)],
                DogNames.getInstance().getDogNames().get(rand.nextInt(DogNames.getInstance().getDogNames().size())),
                rand.nextInt(20));
    }
}

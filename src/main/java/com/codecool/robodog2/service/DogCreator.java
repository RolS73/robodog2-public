package com.codecool.robodog2.service;

import com.codecool.robodog2.model.Breed;
import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.DogNames;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class DogCreator {

    private DogCreator(){}

    public static Dog createRandomDog() {
        Random rand = new Random();

        return new Dog(Breed.values()[rand.nextInt(Breed.values().length)],
                DogNames.getInstance().getDogNames().get(rand.nextInt(DogNames.getInstance().getDogNames().size())),
                rand.nextInt(20));
    }
}

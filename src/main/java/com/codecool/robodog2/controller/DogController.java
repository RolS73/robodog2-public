package com.codecool.robodog2.controller;

import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.service.services.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(value = "/dog")
public class DogController {

    DogService dogService;

    @Autowired
    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @GetMapping
    public List<Dog> getDogStorage() {
        return dogService.listDogs();
    }

    @PostMapping
    public void addDogToStorage(@RequestBody Dog dogFromBody) {
        dogService.addDog(dogFromBody);
    }

    @GetMapping(value = "/random")
    public Dog addRandomDogToStorage() {
        return dogService.addRandomDog();
    }

    @GetMapping(value = "/{id}")
    public Dog getDogFromURL(@PathVariable long id) {
        return dogService.getDog(id);
    }

    @DeleteMapping(value = "/{id}")
    public void removeDogById(@PathVariable long id) {
        dogService.deleteDog(id);
    }

    @PutMapping(value = "/{id}")
    public void updateDogAttribute(@PathVariable long id, @RequestBody Dog dogData)  {
        dogService.updateDog(dogData, id);
    }
}

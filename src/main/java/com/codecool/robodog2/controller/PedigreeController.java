package com.codecool.robodog2.controller;


import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Pedigree;
import com.codecool.robodog2.service.services.PedigreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class PedigreeController {

    PedigreeService pedigreeService;

    @Autowired
    public PedigreeController(PedigreeService pedigreeService) {
        this.pedigreeService = pedigreeService;
    }

    @GetMapping(value = "dog/{dog_id}/directpedigree")
    public Pedigree getPedigree(@PathVariable long dog_id) {
        return pedigreeService.getPedigree(dog_id);
    }

    @GetMapping(value = "dog/pedigrees")
    public List<Pedigree> getAllPedigrees() {
        return pedigreeService.listPedigrees();
    }

    @PostMapping(value = "dog/{dog_id}/pedigree")
    public void addPedigree(@RequestBody Pedigree pedigree) {
        pedigreeService.addPedigree(pedigree);
    }

    @PutMapping(value = "dog/{dog_id}/pedigree")
    public void updatePedigree(@RequestBody Pedigree pedigree, @PathVariable long dog_id) {
        pedigreeService.updatePedigree(pedigree, dog_id);
    }

    @DeleteMapping(value = "dog/{dog_id}/pedigree")
    public void deletePedigree(@PathVariable long dog_id) {
        pedigreeService.deletePedigree(dog_id);
    }

    @GetMapping(value = "dog/{dog_id}/pedigree")
    public List<Dog> getFamilyOfDog(@PathVariable long dog_id) {
        return pedigreeService.getFamilyOfDog(dog_id);
    }

    /*@PostMapping(value = "/dog/{dog_id}/pedigree")
    public void setPedigreeOfDog(@PathVariable long id, @RequestBody Pedigree pedigree) {
        pedigreeService.setDogPedigree(pedigree, id);
    }*/

    /*@GetMapping(value = "/dog/puppy")
    public String getNewPuppy(@RequestBody */

    @GetMapping(value = "/dog/{dog_id}/pedigree/mom")
    public Dog getPuppyMom(@PathVariable("dog_id") long id) {
        return pedigreeService.getPuppyMom(id);
    }

    @GetMapping(value = "/dog/{dog_id}/pedigree/dad")
    public Dog getPuppyDad(@PathVariable("dog_id") long id) {
        return pedigreeService.getPuppyDad(id);
    }

    @GetMapping(value = "/dog/{dog_id}/pedigree/siblings")
    public List<Dog> getDogSiblings(@PathVariable("dog_id") long id) {
        return pedigreeService.getDogSiblings(id);
    }

    @GetMapping(value = "/dog/puppy")
    public Dog postRandomPuppyFromParents(@RequestParam long momId, long dadId) {
        return pedigreeService.getRandomPuppyFromParents(dadId, momId);
    }
}

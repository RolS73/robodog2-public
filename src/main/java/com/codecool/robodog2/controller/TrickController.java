package com.codecool.robodog2.controller;

import com.codecool.robodog2.model.Trick;
import com.codecool.robodog2.service.services.TrickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/trick")
public class TrickController {

    TrickService trickService;

    @Autowired
    public TrickController(TrickService trickService) {
        this.trickService = trickService;
    }

    @GetMapping(value = "/{id}")
    public Trick getTrick(@PathVariable long id) {
        return trickService.getTrick(id);
    }

    @GetMapping
    public List<Trick> getAllTricks() {
        return trickService.listTricks();
    }

    @PostMapping
    public void addTrick(@RequestBody Trick trick) {
        trickService.addTrick(trick);
    }

    @PutMapping(value = "/{id}")
    public void updateTrick(@RequestBody Trick trick, @PathVariable long id) {
        trickService.updateTrick(trick, id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteTrick(@PathVariable long id) {
        trickService.deleteTrick(id);
    }
}

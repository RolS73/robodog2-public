package com.codecool.robodog2.controller;

import com.codecool.robodog2.model.Skill;
import com.codecool.robodog2.service.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/skill")
public class SkillController {

    SkillService skillService;

    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping(value = "/{id}")
    public Skill getSkill(@PathVariable long id) {
        return skillService.getSkill(id);
    }

    @GetMapping
    public List<Skill> getAllSkills() {
        return skillService.listSkills();
    }

    @PostMapping
    public void addSkill(@RequestBody Skill skill) {
        skillService.addSkill(skill);
    }

    @PutMapping(value = "/{id}")
    public void updateSkill(@RequestBody Skill skill, @PathVariable long id) {
        skillService.updateSkill(skill, id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteSkill(@PathVariable long id) {
        skillService.deleteSkill(id);
    }

    @GetMapping(value = "/name/{trick_name}/dog/{dog_id}")
    public long getSkillProgress(@PathVariable String trick_name, @PathVariable long dog_id) {
        return skillService.getSkillProgress(trick_name, dog_id);
    }

    @PutMapping(value = "/name/{trick_name}/dog/{dog_id}")
    public void updateSkillProgress(@PathVariable String trick_name, @PathVariable long dog_id) {
        skillService.updateSkillProgress(trick_name, dog_id);
    }
}

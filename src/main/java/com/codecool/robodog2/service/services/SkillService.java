package com.codecool.robodog2.service.services;

import com.codecool.robodog2.dao.interfaces.SkillDAO;
import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Skill;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillService {

    SkillDAO skillDAO;

    public SkillService(SkillDAO skillDAO){
        this.skillDAO = skillDAO;
    }

    public void addSkill(Skill skill) {
       skillDAO.addSkill(skill);
    }

    public List<Skill> listSkills() {
        return skillDAO.listSkill();
    }

    public Skill getSkill(long id) {
        return skillDAO.getSkill(id);
    }

    public void updateSkill(Skill skill, long id) {
        skillDAO.updateSkill(skill, id);
    }

    public void deleteSkill(long id) {
        skillDAO.deleteSkill(id);
    }

    public List<Dog> getDogsWithSkillId(long skillId) {
        return skillDAO.getDogsWithSkillId(skillId);
    }

    public Optional<Skill> getDogSkill(long dogId, long skillId) {
        return getDogSkill(dogId, skillId);
    }

    public long getSkillProgress(String trick_name, long dogid) {
        return skillDAO.getSkillProgress(trick_name, dogid);
    }

    public void updateSkillProgress(String trick_name, long dogid) {
        skillDAO.updateSkillProgress(trick_name, dogid);
    }
}

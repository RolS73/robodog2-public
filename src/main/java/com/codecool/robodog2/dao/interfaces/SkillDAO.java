package com.codecool.robodog2.dao.interfaces;

import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Skill;

import java.util.List;
import java.util.Optional;

public interface SkillDAO {

    void addSkill(Skill skill);

    List<Skill> listSkill();

    Skill getSkill(long id);

    void updateSkill(Skill skill, long id);

    void deleteSkill(long id);

    List<Dog> getDogsWithSkillId(long skillId);

    Optional<Skill> getDogSkill(long dogId, long skillId);

    long getSkillProgress(String trick_name, long dogid);

    void updateSkillProgress(String trick_name, long dogid);
}

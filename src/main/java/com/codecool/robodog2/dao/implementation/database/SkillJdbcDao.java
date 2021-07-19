package com.codecool.robodog2.dao.implementation.database;

import com.codecool.robodog2.dao.interfaces.SkillDAO;
import com.codecool.robodog2.dao.mapper.DogMapper;
import com.codecool.robodog2.dao.mapper.SkillMapper;
import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class SkillJdbcDao implements SkillDAO {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public SkillJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addSkill(Skill skill) {
        String query = "Insert into Skill (DOG_ID, TRICK_ID, LEVEL) Values(?, ?, ?)";
        this.jdbcTemplate.update(query, skill.getDog_Id(), skill.getTrick_Id(), skill.getLevel());
    }

    @Override
    public List<Skill> listSkill() {
        String query = "SELECT * FROM Skill";
        return jdbcTemplate.query(query, new SkillMapper());
    }

    @Override
    public Skill getSkill(long id) {
        String query = "SELECT * FROM Skill WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new SkillMapper(), id);
    }

    @Override
    public void updateSkill(Skill skill, long id) {
        String query = "UPDATE Skill " +
                "SET dog_Id = ?," +
                "trick_Id = ?," +
                "level = ?" +
                " WHERE id = ?";
        jdbcTemplate.update(query, skill.getDog_Id(), skill.getTrick_Id(), skill.getLevel(), id);
    }

    @Override
    public void deleteSkill(long id) {
        String query = "DELETE FROM Skill WHERE id = ?";
        jdbcTemplate.update(query, id);
    }

    @Override
    public List<Dog> getDogsWithSkillId(long skillId) {
        String query = "SELECT * FROM dog INNER JOIN SKILL ON DOG.ID = SKILL.DOG_ID AND SKILL.TRICK_ID = ?";
        return jdbcTemplate.query(query, new DogMapper(), skillId);
    }

    @Override
    public Optional<Skill> getDogSkill(long dogId, long skillId) {
        String query = "SELECT * FROM SKILL WHERE DOG_ID = ? AND SKILL_ID = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(query, new SkillMapper(), dogId, skillId));
    }

    @Override
    public long getSkillProgress(String trick_name, long dogid) {
        String query = "SELECT * FROM skill where dog_id = ? AND trick_id = (SELECT id FROM TRICK where name = ?)";
        return Objects.requireNonNull(jdbcTemplate.queryForObject(query, new SkillMapper(), dogid, trick_name)).getLevel();
    }

    @Override
    public void updateSkillProgress(String trick_name, long dogid) {
        String query = "SELECT * FROM skill where dog_id = ? AND trick_id = (SELECT id FROM TRICK where name = ?)";
        Skill skillToUpdate = jdbcTemplate.queryForObject(query, new SkillMapper(), dogid, trick_name);
        if (skillToUpdate != null && skillToUpdate.getLevel() < 3) {
            skillToUpdate.setLevel(skillToUpdate.getLevel() + 1);
            updateSkill(skillToUpdate, skillToUpdate.getId());
        }
    }


}

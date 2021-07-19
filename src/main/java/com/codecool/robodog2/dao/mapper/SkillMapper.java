package com.codecool.robodog2.dao.mapper;

import com.codecool.robodog2.model.Skill;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SkillMapper implements RowMapper<Skill> {

    @Override
    public Skill mapRow(ResultSet rs, int rowNum) throws SQLException {
        Skill skill = new Skill();
        skill.setId(rs.getLong("id"));
        skill.setDog_Id(rs.getLong("dog_id"));
        skill.setTrick_Id(rs.getLong("trick_id"));
        skill.setLevel(rs.getLong("level"));
        return skill;
    }

}

package com.codecool.robodog2.dao.implementation.database;

import com.codecool.robodog2.dao.interfaces.TrickDao;
import com.codecool.robodog2.dao.mapper.TrickMapper;
import com.codecool.robodog2.model.Trick;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrickJdbcDao implements TrickDao {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public TrickJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addTrick(Trick trick) {
        String query = "Insert into Trick (NAME) Values (?)";
        this.jdbcTemplate.update(query, trick.getName());
    }

    @Override
    public List<Trick> listTricks() {
        String query = "SELECT * FROM Trick";
        return jdbcTemplate.query(query, new TrickMapper());
    }

    @Override
    public Trick getTrick(long id) {
        String query = "SELECT * FROM Trick WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new TrickMapper(), id);
    }

    @Override
    public void updateTrick(Trick trick, long id) {
        String query = "UPDATE TRICK " +
                "SET name = ? " +
                "WHERE id = ?";
        jdbcTemplate.update(query, trick.getName(), id);
    }

    @Override
    public void deleteTrick(long id) {
        String query = "DELETE FROM Trick WHERE id = ?";
        jdbcTemplate.update(query, id);
    }
}

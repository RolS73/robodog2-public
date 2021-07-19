package com.codecool.robodog2.dao.mapper;
import com.codecool.robodog2.model.Pedigree;
import org.springframework.stereotype.Component;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PedigreeMapper implements RowMapper<Pedigree> {

    @Override
    public Pedigree mapRow(ResultSet rs, int rowNum) throws SQLException {
        Pedigree pedigree = new Pedigree();
        pedigree.setId(rs.getLong("id"));
        pedigree.setPuppyId(rs.getLong("puppy_Id"));
        pedigree.setMomId(rs.getLong("mom_Id"));
        pedigree.setDadId(rs.getLong("dad_Id"));
        return pedigree;
    }
}

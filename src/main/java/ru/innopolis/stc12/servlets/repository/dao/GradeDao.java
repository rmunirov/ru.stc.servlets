package ru.innopolis.stc12.servlets.repository.dao;


import ru.innopolis.stc12.servlets.pojo.Grade;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GradeDao extends AbstractDao<Grade> {
    public GradeDao() {
        tableName = "grade";
        createSql = "INSERT INTO grade VALUES (DEFAULT , ?)";
        updateSql = "UPDATE grade SET name=? WHERE id=?";
    }

    @Override
    protected List<Grade> readParse(ResultSet resultSet) throws SQLException {
        List<Grade> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(new Grade(resultSet.getInt("id"), resultSet.getInt("point")));
        }
        return list;
    }

    @Override
    protected void mappingStatementForCreate(PreparedStatement statement, Grade entity) throws SQLException {
        statement.setInt(1, entity.getGrade());
    }

    @Override
    protected void mappingStatementForUpdate(PreparedStatement statement, Grade entity) throws SQLException {
        statement.setInt(1, entity.getGrade());
        statement.setInt(2, entity.getId());
    }
}

package ru.innopolis.stc12.servlets.repository.dao;

import ru.innopolis.stc12.servlets.pojo.Sex;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SexDao extends AbstractDao<Sex> {
    public SexDao() {
        tableName = "sex";
        createSql = "INSERT INTO sex VALUES (DEFAULT , ?)";
        updateSql = "UPDATE sex SET name=? WHERE id=?";
    }

    @Override
    protected List<Sex> readParse(ResultSet resultSet) throws SQLException {
        List<Sex> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(new Sex(resultSet.getInt("id"), resultSet.getString("sex")));
        }
        return list;
    }

    @Override
    protected boolean createParse(PreparedStatement statement, Sex entity) throws SQLException {
        statement.setString(1, entity.getSex());
        return statement.execute();
    }

    @Override
    protected boolean updateParse(PreparedStatement statement, Sex entity) throws SQLException {
        statement.setString(1, entity.getSex());
        statement.setInt(2, entity.getId());
        return statement.execute();
    }

}

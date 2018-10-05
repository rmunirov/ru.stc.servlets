package ru.innopolis.stc12.servlets.repository.dao;

import ru.innopolis.stc12.servlets.pojo.City;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDao extends AbstractDao<City> {
    public CityDao() {
        tableName = "city";
        createSql = "INSERT INTO city VALUES (DEFAULT , ?)";
        updateSql = "UPDATE city SET name=? WHERE id=?";
    }

    @Override
    protected List<City> readParse(ResultSet resultSet) throws SQLException {
        List<City> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(new City(resultSet.getInt("id"), resultSet.getString("name")));
        }
        return list;
    }

    @Override
    protected boolean createParse(PreparedStatement statement, City entity) throws SQLException {
        statement.setString(1, entity.getName());
        return statement.execute();
    }

    @Override
    protected boolean updateParse(PreparedStatement statement, City entity) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setInt(2, entity.getId());
        return statement.execute();
    }
}

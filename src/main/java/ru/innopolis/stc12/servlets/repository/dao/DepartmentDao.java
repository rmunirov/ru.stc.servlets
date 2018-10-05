package ru.innopolis.stc12.servlets.repository.dao;

import ru.innopolis.stc12.servlets.pojo.Department;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDao extends AbstractDao<Department> {
    public DepartmentDao() {
        tableName = "department";
        createSql = "INSERT INTO department VALUES (DEFAULT , ?)";
        updateSql = "UPDATE department SET name=? WHERE id=?";
    }

    @Override
    protected List<Department> readParse(ResultSet resultSet) throws SQLException {
        List<Department> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(new Department(resultSet.getInt("id"), resultSet.getString("name")));
        }
        return list;
    }

    @Override
    protected boolean createParse(PreparedStatement statement, Department entity) throws SQLException {
        statement.setString(1, entity.getName());
        return statement.execute();
    }

    @Override
    protected boolean updateParse(PreparedStatement statement, Department entity) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setInt(2, entity.getId());
        return statement.execute();
    }
}

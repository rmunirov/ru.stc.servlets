package ru.innopolis.stc12.servlets.repository.dao;

import ru.innopolis.stc12.servlets.pojo.Group;
import ru.innopolis.stc12.servlets.pojo.Teacher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDao extends AbstractDao<Group> {
    public GroupDao() {
        tableName = "groups";
        createSql = "INSERT INTO groups VALUES (DEFAULT , ?, ?)";
        updateSql = "UPDATE groups SET name=?, teacher=? WHERE id=?";
    }

    @Override
    protected List<Group> readParse(ResultSet resultSet) throws SQLException {
        List<Group> list = new ArrayList<>();
        while (resultSet.next()) {
            Teacher teacher = DaoFactory.getTeacherDao().read(resultSet.getInt("curator"));
            list.add(new Group(resultSet.getInt("id"), resultSet.getString("name"), teacher));
        }
        return list;
    }

    @Override
    protected void mappingStatementForCreate(PreparedStatement statement, Group entity) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setInt(2, entity.getCurator().getId());
    }

    @Override
    protected void mappingStatementForUpdate(PreparedStatement statement, Group entity) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setInt(2, entity.getCurator().getId());
        statement.setInt(3, entity.getId());
    }
}

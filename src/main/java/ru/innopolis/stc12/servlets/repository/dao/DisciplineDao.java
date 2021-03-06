package ru.innopolis.stc12.servlets.repository.dao;

import ru.innopolis.stc12.servlets.pojo.Discipline;
import ru.innopolis.stc12.servlets.pojo.Teacher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisciplineDao extends AbstractDao<Discipline> {
    public DisciplineDao() {
        tableName = "discipline";
        createSql = "INSERT INTO discipline VALUES (DEFAULT , ?, ?)";
        updateSql = "UPDATE discipline SET name=?, teacher=? WHERE id=?";
    }

    @Override
    protected List<Discipline> readParse(ResultSet resultSet) throws SQLException {
        List<Discipline> list = new ArrayList<>();
        while (resultSet.next()) {
            Teacher teacher = DaoFactory.getTeacherDao().read(resultSet.getInt("teacher"));
            list.add(new Discipline(resultSet.getInt("id"), resultSet.getString("name"), teacher));
        }
        return list;
    }

    @Override
    protected void mappingStatementForCreate(PreparedStatement statement, Discipline entity) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setInt(2, entity.getTeacher().getId());
    }

    @Override
    protected void mappingStatementForUpdate(PreparedStatement statement, Discipline entity) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setInt(2, entity.getTeacher().getId());
        statement.setInt(3, entity.getId());
    }
}

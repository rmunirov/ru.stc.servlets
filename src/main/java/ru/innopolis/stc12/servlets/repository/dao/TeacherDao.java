package ru.innopolis.stc12.servlets.repository.dao;

import ru.innopolis.stc12.servlets.pojo.Department;
import ru.innopolis.stc12.servlets.pojo.Sex;
import ru.innopolis.stc12.servlets.pojo.Teacher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDao extends AbstractDao<Teacher> {
    public TeacherDao() {
        tableName = "teachers";
        createSql = "INSERT INTO teachers VALUES (DEFAULT , ?, ?, ?, ?, ?, ?, ?)";
        updateSql = "UPDATE teachers SET name=?, surname=?, department=?, sex=?, address=?, phone=?, email=? WHERE id=?";
    }

    @Override
    protected List<Teacher> readParse(ResultSet resultSet) throws SQLException {
        List<Teacher> list = new ArrayList<>();
        while (resultSet.next()) {
            Department department = DaoFactory.getDepartmentDao().read(resultSet.getInt("department"));
            Sex sex = DaoFactory.getSexDao().read(resultSet.getInt("sex"));
            list.add(new Teacher(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    department,
                    sex,
                    resultSet.getString("address"),
                    resultSet.getString("phone"),
                    resultSet.getString("email")
            ));
        }
        return list;
    }

    @Override
    protected void mappingStatementForCreate(PreparedStatement statement, Teacher entity) throws SQLException {
        parseStatementForCreateAndUpdate(statement, entity);
    }

    @Override
    protected void mappingStatementForUpdate(PreparedStatement statement, Teacher entity) throws SQLException {
        parseStatementForCreateAndUpdate(statement, entity);
        statement.setInt(8, entity.getId());
    }

    private void parseStatementForCreateAndUpdate(PreparedStatement statement, Teacher entity) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getSurname());
        statement.setInt(3, entity.getDepartment().getId());
        statement.setInt(4, entity.getSex().getId());
        statement.setString(5, entity.getAddress());
        statement.setString(6, entity.getPhone());
        statement.setString(7, entity.getEmail());
    }

}

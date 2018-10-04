package ru.innopolis.stc12.servlets.repository.dao;

import ru.innopolis.stc12.servlets.pojo.City;
import ru.innopolis.stc12.servlets.pojo.Group;
import ru.innopolis.stc12.servlets.pojo.Sex;
import ru.innopolis.stc12.servlets.pojo.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentDao extends AbstractDao<Student> {
    public StudentDao() {
        readSql = "SELECT * FROM students WHERE id = ?";
        createSql = "INSERT INTO students VALUES (DEFAULT , ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        deleteSql = "DELETE FROM students WHERE id=?";
        updateSql = "UPDATE students SET name=?, surname=?, sex=?, date_of_receipt=?, \"group\"=?, address=?, phone=?, email=?, city=? WHERE id=?";
        readAllSql = "SELECT * FROM students ORDER BY surname";
    }

    @Override
    protected List<Student> readParse(ResultSet resultSet) throws SQLException {
/*
        SELECT *
                FROM students
        INNER JOIN sex ON students.sex = sex.id
        INNER JOIN groups ON students.group = groups.id
        INNER JOIN personal_data ON students.personal_data = personal_data.id
        WHERE students.id = 38
        ...*/
        List<Student> list = new ArrayList<>();
        while (resultSet.next()) {
            Sex sex = DaoFactory.getSexDao().read(resultSet.getInt("sex"));
            Group group = DaoFactory.getGroupDao().read(resultSet.getInt("group"));
            City city = DaoFactory.getCityDao().read(resultSet.getInt("city"));
            list.add(new Student(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    sex,
                    resultSet.getDate("date_of_receipt"),
                    group,
                    resultSet.getString("address"),
                    resultSet.getString("phone"),
                    resultSet.getString("email"),
                    city));
        }
        return list;
    }

    @Override
    protected boolean createParse(PreparedStatement statement, Student entity) throws SQLException {
        if (entity.getSex() == null) return false;
        if (entity.getGroup() == null) return false;
        if (entity.getCity() == null) return false;

        parseStatementForCreateAndUpdate(statement, entity);
        return statement.execute();
    }

    @Override
    protected boolean updateParse(PreparedStatement statement, Student entity) throws SQLException {
        if (entity.getSex() == null) return false;
        if (entity.getGroup() == null) return false;
        if (entity.getCity() == null) return false;

        parseStatementForCreateAndUpdate(statement, entity);
        statement.setInt(10, entity.getId());
        return statement.execute();
    }

    private java.sql.Date convertUtilDateToSqlDate(Date date) {
        if (date == null) {
            return null;
        }
        return new java.sql.Date(date.getTime());
    }

    private void parseStatementForCreateAndUpdate(PreparedStatement statement, Student entity) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getSurname());
        statement.setInt(3, entity.getSex().getId());
        statement.setDate(4, convertUtilDateToSqlDate(entity.getDateOfReceipt()));
        statement.setInt(5, entity.getGroup().getId());
        statement.setString(6, entity.getAddress());
        statement.setString(7, entity.getPhone());
        statement.setString(8, entity.getEmail());
        statement.setInt(9, entity.getCity().getId());
    }
}

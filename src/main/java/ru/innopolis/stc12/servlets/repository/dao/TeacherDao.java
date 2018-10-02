package ru.innopolis.stc12.servlets.repository.dao;

import ru.innopolis.stc12.servlets.pojo.Department;
import ru.innopolis.stc12.servlets.pojo.PersonalData;
import ru.innopolis.stc12.servlets.pojo.Sex;
import ru.innopolis.stc12.servlets.pojo.Teacher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDao extends AbstractDao<Teacher> {
    public TeacherDao() {
        readSql = "SELECT * FROM teachers WHERE id = ?";
        createSql = "INSERT INTO teachers VALUES (DEFAULT , ?, ?, ?, ?, ?)";
        deleteSql = "DELETE FROM teachers WHERE id=?";
        updateSql = "UPDATE teachers SET name=?, surname=?, department=?, personal_data=?, sex=? WHERE id=?";
        readAllSql = "SELECT * FROM teachers";
    }

    @Override
    protected List<Teacher> readParse(ResultSet resultSet) throws SQLException {
        List<Teacher> list = new ArrayList<>();
        while (resultSet.next()) {
            Department department = DaoFactory.getDepartmentDao().read(resultSet.getInt("department"));
            PersonalData personalData = DaoFactory.getPersonalDataDao().read(resultSet.getInt("personal_data"));
            Sex sex = DaoFactory.getSexDao().read(resultSet.getInt("sex"));
            list.add(new Teacher(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    department,
                    personalData,
                    sex));
        }
        return list;
    }

    @Override
    protected boolean createParse(PreparedStatement statement, Teacher entity) throws SQLException {
        if (entity.getSex() == null) return false;
        if (entity.getDepartment() == null) return false;
        if (entity.getPersonalData() == null) return false;

        parseStatementForCreateAndUpdate(statement, entity);
        PersonalDataDao personalDataDao = DaoFactory.getPersonalDataDao();
        PersonalData personalData = personalDataDao.read(entity.getPersonalData().getId());
        if (personalData == null) {
            personalDataDao.create(entity.getPersonalData());
        }
        return statement.execute();
    }

    @Override
    protected boolean updateParse(PreparedStatement statement, Teacher entity) throws SQLException {
        if (entity.getSex() == null) return false;
        if (entity.getDepartment() == null) return false;
        if (entity.getPersonalData() == null) return false;

        parseStatementForCreateAndUpdate(statement, entity);
        statement.setInt(6, entity.getId());
        return statement.execute();
    }

    private void parseStatementForCreateAndUpdate(PreparedStatement statement, Teacher entity) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getSurname());
        statement.setInt(3, entity.getDepartment().getId());
        statement.setInt(4, entity.getPersonalData().getId());
        statement.setInt(5, entity.getSex().getId());
    }

}

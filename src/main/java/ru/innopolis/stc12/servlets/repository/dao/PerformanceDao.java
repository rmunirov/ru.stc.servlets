package ru.innopolis.stc12.servlets.repository.dao;

import ru.innopolis.stc12.servlets.pojo.Discipline;
import ru.innopolis.stc12.servlets.pojo.Grade;
import ru.innopolis.stc12.servlets.pojo.Performance;
import ru.innopolis.stc12.servlets.pojo.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PerformanceDao extends AbstractDao<Performance> {
    public PerformanceDao() {
        tableName = "performance";
        createSql = "INSERT INTO performance VALUES (DEFAULT , ?, ?, ?, ?)";
        updateSql = "UPDATE performance SET discipline=?, student=?, grade=?, description=? WHERE id=?";
    }

    @Override
    protected List<Performance> readParse(ResultSet resultSet) throws SQLException {
        List<Performance> list = new ArrayList<>();
        while (resultSet.next()) {
            Discipline discipline = DaoFactory.getDisciplineDao().read(resultSet.getInt("discipline"));
            Student student = DaoFactory.getStudentDao().read(resultSet.getInt("student"));
            Grade grade = DaoFactory.getGradeDao().read(resultSet.getInt("grade"));
            list.add(new Performance(
                    resultSet.getInt("id"),
                    discipline,
                    student,
                    grade,
                    resultSet.getString("description")));
        }
        return list;
    }

    @Override
    protected boolean createParse(PreparedStatement statement, Performance entity) throws SQLException {
        if (entity.getDiscipline() == null) return false;
        if (entity.getGrade() == null) return false;
        if (entity.getStudent() == null) return false;

        parseStatementForCreateAndUpdate(statement, entity);
        return statement.execute();
    }

    @Override
    protected boolean updateParse(PreparedStatement statement, Performance entity) throws SQLException {
        if (entity.getDiscipline() == null) return false;
        if (entity.getGrade() == null) return false;
        if (entity.getStudent() == null) return false;

        parseStatementForCreateAndUpdate(statement, entity);
        statement.setInt(5, entity.getId());
        return statement.execute();
    }

    private void parseStatementForCreateAndUpdate(PreparedStatement statement, Performance entity) throws SQLException {
        statement.setInt(1, entity.getDiscipline().getId());
        statement.setInt(2, entity.getStudent().getId());
        statement.setInt(3, entity.getGrade().getId());
        statement.setString(4, entity.getDescription());
    }

}

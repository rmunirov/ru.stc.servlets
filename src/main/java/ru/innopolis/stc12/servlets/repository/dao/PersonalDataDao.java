package ru.innopolis.stc12.servlets.repository.dao;

import ru.innopolis.stc12.servlets.pojo.City;
import ru.innopolis.stc12.servlets.pojo.PersonalData;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonalDataDao extends AbstractDao<PersonalData> {
    public PersonalDataDao() {
        readSql = "SELECT * FROM personal_data WHERE id = ?";
        createSql = "INSERT INTO personal_data VALUES (DEFAULT , ?, ?, ?, ?, ?)";
        deleteSql = "DELETE FROM personal_data WHERE id=?";
        updateSql = "UPDATE personal_data SET date_of_birth=?, city=?, address=?, phone=?, email=? WHERE id=?";
        readAllSql = "SELECT * FROM personal_data";
    }

    @Override
    protected List<PersonalData> readParse(ResultSet resultSet) throws SQLException {
        List<PersonalData> list = new ArrayList<>();
        while (resultSet.next()) {
            City city = DaoFactory.getCityDao().read(resultSet.getInt("city"));
            list.add(new PersonalData(
                    resultSet.getInt("id"),
                    resultSet.getDate("date_of_birth"),
                    city,
                    resultSet.getString("address"),
                    resultSet.getString("phone"),
                    resultSet.getString("email")));
        }
        return list;
    }

    @Override
    protected boolean createParse(PreparedStatement statement, PersonalData entity) throws SQLException {
        if (entity.getCity() == null) return false;

        parseStatementForCreateAndUpdate(statement, entity);
        return statement.execute();
    }

    @Override
    protected boolean updateParse(PreparedStatement statement, PersonalData entity) throws SQLException {
        if (entity.getCity() == null) return false;

        parseStatementForCreateAndUpdate(statement, entity);
        statement.setInt(6, entity.getId());
        return statement.execute();
    }

    private java.sql.Date convertUtilDateToSqlDate(Date date) {
        if (date == null) {
            return null;
        }
        return new java.sql.Date(date.getTime());
    }

    private void parseStatementForCreateAndUpdate(PreparedStatement statement, PersonalData entity) throws SQLException {
        statement.setDate(1, convertUtilDateToSqlDate(entity.getDateOfBirth()));
        statement.setInt(2, entity.getCity().getId());
        statement.setString(3, entity.getAddress());
        statement.setString(4, entity.getPhone());
        statement.setString(5, entity.getEmail());
    }

}

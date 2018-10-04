package ru.innopolis.stc12.servlets.repository.dao;

import org.apache.log4j.Logger;
import ru.innopolis.stc12.servlets.pojo.User;
import ru.innopolis.stc12.servlets.repository.connectionmanager.ConnectionManager;
import ru.innopolis.stc12.servlets.repository.connectionmanager.ConnectionManagerJdbcImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao<User> {
    private static final Logger LOGGER = Logger.getLogger(UserDao.class);
    private static final String GET_LOGIN_SQL = "SELECT * FROM users WHERE username = ?";
    private static ConnectionManager connectionManager = ConnectionManagerJdbcImpl.getInstance();

    public UserDao() {
        readSql = "SELECT * FROM users WHERE id = ?";
        createSql = "INSERT INTO users VALUES (DEFAULT, ?, ?, ?)";
        deleteSql = "DELETE FROM users WHERE id=?";
        updateSql = "UPDATE users SET username=?, userpassword=?, role=? WHERE id=?";
        readAllSql = "SELECT * FROM users";

    }

    public User readByLogin(String login) {
        ResultSet resultSet = null;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_LOGIN_SQL)) {
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getInt("role")
                );
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            return null;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    LOGGER.error(e);
                }
            }
        }
        return null;
    }

    @Override
    protected List<User> readParse(ResultSet resultSet) throws SQLException {
        List<User> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(new User(
                    resultSet.getInt("id"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getInt("role")
            ));
        }
        return list;
    }

    @Override
    protected boolean createParse(PreparedStatement statement, User entity) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getPassword());
        statement.setInt(3, entity.getRole());
        return statement.execute();
    }

    @Override
    protected boolean updateParse(PreparedStatement statement, User entity) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getPassword());
        statement.setInt(3, entity.getRole());
        statement.setInt(4, entity.getId());
        return statement.execute();
    }
}

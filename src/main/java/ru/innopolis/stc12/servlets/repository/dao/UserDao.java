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
    private static ConnectionManager connectionManager = ConnectionManagerJdbcImpl.getInstance();

    public UserDao() {
        tableName = "users";
        createSql = "INSERT INTO users VALUES (DEFAULT, ?, ?, ?, ?)";
        updateSql = "UPDATE users SET username=?, userpassword=?, role=?, email=? WHERE id=?";

    }

    public User readByLogin(String login) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    LOGGER.info("read record by login in table users");
                    return new User(
                            resultSet.getInt("id"),
                            resultSet.getString("username"),
                            resultSet.getString("userpassword"),
                            resultSet.getInt("role"),
                            resultSet.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            return null;
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
                    resultSet.getInt("role"),
                    resultSet.getString("email")
            ));
        }
        return list;
    }

    @Override
    protected void mappingStatementForCreate(PreparedStatement statement, User entity) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getPassword());
        statement.setInt(3, entity.getRole());
        statement.setString(4, entity.getEmail());
    }

    @Override
    protected void mappingStatementForUpdate(PreparedStatement statement, User entity) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getPassword());
        statement.setInt(3, entity.getRole());
        statement.setString(4, entity.getEmail());
        statement.setInt(5, entity.getId());
    }
}

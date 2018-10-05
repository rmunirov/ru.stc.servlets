package ru.innopolis.stc12.servlets.repository.dao;

import org.apache.log4j.Logger;
import ru.innopolis.stc12.servlets.repository.connectionmanager.ConnectionManager;
import ru.innopolis.stc12.servlets.repository.connectionmanager.ConnectionManagerJdbcImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<E> implements GenericDao<E> {
    private static final Logger LOGGER = Logger.getLogger(AbstractDao.class);
    private static ConnectionManager connectionManager = ConnectionManagerJdbcImpl.getInstance();
    protected String createSql;
    protected String updateSql;
    protected String tableName;

    protected abstract List<E> readParse(ResultSet resultSet) throws SQLException;

    protected abstract boolean createParse(PreparedStatement statement, E entity) throws SQLException;

    protected abstract boolean updateParse(PreparedStatement statement, E entity) throws SQLException;

    @Override
    public boolean create(E entity) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(createSql)) {
            boolean result = createParse(preparedStatement, entity);
            LOGGER.info("request for creating item " + entity.getClass().getName() + ", result - " + result);
            return result;
        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        }
    }

    @Override
    public E read(int id) {
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
        ResultSet resultSet = null;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            List<E> list = readParse(resultSet);
            if (list.isEmpty()) {
                LOGGER.info("read record from table " + tableName + ". Record not found");
                return null;
            }
            LOGGER.info("read record from table " + tableName + ". Record found");
            return list.get(0);
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
    }

    @Override
    public boolean update(E entity) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {
            boolean result = updateParse(preparedStatement, entity);
            LOGGER.info("request for updating item " + entity.getClass().getName() + ", result - " + result);
            return result;
        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM " + tableName + " WHERE id=?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            boolean result = preparedStatement.execute();
            LOGGER.info("deleted item in table " + tableName);
            return result;
        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        }
    }

    @Override
    public List<E> getAll() {
        String sql = "SELECT * FROM " + tableName;
        ResultSet resultSet = null;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            resultSet = preparedStatement.executeQuery();
            LOGGER.info("read all records from table " + tableName);
            return readParse(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e);
            return new ArrayList<>();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    LOGGER.error(e);
                }
            }
        }
    }
}

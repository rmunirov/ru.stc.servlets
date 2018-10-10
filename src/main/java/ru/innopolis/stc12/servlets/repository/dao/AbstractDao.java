package ru.innopolis.stc12.servlets.repository.dao;

import org.apache.log4j.Logger;
import ru.innopolis.stc12.servlets.repository.connectionmanager.ConnectionManager;
import ru.innopolis.stc12.servlets.repository.connectionmanager.ConnectionManagerJdbcImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<E> implements GenericDao<E> {
    private static final Logger LOGGER = Logger.getLogger(AbstractDao.class);
    private ConnectionManager connectionManager;
    protected String createSql;
    protected String updateSql;
    protected String tableName;

    public AbstractDao() {
        connectionManager = ConnectionManagerJdbcImpl.getInstance();
    }

    public AbstractDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    protected abstract List<E> readParse(ResultSet resultSet) throws SQLException;

    protected abstract void mappingStatementForCreate(PreparedStatement statement, E entity) throws SQLException;

    protected abstract void mappingStatementForUpdate(PreparedStatement statement, E entity) throws SQLException;

    @Override
    public int create(E entity) {
        if (entity == null) return -1;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(createSql, Statement.RETURN_GENERATED_KEYS)) {
            mappingStatementForCreate(preparedStatement, entity);
            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        int id = resultSet.getInt(1);
                        LOGGER.info("request for creating item " + entity.getClass().getName() + ", id - " + id + " has been successfully");
                        return id;
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return -1;
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
        if (entity == null) return false;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {
            mappingStatementForUpdate(preparedStatement, entity);
            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                LOGGER.info("request for updating item " + entity.getClass().getName() + " has been successfully");
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM " + tableName + " WHERE id=?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                LOGGER.info("deleted item in table " + tableName);
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return false;
    }

    @Override
    public List<E> getAll() {
        String sql = "SELECT * FROM " + tableName + " ORDER BY id";
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

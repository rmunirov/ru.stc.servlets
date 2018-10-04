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
    protected String readSql;
    protected String createSql;
    protected String deleteSql;
    protected String updateSql;
    protected String readAllSql;

    protected abstract List<E> readParse(ResultSet resultSet) throws SQLException;

    protected abstract boolean createParse(PreparedStatement statement, E entity) throws SQLException;

    protected abstract boolean updateParse(PreparedStatement statement, E entity) throws SQLException;

    @Override
    public boolean create(E entity) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(createSql)) {
            return createParse(preparedStatement, entity);
        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        }
    }

    @Override
    public E read(int id) {
        ResultSet resultSet = null;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(readSql)) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            List<E> list = readParse(resultSet);
            if (list.isEmpty()) {
                return null;
            }
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
            return updateParse(preparedStatement, entity);
        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        }
    }

    @Override
    public List<E> getAll() {
        ResultSet resultSet = null;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(readAllSql)) {
            resultSet = preparedStatement.executeQuery();
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

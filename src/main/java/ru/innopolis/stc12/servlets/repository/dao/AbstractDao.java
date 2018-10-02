package ru.innopolis.stc12.servlets.repository.dao;

import ru.innopolis.stc12.servlets.repository.connectionManager.ConnectionManager;
import ru.innopolis.stc12.servlets.repository.connectionManager.ConnectionManagerJdbcImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDao<E> implements GenericDao<E> {
    private static ConnectionManager connectionManager = ConnectionManagerJdbcImpl.getInstance();   //TODO this right?
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
        try (Connection connection = connectionManager.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(createSql)) {
                return createParse(preparedStatement, entity);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public E read(int id) {
        try (Connection connection = connectionManager.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(readSql)) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                List<E> list = readParse(resultSet);
                if (list.isEmpty()) {
                    return null;
                }
                return list.get(0);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean update(E entity) {
        try (Connection connection = connectionManager.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {
                return updateParse(preparedStatement, entity);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = connectionManager.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)) {
                preparedStatement.setInt(1, id);
                return preparedStatement.execute();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<E> getAll() {
        //TODO work very long
        try (Connection connection = connectionManager.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(readAllSql)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                return readParse(resultSet);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}

package ru.innopolis.stc12.servlets.repository.connectionmanager;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManagerJdbcImpl implements ConnectionManager {
    private static final Logger LOGGER = Logger.getLogger(ConnectionManagerJdbcImpl.class);
    private static ConnectionManager connectionManager;

    private ConnectionManagerJdbcImpl() {
    }

    public static ConnectionManager getInstance() {
        if (connectionManager == null) {
            connectionManager = new ConnectionManagerJdbcImpl();
        }
        LOGGER.info("get ConnectionManager instance");
        return connectionManager;
    }

    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/university",
                    "postgres",
                    "hfbkm1988");
            LOGGER.info("get connection");
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error(e);
        }
        return connection;
    }
}

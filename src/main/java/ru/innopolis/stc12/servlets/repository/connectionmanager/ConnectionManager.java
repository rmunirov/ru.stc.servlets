package ru.innopolis.stc12.servlets.repository.connectionmanager;

import java.sql.Connection;

public interface ConnectionManager {
    Connection getConnection();
}

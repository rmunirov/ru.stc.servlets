package ru.innopolis.stc12.servlets.repository.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.innopolis.stc12.servlets.pojo.City;
import ru.innopolis.stc12.servlets.repository.connectionmanager.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class AbstractDaoTest {
    private AbstractDao<City> dao;
    @Mock
    private ConnectionManager mockedConnectionManager;
    @Mock
    private Connection mockedConnection;
    @Mock
    private PreparedStatement mockedStatement;
    @Mock
    private ResultSet resultSet;

    @BeforeEach
    void setUp() {
        initMocks(this);
        dao = new CityDao(mockedConnectionManager);
    }

    @Test
    void createWhenEntityIsNull() {
        assertEquals(-1, dao.create(null));
    }

    @Test
    void createWhenSqlExecuteReturnZero() throws SQLException {
        when(mockedConnectionManager.getConnection()).thenReturn(mockedConnection);
        when(mockedConnection.prepareStatement(any(), anyInt())).thenReturn(mockedStatement);
        when(mockedStatement.executeUpdate()).thenReturn(0);
        assertEquals(-1, dao.create(new City("city")));
    }

    @Test
    void createWhenResultSetNextReturnFalse() throws SQLException {
        when(mockedConnectionManager.getConnection()).thenReturn(mockedConnection);
        when(mockedConnection.prepareStatement(any(), anyInt())).thenReturn(mockedStatement);
        when(mockedStatement.executeUpdate()).thenReturn(1);
        when(mockedStatement.getGeneratedKeys()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);
        assertEquals(-1, dao.create(new City("city")));
    }

    @Test
    void createWhenResultSetGetIntReturnNumber() throws SQLException {
        when(mockedConnectionManager.getConnection()).thenReturn(mockedConnection);
        when(mockedConnection.prepareStatement(any(), anyInt())).thenReturn(mockedStatement);
        when(mockedStatement.executeUpdate()).thenReturn(1);
        when(mockedStatement.getGeneratedKeys()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(anyInt())).thenReturn(5);
        assertEquals(5, dao.create(new City("city")));
    }

    @Test
    void read() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void getAll() {
    }
}
package ru.innopolis.stc12.servlets.repository.dao;

import java.util.List;

public interface GenericDao<E> {
    int create(E entity);

    E read(int id);

    boolean update(E entity);

    boolean delete(int id);

    List<E> getAll();
}

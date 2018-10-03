package ru.innopolis.stc12.servlets.service;

import ru.innopolis.stc12.servlets.pojo.Sex;
import ru.innopolis.stc12.servlets.repository.dao.SexDao;

import java.util.List;

public class SexService {
    private SexDao sexDao;

    public SexService(SexDao sexDao) {
        this.sexDao = sexDao;
    }

    public SexService() {
        sexDao = new SexDao();
    }

    public List<Sex> getAll() {
        return sexDao.getAll();
    }

    public Sex get(int id) {
        return sexDao.read(id);
    }
}

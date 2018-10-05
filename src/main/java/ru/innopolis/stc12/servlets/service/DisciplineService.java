package ru.innopolis.stc12.servlets.service;

import ru.innopolis.stc12.servlets.pojo.Discipline;
import ru.innopolis.stc12.servlets.repository.dao.DisciplineDao;

import java.util.List;

public class DisciplineService {
    private DisciplineDao disciplineDao;

    public DisciplineService() {
        disciplineDao = new DisciplineDao();
    }

    public DisciplineService(DisciplineDao disciplineDao) {
        this.disciplineDao = disciplineDao;
    }

    public List<Discipline> getAll() {
        return disciplineDao.getAll();
    }
}

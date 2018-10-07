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

    public Discipline get(int id) {
        return disciplineDao.read(id);
    }

    public int add(Discipline discipline) {
        return disciplineDao.create(discipline);
    }

    public boolean update(Discipline discipline) {
        return disciplineDao.update(discipline);
    }

    public boolean remove(int id) {
        return disciplineDao.delete(id);
    }
}

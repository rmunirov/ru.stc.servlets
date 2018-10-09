package ru.innopolis.stc12.servlets.service;

import ru.innopolis.stc12.servlets.pojo.Teacher;
import ru.innopolis.stc12.servlets.repository.dao.TeacherDao;

import java.util.List;

public class TeacherService {
    private TeacherDao teacherDao;

    public TeacherService(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    public TeacherService() {
        teacherDao = new TeacherDao();
    }

    public List<Teacher> getAll() {
        return teacherDao.getAll();
    }

    public Teacher get(int id) {
        return teacherDao.read(id);
    }

    public int add(Teacher teacher) {
        return teacherDao.create(teacher);
    }

    public boolean update(Teacher teacher) {
        return teacherDao.update(teacher);
    }

    public boolean remove(int id) {
        return teacherDao.delete(id);
    }
}

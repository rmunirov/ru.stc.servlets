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
}

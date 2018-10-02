package ru.innopolis.stc12.servlets.service;

import ru.innopolis.stc12.servlets.pojo.Student;
import ru.innopolis.stc12.servlets.repository.dao.StudentDao;

import java.util.List;

public class StudentService {
    private StudentDao studentDao;

    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public StudentService() {
        studentDao = new StudentDao();
    }

    public List<Student> getAll() {
        return studentDao.getAll();
    }
}

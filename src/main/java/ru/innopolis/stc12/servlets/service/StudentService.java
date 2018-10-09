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

    public int add(Student student) {
        return studentDao.create(student);
    }

    public Student get(int id) {
        return studentDao.read(id);
    }

    public boolean update(Student student) {
        return studentDao.update(student);
    }

    public boolean remove(int id) {
        return studentDao.delete(id);
    }
}

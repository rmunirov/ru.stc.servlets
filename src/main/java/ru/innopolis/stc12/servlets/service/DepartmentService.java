package ru.innopolis.stc12.servlets.service;

import ru.innopolis.stc12.servlets.pojo.Department;
import ru.innopolis.stc12.servlets.repository.dao.DepartmentDao;

import java.util.List;

public class DepartmentService {
    private DepartmentDao departmentDao;

    public DepartmentService(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public DepartmentService() {
        departmentDao = new DepartmentDao();
    }

    public List<Department> getAll() {
        return departmentDao.getAll();
    }

    public Department get(int id) {
        return departmentDao.read(id);
    }
}

package ru.innopolis.stc12.servlets.service;

import ru.innopolis.stc12.servlets.pojo.Group;
import ru.innopolis.stc12.servlets.repository.dao.GroupDao;

import java.util.List;

public class GroupService {
    private GroupDao groupDao;

    public GroupService(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public GroupService() {
        groupDao = new GroupDao();
    }

    public List<Group> getAll() {
        return groupDao.getAll();
    }

    public Group get(int id) {
        return groupDao.read(id);
    }
}

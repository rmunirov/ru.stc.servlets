package ru.innopolis.stc12.servlets.service;

import ru.innopolis.stc12.servlets.pojo.User;
import ru.innopolis.stc12.servlets.repository.dao.UserDao;
import ru.innopolis.stc12.servlets.service.utils.HashUtil;

public class UserService {
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserService() {
        userDao = new UserDao();
    }

    public int getRole(String login) {
        User user;
        if (login == null) {
            return -1;
        }
        user = userDao.get(login);
        if (user == null) {
            return -1;
        }
        return user.getRole();
    }

    public boolean checkAuth(String login, String password) {
        User user;
        if (login == null) {
            return false;
        }
        if (password == null) {
            return false;
        }
        user = userDao.get(login);
        if (user == null) {
            return false;
        }
        if (user.getPassword().equals(HashUtil.StringToMD5(password))) {
            return true;
        }
        return false;
    }
}

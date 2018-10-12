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
        user = userDao.readByLogin(login);
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
        user = userDao.readByLogin(login);
        if (user == null) {
            return false;
        }
        return user.getPassword().equals(HashUtil.HashPassword(password));
    }

    public boolean add(User user) {
        if (userDao.create(user) == -1) {
            return false;
        }
        return true;
    }

    public User get(String login) {
        if (login == null) {
            return null;
        }
        return userDao.readByLogin(login);
    }
}

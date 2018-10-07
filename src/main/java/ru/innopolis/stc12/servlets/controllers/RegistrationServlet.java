package ru.innopolis.stc12.servlets.controllers;

import ru.innopolis.stc12.servlets.pojo.User;
import ru.innopolis.stc12.servlets.service.UserService;
import ru.innopolis.stc12.servlets.service.utils.HashUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {
    private final Integer LOGIN_MIN_LENGTH = 4;
    private final Integer PASSWORD_MIN_LENGTH = 4;
    private final Integer ADMIN_ROLE = 2;
    private final Integer USER_ROLE = 1;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("userName");
        String password = req.getParameter("userPassword");
        if (login.length() < LOGIN_MIN_LENGTH) {
            resp.sendRedirect("registration?action=wrongUserName");
            return;
        }
        if (password.length() < PASSWORD_MIN_LENGTH) {
            resp.sendRedirect("registration?action=wrongUserPassword");
            return;
        }
        if (userService.get(login) != null) {
            resp.sendRedirect("registration?action=wrongUserExist");
            return;
        }
        userService.add(new User(login, HashUtil.StringToMD5(password), USER_ROLE));
        resp.sendRedirect("registration?action=registrationCompleted");
    }
}

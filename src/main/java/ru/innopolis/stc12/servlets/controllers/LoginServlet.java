package ru.innopolis.stc12.servlets.controllers;

import ru.innopolis.stc12.servlets.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private static final String LOGIN_ATTRIBUTE = "login";
    private static final String PASSWORD_ATTRIBUTE = "password";
    private static final String ROLE_ATTRIBUTE = "role";
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("logout".equals(req.getParameter("action"))) {
            req.getSession().invalidate();
        }

        if (req.getSession().getAttribute(LOGIN_ATTRIBUTE) != null) {
            resp.sendRedirect("/index.jsp");
        }
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(LOGIN_ATTRIBUTE);
        String password = req.getParameter(PASSWORD_ATTRIBUTE);
        if (userService.checkAuth(login, password)) {
            int role = userService.getRole(login);
            req.getSession().setAttribute(LOGIN_ATTRIBUTE, login);
            req.getSession().setAttribute(ROLE_ATTRIBUTE, role);
            resp.sendRedirect("/index.jsp");
        } else {
            resp.sendRedirect("login?action=wrongUser");
        }
    }
}

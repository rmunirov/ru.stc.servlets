package ru.innopolis.stc12.servlets.controllers;

import ru.innopolis.stc12.servlets.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//TODO remember me and forgot password
public class LoginServlet extends HttpServlet {
    private static final String LOGIN_ATTRIBUTE = "username";
    private static final String PASS_ATTRIBUTE = "password";
    private static final String ROLE_ATTRIBUTE = "role";
    private static final String ACTION_ATTRIBUTE = "action";
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ACTION_ATTRIBUTE);
        if (action != null) {
            switch (action) {
                case "logout":
                    req.getSession().invalidate();
                    break;
                case "wrongUser":
                    req.setAttribute("message", "Login or password wrong");
                    break;
                case "noAuth":
                    req.setAttribute("message", "Access is denied");
                    break;
                default:
            }
            req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
        }
        String login = (String) req.getSession().getAttribute(LOGIN_ATTRIBUTE);
        if (login != null) {
            resp.sendRedirect("/index.jsp");
        } else {
            req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(LOGIN_ATTRIBUTE);
        String password = req.getParameter(PASS_ATTRIBUTE);
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

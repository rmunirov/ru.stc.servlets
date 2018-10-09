package ru.innopolis.stc12.servlets.controllers;

import org.apache.commons.validator.routines.EmailValidator;
import ru.innopolis.stc12.servlets.pojo.User;
import ru.innopolis.stc12.servlets.service.UserService;
import ru.innopolis.stc12.servlets.service.utils.HashUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {
    private static final String USERNAME_ATTRIBUTE = "username";
    private static final String PASS_ATTRIBUTE = "password";
    private static final String CONFIRM_PASS_ATTRIBUTE = "confirm-password";
    private static final String EMAIL_ATTRIBUTE = "email";
    private static final String MESSAGE_ATTRIBUTE = "message";
    private static final String MESSAGE_TYPE_ATTRIBUTE = "messageType";

    private final Integer ADMIN_ROLE = 2;
    private final Integer USER_ROLE = 1;
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter(USERNAME_ATTRIBUTE);
        String password = req.getParameter(PASS_ATTRIBUTE);
        String confirmPassword = req.getParameter(CONFIRM_PASS_ATTRIBUTE);
        String email = req.getParameter(EMAIL_ATTRIBUTE);
        if (!checkUsernameValidation(username)) {
            forwardWithMessage(req, resp, "Username is not correct", "bad");
            return;
        }
        if (!checkPasswordValidation(password)) {
            forwardWithMessage(req, resp, "Password is not correct", "bad");
            return;
        }
        if (checkUsernameExist(username)) {
            forwardWithMessage(req, resp, "User already exists", "bad");
            return;
        }
        if (!checkEmail(email)) {
            forwardWithMessage(req, resp, "Email is not correct", "bad");
            return;
        }
        if (!password.equals(confirmPassword)) {
            forwardWithMessage(req, resp, "Password and Confirm password not equals", "bad");
            return;
        }
        if (userService.add(new User(username, HashUtil.StringToMD5(password), USER_ROLE, email))) {
            forwardWithMessage(req, resp, "Registration completed successfully, <a href=\"/login\"> Log in</a>", "good");
        } else {
            forwardWithMessage(req, resp, "Registration failed, try again later", "bad");
        }
    }

    private void forwardWithMessage(HttpServletRequest req, HttpServletResponse resp, String message, String type) throws ServletException, IOException {
        req.setAttribute(MESSAGE_TYPE_ATTRIBUTE, type);
        req.setAttribute(MESSAGE_ATTRIBUTE, message);
        req.getRequestDispatcher("/WEB-INF/pages/registration.jsp").forward(req, resp);
    }

    private boolean checkUsernameValidation(String username) {
        if (username == null) {
            return false;
        }
        int usernameMinLength = 4;
        if (username.length() < usernameMinLength) {
            return false;
        }

        return true;
    }

    private boolean checkPasswordValidation(String password) {
        if (password == null) {
            return false;
        }
        int passwordMinLength = 4;
        if (password.length() < passwordMinLength) {
            return false;
        }
        return true;
    }

    private boolean checkUsernameExist(String username) {
        if (username == null) {
            return false;
        }
        if (userService.get(username) == null) {
            return false;
        }
        return true;
    }

    private boolean checkEmail(String email) {
        if (email == null) {
            return false;
        }
        return EmailValidator.getInstance().isValid(email);
    }
}

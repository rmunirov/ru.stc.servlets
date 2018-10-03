package ru.innopolis.stc12.servlets.controllers;

import ru.innopolis.stc12.servlets.pojo.Teacher;
import ru.innopolis.stc12.servlets.service.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TeacherServlet extends HttpServlet {
    private TeacherService teacherService;

    @Override
    public void init() throws ServletException {
        super.init();
        teacherService = new TeacherService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Teacher> list = teacherService.getAll();
        req.setAttribute("list", list);
        req.getRequestDispatcher("/teachers.jsp").forward(req, resp);
    }
}

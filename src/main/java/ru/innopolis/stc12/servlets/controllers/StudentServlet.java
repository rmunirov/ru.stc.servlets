package ru.innopolis.stc12.servlets.controllers;

import ru.innopolis.stc12.servlets.pojo.Student;
import ru.innopolis.stc12.servlets.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class StudentServlet extends HttpServlet {
    private StudentService studentService;

    @Override
    public void init() throws ServletException {
        super.init();
        studentService = new StudentService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> list = studentService.getAll();
        req.setAttribute("list", list);
        req.getRequestDispatcher("/student.jsp").forward(req, resp);
    }
}

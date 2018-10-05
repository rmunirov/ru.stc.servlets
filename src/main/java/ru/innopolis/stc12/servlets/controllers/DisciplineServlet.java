package ru.innopolis.stc12.servlets.controllers;

import org.apache.log4j.Logger;
import ru.innopolis.stc12.servlets.pojo.Discipline;
import ru.innopolis.stc12.servlets.service.DisciplineService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DisciplineServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(DisciplineServlet.class);
    private DisciplineService disciplineService;

    @Override
    public void init() throws ServletException {
        super.init();
        disciplineService = new DisciplineService();
        LOGGER.info("DisciplineServlet is init");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Discipline> list = disciplineService.getAll();
        req.setAttribute("list", list);
        req.getRequestDispatcher("/discipline.jsp").forward(req, resp);
    }
}

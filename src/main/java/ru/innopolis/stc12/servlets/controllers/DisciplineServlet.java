package ru.innopolis.stc12.servlets.controllers;

import ru.innopolis.stc12.servlets.pojo.Discipline;
import ru.innopolis.stc12.servlets.pojo.Teacher;
import ru.innopolis.stc12.servlets.service.DisciplineService;
import ru.innopolis.stc12.servlets.service.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DisciplineServlet extends HttpServlet {
    private final DisciplineService disciplineService = new DisciplineService();
    private final TeacherService teacherService = new TeacherService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newDiscipline = req.getParameter("action");
        if (newDiscipline != null) {
            if ("newDiscipline".equals(newDiscipline)) {
                forwardNewDiscipline(req, resp);
                return;
            }
        }
        String action = req.getParameter("searchAction");
        if (action != null) {
            if ("searchById".equals(action)) {
                searchDisciplineById(req, resp);
            }
        } else {
            List<Discipline> list = disciplineService.getAll();
            forwardListDisciplines(req, resp, list);
        }
    }

    private void forwardListDisciplines(HttpServletRequest req, HttpServletResponse resp, List disciplineList) throws ServletException, IOException {
        req.setAttribute("disciplineList", disciplineList);
        req.getRequestDispatcher("/WEB-INF/pages/discipline.jsp").forward(req, resp);
    }

    private void forwardNewDiscipline(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Teacher> teacherList = teacherService.getAll();
        req.setAttribute("teacherList", teacherList);
        req.getRequestDispatcher("/WEB-INF/pages/new-discipline.jsp").forward(req, resp);
    }

    private void searchDisciplineById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("idDiscipline"));
        Discipline discipline = disciplineService.get(id);
        if (discipline == null) {
            String message = "The discipline not found by id";
            req.setAttribute("message", message);
        } else {
            List<Teacher> teacherList = teacherService.getAll();
            req.setAttribute("discipline", discipline);
            req.setAttribute("teacherList", teacherList);
            req.setAttribute("action", "edit");
            req.getRequestDispatcher("/WEB-INF/pages/new-discipline.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null) {
            switch (action) {
                case "add":
                    addDisciplineAction(req, resp);
                    break;
                case "edit":
                    editDisciplineAction(req, resp);
                    break;
                case "remove":
                    removeDisciplineAction(req, resp);
                    break;
            }
        }
    }

    private void addDisciplineAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        int teacherId = Integer.parseInt(req.getParameter("teachers"));
        Teacher teacher = teacherService.get(teacherId);
        Discipline discipline = new Discipline(name, teacher);
        int idDiscipline = disciplineService.add(discipline);
        //TODO do a negative message
        if (idDiscipline != -1) {
            req.setAttribute("idDiscipline", idDiscipline);
            List<Discipline> disciplineList = disciplineService.getAll();
            String message = "The new discipline has been successfully created.";
            req.setAttribute("message", message);
            forwardListDisciplines(req, resp, disciplineList);
        }
    }

    private void editDisciplineAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        int idDiscipline = Integer.parseInt(req.getParameter("idDiscipline"));
        int idTeacher = Integer.parseInt(req.getParameter("teachers"));
        Teacher teacher = teacherService.get(idTeacher);
        Discipline discipline = new Discipline(idDiscipline, name, teacher);
        String message = null;
        if (disciplineService.update(discipline)) {
            message = "The discipline has been successfully updated.";
        }
        List<Discipline> disciplineList = disciplineService.getAll();
        req.setAttribute("idDiscipline", idDiscipline);
        req.setAttribute("message", message);
        forwardListDisciplines(req, resp, disciplineList);
    }

    private void removeDisciplineAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idDiscipline = Integer.parseInt(req.getParameter("idDiscipline"));
        if (disciplineService.remove(idDiscipline)) {
            String message = "The discipline has been successfully removed";
            req.setAttribute("message", message);
        }
        List<Discipline> disciplineList = disciplineService.getAll();
        forwardListDisciplines(req, resp, disciplineList);
    }
}

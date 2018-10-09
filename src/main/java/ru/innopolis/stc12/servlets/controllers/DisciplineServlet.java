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
    private static final String ID_DISCIPLINE_ATTRIBUTE = "idDiscipline";
    private static final String SEARCH_ACTION_ATTRIBUTE = "searchAction";
    private static final String ACTION_ATTRIBUTE = "action";
    private static final String DISCIPLINE_ATTRIBUTE = "discipline";
    private static final String MESSAGE_ATTRIBUTE = "message";
    private static final String MESSAGE_TYPE_ATTRIBUTE = "messageType";
    private static final String NAME_ATTRIBUTE = "name";
    private static final String TEACHERS_ATTRIBUTE = "teachers";
    private static final String TEACHER_LIST_ATTRIBUTE = "teacherList";
    private static final String DISCIPLINE_LIST_ATTRIBUTE = "disciplineList";
    private final DisciplineService disciplineService = new DisciplineService();
    private final TeacherService teacherService = new TeacherService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newDiscipline = req.getParameter(ACTION_ATTRIBUTE);
        if (newDiscipline != null && newDiscipline.equals("newDiscipline")) {
            forwardNewDiscipline(req, resp);
            return;
        }
        String action = req.getParameter(SEARCH_ACTION_ATTRIBUTE);
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
        req.setAttribute(DISCIPLINE_LIST_ATTRIBUTE, disciplineList);
        req.getRequestDispatcher("/WEB-INF/pages/discipline.jsp").forward(req, resp);
    }

    private void forwardNewDiscipline(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Teacher> teacherList = teacherService.getAll();
        req.setAttribute(TEACHER_LIST_ATTRIBUTE, teacherList);
        req.getRequestDispatcher("/WEB-INF/pages/new-discipline.jsp").forward(req, resp);
    }

    private void searchDisciplineById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter(ID_DISCIPLINE_ATTRIBUTE));
        Discipline discipline = disciplineService.get(id);
        if (discipline == null) {
            req.setAttribute(MESSAGE_ATTRIBUTE, "The discipline not found by id");
        } else {
            List<Teacher> teacherList = teacherService.getAll();
            req.setAttribute(DISCIPLINE_ATTRIBUTE, discipline);
            req.setAttribute(TEACHER_LIST_ATTRIBUTE, teacherList);
            req.setAttribute(ACTION_ATTRIBUTE, "edit");
            req.getRequestDispatcher("/WEB-INF/pages/new-discipline.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ACTION_ATTRIBUTE);
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
                default:
            }
        }
    }

    private void addDisciplineAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter(NAME_ATTRIBUTE);
        int teacherId = Integer.parseInt(req.getParameter(TEACHERS_ATTRIBUTE));
        Teacher teacher = teacherService.get(teacherId);
        Discipline discipline = new Discipline(name, teacher);
        int idDiscipline = disciplineService.add(discipline);
        if (idDiscipline != -1) {
            req.setAttribute(ID_DISCIPLINE_ATTRIBUTE, idDiscipline);
            req.setAttribute(MESSAGE_TYPE_ATTRIBUTE, "good");
            req.setAttribute(MESSAGE_ATTRIBUTE, "The new discipline has been successfully created.");
        } else {
            req.setAttribute(MESSAGE_TYPE_ATTRIBUTE, "bad");
            req.setAttribute(MESSAGE_ATTRIBUTE, "The new discipline created has been failed.");
        }
        List<Discipline> disciplineList = disciplineService.getAll();
        forwardListDisciplines(req, resp, disciplineList);
    }

    private void editDisciplineAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter(NAME_ATTRIBUTE);
        int idDiscipline = Integer.parseInt(req.getParameter(ID_DISCIPLINE_ATTRIBUTE));
        int idTeacher = Integer.parseInt(req.getParameter(TEACHERS_ATTRIBUTE));
        Teacher teacher = teacherService.get(idTeacher);
        Discipline discipline = new Discipline(idDiscipline, name, teacher);
        if (disciplineService.update(discipline)) {
            req.setAttribute(MESSAGE_ATTRIBUTE, "The discipline has been successfully updated.");
            req.setAttribute(MESSAGE_TYPE_ATTRIBUTE, "good");
        } else {
            req.setAttribute(MESSAGE_TYPE_ATTRIBUTE, "bad");
            req.setAttribute(MESSAGE_ATTRIBUTE, "The discipline updated has been failed.");
        }
        req.setAttribute(ID_DISCIPLINE_ATTRIBUTE, idDiscipline);
        List<Discipline> disciplineList = disciplineService.getAll();
        forwardListDisciplines(req, resp, disciplineList);
    }

    private void removeDisciplineAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idDiscipline = Integer.parseInt(req.getParameter(ID_DISCIPLINE_ATTRIBUTE));
        if (disciplineService.remove(idDiscipline)) {
            req.setAttribute(MESSAGE_TYPE_ATTRIBUTE, "good");
            req.setAttribute(MESSAGE_ATTRIBUTE, "The discipline has been successfully removed");
        } else {
            req.setAttribute(MESSAGE_TYPE_ATTRIBUTE, "bad");
            req.setAttribute(MESSAGE_ATTRIBUTE, "The discipline removed has been failed");
        }
        List<Discipline> disciplineList = disciplineService.getAll();
        forwardListDisciplines(req, resp, disciplineList);
    }
}

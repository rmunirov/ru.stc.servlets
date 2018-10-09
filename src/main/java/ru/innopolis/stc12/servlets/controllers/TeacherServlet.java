package ru.innopolis.stc12.servlets.controllers;

import ru.innopolis.stc12.servlets.pojo.Department;
import ru.innopolis.stc12.servlets.pojo.Sex;
import ru.innopolis.stc12.servlets.pojo.Teacher;
import ru.innopolis.stc12.servlets.service.DepartmentService;
import ru.innopolis.stc12.servlets.service.SexService;
import ru.innopolis.stc12.servlets.service.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TeacherServlet extends HttpServlet {
    private static final String ID_TEACHER_ATTRIBUTE = "idTeacher";
    private static final String SEARCH_ACTION_ATTRIBUTE = "searchAction";
    private static final String ACTION_ATTRIBUTE = "action";
    private static final String TEACHER_ATTRIBUTE = "teacher";
    private static final String MESSAGE_ATTRIBUTE = "message";
    private static final String MESSAGE_TYPE_ATTRIBUTE = "messageType";
    private static final String NAME_ATTRIBUTE = "name";
    private static final String SURNAME_ATTRIBUTE = "surname";
    private static final String ADDRESS_ATTRIBUTE = "address";
    private static final String PHONE_ATTRIBUTE = "phone";
    private static final String EMAIL_ATTRIBUTE = "email";
    private static final String TEACHER_LIST_ATTRIBUTE = "teacherList";
    private static final String DEPARTMENT_LIST_ATTRIBUTE = "departmentList";
    private static final String SEX_LIST_ATTRIBUTE = "sexList";
    private static final String DEPARTMENT_ATTRIBUTE = "departments";
    private static final String SEX_ATTRIBUTE = "sexes";
    private final TeacherService teacherService = new TeacherService();
    private final DepartmentService departmentService = new DepartmentService();
    private final SexService sexService = new SexService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newTeacher = req.getParameter(ACTION_ATTRIBUTE);
        if (newTeacher != null && newTeacher.equals("newTeacher")) {
            forwardNewTeacher(req, resp);
            return;
        }
        String action = req.getParameter(SEARCH_ACTION_ATTRIBUTE);
        if (action != null) {
            if ("searchById".equals(action)) {
                searchTeacherById(req, resp);
            }
        } else {
            List<Teacher> list = teacherService.getAll();
            forwardListTeachers(req, resp, list);
        }
    }

    private void forwardListTeachers(HttpServletRequest req, HttpServletResponse resp, List teacherList) throws ServletException, IOException {
        req.setAttribute(TEACHER_LIST_ATTRIBUTE, teacherList);
        req.getRequestDispatcher("/WEB-INF/pages/teachers.jsp").forward(req, resp);
    }

    private void forwardNewTeacher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Department> departmentList = departmentService.getAll();
        List<Sex> sexList = sexService.getAll();
        req.setAttribute(DEPARTMENT_LIST_ATTRIBUTE, departmentList);
        req.setAttribute(SEX_LIST_ATTRIBUTE, sexList);
        req.getRequestDispatcher("/WEB-INF/pages/new-teacher.jsp").forward(req, resp);
    }

    private void searchTeacherById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter(ID_TEACHER_ATTRIBUTE));
        Teacher teacher = teacherService.get(id);
        if (teacher == null) {
            req.setAttribute(MESSAGE_ATTRIBUTE, "The teacher not found by id");
        } else {
            List<Department> departmentList = departmentService.getAll();
            List<Sex> sexList = sexService.getAll();
            req.setAttribute(TEACHER_ATTRIBUTE, teacher);
            req.setAttribute(DEPARTMENT_LIST_ATTRIBUTE, departmentList);
            req.setAttribute(SEX_LIST_ATTRIBUTE, sexList);
            req.setAttribute(ACTION_ATTRIBUTE, "edit");
            req.getRequestDispatcher("/WEB-INF/pages/new-teacher.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ACTION_ATTRIBUTE);
        if (action != null) {
            switch (action) {
                case "add":
                    addTeacherAction(req, resp);
                    break;
                case "edit":
                    editTeacherAction(req, resp);
                    break;
                case "remove":
                    removeTeacherAction(req, resp);
                    break;
                default:
            }
        }
    }

    private void addTeacherAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter(NAME_ATTRIBUTE);
        String surname = req.getParameter(SURNAME_ATTRIBUTE);
        int idDepartment = Integer.parseInt(req.getParameter(DEPARTMENT_ATTRIBUTE));
        int idSex = Integer.parseInt(req.getParameter(SEX_ATTRIBUTE));
        String address = req.getParameter(ADDRESS_ATTRIBUTE);
        String phone = req.getParameter(PHONE_ATTRIBUTE);
        String email = req.getParameter(EMAIL_ATTRIBUTE);
        Department department = departmentService.get(idDepartment);
        Sex sex = sexService.get(idSex);
        Teacher teacher = new Teacher(
                name,
                surname,
                department,
                sex,
                address,
                phone,
                email
        );
        int idTeacher = teacherService.add(teacher);
        if (idTeacher != -1) {
            req.setAttribute(ID_TEACHER_ATTRIBUTE, idTeacher);
            req.setAttribute(MESSAGE_TYPE_ATTRIBUTE, "good");
            req.setAttribute(MESSAGE_ATTRIBUTE, "The new teacher has been successfully created.");
        } else {
            req.setAttribute(MESSAGE_TYPE_ATTRIBUTE, "bad");
            req.setAttribute(MESSAGE_ATTRIBUTE, "The new teacher created has been failed.");
        }
        List<Teacher> teacherList = teacherService.getAll();
        forwardListTeachers(req, resp, teacherList);
    }

    private void editTeacherAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter(NAME_ATTRIBUTE);
        String surname = req.getParameter(SURNAME_ATTRIBUTE);
        int idDepartment = Integer.parseInt(req.getParameter(DEPARTMENT_ATTRIBUTE));
        int idSex = Integer.parseInt(req.getParameter(SEX_ATTRIBUTE));
        int idTeacher = Integer.parseInt(req.getParameter(ID_TEACHER_ATTRIBUTE));
        String address = req.getParameter(ADDRESS_ATTRIBUTE);
        String phone = req.getParameter(PHONE_ATTRIBUTE);
        String email = req.getParameter(EMAIL_ATTRIBUTE);
        Department department = departmentService.get(idDepartment);
        Sex sex = sexService.get(idSex);
        Teacher teacher = new Teacher(
                idTeacher,
                name,
                surname,
                department,
                sex,
                address,
                phone,
                email
        );
        if (teacherService.update(teacher)) {
            req.setAttribute(MESSAGE_ATTRIBUTE, "The teacher has been successfully updated.");
            req.setAttribute(MESSAGE_TYPE_ATTRIBUTE, "good");
        } else {
            req.setAttribute(MESSAGE_TYPE_ATTRIBUTE, "bad");
            req.setAttribute(MESSAGE_ATTRIBUTE, "The teacher updated has been failed.");
        }
        req.setAttribute(ID_TEACHER_ATTRIBUTE, idTeacher);
        List<Teacher> teacherList = teacherService.getAll();
        forwardListTeachers(req, resp, teacherList);
    }

    private void removeTeacherAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idTeacher = Integer.parseInt(req.getParameter(ID_TEACHER_ATTRIBUTE));
        if (teacherService.remove(idTeacher)) {
            req.setAttribute(MESSAGE_TYPE_ATTRIBUTE, "good");
            req.setAttribute(MESSAGE_ATTRIBUTE, "The teacher has been successfully removed");
        } else {
            req.setAttribute(MESSAGE_TYPE_ATTRIBUTE, "bad");
            req.setAttribute(MESSAGE_ATTRIBUTE, "The teacher removed has been failed");
        }
        List<Teacher> disciplineList = teacherService.getAll();
        forwardListTeachers(req, resp, disciplineList);
    }
}

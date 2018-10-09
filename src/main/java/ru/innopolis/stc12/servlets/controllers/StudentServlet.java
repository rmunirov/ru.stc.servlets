package ru.innopolis.stc12.servlets.controllers;

import org.apache.log4j.Logger;
import ru.innopolis.stc12.servlets.pojo.City;
import ru.innopolis.stc12.servlets.pojo.Group;
import ru.innopolis.stc12.servlets.pojo.Sex;
import ru.innopolis.stc12.servlets.pojo.Student;
import ru.innopolis.stc12.servlets.service.CityService;
import ru.innopolis.stc12.servlets.service.GroupService;
import ru.innopolis.stc12.servlets.service.SexService;
import ru.innopolis.stc12.servlets.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class StudentServlet extends HttpServlet {
    private static final String ID_STUDENT_ATTRIBUTE = "idStudent";
    private static final String SEARCH_ACTION_ATTRIBUTE = "searchAction";
    private static final String ACTION_ATTRIBUTE = "action";
    private static final String STUDENT_ATTRIBUTE = "student";
    private static final String MESSAGE_ATTRIBUTE = "message";
    private static final String MESSAGE_TYPE_ATTRIBUTE = "messageType";
    private static final String NAME_ATTRIBUTE = "name";
    private static final String SURNAME_ATTRIBUTE = "surname";
    private static final String DATE_OF_RECEIPT_ATTRIBUTE = "dateOfReceipt";
    private static final String ADDRESS_ATTRIBUTE = "address";
    private static final String PHONE_ATTRIBUTE = "phone";
    private static final String EMAIL_ATTRIBUTE = "email";
    private static final String STUDENT_LIST_ATTRIBUTE = "studentList";
    private static final String GROUP_LIST_ATTRIBUTE = "groupList";
    private static final String GROUP_ATTRIBUTE = "groups";
    private static final String SEX_LIST_ATTRIBUTE = "sexList";
    private static final String SEX_ATTRIBUTE = "sexes";
    private static final String CITY_LIST_ATTRIBUTE = "cityList";
    private static final String CITY_ATTRIBUTE = "cities";
    private final StudentService studentService = new StudentService();
    private final SexService sexService = new SexService();
    private final GroupService groupService = new GroupService();
    private final CityService cityService = new CityService();
    

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newStudent = req.getParameter(ACTION_ATTRIBUTE);
        if (newStudent != null && newStudent.equals("newStudent")) {
            forwardNewStudent(req, resp);
            return;
        }
        String action = req.getParameter(SEARCH_ACTION_ATTRIBUTE);
        if (action != null) {
            if ("searchById".equals(action)) {
                searchStudentById(req, resp);
            }
        } else {
            List<Student> list = studentService.getAll();
            forwardListStudents(req, resp, list);
        }    
    }

    private void forwardListStudents(HttpServletRequest req, HttpServletResponse resp, List studentList) throws ServletException, IOException {
        req.setAttribute(STUDENT_LIST_ATTRIBUTE, studentList);
        req.getRequestDispatcher("/WEB-INF/pages/student.jsp").forward(req, resp);
    }

    private void forwardNewStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Group> groupList = groupService.getAll();
        List<Sex> sexList = sexService.getAll();
        List<City> cityList = cityService.getAll();
        req.setAttribute(GROUP_LIST_ATTRIBUTE, groupList);
        req.setAttribute(SEX_LIST_ATTRIBUTE, sexList);
        req.setAttribute(CITY_LIST_ATTRIBUTE, cityList);
        req.getRequestDispatcher("/WEB-INF/pages/new-student.jsp").forward(req, resp);
    }

    private void searchStudentById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter(ID_STUDENT_ATTRIBUTE));
        Student student = studentService.get(id);
        if (student == null) {
            req.setAttribute(MESSAGE_ATTRIBUTE, "The student not found by id");
        } else {
            List<Group> groupList = groupService.getAll();
            List<Sex> sexList = sexService.getAll();
            List<City> cityList = cityService.getAll();
            req.setAttribute(STUDENT_ATTRIBUTE, student);
            req.setAttribute(GROUP_LIST_ATTRIBUTE, groupList);
            req.setAttribute(SEX_LIST_ATTRIBUTE, sexList);
            req.setAttribute(CITY_LIST_ATTRIBUTE, cityList);
            req.setAttribute(ACTION_ATTRIBUTE, "edit");
            req.getRequestDispatcher("/WEB-INF/pages/new-student.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ACTION_ATTRIBUTE);
        if (action != null) {
            switch (action) {
                case "add":
                    addStudentAction(req, resp);
                    break;
                case "edit":
                    editStudentAction(req, resp);
                    break;
                case "remove":
                    removeStudentAction(req, resp);
                    break;
                default:
            }
        }
    }

    private void addStudentAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter(NAME_ATTRIBUTE);
        String surname = req.getParameter(SURNAME_ATTRIBUTE);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOfReceipt = null;
        try {
            dateOfReceipt = format.parse(req.getParameter(DATE_OF_RECEIPT_ATTRIBUTE));
        } catch (ParseException e) {
            Logger.getLogger(StudentServlet.class).error(e);
        }
        int idGroup = Integer.parseInt(req.getParameter(GROUP_ATTRIBUTE));
        int idSex = Integer.parseInt(req.getParameter(SEX_ATTRIBUTE));
        int idCity = Integer.parseInt(req.getParameter(CITY_ATTRIBUTE));
        String address = req.getParameter(ADDRESS_ATTRIBUTE);
        String phone = req.getParameter(PHONE_ATTRIBUTE);
        String email = req.getParameter(EMAIL_ATTRIBUTE);
        Group group = groupService.get(idGroup);
        Sex sex = sexService.get(idSex);
        City city = cityService.get(idCity);
        Student student = new Student(
                name,
                surname,
                sex,
                dateOfReceipt,
                group,
                address,
                phone,
                email,
                city
        );
        int idStudent = studentService.add(student);
        if (idStudent != -1) {
            req.setAttribute(ID_STUDENT_ATTRIBUTE, idStudent);
            req.setAttribute(MESSAGE_TYPE_ATTRIBUTE, "good");
            req.setAttribute(MESSAGE_ATTRIBUTE, "The new student has been successfully created.");
        } else {
            req.setAttribute(MESSAGE_TYPE_ATTRIBUTE, "bad");
            req.setAttribute(MESSAGE_ATTRIBUTE, "The new student created has been failed.");
        }
        List<Student> teacherList = studentService.getAll();
        forwardListStudents(req, resp, teacherList);
    }

    private void editStudentAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter(NAME_ATTRIBUTE);
        String surname = req.getParameter(SURNAME_ATTRIBUTE);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOfReceipt = null;
        try {
            dateOfReceipt = format.parse(req.getParameter(DATE_OF_RECEIPT_ATTRIBUTE));
        } catch (ParseException e) {
            Logger.getLogger(StudentServlet.class).error(e);
        }
        int idGroup = Integer.parseInt(req.getParameter(GROUP_ATTRIBUTE));
        int idSex = Integer.parseInt(req.getParameter(SEX_ATTRIBUTE));
        int idCity = Integer.parseInt(req.getParameter(CITY_ATTRIBUTE));
        int idStudent = Integer.parseInt(req.getParameter(STUDENT_ATTRIBUTE));
        String address = req.getParameter(ADDRESS_ATTRIBUTE);
        String phone = req.getParameter(PHONE_ATTRIBUTE);
        String email = req.getParameter(EMAIL_ATTRIBUTE);
        Group group = groupService.get(idGroup);
        Sex sex = sexService.get(idSex);
        City city = cityService.get(idCity);
        Student student = new Student(
                idStudent,
                name,
                surname,
                sex,
                dateOfReceipt,
                group,
                address,
                phone,
                email,
                city
        );
        if (studentService.update(student)) {
            req.setAttribute(MESSAGE_ATTRIBUTE, "The student has been successfully updated.");
            req.setAttribute(MESSAGE_TYPE_ATTRIBUTE, "good");
        } else {
            req.setAttribute(MESSAGE_TYPE_ATTRIBUTE, "bad");
            req.setAttribute(MESSAGE_ATTRIBUTE, "The student updated has been failed.");
        }
        req.setAttribute(ID_STUDENT_ATTRIBUTE, idStudent);
        List<Student> teacherList = studentService.getAll();
        forwardListStudents(req, resp, teacherList);
    }

    private void removeStudentAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idStudent = Integer.parseInt(req.getParameter(ID_STUDENT_ATTRIBUTE));
        if (studentService.remove(idStudent)) {
            req.setAttribute(MESSAGE_TYPE_ATTRIBUTE, "good");
            req.setAttribute(MESSAGE_ATTRIBUTE, "The student has been successfully removed");
        } else {
            req.setAttribute(MESSAGE_TYPE_ATTRIBUTE, "bad");
            req.setAttribute(MESSAGE_ATTRIBUTE, "The student removed has been failed");
        }
        List<Student> teacherList = studentService.getAll();
        forwardListStudents(req, resp, teacherList);
    }

}

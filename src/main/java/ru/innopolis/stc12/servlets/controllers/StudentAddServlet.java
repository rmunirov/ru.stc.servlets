package ru.innopolis.stc12.servlets.controllers;

import ru.innopolis.stc12.servlets.pojo.*;
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

public class StudentAddServlet extends HttpServlet {
    private StudentService studentService;
    private SexService sexService;
    private GroupService groupService;
    private CityService cityService;

    @Override
    public void init() throws ServletException {
        super.init();
        studentService = new StudentService();
        sexService = new SexService();
        groupService = new GroupService();
        cityService = new CityService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Sex> sexList = sexService.getAll();
        List<Group> groupList = groupService.getAll();
        List<City> cityList = cityService.getAll();
        req.setAttribute("sexList", sexList);
        req.setAttribute("groupList", groupList);
        req.setAttribute("cityList", cityList);
        req.getRequestDispatcher("/studentAdder.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Sex sex = sexService.get(Integer.valueOf(req.getParameter("studentSex")));
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date dateOfReceipt = null;
        Date dateOfBirth = null;
        try {
            dateOfReceipt = format.parse(req.getParameter("studentDateOfReceipt"));
            dateOfBirth = format.parse(req.getParameter("studentDateOfBirth"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Group group = groupService.get(Integer.valueOf(req.getParameter("studentGroup")));
        City city = cityService.get(Integer.valueOf(req.getParameter("studentCity")));
        PersonalData personalData = new PersonalData(
                0,
                dateOfBirth,
                city,
                req.getParameter("studentAddress"),
                req.getParameter("studentPhone"),
                req.getParameter("studentEmail")
        );
        studentService.add(new Student(
                0,
                req.getParameter("studentName"),
                req.getParameter("studentSurname"),
                sex,
                dateOfReceipt,
                group,
                personalData
        ));
    }
}

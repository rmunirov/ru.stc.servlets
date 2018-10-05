<%@ page import="ru.innopolis.stc12.servlets.pojo.Discipline" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 05.10.2018
  Time: 12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Discipline list</title>
</head>
<body>
<div>
    <%@include file="menu.jsp" %>
</div>
<div>
    <h3 align="center">Teacher list</h3>
</div>
<div>
    <table align="center" border="1" cellpadding="1" cellspacing="1" style="width:500px;border-collapse:collapse">
        <thead>
        <tr>
            <th scope="col">Discipline</th>
            <th scope="col">Teacher</th>
            <th scope="col">Department</th>
        </tr>
        </thead>
        <tbody>
        <%
            //how worked JspWriter (out), class is not available
            List<Discipline> list = (List<Discipline>) request.getAttribute("list");
            for (Discipline discipline : list) {
        %>
        <tr align="center">
            <td><%=discipline.getName()%>
            </td>
            <td><%=discipline.getTeacher().getSurname() + " " + discipline.getTeacher().getName()%>
            </td>
            <td><%=discipline.getTeacher().getDepartment().getName()%>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>

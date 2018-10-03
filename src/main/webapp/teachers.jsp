<%@ page import="ru.innopolis.stc12.servlets.pojo.Teacher" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: munirov.rr
  Date: 03.10.2018
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Teacher list</title>
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
            <th scope="col">Name</th>
            <th scope="col">Surname</th>
            <th scope="col">Department</th>
        </tr>
        </thead>
        <tbody>
        <%
            //how worked JspWriter (out), class is not available
            List<Teacher> list = (List<Teacher>) request.getAttribute("list");
            for (Teacher teacher : list) {
        %>
        <tr align="center">
            <td><%=teacher.getName()%>
            </td>
            <td><%=teacher.getSurname()%>
            </td>
            <td><%=teacher.getDepartment().getName()%>
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
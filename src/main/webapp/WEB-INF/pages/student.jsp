<%@ page import="ru.innopolis.stc12.servlets.pojo.Student" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: munirov.rr
  Date: 02.10.2018
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student list</title>
</head>
<body>
<div>
    <%@include file="menu.jsp" %>
</div>
<div>
    <h3 align="center">Student list</h3>
</div>
<div>
    <table align="center" border="1" cellpadding="1" cellspacing="1" style="width:500px;border-collapse:collapse">
        <thead>
        <tr>
            <th scope="col">Surname</th>
            <th scope="col">Name</th>
            <th scope="col">Group</th>
            <th scope="col">City</th>
        </tr>
        </thead>
        <tbody>
        <%
            //how worked JspWriter (out), class is not available
            List<Student> list = (List<Student>) request.getAttribute("list");
            for (Student student : list) {
        %>
        <tr align="center">
            <td><%=student.getSurname()%>
            </td>
            <td><%=student.getName()%>
            </td>
            <td><%=student.getGroup().getName()%>
            </td>
            <td><%=student.getCity().getName()%>
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

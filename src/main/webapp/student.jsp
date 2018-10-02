<%@ page import="ru.innopolis.stc12.servlets.pojo.Student" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: munirov.rr
  Date: 02.10.2018
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список студентов:</title>
</head>
<body>
<%
    List<Student> list = (List<Student>) request.getAttribute("list");
    for (Student student : list) {
%>
<%=student.getSurname()%> <%=student.getName()%> <%=student.getGroup().getName()%><BR>
<%
    }
%>
</body>
</html>

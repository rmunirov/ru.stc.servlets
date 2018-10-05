<%--
  Created by IntelliJ IDEA.
  User: munirov.rr
  Date: 03.10.2018
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<%@include file="header.jsp" %>

<h1 style="text-align:center">University site.</h1>

<table align="center" border="0" cellpadding="5" cellspacing="5"
       style="table-layout: fixed;text-align: center">
    <tbody>
    <tr>
        <%
            final int ANON_ROLE = 0;
            final int USER_ROLE = 1;
            final int ADMIN_ROLE = 2;
            Integer role = ((Integer) request.getSession().getAttribute("role"));
            if (role == null) {
                role = ANON_ROLE;
            }
            if (role >= ANON_ROLE) {
        %>
        <td><a href="/teachers">Teachers</a></td>
        <td><a href="/discipline">Disciplines</a></td>
        <%}%>
        <%
            if (role >= USER_ROLE) {
        %>
        <td><a href="/students">Students</a></td>
        <%}%>
        <%
            if (role >= ADMIN_ROLE) {
        %>
        <td><a href="/studentAdder">Add student</a></td>
        <%}%>
    </tr>
    </tbody>
</table>
</body>
</html>

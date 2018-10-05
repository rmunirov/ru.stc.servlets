<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 05.10.2018
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<table align="right" border="0" cellpadding="1" cellspacing="1"
       style="width:200px;table-layout: fixed;text-align: right">
    <tbody>
    <tr>
        <%
            String login = (String) request.getSession().getAttribute("login");
            if (login != null) {
        %>
        <td>Hi, <%=login%>, <a href="/login?action=logout">Logout</a></td>
        <%
        } else {
        %>
        <td><a href="/login">Login</a></td>
        <td><a href="/registration">Registration</a></td>
        <%
            }
        %>
    </tr>
    </tbody>
</table>
<br>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 05.10.2018
  Time: 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<%@include file="menu.jsp" %>
<div style="
color: tomato;
position: fixed;
width: 250px;
height: 20px;
margin: -10px 0 0 -125px;
top: 15%;
left: 50%"
     align="center">
    <%if ("wrongUserName".equals(request.getParameter("action"))) {%>
    <p>User name is not correct</p>
    <%}%>
    <%if ("wrongUserPassword".equals(request.getParameter("action"))) {%>
    <p>Password is not correct</p>
    <%}%>
    <%if ("wrongUserExist".equals(request.getParameter("action"))) {%>
    <p>User already exists</p>
    <%}%>
</div>
<div style="
position: fixed;
top: 30%;
left: 50%;
width: 250px;
height: 180px;
margin: -90px 0 0 -125px;
background: aquamarine"
     align="center">
    <form method="post" name="registrationForm">
        <div>
            <p>User name: <input maxlength="30" name="userName" size="30" type="text"/></p>

            <p>Password: <input maxlength="30" name="userPassword" size="30" type="password"/></p>

            <p><input name="addUser" type="submit" value="Add"/></p>
        </div>
    </form>
</div>
<div style="color: greenyellow">
    <%if ("registrationCompleted".equals(request.getParameter("action"))) {%>
    <p>Registration completed successfully, <a href="/login"> Log in</a></p>
    <%}%>
</div>
</body>
</html>

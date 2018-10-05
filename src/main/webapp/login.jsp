<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 03.10.2018
  Time: 21:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authorization</title>
</head>
<body>
<%@include file="menu.jsp" %>
<%
    if ("wrongUser".equals(request.getParameter("action"))) {
%>
<div style="
color: tomato;
position: fixed;
width: 200px;
height: 20px;
margin: -10px 0 0 -100px;
top: 20%;
left: 50%"
     align="center"> Login or password wrong
</div>
<%}%>
<div style="
position: fixed;
top: 30%;
left: 50%;
width: 200px;
height: 140px;
margin: -70px 0 0 -100px;
background: aquamarine"
     align="center">
    <form action="/login" method="post">
        Login:<br>
        <input type="text" name="login"><br>
        Password:<br>
        <input type="password" name="password"><br><br>
        <input type="submit" value="login">
    </form>
</div>
</body>
</html>

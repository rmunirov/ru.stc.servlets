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
    <title>Title</title>
</head>
<body>
<%
    if ("wrongUser".equals(request.getParameter("action"))) {
%>
<div style="color: blue"> Login or password wrong</div>
<%}%>
<form action="/login" method="post">
    Login:<<br>
    <input type="text" name="login"><BR>
    Password:<<br>
    <input type="password" name="password"><BR>
    <input type="submit">
</form>
</body>
</html>

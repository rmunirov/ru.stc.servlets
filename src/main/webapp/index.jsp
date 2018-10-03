<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 01.10.2018
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<div>
    <%@include file="menu.jsp" %>
</div>
<div>
    <a href="/hello">Hello link</a><BR>
    <a href="/iterator">Iterator link</a><BR>
    <a href="/students">Students</a><BR><BR>
    <form method="post" action="/hello">
        <input type="text" name="testText">
        <input type="submit">
    </form>
</div>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: munirov.rr
  Date: 03.10.2018
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <script src="/resources/js/bootstrap.min.js"></script>
    <title>Authorization</title>
</head>
<body>
<%@include file="header.jsp" %>
<div class="navbar navbar-default " role="navigation" id="slide-nav">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/index.jsp">University site</a>
        </div>
        <div id="slidemenu">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/teachers">Teachers</a></li>
                <li><a href="/discipline">Disciplines</a></li>
                <li><a href="/inner/students">Students</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="col-md-6 col-xs-6 text-center"><a href="#">
                    <button type="button" class=" btn btn-default hom_nav_btn ">Log in</button>
                </a></li>
                <li class="col-md-6 col-xs-6 text-center"><a href="#">
                    <button type="button" class="btn btn-danger hom_nav_btn_red">Sign Up</button>
                </a></li>
            </ul>
        </div>
    </div>
</div>
<%--
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
        <td><a href="/inner/students">Students</a></td>
        <%}%>
        <%
            if (role >= ADMIN_ROLE) {
        %>
        <td><a href="/inner/studentAdder">Add student</a></td>
        <%}%>
    </tr>
    </tbody>
</table>--%>
</body>
</html>

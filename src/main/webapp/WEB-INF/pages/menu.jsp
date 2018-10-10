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
</head>
<body>
<c:set var="sessionRole" value="${sessionScope.get(\"role\")}"/>
<c:set var="sessionName" value="${sessionScope.get(\"username\")}"/>
<c:set var="userRoleConst" value="1"/>
<div class="navbar navbar-default " role="navigation" id="slide-nav">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/index.jsp">University site</a>
        </div>
        <div id="slidemenu">
            <ul class="nav navbar-nav">
                <c:choose>
                    <c:when test="${empty sessionRole}">
                        <li><a href="/teachers">Teachers</a></li>
                        <li><a href="/discipline">Disciplines</a></li>
                    </c:when>
                    <c:when test="${not empty sessionRole}">
                        <li><a href="/teachers">Teachers</a></li>
                        <li><a href="/discipline">Disciplines</a></li>
                        <li><a href="/inner/students">Students</a></li>
                    </c:when>
                </c:choose>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${empty sessionRole}">
                    <li class="col-md-6 col-xs-6 text-center">
                        <a href="/login">
                            <button type="button" class="btn btn-default hom_nav_btn">Log in</button>
                        </a>
                    </li>
                    <li class="col-md-6 col-xs-6 text-center">
                        <a href="/registration">
                            <button type="button" class="btn btn-danger hom_nav_btn_red">Sign Up</button>
                        </a>
                    </li>
                </c:if>
                <c:if test="${not empty sessionRole}">
                    <li class="col-md-12 col-xs-12 text-center">
                        <a href="/login?action=logout">
                            Hi, ${sessionName}
                            <button type="button" class="btn btn-danger hom_nav_btn_red">Logout</button>
                        </a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</div>
</body>
</html>

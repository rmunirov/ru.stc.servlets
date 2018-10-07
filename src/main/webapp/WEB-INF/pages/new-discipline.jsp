<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 07.10.2018
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <script src="/resources/js/bootstrap.min.js"></script>
    <title>New discipline</title>
</head>
<body>
<div>
    <c:import url="menu.jsp"></c:import>
</div>
<div class="container">
    <form action="/discipline" method="post" role="form" data-toggle="validator">
        <c:if test="${empty action}">
            <c:set var="action" value="add"/>
        </c:if>
        <input type="hidden" id="action" name="action" value="${action}">
        <input type="hidden" id="idDiscipline" name="idDiscipline" value="${discipline.id}">
        <h2>Discipline</h2>
        <div class="form-group col-xs-4">
            <label for="name" class="control-label col-xs-4">Name:</label>
            <input type="text" name="name" id="name" class="form-control" value="${discipline.name}" required="true"/>
            <label for="teachers" class="control-label col-xs-4">Teacher:</label>
            <select name="teachers" id="teachers" size="1" class="form-control">
                <c:forEach var="teacher" items="${teacherList}">
                    <option value="${teacher.id}">${teacher.surname} ${teacher.name}</option>
                </c:forEach>
            </select>
            <br>
            <br>
            <button type="submit" class="btn btn-primary btn-md">Accept</button>
        </div>
    </form>
</div>
</body>
</html>

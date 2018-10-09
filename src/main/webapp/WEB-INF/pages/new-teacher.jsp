<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 09.10.2018
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <script src="/resources/js/bootstrap.min.js"></script>
    <title>New teacher</title>
</head>
<body>
<div>
    <c:import url="menu.jsp"></c:import>
</div>
<div class="container">
    <form action="/teachers" method="post" role="form" data-toggle="validator">
        <c:if test="${empty action}">
            <c:set var="action" value="add"/>
        </c:if>
        <input type="hidden" id="action" name="action" value="${action}">
        <input type="hidden" id="idTeacher" name="idTeacher" value="${teacher.id}">
        <h2>Teacher</h2>
        <div class="form-group col-xs-4">
            <label for="name" class="control-label col-xs-4">Name:</label>
            <input type="text" name="name" id="name" class="form-control" value="${teacher.name}" required="true"/>
            <label for="name" class="control-label col-xs-4">Last name:</label>
            <input type="text" name="surname" id="surname" class="form-control" value="${teacher.surname}"
                   required="true"/>
            <label for="departments" class="control-label col-xs-4">Department:</label>
            <select name="departments" id="departments" size="1" class="form-control">
                <c:forEach var="department" items="${departmentList}">
                    <option value="${department.id}">${department.name}</option>
                </c:forEach>
            </select>
            <label for="sexes" class="control-label col-xs-4">Sex:</label>
            <select name="sexes" id="sexes" size="1" class="form-control">
                <c:forEach var="sex" items="${sexList}">
                    <option value="${sex.id}">${sex.sex}</option>
                </c:forEach>
            </select>
            <label for="address" class="control-label col-xs-4">Address:</label>
            <input type="text" name="address" id="address" class="form-control" value="${teacher.address}"
                   required="true"/>
            <label for="phone" class="control-label col-xs-4">Phone:</label>
            <input type="text" name="phone" id="phone" class="form-control" value="${teacher.phone}" required="true"/>
            <label for="email" class="control-label col-xs-4">Email:</label>
            <input type="text" name="email" id="email" class="form-control" value="${teacher.email}" required="true"/>
            <br>
            <br>
            <button type="submit" class="btn btn-primary btn-md">Accept</button>
        </div>
    </form>
</div>
</body>
</html>


<%--
  Created by IntelliJ IDEA.
  User: munirov.rr
  Date: 03.10.2018
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <script src="/resources/js/bootstrap.min.js"></script>
    <title>New student</title>
</head>
<body>
<div>
    <c:import url="menu.jsp"></c:import>
</div>
<div class="container">
    <form action="/inner/students" method="post" role="form" data-toggle="validator">
        <c:if test="${empty action}">
            <c:set var="action" value="add"/>
        </c:if>
        <input type="hidden" id="action" name="action" value="${action}">
        <input type="hidden" id="idStudent" name="idStudent" value="${student.id}">
        <h2>Student</h2>
        <div class="form-group col-xs-4">
            <label for="name" class="control-label col-xs-4">Name:</label>
            <input type="text" name="name" id="name" class="form-control" value="${student.name}" required="true"/>
            <label for="name" class="control-label col-xs-4">Last name:</label>
            <input type="text" name="surname" id="surname" class="form-control" value="${student.surname}"
                   required="true"/>
            <label for="sexes" class="control-label col-xs-4">Sex:</label>
            <select name="sexes" id="sexes" size="1" class="form-control">
                <c:forEach var="sex" items="${sexList}">
                    <option value="${sex.id}">${sex.sex}</option>
                </c:forEach>
            </select>
            <label for="dateReceipt" class="control-label col-xs-4">Date of receipt:</label>
            <input type="date" name="dateReceipt" id="dateReceipt" class="form-control" value="${student.dateOfReceipt}"
                   required="true"/>
            <label for="groups" class="control-label col-xs-4">Group:</label>
            <select name="groups" id="groups" size="1" class="form-control">
                <c:forEach var="group" items="${groupList}">
                    <option value="${group.id}">${group.name}</option>
                </c:forEach>
            </select>
            <label for="address" class="control-label col-xs-4">Address:</label>
            <input type="text" name="address" id="address" class="form-control" value="${student.address}"
                   required="true"/>
            <label for="phone" class="control-label col-xs-4">Phone:</label>
            <input type="text" name="phone" id="phone" class="form-control" value="${student.phone}" required="true"/>
            <label for="email" class="control-label col-xs-4">Email:</label>
            <input type="text" name="email" id="email" class="form-control" value="${student.email}" required="true"/>
            <label for="cities" class="control-label col-xs-4">City:</label>
            <select name="cities" id="cities" size="1" class="form-control">
                <c:forEach var="city" items="${cityList}">
                    <option value="${city.id}">${city.name}</option>
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

<%--
  Created by IntelliJ IDEA.
  User: munirov.rr
  Date: 02.10.2018
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <script src="/resources/js/bootstrap.min.js"></script>
    <title>Student list</title>
</head>
<body>
<div>
    <c:import url="menu.jsp"></c:import>
</div>
<div>
    <h3 align="center">Student list</h3>
</div>
<div class="container">
    <c:if test="${not empty message}">
        <c:set var="classMessage" value="alert alert-success"/>
        <c:if test="${not empty messageType && messageType == 'bad'}">
            <c:set var="classMessage" value="alert alert-danger"/>
        </c:if>
        <div class="${classMessage}">
                ${message}
        </div>
    </c:if>
    <form action="/inner/students" method="post" id="studentForm" role="form">
        <input type="hidden" id="idStudent" name="idStudent">
        <input type="hidden" id="action" name="action">
        <c:choose>
            <c:when test="${not empty studentList}">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <td>#</td>
                        <th>Student</th>
                        <th>Group</th>
                        <th>City</th>
                    </tr>
                    </thead>
                    <c:set var="sessionRole" value="${sessionScope.get(\"role\")}"/>
                    <c:set var="adminRoleConst" value="2"/>
                    <c:forEach var="student" items="${studentList}">
                        <c:set var="classSucess" value=""/>
                        <c:if test="${idStudent == student.id}">
                            <c:set var="classSucess" value="info"/>
                        </c:if>
                        <tr class="${classSucess}">
                            <td>
                                <c:choose>
                                    <c:when test="${not empty sessionRole && sessionRole == adminRoleConst}">
                                        <a href="/inner/students?idStudent=${student.id}&searchAction=searchById">${student.id}</a>
                                    </c:when>
                                    <c:otherwise>
                                        ${student.id}
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${student.surname} ${student.name}</td>
                            <td>${student.group.name}</td>
                            <td>${student.city.name}</td>
                            <c:if test="${not empty sessionRole && sessionRole == adminRoleConst}">
                                <td>
                                    <a href="#" id="remove"
                                       onclick="document.getElementById('action').value = 'remove';
                                               document.getElementById('idStudent').value = '${student.id}';
                                               document.getElementById('studentForm').submit();">
                                        <span class="glyphicon glyphicon-trash"/>
                                    </a>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <br>
                <div class="alert alert-info">
                    No student found
                </div>
            </c:otherwise>
        </c:choose>
    </form>
    <c:if test="${not empty sessionRole && sessionRole == adminRoleConst}">
        <form action="/inner/students">
            <input type="hidden" name="action" value="newStudent">
            <br>
            <button type="submit" class="btn btn-primary btn-md">New Student</button>
        </form>
    </c:if>
</div>
</body>
</html>
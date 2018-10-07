<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 05.10.2018
  Time: 12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <script src="/resources/js/bootstrap.min.js"></script>
    <title>Discipline list</title>
</head>
<body>
<div>
    <c:import url="menu.jsp"></c:import>
</div>
<div>
    <h3 align="center">Discipline list</h3>
</div>
<div class="container">
    <c:if test="${not empty message}">
        <div class="alert alert-success">
                ${message}
        </div>
    </c:if>
    <form action="/discipline" method="post" id="disciplineForm" role="form">
        <input type="hidden" id="idDiscipline" name="idDiscipline">
        <input type="hidden" id="action" name="action">
        <c:choose>
            <c:when test="${not empty disciplineList}">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <td>#</td>
                        <th>Discipline</th>
                        <th>Teacher name</th>
                        <th>Teacher last name</th>
                    </tr>
                    </thead>
                    <c:forEach var="discipline" items="${disciplineList}">
                        <c:set var="classSucess" value=""/>
                        <c:if test="${idDiscipline == discipline.id}">
                            <c:set var="classSucess" value="info"/>
                        </c:if>
                        <tr class="${classSucess}">
                            <td>
                                <a href="/discipline?idDiscipline=${discipline.id}&searchAction=searchById">${discipline.id}</a>
                            </td>
                            <td>${discipline.name}</td>
                            <td>${discipline.teacher.name}</td>
                            <td>${discipline.teacher.surname}</td>
                            <td>
                                <a href="#" id="remove"
                                   onclick="document.getElementById('action').value = 'remove';
                                           document.getElementById('idDiscipline').value = '${discipline.id}';
                                           document.getElementById('disciplineForm').submit();">
                                    <span class="glyphicon glyphicon-trash"/>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <br>
                <div class="alert alert-info">
                    No discipline found
                </div>
            </c:otherwise>
        </c:choose>
    </form>
    <form action="/discipline">
        <input type="hidden" name="action" value="newDiscipline">
        <br>
        <button type="submit" class="btn btn-primary btn-md">New discipline</button>
    </form>
</div>
</body>
</html>

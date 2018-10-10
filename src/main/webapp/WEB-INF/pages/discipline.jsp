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
        <c:set var="classMessage" value="alert alert-success"/>
        <c:if test="${not empty messageType && messageType == 'bad'}">
            <c:set var="classMessage" value="alert alert-danger"/>
        </c:if>
        <div class="${classMessage}">
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
                        <th>Teacher</th>
                    </tr>
                    </thead>
                    <c:set var="sessionRole" value="${sessionScope.get(\"role\")}"/>
                    <c:set var="adminRoleConst" value="2"/>
                    <c:forEach var="discipline" items="${disciplineList}">
                        <c:set var="classSucess" value=""/>
                        <c:if test="${idDiscipline == discipline.id}">
                            <c:set var="classSucess" value="info"/>
                        </c:if>
                        <tr class="${classSucess}">
                            <td>
                                <c:choose>
                                    <c:when test="${not empty sessionRole && sessionRole == adminRoleConst}">
                                        <a href="/discipline?idDiscipline=${discipline.id}&searchAction=searchById">${discipline.id}</a>
                                    </c:when>
                                    <c:otherwise>
                                        ${discipline.id}
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${discipline.name}</td>
                            <td>${discipline.teacher.surname} ${discipline.teacher.name}</td>
                            <c:if test="${not empty sessionRole && sessionRole == adminRoleConst}">
                                <td>
                                    <a href="#" id="remove"
                                       onclick="document.getElementById('action').value = 'remove';
                                               document.getElementById('idDiscipline').value = '${discipline.id}';
                                               document.getElementById('disciplineForm').submit();">
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
                    No discipline found
                </div>
            </c:otherwise>
        </c:choose>
    </form>
    <c:if test="${not empty sessionRole && sessionRole == adminRoleConst}">
        <form action="/discipline">
            <input type="hidden" name="action" value="newDiscipline">
            <br>
            <button type="submit" class="btn btn-primary btn-md">New discipline</button>
        </form>
    </c:if>
</div>
</body>
</html>

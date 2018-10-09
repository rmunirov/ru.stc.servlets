<%--
  Created by IntelliJ IDEA.
  User: munirov.rr
  Date: 03.10.2018
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <script src="/resources/js/bootstrap.min.js"></script>
    <title>Teacher list</title>
</head>
<body>
<div>
    <c:import url="menu.jsp"></c:import>
</div>
<div>
    <h3 align="center">Teacher list</h3>
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
    <form action="/teachers" method="post" id="teacherForm" role="form">
        <input type="hidden" id="idTeacher" name="idTeacher">
        <input type="hidden" id="action" name="action">
        <c:choose>
            <c:when test="${not empty teacherList}">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <td>#</td>
                        <th>Teacher</th>
                        <th>Department</th>
                    </tr>
                    </thead>
                    <c:forEach var="teaher" items="${teacherList}">
                        <c:set var="classSucess" value=""/>
                        <c:if test="${idTeacher == teaher.id}">
                            <c:set var="classSucess" value="info"/>
                        </c:if>
                        <tr class="${classSucess}">
                            <td>
                                <a href="/teachers?idTeacher=${teaher.id}&searchAction=searchById">${teaher.id}</a>
                            </td>
                            <td>${teaher.surname} ${teaher.name}</td>
                            <td>${teaher.department.name}</td>
                            <td>
                                <a href="#" id="remove"
                                   onclick="document.getElementById('action').value = 'remove';
                                           document.getElementById('idTeacher').value = '${teaher.id}';
                                           document.getElementById('teacherForm').submit();">
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
                    No teacher found
                </div>
            </c:otherwise>
        </c:choose>
    </form>
    <form action="/teachers">
        <input type="hidden" name="action" value="newTeacher">
        <br>
        <button type="submit" class="btn btn-primary btn-md">New Teacher</button>
    </form>
</div>
<%--<div>
    <%@include file="menu.jsp" %>
</div>
<div>
    <h3 align="center">Teacher list</h3>
</div>
<div>
    <table align="center" border="1" cellpadding="1" cellspacing="1" style="width:500px;border-collapse:collapse">
        <thead>
        <tr>
            <th scope="col">Teacher</th>
            <th scope="col">Department</th>
        </tr>
        </thead>
        <tbody>
        <%
            //how worked JspWriter (out), class is not available
            List<Teacher> list = (List<Teacher>) request.getAttribute("list");
            for (Teacher teacher : list) {
        %>
        <tr align="center">
            <td><%=teacher.getSurname() + " " + teacher.getName()%>
            </td>
            <td><%=teacher.getDepartment().getName()%>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>--%>
</body>
</html>
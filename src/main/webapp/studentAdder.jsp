<%@ page import="ru.innopolis.stc12.servlets.pojo.City" %>
<%@ page import="ru.innopolis.stc12.servlets.pojo.Group" %>
<%@ page import="ru.innopolis.stc12.servlets.pojo.Sex" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: munirov.rr
  Date: 03.10.2018
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student adder</title>
</head>
<body>
<div>
    <%@include file="menu.jsp" %>
</div>
<div>
    <h3 style="text-align:center">Add are new student</h3>
</div>
<div>
    <form name="StudentAddForm" method="post">
        <p>Name:
            <input maxlength="20" name="studentName" size="20" type="text"/>
        </p>
        <p>Surname:
            <input maxlength="20" name="studentSurname" size="20" type="text"/>
        </p>
        <p>Sex:
            <%List<Sex> sexList = ((List<Sex>) request.getAttribute("sexList"));%>
            <select name="studentSex" size="1">
                <%for (Sex sex : sexList) {%>
                <option value="<%=sex.getId()%>"><%=sex.getSex()%>
                </option>
                <%
                    }
                %>
            </select>
        </p>
        <p>Date of Receipt:
            <input maxlength="20" name="studentDateOfReceipt" size="20" type="date"/>
        </p>
        <p>Group:
            <%List<Group> groupList = ((List<Group>) request.getAttribute("groupList"));%>
            <select name="studentGroup" size="1">
                <%for (Group group : groupList) {%>
                <option value="<%=group.getId()%>"><%=group.getName()%>
                </option>
                <%
                    }
                %>
            </select>
        </p>
        <p>City:
            <%List<City> cityList = ((List<City>) request.getAttribute("cityList"));%>
            <select name="studentCity" size="1">
                <%for (City city : cityList) {%>
                <option value="<%=city.getId()%>"><%=city.getName()%>
                </option>
                <%
                    }
                %>
            </select>
        </p>
        <p>Address:
            <input maxlength="40" name="studentAddress" size="40" type="text"/>
        </p>

        <p>Phone:
            <input maxlength="20" name="studentPhone" size="20" type="tel"/>
        </p>

        <p>Email:
            <input maxlength="20" name="studentEmail" size="20" type="email"/>
        </p>
        <p><input name="studentAdd" type="submit" value="Add"/></p>
    </form>
</div>
</body>
</html>

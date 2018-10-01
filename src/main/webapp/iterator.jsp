<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 01.10.2018
  Time: 22:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    List<Integer> list = (List<Integer>) request.getAttribute("List");
    for (Integer i : list) {
%>
<%=i%><<br>
<%
    }
%>
</body>
</html>

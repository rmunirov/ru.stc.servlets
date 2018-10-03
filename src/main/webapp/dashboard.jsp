<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 03.10.2018
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Your in how <%=request.getSession().getAttribute("login")%>, <a href="/login?action=logout">Logout</a>
<h1>
    Dashboard
</h1>

</body>
</html>

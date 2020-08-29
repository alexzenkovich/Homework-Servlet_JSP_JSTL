<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.Role" %>
<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>User JSP</title>
</head>
<body>

<%
    User user = (User) request.getSession().getAttribute("user");

    if (user.getRole() == Role.USER) {
        out.println("<h2>User data:</h2>");
    } else if (user.getRole() == Role.ADMIN) {
        out.println("<h2>Admin data:</h2>");
    }
    out.println(user.getLogin());
    out.println(user.getPassword());
    out.println(user.getRole());
%>

<%
    if (user.getRole() == Role.ADMIN) {
        List<User> users = ((List<User>) request.getAttribute("users"));

        out.println("<h2>Users list:</h2>");
        out.println("<ul>");
        for (User u : users) {
            out.println("<li>" + u + "</li>");
        }
        out.println("</ul>");
    }
%>

<form method="post" action="${pageContext.request.contextPath}/logout">
    <input type="submit" value="Log out">
</form>

</body>
</html>

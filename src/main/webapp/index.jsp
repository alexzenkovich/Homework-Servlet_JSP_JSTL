<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
</head>

<body>

<%
    List<User> params = ((List<User>) request.getAttribute("params"));

    if (params != null) {
        for (User u : params) {
            out.println("<h2>" + u + "</h2>");
        }
    }
%>

<%
    String error = ((String) request.getAttribute("error"));

    if (error != null) {
        out.println("<h2>" + error + "</h2>");
    }
%>

<%
    String exists = ((String) request.getAttribute("exists"));
    if (exists != null) {
        out.println("<h2>" + exists + "</h2>");
    }
%>

<%@ include file="templates/login.jsp" %>

<%@include file="templates/registration.jsp"%>


</body>

</html>

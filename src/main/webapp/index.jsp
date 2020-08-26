<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
</head>

<body>

<%
    String[] params = ((String[]) request.getAttribute("params"));

    if (params != null) {
        for (int i = 0; i < params.length; i++) {
            out.println("<h2>" + params[i] + "</h2>");
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

<%@include file="templates/registration.jsp"%>>


</body>

</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="by.itacademy.model.users.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authentification page</title>
</head>

<body>

<c:if test="${requestScope.error != null}">
    <h3>${requestScope.error}</h3>
    <c:remove var="error" scope="request"/>
</c:if>

<c:if test="${requestScope.exists != null}">
    <h3>${requestScope.exists}</h3>
</c:if>

<jsp:include page="login.jsp"/>

<jsp:include page="registration.jsp"/>

</body>

</html>

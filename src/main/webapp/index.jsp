<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Autentification</title>
</head>

<body>

<c:if test="${requestScope.error != null}">
    <h3>${requestScope.error}</h3>
    <c:remove var="error" scope="request"/>
</c:if>

<c:if test="${requestScope.exists != null}">
    <h3>${requestScope.exists}</h3>
</c:if>

<jsp:include page="templates/login.jsp"/>

<jsp:include page="templates/registration.jsp"/>



</body>

</html>

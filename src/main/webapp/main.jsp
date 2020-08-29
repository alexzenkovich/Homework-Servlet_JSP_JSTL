<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>User JSP</title>
</head>
<body>

<c:choose>
    <c:when test="${sessionScope.user.role == Role.USER}"><c:out value="User data:"/></c:when>
    <c:when test="${sessionScope.user.role == Role.ADMIN}"><c:out value="Admin data:"/></c:when>
</c:choose><br><br>
<c:out value="login: ${sessionScope.user.login}"/><br>
<c:out value="password: ${sessionScope.user.password}"/><br>
<c:out value="user`s status: ${sessionScope.user.role}"/><br>

<c:if test="${sessionScope.user.role == Role.ADMIN}">
    <br>
    <c:out value="Users list:"/><br>
    <ul>
        <c:forEach var="user" items="${requestScope.users}">
            <li><c:out value="${user}"/></li><br>
        </c:forEach>
    </ul>
</c:if>

<form method="post" action="${pageContext.request.contextPath}/logout">
    <input type="submit" value="Log out">
</form>

</body>
</html>

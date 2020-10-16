<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.users.Role" %>
<%@ page import="model.users.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>${sessionScope.user.role}</title>
</head>
<body>

<c:choose>
    <c:when test="${sessionScope.user.role == Role.USER}"><c:out value="User data:"/></c:when>
    <c:when test="${sessionScope.user.role == Role.ADMINISTRATOR}"><c:out value="Admin data:"/></c:when>
</c:choose><br><br>

<c:out value="Id: ${sessionScope.user.id}"/><br>
<c:out value="Name: ${sessionScope.user.name}"/><br>
<c:out value="Surname: ${sessionScope.user.surname}"/><br>
<c:out value="Email: ${sessionScope.user.email}"/><br>
<c:out value="Age: ${sessionScope.user.age}"/><br>
<c:out value="Login: ${sessionScope.user.authenticate.login}"/><br>
<c:out value="Password: ${sessionScope.user.authenticate.password}"/><br>
<c:if test="${sessionScope.user.role == Role.USER}">
    <form method="post" action="templates/edit_profile.jsp">
        <input type="submit" value="edit profile">
    </form>
</c:if>


<c:if test="${sessionScope.user.role == Role.ADMINISTRATOR}">
    <br>
    <c:out value="Users list:"/><br>
    <ul>
        <c:forEach var="user" items="${requestScope.users}">
            <c:set var="userInfo" value="${user.id} | ${user.name} | ${user.surname} | ${user.email} |
            ${user.age} | ${user.authenticate.login} | ${user.authenticate.password} | ${user.role}"/>
            <li><c:out value="${userInfo}"/><br>
                <c:if test="${user.role != Role.ADMINISTRATOR}">
                    <c:url var="update" value="/change?action=update" scope="request">
                        <c:param name="userId" value="${user.id}"/>
                        <c:param name="adminId" value="${sessionScope.user.id}"/>
                    </c:url>
                    <c:url var="delete" value="/change?action=delete" scope="request">
                        <c:param name="userId" value="${user.id}"/>
                        <c:param name="adminId" value="${sessionScope.user.id}"/>
                    </c:url>
                    <a href="<c:out value="${update}"/>">change user to admin</a>
                    <a href="<c:out value="${delete}"/>">delete</a>
                </c:if>
            </li>
            <br>
        </c:forEach>
    </ul>
</c:if>

</body>
</html>

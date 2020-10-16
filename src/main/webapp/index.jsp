<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="model.users.Role" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>

<head>
    <meta charset="UTF-8" lang="ru">
    <link href="projectStyle.css" rel="stylesheet">
    <title>Home page</title>
</head>

<body>
<header>
    <h1>Online-library</h1>
    <div id="authenticate">
        <c:choose>
            <c:when test="${sessionScope.user.role == Role.USER || sessionScope.user.role == Role.ADMINISTRATOR}">
                <c:import url="/templates/buttons/profile_button.jsp"/>
                <c:import url="/templates/buttons/basket_button.jsp"/>
                <c:import url="/templates/buttons/logout.jsp"/>
            </c:when>
            <c:otherwise>
                <c:import url="/templates/buttons/login_button.jsp"/>
                <c:import url="/templates/buttons/registration_button.jsp"/>
            </c:otherwise>
        </c:choose>
    </div>
</header>
<main>
    <div id="navigate_map">

    </div>
    <div id="content">
        <c:if test="${requestScope.exists != null}"><h3>${requestScope.exists}</h3></c:if>
        <c:if test="${requestScope.error != null}"><h3>${requestScope.error}</h3></c:if>
        <c:if test="${requestScope.emptyBasket != null}"><h3>${requestScope.emptyBasket}</h3></c:if>
        <c:if test="${requestScope.basketMessage != null}"><h3>${requestScope.basketMessage}</h3></c:if>
        <c:import url="books.jsp"/>
    </div>
</main>
<footer>
    <h3>FOOTER</h3>
</footer>

</body>

</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.itacademy.model.users.Role" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>

<head>
    <meta charset="UTF-8" lang="ru">
    <link href="${pageContext.request.contextPath}/WEB-INF/views/projectStyle.css" rel="stylesheet">
    <title>Home page</title>
</head>

<body>
<header>
    <h1>Online-library</h1>
    <div id="authenticate">
        <c:choose>
            <c:when test="${sessionScope.user.role == Role.USER || sessionScope.user.role == Role.ADMINISTRATOR}">
                <c:import url="/WEB-INF/views/templates/buttons/profile_button.jsp"/>
                <c:import url="/WEB-INF/views/templates/buttons/basket_button.jsp"/>
                <c:import url="/WEB-INF/views/templates/buttons/logout.jsp"/>
            </c:when>
            <c:otherwise>
                <c:import url="/WEB-INF/views/templates/buttons/login_button.jsp"/>
                <c:import url="/WEB-INF/views/templates/buttons/registration_button.jsp"/>
            </c:otherwise>
        </c:choose>
    </div>
</header>
<main>
    <div id="content">
        <c:if test="${exists != null}"><h3>${exists}</h3></c:if>
        <c:if test="${error != null}"><h3>${error}</h3></c:if>
        <c:if test="${requestScope.emptyBasket != null}"><h3>${requestScope.emptyBasket}</h3></c:if>
        <c:if test="${requestScope.basketMessage != null}"><h3>${requestScope.basketMessage}</h3></c:if>
<%--        <c:import url="books.jsp"/>--%>
        <c:choose>
            <c:when test="${requestScope.books != null}"><c:set var="list" value="${requestScope.books}"/></c:when>
            <c:when test="${requestScope.booksFromBasket != null}"><c:set var="list" value="${requestScope.booksFromBasket}"/></c:when>
            <c:when test="${requestScope.bookInfo != null}"><c:set var="bookInfo" value="${requestScope.bookInfo}"/></c:when>
        </c:choose>

<%--        <h2>${requestScope.bookInfo.author}</h2><br>--%>


<%--        <h3>${requestScope.bookInfo.title}</h3>--%>


<%--        <ol>--%>
<%--            <c:forEach var="book" items="${list}">--%>
<%--                <c:set var="info" value="${book.author} | ${book.title}"/>--%>
<%--                <li>--%>
<%--                    <c:out value="Books:"/>--%>
<%--                    <c:out value="${info}"/>--%>
<%--                    <c:if test="${sessionScope.user.role == Role.USER && requestScope.books != null}">--%>
<%--                        <c:url var="put" value="/basket" scope="request">--%>
<%--                            <c:import scope="request" url="templates/days_for_reading.jsp"/>--%>
<%--                            <c:param name="action" value="addBook"/>--%>
<%--                            <c:param name="bookId" value="${book.id}"/>--%>
<%--                            <c:param name="userId" value="${sessionScope.user.id}"/>--%>
<%--                        </c:url>--%>
<%--                    </c:if>--%>
<%--                    <c:url var="information" value="/book" scope="request">--%>
<%--                        <c:param name="action" value="info"/>--%>
<%--                        <c:param name="id" value="${book.id}"/>--%>
<%--                    </c:url>--%>
<%--                    <a href="<c:out value="${information}"/>">info</a>--%>
<%--                </li>--%>
<%--            </c:forEach>--%>
<%--        </ol>--%>
    </div>
</main>
<footer>
    <h3>FOOTER</h3>
</footer>

</body>

</html>

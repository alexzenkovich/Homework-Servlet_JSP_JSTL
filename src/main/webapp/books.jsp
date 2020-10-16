<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.users.Role" %>
<%--
  Created by IntelliJ IDEA.
  User: aalex
  Date: 22.09.2020
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:choose>
    <c:when test="${requestScope.books != null}"><c:set var="list" value="${requestScope.books}"/></c:when>
    <c:when test="${requestScope.booksFromBasket != null}"><c:set var="list" value="${requestScope.booksFromBasket}"/></c:when>
    <c:when test="${requestScope.bookInfo != null}"><c:set var="bookInfo" value="${requestScope.bookInfo}"/></c:when>
</c:choose>
<p>
    <h2>${requestScope.bookInfo.author}</h2><br>
    <h3>${requestScope.bookInfo.title}</h3>
<p>

</p>

<ol>
    <c:forEach var="book" items="${list}">
        <c:set var="info" value="${book.author} | ${book.title}"/>
        <li>
            <c:out value="Books:"/>
            <c:out value="${info}"/>
            <c:if test="${sessionScope.user.role == Role.USER && requestScope.books != null}">
                <c:url var="put" value="/basket" scope="request">
                    <c:import scope="request" url="templates/days_for_reading.jsp"/>
                    <c:param name="action" value="addBook"/>
                    <c:param name="bookId" value="${book.id}"/>
                    <c:param name="userId" value="${sessionScope.user.id}"/>
                </c:url>
            </c:if>
            <c:url var="information" value="/book" scope="request">
                <c:param name="action" value="info"/>
                <c:param name="id" value="${book.id}"/>
            </c:url>
            <a href="<c:out value="${information}"/>">info</a>
        </li>
    </c:forEach>
</ol>
</body>
</html>

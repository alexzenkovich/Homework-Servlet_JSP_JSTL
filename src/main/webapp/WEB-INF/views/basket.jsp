<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sev" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/projectStyle.css">
    <title><sec:authentication property="principal.name"/>`s basket</title>
</head>
<body>
<header>
    <h1>Online-library</h1>
    <div id="authenticate">
        <table class="user_buttons">
            <tr>
                <sec:authorize access="hasAuthority('ADMINISTRATOR')">
                    <td><c:import url="templates/buttons/books_button.jsp"/></td>
                    <td><c:import url="templates/buttons/users_button.jsp"/></td>
                </sec:authorize>
                <td><c:import url="templates/buttons/profile_button.jsp"/></td>
                <td><c:import url="templates/buttons/logout_button.jsp"/></td>
            </tr>
        </table>
    </div>
</header>
<main>
    <div id="error">
        <c:if test="${error != null}"><h3>${error}</h3></c:if>
    </div>
    <div id="divBasketPage">
        <h2>Books in your basket:</h2>
        <table class="mainTable">
            <thead>
            <tr>
                <th>Author</th>
                <th>Title</th>
                <th>Date of taking</th>
                <th>Days for reading</th>
                <th></th>
            </tr>
            </thead>
            <c:forEach var="basketCell" items="${booksInBasket}">
                <tr>
                    <td>${basketCell.book.author}</td>
                    <td>${basketCell.book.title}</td>
                    <td>${basketCell.dateOfTakingBook}</td>
                    <td>${basketCell.daysForReading}</td>
                    <td>
                        <form method="post" action="<c:url value="/deleteFromBasket"/>">
                            <input type="hidden" name="basketCellId" value="${basketCell.id}">
                            <input type="submit" value="delete"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</main>
<footer>
    <c:import url="templates/buttons/to_home_button.jsp"/>
</footer>


</body>
</html>
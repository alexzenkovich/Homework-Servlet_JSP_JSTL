<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<html>

<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/projectStyle.css">
    <title>Home page</title>
</head>

<body>
<header>
    <h1>Online-library</h1>
    <div id="authenticate">
        <sec:authorize access="isAuthenticated()">
            <table class="user_buttons">
                <tr>
                    <sec:authorize access="hasAuthority('ADMINISTRATOR')">
                        <td><c:import url="templates/buttons/books_button.jsp"/></td>
                    </sec:authorize>
                    <td><c:import url="templates/buttons/basket_button.jsp"/></td>
                    <td><c:import url="templates/buttons/profile_button.jsp"/></td>
                    <td><c:import url="templates/buttons/logout_button.jsp"/></td>
                </tr>
            </table>
        </sec:authorize>

        <sec:authorize access="!isAuthenticated()">
            <table>
                <tr>
                    <td><c:import url="templates/buttons/login_button.jsp"/></td>
                    <td><c:import url="templates/buttons/registration_button.jsp"/></td>
                </tr>
            </table>
        </sec:authorize>
    </div>
</header>
<main>
    <div id="error">
        <table>
            <tr>
                <td></td>
                <td>
                    <c:if test="${error != null}"><h3>${error}</h3></c:if>
                    <c:if test="${basketMessage != null}">${basketMessage}</c:if>
                </td>
                <td></td>
            </tr>
        </table>
    </div>
    <div id="divMainTable">
        <table class="mainTable">
            <thead>
            <tr>
                <th>No.</th>
                <th>Author</th>
                <th>Title</th>
                <th>Numbers of pages</th>
                <sec:authorize access="isAuthenticated()">
                    <th></th>
                    <th></th>
                </sec:authorize>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="book" items="${books}">
                <tr>
                    <td>${book.id}</td>
                    <td>${book.author}</td>
                    <td>${book.title}</td>
                    <td>${book.numberOfPages}</td>
                    <sec:authorize access="isAuthenticated()">
                        <td>
                            <form method="post" action="<c:url value="/addToBasket"/>">
                                <label>
                                    <input width="20" type="number" min="1" name="daysForReading"/>
                                </label>
                                <input type="hidden" name="userId" value="${user.id}">
                                <input type="hidden" name="bookId" value="${book.id}">
                                <input type="submit" value="add book"/>
                            </form>
                        </td>
                        <td>
                            <form method="post" action="<c:url value="/getBookInfo"/>">
                                <input type="hidden" name="userId" value="${user.id}">
                                <input type="hidden" name="bookId" value="${book.id}">
                                <input type="submit" value="about book"/>
                            </form>
                        </td>
                    </sec:authorize>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</main>


<footer>
    <h2 align="center">Your advertisement could be here</h2>
</footer>

</body>

</html>

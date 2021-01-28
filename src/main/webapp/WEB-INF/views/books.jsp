<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/projectStyle.css">
    <title>books</title>
</head>
<body>
<header>
    <h1>Online-library</h1>
    <div id="authenticate">
        <table class="user_buttons">
            <tr>
                <sec:authorize access="hasAuthority('ADMINISTRATOR')">
                    <td><c:import url="templates/buttons/users_button.jsp"/></td>
                </sec:authorize>
                <td><c:import url="templates/buttons/basket_button.jsp"/></td>
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
    <c:import url="templates/buttons/to_home_button.jsp"/>
    <div id="divMainTable">
        <table class="mainTable">
            <thead>
            <tr>
                <th>No.</th>
                <th>Author</th>
                <th>Title</th>
                <th>Numbers of pages</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="book" items="${books}">
                <tr>
                    <td>${book.id}</td>
                    <td>${book.author}</td>
                    <td>${book.title}</td>
                    <td>${book.numberOfPages}</td>
                    <td>
                        <form method="post" action="<c:url value="/getBookInfo"/>">
                            <input type="hidden" name="userId" value="<sec:authentication property="principal.id"/>">
                            <input type="hidden" name="bookId" value="${book.id}">
                            <input type="submit" value="about book"/>
                        </form>
                    </td>
                    <td>
                        <form method="post" action="<c:url value="/deleteBook"/>">
                            <input type="hidden" name="bookId" value="${book.id}">
                            <input type="submit" value="delete book"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <c:import url="templates/buttons/add_book_button.jsp"/>
    </div>
</main>

<footer>
    <c:import url="templates/buttons/to_home_button.jsp"/>
</footer>
</body>
</html>

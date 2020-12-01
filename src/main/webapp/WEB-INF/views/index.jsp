<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ page import="by.it_academy.model.users.Role" %>
<html>

<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/projectStyle.css">
    <title>Home page</title>
</head>

<body>
<header>
    <h1>Online-library</h1>
    <div id="authenticate">
        <c:choose>
            <c:when test="${user.role == Role.USER || user.role == Role.ADMINISTRATOR}">
                <table>
                    <tr>
                        <td><c:import url="/WEB-INF/views/templates/buttons/profile_button.jsp"/></td>
                        <td><c:import url="/WEB-INF/views/templates/buttons/basket_button.jsp"/></td>
                        <td><c:import url="/WEB-INF/views/templates/buttons/logout_button.jsp"/></td>
                    </tr>
                </table>
            </c:when>
            <c:otherwise>
                <table>
                    <tr>
                        <td><c:import url="/WEB-INF/views/templates/buttons/login_button.jsp"/></td>
                        <td><c:import url="/WEB-INF/views/templates/buttons/registration_button.jsp"/></td>
                    </tr>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
</header>
<main>
    <div id="error">
        <c:if test="${error != null}"><h3>${error}</h3></c:if>
    </div>
    <div id="divTableBooks">
        <table class="tableBooks">
            <thead>
            <tr>
                <th scope="col" id="colId">No.</th>
                <th scope="col" id="colAuthor">Author</th>
                <th scope="col" id="colTitle">Title</th>
                <th scope="col" id="colPages">Numbers of pages</th>
                <c:if test="${user.authenticate.profileEnable == true && (user.role == Role.ADMINISTRATOR || user.role == Role.USER)}">
                    <th scope="col" id="colAddToBasket"></th>
                    <th scope="col" id="colBookInfo"></th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="book" items="${books}">
                <tr>
                    <td>${book.id}</td>
                    <td>${book.author}</td>
                    <td>${book.title}</td>
                    <td>${book.numberOfPages}</td>
                    <c:if test="${user.authenticate.profileEnable == true && (user.role == Role.ADMINISTRATOR || user.role == Role.USER)}">
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
                    </c:if>
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

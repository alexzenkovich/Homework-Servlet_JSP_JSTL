<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/projectStyle.css">
    <title>${user.name}`s basket</title>
</head>
<body>
<header>
    <h1>Online-library</h1>
    <div id="authenticate">
        <c:import url="/WEB-INF/views/templates/buttons/profile_button.jsp"/>
        <c:import url="/WEB-INF/views/templates/buttons/logout_button.jsp"/>
    </div>
</header>
<main>
    <div id="error">
        <c:if test="${error != null}"><h3>${error}</h3></c:if>
    </div>
    <div id="divTableBooksInBasket">
        <h2>Books in your basket:</h2>
        <table class="tableBooksInBasket">
            <tr>
                <th>Author</th>
                <th>Title</th>
                <th>Date of taking</th>
                <th>Days for reading</th>
                <th></th>
            </tr>
            <c:forEach var="basketCell" items="${booksInBasket}">
                <tr>
                    <td>${basketCell.book.author}</td>
                    <td>${basketCell.book.title}</td>
                    <td>${basketCell.dateOfTakingBook}</td>
                    <td >${basketCell.daysForReading}</td>
                    <td>
                        <form method="post" action="<c:url value="/deleteFromBasket"/>">
                            <input type="hidden" name="userId" value="${user.id}">
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

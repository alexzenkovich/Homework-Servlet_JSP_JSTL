<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${book.author}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/projectStyle.css">
</head>
<body>
<header>
    <h1>Online-library</h1>
    <div id="authenticate">
        <table>
            <tr>
                <td><c:import url="/WEB-INF/views/templates/buttons/profile_button.jsp"/></td>
                <td><c:import url="/WEB-INF/views/templates/buttons/basket_button.jsp"/></td>
                <td><c:import url="/WEB-INF/views/templates/buttons/logout_button.jsp"/></td>
            </tr>
        </table>
    </div>
</header>
<main>
    <div id="error">
        <c:if test="${error != null}"><h3>${error}</h3></c:if>
    </div>
    <div>
        <p>
        <h3>${book.author}</h3>
        </p>
        <p>
            ${book.title}
        </p>
    </div>
</main>

<footer>
    <c:import url="templates/buttons/to_home_button.jsp"/>
</footer>
</body>
</html>

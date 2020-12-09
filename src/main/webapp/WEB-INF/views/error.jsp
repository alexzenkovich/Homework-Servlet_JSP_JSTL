<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/projectStyle.css">
</head>
<body>
<header>
    <h1>Online-library</h1>
</header>
<main>

    <p>Something went wrong...</p>
    <div id="error">
        <c:if test="${error != null}"><h3>${error}</h3></c:if>
    </div>
</main>

<footer>
    <c:import url="templates/buttons/to_home_button.jsp"/>
</footer>
</body>
</html>
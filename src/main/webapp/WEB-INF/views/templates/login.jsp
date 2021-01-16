<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>

<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/projectStyle.css">
    <title>Authentication</title>
</head>

<body>
<header>
    <h1>Online-library</h1>
</header>
<main>
    <div id="error">
        <c:if test="${param.error != null }">${error}</c:if>
        <c:if test="${param.error}">
            <p>You was blocked. You can send a message to administrator.</p>
            <form method="post" action="<c:url value="/login/sendMessage"/> ">
                <label>Here add your message:</label>
                <label>
                    <input type="text" name="message" placeholder="Place for text">
                </label>
                <label>
                    <input type="submit" value="Send message">
                </label>
            </form>
        </c:if>
    </div>
    <div class="div_login_form">
        <form method="post" action="<c:url value="/login"/> ">
            <table class="login_form">
                <tr>
                    <td>Login:</td>
                    <td><input type="text" name="login" placeholder="Enter login"></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password" placeholder="Enter password"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Log in"></td>
                </tr>
            </table>
        </form>
    </div>
</main>
<footer>
    <a href="<c:url value="/index"/>">Home page</a>
</footer>

</body>

</html>

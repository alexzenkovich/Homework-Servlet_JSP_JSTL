<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <c:if test="${error != null}">${error}</c:if>
    </div>
    <div class="div_reg_form">
        <form method="post" action="<c:url value="/registration"/> ">
            <table class="reg_form">
                <tr>
                    <td>Name:</td>
                    <td><input type="text" name="name" placeholder="Enter your name"/></td>
                </tr>
                <tr>
                    <td>Surname:</td>
                    <td><input type="text" name="surname" placeholder="Enter your surname"/></td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td><input type="text" name="email" placeholder="Enter your email"/></td>
                </tr>
                <tr>
                    <td>Age:</td>
                    <td><input type="text" name="age" placeholder="Enter your age"/></td>
                </tr>
                <tr>
                    <td>Login:</td>
                    <td><input type="text" name="login" placeholder="Enter your login"/></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password" placeholder="Enter your password"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Registration"></td>
                </tr>
            </table>
        </form>
    </div>
</main>
<footer>

</footer>
</body>
</html>

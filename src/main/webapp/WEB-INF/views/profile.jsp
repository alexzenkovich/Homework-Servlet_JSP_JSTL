<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/projectStyle.css">
    <title><sec:authentication property="principal.role"/></title>
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
                <td><c:import url="templates/buttons/basket_button.jsp"/></td>
                <td><c:import url="templates/buttons/logout_button.jsp"/></td>
            </tr>
        </table>
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
    <div id="user_data">
        <table>
            <tr>
                <th><h2><sec:authentication property="principal.role"/> data:</h2></th>
                <th></th>
            </tr>
            <tr>
                <td>Name:</td>
                <td><sec:authentication property="principal.name"/></td>
            </tr>
            <tr>
                <td>Surname:</td>
                <td><sec:authentication property="principal.surname"/></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><sec:authentication property="principal.email"/></td>
            </tr>
            <tr>
                <td>Age:</td>
                <td><sec:authentication property="principal.age"/></td>
            </tr>
            <tr>
                <td>Login:</td>
                <td><sec:authentication property="principal.authenticate.login"/></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><sec:authentication property="principal.authenticate.password"/></td>
            </tr>
            <tr>
                <td>Access:</td>
                <td>
                    <sec:authentication property="principal.authenticate.profileEnable" var="profEnable"/>
                    <c:if test="${profEnable == true}">opened</c:if>
                    <c:if test="${profEnable != true}">blocked</c:if>
                </td>
            </tr>
        </table>
    </div>
    <c:import url="templates/edit_profile.jsp"/>
</main>
<footer>
    <c:import url="templates/buttons/to_home_button.jsp"/>
</footer>

</body>
</html>
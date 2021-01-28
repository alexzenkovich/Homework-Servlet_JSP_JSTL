<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/projectStyle.css">
    <title>users</title>
</head>
<body>
<header>
    <h1>Online-library</h1>
    <div id="authenticate">
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

    </div>
</header>
<main>
    <div id="error">
        <c:if test="${error != null}"><h3>${error}</h3></c:if>
    </div>
    <c:import url="templates/buttons/to_home_button.jsp"/>
    <div id="divUserPage">
        <sec:authorize access="hasAuthority('ADMINISTRATOR')" >
            <br>
            <h2>Users list:</h2>
            <table class="mainTable">
                <thead>
                <tr>
                    <th>No.</th>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Email</th>
                    <th>Age</th>
                    <th>Login</th>
                    <th>Password</th>
                    <th>Access</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <c:forEach var="u" items="${users}">
                    <tr>
                        <td>${u.id}</td>
                        <td>${u.name}</td>
                        <td>${u.surname}</td>
                        <td>${u.email}</td>
                        <td>${u.age}</td>
                        <td>${u.authenticate.login}</td>
                        <td>${u.authenticate.password}</td>
                        <td>${u.authenticate.profileEnable}</td>
                        <sec:authentication property="principal.id" var="userId"/>
                        <c:if test="${u.id != userId}">
                            <td>
                                <c:if test="${u.authenticate.profileEnable == true}">
                                    <form method="post" action="<c:url value="/users/blocking"/>">
                                        <input type="hidden" name="userId" value="${u.id}">
                                        <input type="submit" value="block user"/>
                                    </form>
                                </c:if>
                                <c:if test="${u.authenticate.profileEnable == false}">
                                    <form method="post" action="<c:url value="/users/unblocking"/>">
                                        <input type="hidden" name="userId" value="${u.id}">
                                        <input type="submit" value="unblock user"/>
                                    </form>
                                </c:if>
                            </td>
                            <td>
                                <form method="post" action="<c:url value="/users/deleteUser"/>">
                                    <input type="hidden" name="userId" value="${u.id}">
                                    <input type="submit" value="delete user"/>
                                </form>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
        </sec:authorize>
    </div>
</main>

<footer>
    <c:import url="templates/buttons/to_home_button.jsp"/>
</footer>
</body>
</html>


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
                <td><c:import url="templates/buttons/basket_button.jsp"/></td>
                <td><c:import url="templates/buttons/profile_button.jsp"/></td>
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
    <p>
    <td><c:import url="/user/update"/></td>
    </p>
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
                                    <form method="post" action="<c:url value="/user/blocking"/>">
                                        <input type="hidden" name="userId" value="${u.id}">
                                        <input type="submit" value="block user"/>
                                    </form>
                                </c:if>
                                <c:if test="${u.authenticate.profileEnable == false}">
                                    <form method="post" action="<c:url value="/user/unblocking"/>">
                                        <input type="hidden" name="userId" value="${u.id}">
                                        <input type="submit" value="unblock user"/>
                                    </form>
                                </c:if>
                            </td>
                            <td>
                                <form method="post" action="<c:url value="/user/deleteUser"/>">
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
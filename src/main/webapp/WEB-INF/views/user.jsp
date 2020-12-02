<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.it_academy.model.users.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/projectStyle.css">
    <title>${user.role}</title>
</head>
<body>
    <header>
        <h1>Online-library</h1>
        <div id="authenticate">
            <table>
                <tr>
                    <td><c:import url="templates/buttons/basket_button.jsp"/></td>
                    <td><c:import url="templates/buttons/logout_button.jsp"/></td>
                    <td><c:import url="templates/edit_profile.jsp"/></td>
                </tr>
            </table>
        </div>
    </header>
    <main>
        <div id="error">
            <c:if test="${error != null}"><h3>${error}</h3></c:if>
            <c:if test="${basketMessage != null}"><h3>${basketMessage}</h3></c:if>
        </div>
        <div id="user_data">
            <table>
                <tr>
                    <th><h2>${user.role} data:</h2></th>
                    <th></th>
                </tr>
                <tr>
                    <td>Name:</td>
                    <td>${user.name}</td>
                </tr>
                <tr>
                    <td>Surname:</td>
                    <td>${user.surname}</td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td>${user.email}</td>
                </tr>
                <tr>
                    <td>Age:</td>
                    <td>${user.age}</td>
                </tr>
                <tr>
                    <td>Login:</td>
                    <td> ${user.authenticate.login}</td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td>${user.authenticate.password}</td>
                </tr>
                <tr>
                    <td>Access:</td>
                    <td><c:choose>
                        <c:when test="${user.authenticate.profileEnable == true}">open</c:when>
                        <c:otherwise>blocked</c:otherwise>
                    </c:choose></td>
                </tr>
            </table>
        </div>
        <p>
        </p>
        <div id="divUserPage">
            <c:if test="${user.role == Role.ADMINISTRATOR}">
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
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </div>
    </main>
<footer>
    <c:import url="templates/buttons/to_home_button.jsp"/>
</footer>

</body>
</html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>Login form:</h2>
<form method="get" action="<c:url value="/login">">
    <p>
        Login: <input type="text" name="login"/> <br>
    </p>
    <p>
        Password: <input type="password" name="password"/> <br>
    </p>

    <input type="submit" value="Log in">
</form>
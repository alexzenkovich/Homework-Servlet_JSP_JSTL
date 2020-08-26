<h2>Login form:</h2>
<form method="post" action="${pageContext.request.contextPath}/login">
    <p>
        Login: <input type="text" name="login"/> <br>
    </p>
    <p>
        Password: <input type="password" name="password"/> <br>
    </p>

    <input type="submit" value="Log in">
</form>
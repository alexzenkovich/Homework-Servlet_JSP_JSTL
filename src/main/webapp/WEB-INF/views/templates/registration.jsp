<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Registration page</title>
</head>
<body>
<div id="action_form">
    <form method="post" action="${pageContext.request.contextPath}/registration">
        <p>
            Name: <input type="text" name="name" /> <br>
        </p>
        <p>
            Surname: <input type="text" name="surname" /> <br>
        </p>
        <p>
            Email: <input type="text" name="email" /> <br>
        </p>
        <p>
            Age: <input type="text" name="age" /> <br>
        </p>
        <p>
            Login: <input type="text" name="login" /> <br>
        </p>
        <p>
            Password: <input type="password" name="password" /> <br>
        </p>
        <p>
            Role: <input type="text" name="role" value="USER"/> <br>
        </p>
        <input type="submit" value="Registrate">
    </form>
</div>
</body>
</html>

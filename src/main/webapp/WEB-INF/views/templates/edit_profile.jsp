<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit profile</title>
</head>
<body>
<div id="action_form">
    <form method="post" action="<c:url value="/update">">
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
        <input type="submit" value="save changes">
    </form>
</div>
</body>
</html>

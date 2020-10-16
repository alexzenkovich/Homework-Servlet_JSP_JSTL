<%--
  Created by IntelliJ IDEA.
  User: aalex
  Date: 27.09.2020
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="action_form">
    <form method="post" action="${pageContext.request.contextPath}/change">
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

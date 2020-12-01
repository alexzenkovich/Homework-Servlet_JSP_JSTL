<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Authentication</title>
</head>
<body>
<div id="login_button">
    <form method="get" action="<c:url value="/login"/>" >
        <input type="submit" value="Login">
    </form>
</div>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<div id="users_button">
    <form method="get" action="<c:url value="/user/users"/>">
        <input type="submit" value="users">
    </form>
</div>
</body>
</html>
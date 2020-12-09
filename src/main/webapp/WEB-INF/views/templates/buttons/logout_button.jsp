<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div id="logout">
        <form method="post" action="<c:url value="/logout"/>">
            <input type="submit" value="Log out">
        </form>
    </div>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authentication</title>
</head>
<body>
<div id="registration">
    <form method="get" action="<c:url value="/registration"/>">
        <input type="submit" value="Registration">
    </form>
</div>
</body>
</html>

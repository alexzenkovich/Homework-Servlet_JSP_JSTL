<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>books</title>
</head>
<body>
<form method="get" action="<c:url value="/addBook"/>">
    <input type="submit" value="add book"/>
</form>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<div id="books_button">
    <form method="get" action="<c:url value="/books"/>">
        <input type="submit" value="books">
    </form>
</div>
</body>
</html>

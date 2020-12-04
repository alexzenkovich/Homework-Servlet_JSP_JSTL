<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title></title>
</head>
<body>
<div id="basket_button">
    <form method="post" action="<c:url value="/basket"/>">
        <input type="hidden" name="userId" value="${user.id}">
        <input type="submit" value="Your basket ${numberOfBooksInBasket}">
    </form>
</div>
</body>
</html>

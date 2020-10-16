<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: aalex
  Date: 27.09.2020
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="basket_button">
    <form method="post" action="${pageContext.request.contextPath}/basket">
        <input type="submit" value="Your basket ${requestScope.numberOfBooksInBasket}">
    </form>
</div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="to_home_button">
    <form method="post" action="<c:url value="/index"/>">
        <c:if test="${user.id != null}">
            <input type="hidden" name="userId" value="${user.id}">
        </c:if>
        <input type="submit" value="to home page">
    </form>
</div>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="to_home_button">
    <form method="post" action="/index">
        <input type="hidden" name="userId" value="${user.id}">
        <input type="submit" value="to home page">
    </form>
</div>
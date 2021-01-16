<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="profile_button">
    <form method="get" action="<c:url value="/users/profile"/>">
        <input type="submit" value="profile">
    </form>
</div>
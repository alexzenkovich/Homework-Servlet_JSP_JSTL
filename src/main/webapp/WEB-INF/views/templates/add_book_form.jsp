<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/projectStyle.css">
    <title>books</title>
</head>

<body>
<header>
    <h1>Online-library</h1>
</header>
<main>
    <div id="error">
        <c:if test="${error != null}">${error}</c:if>
    </div>
    <div class="div_reg_form">
        <form method="post" action="<c:url value="/addBook"/> ">
            <table class="add_book_form">
                <tr>
                    <td>Author:</td>
                    <td><input type="text" name="author" placeholder="Enter author"/></td>
                </tr>
                <tr>
                    <td>Title:</td>
                    <td><input type="text" name="title" placeholder="Enter title"/></td>
                </tr>
                <tr>
                    <td>Number of pages:</td>
                    <td><input type="text" name="numberOfPages" placeholder="Enter number of pages"/></td>
                </tr>
                    <td><input type="submit" value="add boo"></td>
                </tr>
            </table>
        </form>
    </div>
</main>
<footer>

</footer>
</body>
</html>


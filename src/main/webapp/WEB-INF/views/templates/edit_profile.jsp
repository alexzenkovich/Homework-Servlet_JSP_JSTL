<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/edit_user_form.css">
    <title>Edit profile</title>
</head>
<body>

<button class="open-button" onclick="openForm()" >edit profile</button>
<div class="form-popup" id="myForm">
    <form method="post" action="<c:url value="/user/update"/>" class="form-container">
        <h2>Edit your data:</h2>

        <p>Name: <input type="text" name="name" placeholder="Enter your name"/> <br></p>
        <p>Surname: <input type="text" name="surname" placeholder="Enter your surname"/> <br></p>
        <p>Email: <input type="text" name="email" placeholder="Enter your email"/> <br></p>
        <p>Age: <input type="text" name="age" placeholder="Enter your age"/> <br></p>
        <p>Login: <input type="text" name="login" placeholder="Enter your login"/> <br></p>
        <p>Password: <input type="password" name="password" placeholder="Enter your password"/> <br></p>

        <button type="submit" class="btn">Save</button>
        <button type="button" class="btn cancel" onclick="closeForm()">Close</button>
    </form>
    <script>
        function openForm() {
            document.getElementById("myForm").style.display = "block";
        }

        function closeForm() {
            document.getElementById("myForm").style.display = "none";
        }
    </script>
</div>

</body>
</html>

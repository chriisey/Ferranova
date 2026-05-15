<%@ page import="org.unisa.abeilleamorellifontana_pj.Model.Utente" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cambio Password Ferranova</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/log_regStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css"/>
</head>

<body>

<%@ include file="/WEB-INF/navbar.jsp" %>

<div class="wrapper">
    <form id="passwordForm" action="${pageContext.request.contextPath}/passwordServlet" method="post" onsubmit="return validatePassword()">
        <h3>Cambio Password</h3>
        <div class="cambioPassword">
            <label for="currentPassword">Password Corrente:</label>
            <div class="password-container">
                <input type="password" id="currentPassword" name="currentPassword" required><br>
                <button type="button" class="mostraPassword" id="viewCurrentPassword" aria-label="mostraPassword" tabindex="0" onkeydown="toggleCurrentPassword()" onclick="toggleCurrentPassword()"><i class="fa-regular fa-eye"></i></button>
            </div>

            <label for="newPassword">Nuova Password:</label>
            <div class="password-container">
                <input type="password" id="newPassword" name="newPassword" required><br>
                <button type="button" class="mostraPassword" id="viewNewPassword" aria-label="mostraPassword" tabindex="0" onkeydown="toggleNewPassword()" onclick="toggleNewPassword()"><i class="fa-regular fa-eye"></i></button>
            </div>
            <div id="passwordError" class="alert" style="display: none;">La nuova password non rispetta i criteri di sicurezza.</div>

            <c:if test="${param.error == 1}">
                <div id="alert" class="alert">Vecchia password errata</div>
                <br>
            </c:if>

            <input id="submit" type="submit" value="Cambia Password" />
        </div>
    </form>
</div>

<script>
    function toggleCurrentPassword() {
        var currentPasswordField = document.getElementById("currentPassword");
        var viewCurrentPasswordButton = document.getElementById("viewCurrentPassword").querySelector("i");

        if (currentPasswordField.type === "password") {
            currentPasswordField.type = "text";
            viewCurrentPasswordButton.className = "fa-regular fa-eye-slash";
        } else {
            currentPasswordField.type = "password";
            viewCurrentPasswordButton.className = "fa-regular fa-eye";
        }
    }

    function toggleNewPassword() {
        var newPasswordField = document.getElementById("newPassword");
        var viewNewPasswordButton = document.getElementById("viewNewPassword").querySelector("i");

        if (newPasswordField.type === "password") {
            newPasswordField.type = "text";
            viewNewPasswordButton.className = "fa-regular fa-eye-slash";
        } else {
            newPasswordField.type = "password";
            viewNewPasswordButton.className = "fa-regular fa-eye";
        }
    }

    function validatePassword() {
        var password = document.getElementById("newPassword").value;
        var passwordError = document.getElementById("passwordError");
        var passwordRegex = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\S+$).{8,20}$/;

        if (!passwordRegex.test(password)) {
            passwordError.style.display = 'block';
            return false;
        } else {
            passwordError.style.display = 'none';
            return true;
        }
    }
</script>

</body>
</html>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrazione Ferranova</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/log_regStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css"/>
    <!--script src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&libraries=places"></script-->
    <!--script>
        function initAutocomplete() {
            var input = document.getElementById('indirizzo');
            var autocomplete = new google.maps.places.Autocomplete(input);
            autocomplete.setFields(['address_component', 'geometry']);
        }
    </script-->
    <script>
        function validateForm() {
            var nome = document.getElementById('nome').value;
            var cognome = document.getElementById('cognome').value;
            var email = document.getElementById('email').value;
            var password = document.getElementById('password').value;
            var telefono = document.getElementById('telefono').value;
            var indirizzo = document.getElementById('indirizzo').value;

            var nameRegex = /^[A-Za-z\s]+$/;
            var emailRegex = /^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$/;
            var passwordRegex = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\S+$).{8,20}$/;
            var phoneRegex = /^\d+$/;
            var addressRegex = /^[A-Za-z0-9\s,.-]+$/;

            if (!nameRegex.test(nome) || !nameRegex.test(cognome)) {
                showError("Nome o cognome non validi.");
                return false;
            }

            if (!emailRegex.test(email)) {
                showError("Email non valida.");
                return false;
            }

            if (!passwordRegex.test(password)) {
                showError("Password non valida. Deve contenere almeno un numero, una lettera maiuscola, una minuscola, un carattere speciale e avere una lunghezza tra 8 e 20 caratteri.");
                return false;
            }

            if (!phoneRegex.test(telefono)) {
                showError("Telefono non valido.")
                return false;
            }

            if (!addressRegex.test(indirizzo)) {
                showError("Indirizzo non valido");
                return false;
            }

            return true;
        }

        function showError(message) {
            document.getElementById("alert").innerHTML = message;
            document.getElementById("alert").style.display = "block";

        }
    </script>
</head>

<body onload="initAutocomplete()">

<%@ include file="/WEB-INF/navbar.jsp" %>

<div class="wrapper">
    <form action="registrazione" method="post" onsubmit="return validateForm()">
        <h3>Registrati al sito</h3>

        <label for="nome">Nome</label>
        <input type="text" id="nome" name="nome" required><br>

        <label for="cognome">Cognome</label>
        <input type="text" id="cognome" name="cognome" required><br>

        <label for="email">Email</label>
        <input type="email" id="email" name="email" required><br>

        <label for="password">Password</label>
        <div class="password-container">
            <input type="password" id="password" name="password" required><br>
            <button type="button" class="mostraPassword" id="mostraPassword" aria-label="mostraPassword" tabindex="0" onkeydown="togglePassword()" onclick="togglePassword()"><i class="fa-regular fa-eye"></i></button>
        </div>

        <label for="telefono">Telefono</label>
        <input type="tel" id="telefono" name="telefono" required><br>

        <label for="indirizzo">Indirizzo</label>
        <input type="text" id="indirizzo" name="indirizzo" required><br>

        <div id="alert" class="alert" style="display:none;"></div>

        <c:if test="${param.error == 1}">
            <div id="alert" class="alert" style="display:block;">Email e/o password errate</div>
            <br>
        </c:if>

        <input type="submit" id="submit" value="Registra"/>
    </form>

    <div class="registrazione">
        <p>Sei già utente di Ferranova?</p>
        <a href="login.jsp">entra</a>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/viewPassword.js"></script>
</body>
</html>

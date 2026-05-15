<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="it">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fase di acquisto Ferranova</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/acquistoStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footerStyle.css"/>
    <script>
        function validateForm() {
            var nome = document.getElementById('nome').value;
            var cognome = document.getElementById('cognome').value;
            var indirizzo = document.getElementById('indirizzo').value;
            var intestatario = document.getElementById('intestatario').value;
            var n_carta = document.getElementById('n_carta').value;
            var scadenza = document.getElementById('scadenza').value;
            var cvv = document.getElementById('cvv').value;

            var nameRegex = /^[A-Za-z\s]+$/;
            var addressRegex = /^[A-Za-z0-9\s,.-]+$/;
            var cardholderRegex = /^[A-Za-z\s]+$/;
            var cardNumberRegex = /^\d{16}$/;
            var expiryRegex = /^(0[1-9]|1[0-2])\/?([0-9]{4}|[0-9]{2})$/;
            var cvvRegex = /^[0-9]{3,4}$/;

            if (!nameRegex.test(nome)) {
                showError("Nome non valido.");
                return false;
            }

            if (!nameRegex.test(cognome)) {
                showError("Cognome non valido.");
                return false;
            }

            if (!addressRegex.test(indirizzo)) {
                showError("Indirizzo non valido.");
                return false;
            }

            if (!cardholderRegex.test(intestatario)) {
                showError("Intestatario della carta non valido.");
                return false;
            }

            if (!cardNumberRegex.test(n_carta)) {
                showError("Numero carta non valido. Deve essere di 16 cifre.");
                return false;
            }

            if (!expiryRegex.test(scadenza)) {
                showError("Scadenza non valida. Usa il formato MM/AA.");
                return false;
            }

            if (!cvvRegex.test(cvv)) {
                showError("CVV non valido. Deve essere di 3 o 4 cifre.");
                return false;
            }

            return true;
        }

        function showError(message) {
            var alertDiv = document.getElementById('alert');
            alertDiv.innerText = message;
            alertDiv.style.color = 'red';
            alertDiv.style.fontweight = 'bold';
            alertDiv.style.display = 'block';
        }
    </script>
</head>

<body>

<%@ include file="navbar.jsp" %>

<div class="wrapper">
    <form action="ordineServlet" method="post" onsubmit="return validateForm()">

        <h4>Nominativo</h4>

        <label for="nome">Nome:</label>
        <input type="text" id="nome" placeholder="nome" value="${UtenteConnesso.nome}" name="nome" required><br>

        <label for="cognome">Cognome:</label>
        <input type="text" id="cognome" placeholder="cognome" value="${UtenteConnesso.cognome}" name="cognome" required><br>

        <h4>Indirizzo</h4>

        <label for="indirizzo">Indirizzo:</label>
        <input type="text" id="indirizzo" placeholder="indirizzo" value="${UtenteConnesso.indirizzo}" name="indirizzo" required><br>

        <h4>Dati carta</h4>

        <label for="intestatario">Intestatario della carta:</label>
        <input type="text" id="intestatario" name="intestatario" placeholder="intestatario della carta" required><br>

        <label for="n_carta">Numero carta:</label>
        <input type="text" id="n_carta" name="n_carta" placeholder="numero della carta" required><br>

        <label for="scadenza">Scadenza:</label>
        <input type="text" id="scadenza" name="scadenza" placeholder="scadenza" required><br>

        <label for="cvv">CVV:</label>
        <input type="text" id="cvv" name="cvv" placeholder="CVV" required><br>

        <div id="alert" class="alert" style="display:none;"></div>

        <div class="total-price">
            <p>Totale: <span><fmt:setLocale value="fr_FR"/>
                <!-- Imposta la localizzazione su Francia che usa l'Euro -->
                <fmt:formatNumber value="${sum}" type="currency" currencySymbol="€"/></span></p>
        </div>

        <div class="divAcquista-button">
            <input type="submit" class="acquista-button" value="Acquista">
        </div>
    </form>
</div>

<%@ include file="footer.jsp" %>

<script src="${pageContext.request.contextPath}/js/viewPassword.js"></script>
</body>
</html>

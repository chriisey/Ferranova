<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profilo Ferranova</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/profiloStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footerStyle.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <script>
        function validateForm() {
            const nome = document.getElementById('nome').value;
            const cognome = document.getElementById('cognome').value;
            const telefono = document.getElementById('telefono').value;
            const email = document.getElementById('email').value;
            const indirizzo = document.getElementById('indirizzo').value;

            const NAME_REGEX = /^[A-Za-z\s]+$/;
            const EMAIL_REGEX = /^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$/;
            const PHONE_REGEX = /^\d+$/;
            const ADDRESS_REGEX = /^[A-Za-z0-9\s,.-]+$/;

            if (!NAME_REGEX.test(nome)) {
                showError('Nome non valido');
                return false;
            }

            if (!NAME_REGEX.test(cognome)) {
                showError('Cognome non valido');
                return false;
            }

            if (!EMAIL_REGEX.test(email)) {
                showError('Email non valida');
                return false;
            }

            if (!PHONE_REGEX.test(telefono)) {
                showError('Numero di telefono non valido');
                return false;
            }

            if (!ADDRESS_REGEX.test(indirizzo)) {
                showError('Indirizzo non valido');
                return false;
            }

            return true;
        }

        function showError(message) {
            const errorDiv = document.getElementById('errorDiv');
            errorDiv.innerText = message;
            errorDiv.style.display = 'block';
        }
    </script>
</head>
<body>

<%@ include file="/WEB-INF/navbar.jsp" %>

<div class="container">
    <h1>Dati personali</h1>
    <p class="subparagraph">Visualizza i dati di login e spedizione.</p>


    <form action="${pageContext.request.contextPath}/CambioDatiUtenteServlet" method="post" onsubmit="return validateForm()">
        <div class="profile-section">
            <div class="profile-item">
                <div class="icon"><i class="fas fa-user"></i></div>
                <div class="info">
                    <label for="nome"><strong>Nome</strong></label>
                    <input type="text" id="nome" name="nome" value="${UtenteConnesso.nome}">
                </div>
            </div>
            <div class="profile-item">
                <div class="icon"><i class="fas fa-user"></i></div>
                <div class="info">
                    <label for="cognome"><strong>Cognome</strong></label>
                    <input type="text" id="cognome" name="cognome" value="${UtenteConnesso.cognome}">
                </div>
            </div>
            <div class="profile-item">
                <div class="icon"><i class="fas fa-mobile-alt"></i></div>
                <div class="info">
                    <label for="telefono"><strong>Numero di telefono</strong></label>
                    <input type="text" id="telefono" name="telefono" value="${UtenteConnesso.telefono}">
                </div>
            </div>
            <div class="profile-item">
                <div class="icon"><i class="fas fa-envelope"></i></div>
                <div class="info">
                    <label for="email"><strong>Email</strong></label>
                    <input type="email" id="email" name="email" value="${UtenteConnesso.email}">
                </div>
            </div>
            <div class="profile-item">
                <div class="icon"><i class="fas fa-home"></i></div>
                <div class="info">
                    <label for="indirizzo"><strong>Indirizzo</strong></label>
                    <input type="text" id="indirizzo" name="indirizzo" value="${UtenteConnesso.indirizzo}">
                </div>
            </div>

            <!-- Div per visualizzare i messaggi di errore -->
            <div id="errorDiv" class="error" style="display:none; text-align: center; width: 100%; margin-top: 10px; color:red;"></div>

            <input class="edit-button" type="submit" value="Modifica">

            <div class="cambio-password">
                <p>Vuoi cambiare password?</p>
                <a href="${pageContext.request.contextPath}/password">cambia password</a>
            </div>
        </div>
    </form>

</div>

<%@ include file="/WEB-INF/footer.jsp" %>

</body>
</html>

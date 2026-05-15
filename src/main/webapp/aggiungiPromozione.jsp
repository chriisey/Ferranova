<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Aggiungi Promozioni Ferranova</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/aggiungiProdottoStyle.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footerStyle.css"/>
</head>
<body>

<%@ include file="/WEB-INF/navbar.jsp" %>

<div class="container">
    <h1>Aggiungi Promozione</h1>
    <form action="aggiungi-promozione" method="get">
        <label for="titolo">Titolo:</label>
        <input type="text" id="titolo" name="titolo" required>

        <label for="descrizione">Descrizione:</label>
        <textarea id="descrizione" name="descrizione"></textarea>

        <label for="sconto">Sconto:</label>
        <input type="number" min="1" max="100" id="sconto" name="sconto">

        <script>

            function validateForm() {

                var sconto = document.getElementById("sconto");
                     if(sconto < 0) {
                         alert("Sconto non disponibile");
                     }

            }



        </script>


        <button type="submit">Aggiungi promozione</button>
    </form>
</div>

<%@ include file="/WEB-INF/footer.jsp" %>

</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Aggiungi Prodotti Ferranova</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/aggiungiProdottoStyle.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footerStyle.css"/>
</head>
<body>

<%@ include file="/WEB-INF/navbar.jsp" %>

<div class="container">
    <h1>Aggiungi Prodotto</h1>
    <form action="${pageContext.request.contextPath}/addProduct" method="post" enctype="multipart/form-data" onsubmit="validateForm()">
        <label for="titolo">Titolo:</label>
        <input type="text" id="titolo" name="titolo"
        <c:if test="${not empty param.titolo}"> value="${param.titolo}" </c:if> required>

        <label for="descrizione">Descrizione:</label>
        <textarea id="descrizione" name="descrizione"><c:if
                test="${not empty param.descrizione}">${param.descrizione}</c:if></textarea>

        <label for="quantita">Quantità:</label>
        <input type="number" id="quantita" min="1" name="quantita"
        <c:if test="${not empty param.quantita}"> value="${param.quantita}" </c:if> required>

        <label for="id_promozione">ID Promozione:</label>
        <input type="number" id="id_promozione"  min="1" name="id_promozione"
        <c:if test="${not empty param.id_promozione}"> value="${param.id_promozione}" </c:if> >

        <label for="select-category">Categoria:</label>
        <select name="categoria" id="select-category">
            <c:forEach var="entry" items="${Categorie}">
                <optgroup label="${entry.key}">
                    <c:forEach var="sottocategoria" items="${entry.value}">
                    <option value="${entry.key}-${sottocategoria}">${sottocategoria}</option>
                    </c:forEach>
                </optgroup>
            </c:forEach>
        </select>


        <label for="prezzo">Prezzo:</label>
        <input type="number" step="0.01" min="0.01" id="prezzo" name="prezzo"
        <c:if test="${not empty param.prezzo}"> value="${param.prezzo}" </c:if> required>

        <label for="immagini">Immagini:</label>
        <input type="file" id="immagini" name="immagini" accept=".png" multiple maxlength="10"
               value="Seleziona file la prima foto sarà la foto di facciata del prodotto PNG Max 10" required>
        <button type="submit">Aggiungi Prodotto</button>
    </form>

    <script>

        function validateForm() {
        var quanti = document.getElementById("quantita").value;
        if(quanti < 0) {
                alert("Quantità non disponibile");
            }
        }


    </script>
</div>

<%@ include file="/WEB-INF/footer.jsp" %>

</body>
</html>

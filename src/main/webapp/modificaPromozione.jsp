<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modifica Promozioni Ferranova</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/aggiungiProdottoStyle.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footerStyle.css"/>
</head>
<body>

<%@ include file="/WEB-INF/navbar.jsp" %>

<div class="container">
    <h1>Modifica Promozione</h1>
    <form action="modifica-promozione-effettiva" method="get">
        <input type="hidden" value="${promo.id}" name="id_prom">

        <label for="titolo">Titolo:</label>
        <input type="text" id="titolo" name="titolo"
        <c:if test="${ not empty promo.titolo}"> value="${ promo.titolo}" </c:if> required>

        <label for="descrizione">Descrizione:</label>
        <textarea id="descrizione" name="descrizione"> <c:if
                test="${ not empty promo.descrizione}"> ${ promo.descrizione} </c:if></textarea>

        <label for="sconto">Sconto:</label>
        <input type="number" min="1" max="100" id="sconto" name="sconto"
        <c:if test="${ not empty  promo.sconto}"> value="${promo.sconto}" </c:if> >


        <button type="submit">Modifica promozione</button>
    </form>
</div>

<%@ include file="/WEB-INF/footer.jsp" %>

</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> <!--per rendere la pagina respobsive-->
    <title>Ordini Ferranova</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ordiniStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footerStyle.css"/>
</head>
<body>

<%@ include file="navbar.jsp" %>

<c:choose>

    <c:when test="${not empty Ordini}">

    <h1>Ordini Effettuati</h1>
    <div class="orders-container">

        <c:forEach var="Ordine" items="${Ordini}">
            <div class="order">
                <div class="order-header">
                    <p class="dataOrdine"><strong>${Ordine.dataOrdine}</strong></p>
                    <div class=" order-details">
                        <p><strong>ID ordine: </strong>${Ordine.idOrdine}</p>
                        <p><strong>Stato: </strong>${Ordine.statoOrdine}</p>
                        <p><strong>Subtotale: </strong><fmt:setLocale value="fr_FR"/>
                            <!-- Imposta la localizzazione su Francia che usa l'Euro -->
                            <fmt:formatNumber value="${Ordine.prezzoOrdine}" type="currency"
                                              currencySymbol="€"/></p>

                        <c:if test="${!(Ordine.statoOrdine.stato == 'spedito' || Ordine.statoOrdine.stato == 'consegnato') || empty Ordine.dataSpedizione}">
                            <p class="dataSpedizione"><strong>Data spedizione: </strong>- </p>
                        </c:if>

                        <c:if test="${ (Ordine.statoOrdine.stato == 'spedito' || Ordine.statoOrdine.stato == 'consegnato' ) && not empty Ordine.dataSpedizione}">
                            <p class="dataSpedizione"><strong>Data spedizione: </strong>${Ordine.dataSpedizione}</p>
                        </c:if>

                        <p><strong>Costo spedizione:</strong><fmt:setLocale value="fr_FR"/>
                            <!-- Imposta la localizzazione su Francia che usa l'Euro -->
                            <fmt:formatNumber value="${Ordine.prezzoSpedizione}" type="currency"
                                              currencySymbol="€"/></p>

                        <c:if test="${ !(Ordine.statoOrdine.stato == 'consegnato') || empty Ordine.dataConsegna}">
                            <p class="dataConsegna"><strong>Data consegna: </strong> - </p>
                        </c:if>

                        <c:if test="${ Ordine.statoOrdine.stato == 'consegnato'  && not empty Ordine.dataConsegna}">
                            <p class="dataConsegna"><strong>Data consegna: </strong>${Ordine.dataConsegna}</p>
                        </c:if>

                    </div>
                </div>
                <div class="order-products">

                    <c:forEach var="entry" items="${Ordine.prodottiQuantitaOrdine}">
                        <c:set var="prodotto" value="${mappaProdotti[entry.key]}"/>
                        <div class="product">
                            <a href="${pageContext.request.contextPath}/prod?id_prodotto=${prodotto.id}">
                                <img src="${pageContext.request.contextPath}/product_images/${prodotto.id}/1.png"
                                     alt="immagine ${prodotto.titolo}"><br>
                                <p>${prodotto.titolo}</p>
                            </a>
                            <p>${entry.value.quantita} Pz.</p>
                            <p><fmt:setLocale value="it_IT"/>
                                <fmt:formatNumber value="${entry.value.prezzoFinale}" type="currency" currencySymbol="€"/>
                            </p>
                        </div>
                    </c:forEach>

                </div>
            </div>
        </c:forEach>

    </div>
</c:when>

<c:otherwise>
    <div class="orderList-empty">
        <img src="${pageContext.request.contextPath}/images/ListaOrdiniVuota.png" alt="lista ordini vuota">
    </div>
</c:otherwise>
</c:choose>

<%@ include file="footer.jsp" %>

</body>
</html>
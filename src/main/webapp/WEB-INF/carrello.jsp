<%@ page import="com.google.gson.Gson" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Carrello Ferranova</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/carrelloStyle.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footerStyle.css"/>
</head>

<body>
<%@ include file="navbar.jsp" %>

<c:set var="mappa" value="${Carrello.prodottiQuantita}"/>
<c:set var="lista" value="${lista}"/>

<c:choose>

    <c:when test="${not empty lista}">
        <div class="container">
            <div class="cart">

                <h2>Carrello</h2>
                <table>
                    <thead>
                    <tr>
                        <th class="product-title-tab">Prodotto</th>
                        <th class="quantity-title">Quantità</th>
                        <th>Prezzo</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach var="elemento" items="${lista}">

                        <tr id="product-row-${elemento.id}">
                            <td>
                                <div class="product-info">
                                    <a href="${pageContext.request.contextPath}/prod?id_prodotto=${elemento.id}"><img
                                            src="${pageContext.request.contextPath}/product_images/${elemento.id}/1.png"
                                            alt="immagine ${elemento.titolo}"></a>

                                    <div>
                                        <p>${elemento.titolo}</p>
                                        <span>Cod. prodotto: ${elemento.id}</span>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="quantity-container">
                                    <button class="minus" aria-label="minus" tabindex="0"
                                            onkeydown="updateCart(${elemento.id}, 'update', -1, ${(elemento.prezzo * (1 - ( promozioni[elemento.idPromozione] / 100)))})"
                                            onclick="updateCart(${elemento.id}, 'update', -1, ${(elemento.prezzo * (1 - ( promozioni[elemento.idPromozione] / 100)))})">
                                        &minus;
                                    </button>
                                    <p id="quantity-${elemento.id}">${mappa[elemento.id]} Pz.</p>
                                    <button class="plus" aria-label="plus" tabindex="0"
                                            onkeydown="updateCart(${elemento.id}, 'update', 1, ${(elemento.prezzo * (1 - ( promozioni[elemento.idPromozione] / 100)))})"
                                            onclick="updateCart(${elemento.id}, 'update', 1, ${(elemento.prezzo * (1 - ( promozioni[elemento.idPromozione] / 100)))})">&plus;
                                    </button>
                                    <button class="delete" aria-label="delete" tabindex="0"
                                            onkeydown="updateCart(${elemento.id}, 'remove', 0, ${(elemento.prezzo * (1 - ( promozioni[elemento.idPromozione] / 100)))})"
                                            onclick="updateCart(${elemento.id}, 'remove', 0, ${(elemento.prezzo * (1 - ( promozioni[elemento.idPromozione] / 100)))})">
                                        rimuovi
                                    </button>
                                </div>

                            </td>
                            <td id="price-${elemento.id}">
                                <div class="price-container">
                                        <fmt:setLocale value="fr_FR"/>
                                    <!-- Imposta la localizzazione su Francia che usa l'Euro -->
                                        <fmt:formatNumber value="${(elemento.prezzo * (1 - ( promozioni[elemento.idPromozione] / 100))) * mappa[elemento.id] }" type="currency"
                                                  currencySymbol="€"/>
                                    <p id="alert-${elemento.id}" class="alert"></p>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="summary">
                <c:set var="spedizione" value="${ sum <= 100 ? 10 : 0  }"/>
                <h2>Riassunto</h2>
                <p>Subtotale: <span id="sub_totale"  prezzo-tot="${sum}">
                    <fmt:setLocale value="fr_FR"/>
                    <!-- Imposta la localizzazione su Francia che usa l'Euro -->
                        <fmt:formatNumber value="${sum }" type="currency" currencySymbol="€"/></span></p>
                <p id="spedizione">Costi di spedizione: <span><fmt:setLocale value="fr_FR"/>
                    <!-- Imposta la localizzazione su Francia che usa l'Euro -->
                        <fmt:formatNumber value="${spedizione }" type="currency" currencySymbol="€"/>
             </span></p>
                <p class="total" id="total">Totale: <span><fmt:setLocale value="fr_FR"/>
                    <!-- Imposta la localizzazione su Francia che usa l'Euro -->
                        <fmt:formatNumber value="${sum + spedizione}" type="currency" currencySymbol="€"/></span></p>
                <a href="${pageContext.request.contextPath}/preAcquisto">Procedi</a>
            </div>
        </div>
    </c:when>

    <c:otherwise>
        <div class="cart-empty">
            <img src="${pageContext.request.contextPath}/images/CarrelloVuoto.png" alt="carrello vuoto">
        </div>
    </c:otherwise>
</c:choose>

<%@ include file="footer.jsp" %>


<script>
    function updateCart(productId, action, quantity, price) {
        const xhr = new XMLHttpRequest();
        xhr.open("POST", "${pageContext.request.contextPath}/carrelloAjax", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        let alertElement = document.getElementById("alert-" + productId);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    if (action === 'update') {
                        document.getElementById("quantity-" + productId).textContent = xhr.responseText + " Pz.";
                        let newPrice = xhr.responseText * price;
                        let formattedPrice = newPrice.toLocaleString('fr-FR', {style: 'currency', currency: 'EUR'});
                        document.getElementById("price-" + productId).textContent = formattedPrice;
                        const prezzo_totale = document.getElementById("sub_totale").getAttribute("prezzo-tot");
                        let sub_tot = 0;
                        if (quantity === 1) {
                            sub_tot = parseFloat(prezzo_totale) + parseFloat(price);
                        } else if (quantity === -1) {
                            sub_tot = parseFloat(prezzo_totale) - parseFloat(price);
                        }
                        document.getElementById("sub_totale").innerHTML = sub_tot.toLocaleString('fr-FR', {
                            style: 'currency',
                            currency: 'EUR'
                        });
                        document.getElementById("sub_totale").setAttribute("prezzo-tot", sub_tot);
                        let costSpedizione = 10;
                        let totaleFinale = 0;
                        if (sub_tot <= 100) {
                            totaleFinale = sub_tot + costSpedizione;
                        } else if (sub_tot > 100) {
                            costSpedizione = 0;
                            totaleFinale = sub_tot + costSpedizione;
                        }
                        document.getElementById("spedizione").innerHTML = "Costo di spedizione: " + costSpedizione.toLocaleString('fr-FR', {
                            style: 'currency',
                            currency: 'EUR'
                        });
                        document.getElementById("total").innerHTML = "Totale: " + totaleFinale.toLocaleString('fr-FR', {
                            style: 'currency',
                            currency: 'EUR'
                        });
                    } else if (action === 'remove') {
                        const row = document.getElementById("product-row-" + productId);
                        const prezzo_totale = document.getElementById("sub_totale").getAttribute("prezzo-tot");
                        row.parentNode.removeChild(row);
                        const sub_tot = parseFloat(prezzo_totale) - (parseFloat(price) * xhr.responseText);
                        document.getElementById("sub_totale").setAttribute("prezzo-tot", sub_tot);
                        document.getElementById("sub_totale").innerHTML = sub_tot.toLocaleString('fr-FR', {
                            style: 'currency',
                            currency: 'EUR'
                        });

                        let costSpedizione = 10;
                        let totaleFinale = 0;
                        if (sub_tot <= 100) {
                            totaleFinale = sub_tot + costSpedizione;
                        } else if (sub_tot > 100) {
                            costSpedizione = 0;
                            totaleFinale = sub_tot + costSpedizione;
                        }
                        document.getElementById("spedizione").innerHTML = "Costo di spedizione: " + costSpedizione.toLocaleString('fr-FR', {
                            style: 'currency',
                            currency: 'EUR'
                        });
                        document.getElementById("total").innerHTML = "Totale: " + totaleFinale.toLocaleString('fr-FR', {
                            style: 'currency',
                            currency: 'EUR'
                        });

                        if (sub_tot === 0) {
                            location.reload();
                        }
                    }
                }

                if (xhr.status === 400) {
                    if (action === "update") {
                        alertElement.innerHTML = "Disponibilità conclusa";
                        alertElement.style.color = "red";
                    } else if (action === "remove") {
                        location.reload();
                    }

                    // Transizione
                    alertElement.style.opacity = 0;
                    alertElement.style.transform = "translateX(-200px) translateY(-20px)";
                    alertElement.style.display = "block";
                    alertElement.offsetHeight; // Forza il reflow per applicare le nuove proprietà
                    alertElement.style.transition = "opacity 0.5s, transform 0.5s";
                    alertElement.style.opacity = 1;
                    alertElement.style.transform = "translateX(-190px) translateY(-20px)";

                    setTimeout(function () {
                        alertElement.style.transition = "opacity 0.5s, transform 0.5s";
                        alertElement.style.opacity = 0;
                        alertElement.style.transform = "translateX(-200px) translateY(-20px)";
                    }, 2000); //dopo 3 secondi scompare

                    setTimeout(function () {
                        alertElement.style.display = "none";
                    }, 2500); //dopo 4 sec
                }
                if(this.status === 401){
                    if (action === "update") {
                        alertElement.innerHTML = "Quantità negativa";
                        alertElement.style.color = "red";
                    } else if (action === "remove") {
                        location.reload();
                    }

                    // Transizione
                    alertElement.style.opacity = 0;
                    alertElement.style.transform = "translateX(-200px) translateY(-20px)";
                    alertElement.style.display = "block";
                    alertElement.offsetHeight; // Forza il reflow per applicare le nuove proprietà
                    alertElement.style.transition = "opacity 0.5s, transform 0.5s";
                    alertElement.style.opacity = 1;
                    alertElement.style.transform = "translateX(-190px) translateY(-20px)";

                    setTimeout(function () {
                        alertElement.style.transition = "opacity 0.5s, transform 0.5s";
                        alertElement.style.opacity = 0;
                        alertElement.style.transform = "translateX(-200px) translateY(-20px)";
                    }, 2000); //dopo 3 secondi scompare

                    setTimeout(function () {
                        alertElement.style.display = "none";
                    }, 2500); //dopo 4 sec
                }

            }
        };
        xhr.send("prod=" + productId + "&action=" + action + "&quantity=" + quantity);
    }
</script>


</body>
</html>
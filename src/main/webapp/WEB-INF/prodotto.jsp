<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${prodotto.titolo} Ferranova</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/prodottoStyle.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/caroselloStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footerStyle.css"/>
</head>
<body>

<%@ include file="navbar.jsp" %>

<div class="product-container">


    <div class="product-image">
        <c:forEach var="i" begin="1" end="${num_foto}">

            <div class="mySlides">
                <div class="numbertext">${i} / ${num_foto}</div>
                <img src="${pageContext.request.contextPath}/product_images/${prodotto.id}/${i}.png" style="width:100%"
                     alt="immagine ${prodotto.titolo}"><br>
            </div>
        </c:forEach>

        <button class="prev" onclick="plusSlides(-1)">&#10094;</button>
        <!--il codice corrisponde al simbolo della freccetta-->
        <button class="next" onclick="plusSlides(1)">&#10095;</button>
    </div>

    <div class="product-details">
        <h1 class="product-title">${prodotto.titolo}</h1>
        <div class="product-price">
            <div class="scontoDiv">
                <c:if test="${prodotto.idPromozione >0 || not empty promozioni[prodotto.idPromozione]}">

                <p style="text-decoration: line-through; color: #333333; font-size: 18px">
                    <fmt:setLocale value="fr_FR"/> <!-- Imposta la localizzazione su Francia che usa l'Euro -->
                    <fmt:formatNumber value="${prodotto.prezzo}" type="currency" currencySymbol="€"/></p>
                <p style="margin-left: 5px"> -${promozioni[prodotto.idPromozione]}% </p>
                </c:if>
            </div>

            <p style="margin-top: 10px">
                <fmt:setLocale value="fr_FR"/> <!-- Imposta la localizzazione su Francia che usa l'Euro -->
            <fmt:formatNumber value="${prodotto.prezzo * (1 - ( promozioni[prodotto.idPromozione] / 100))}"
                              type="currency"
                              currencySymbol="€"/></div>
        </p>


        <div class="quantity-container">
            <label for="quantity">Pezzi disponibili: ${prodotto.quantita}</label><br>
            <input type="number" id="quantity" name="quantity" min="1" value="1" max="${prodotto.quantita}">
        </div>

        <button onclick="addToCart(${prodotto.id})" class="add-to-cart">Aggiungi al carrello</button>

        <p id="alert" class="alert"></p>
    </div>

</div>

<div class="product-description">
    <h2>Descrizione</h2>
    <p>${prodotto.descrizione}</p>
</div>

<%@ include file="footer.jsp" %>

<script src="${pageContext.request.contextPath}/js/carousel.js"></script>

<script>
    function addToCart(product_id) {
        var quantity = document.getElementById("quantity").value;
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "${pageContext.request.contextPath}/carrelloAjax?prod=" + product_id + "&quantity=" + quantity, true);

        xhr.onreadystatechange = function () {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                var alertElement = document.getElementById("alert");
                if (xhr.status === 200) {
                    alertElement.innerHTML = "Aggiunto al carrello";
                    alertElement.style.color = "#335e1e";
                } else if (xhr.status === 400) {
                    alertElement.innerHTML = "Quantità non disponibile";
                    alertElement.style.color = "red";
                } else {
                    alertElement.innerHTML = "Errore";
                    alertElement.style.color = "red";
                }

                alertElement.style.opacity = 0;
                alertElement.style.transform = "translateY(-20px)";
                alertElement.style.display = "block";
                alertElement.offsetHeight; // Forza il reflow per applicare le nuove proprietà
                alertElement.style.transition = "opacity 0.5s, transform 0.5s";
                alertElement.style.opacity = 1;
                alertElement.style.transform = "translateY(20px)";

                setTimeout(function () {
                    alertElement.style.transition = "opacity 0.5s, transform 0.5s";
                    alertElement.style.opacity = 0;
                    alertElement.style.transform = "translateY(-20px)";
                }, 2000); //dopo 2 secondi scompare

                setTimeout(function () {
                    alertElement.style.display = "none";
                }, 2500); //dopo 2.5 secondi scompare
            }
        };
        xhr.send();
    }
</script>


</body>
</html>

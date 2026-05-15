<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi siamo Ferranova</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footerStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/privacyStyle.css"/>
</head>
<body>

<%@ include file="./WEB-INF/navbar.jsp" %>

<div class="container">
    <h1>Chi siamo?</h1>

    <p>Benvenuti sul nostro sito! Siamo tre ragazzi appassionati di tecnologia e innovazione, uniti dalla voglia di creare qualcosa di utile e accessibile. Il nostro viaggio è iniziato con un progetto universitario di tecnologie software per il web, ma presto abbiamo capito che le nostre competenze e la nostra sinergia potevano portarci ben oltre le aule di studio.</p>

    <h2>La Nostra Visione</h2>
    <p>Abbiamo deciso di mettere a frutto le nostre conoscenze tecniche e la nostra passione per creare un sito che potesse rispondere alle esigenze di chi, come noi, ama il fai-da-te, l'edilizia e il giardinaggio. Il nostro obiettivo è offrire una piattaforma dove trovare facilmente prodotti di qualità per la ferramenta, l'edilizia e il giardinaggio, supportata da un'interfaccia semplice e intuitiva.</p>

    <h2>Cosa Offriamo</h2>
    <p>Il nostro sito è pensato per essere un punto di riferimento per professionisti e appassionati. Offriamo una vasta gamma di prodotti accuratamente selezionati per garantirvi sempre il meglio:<br>
        - Ferramenta: Utensili, viti, bulloni e tutto ciò che serve per i tuoi lavori di precisione.<br>
        - Edilizia: Materiali di costruzione, attrezzature e accessori per ogni tipo di progetto edilizio.<br>
        - Giardinaggio: Attrezzi, semi, concimi e tutto il necessario per il tuo angolo verde.
    </p>

    <h2>Perché Sceglierci</h2>
    <p>Scegliere noi significa affidarsi a una squadra giovane, dinamica e motivata. Siamo sempre alla ricerca delle migliori soluzioni per migliorare la vostra esperienza di acquisto, con un occhio di riguardo per la qualità dei prodotti e la soddisfazione dei nostri clienti.</p>

    <h2>La Nostra Storia</h2>
    <p>Siamo partiti da un semplice progetto di tecnologie software e, grazie alla nostra dedizione e alla voglia di metterci in gioco, abbiamo trasformato un’idea in una realtà concreta. Ogni giorno lavoriamo per crescere e migliorare, ascoltando i feedback dei nostri clienti e adattandoci alle nuove sfide che il mercato ci presenta.<br>
        Grazie per averci scelto e per far parte di questa avventura insieme a noi!<br><br>
        I vostri,<br>
        Abeille Alessandro,<br>
        Amorelli Domenico, <br>
        Fontana Christian
    </p>

</div>


<%@ include file="./WEB-INF/footer.jsp" %>

</body>
</html>

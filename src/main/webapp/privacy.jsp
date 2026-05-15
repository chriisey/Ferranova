<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Privacy Ferranova</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footerStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/privacyStyle.css"/>
</head>
<body>

<%@ include file="./WEB-INF/navbar.jsp" %>

<div class="container">
    <h1>Avviso Legale: Gestione della Privacy</h1>

    <h2>Impostazioni di Privacy e Dati Personali</h2>
    <p>Con riferimento alle normative vigenti in materia di protezione dei dati personali, la presente pagina consente all'utente di esaminare e gestire le proprie impostazioni di privacy e i dati personali condivisi con il sito web.</p>

    <h2>Preferenze sui Cookie e Accettazione</h2>
    <p>Si informa l'utente che le preferenze sull'accettazione dei cookie possono essere gestite tramite le impostazioni del browser. L'accettazione dei cookie è soggetta alla normativa vigente in materia di privacy e protezione dei dati personali.</p>

    <h2>Trattamento dei Dati Personali</h2>
    <p>I dati personali dell'utente, inclusi nome ed email, sono trattati nel rispetto della normativa sulla privacy. L'utente ha il diritto di richiedere l'accesso, la rettifica o la cancellazione dei propri dati personali in conformità con le disposizioni di legge applicabili.</p>

    <h2>Preferenze di Marketing e Comunicazioni</h2>
    <p>L'utente può esprimere le proprie preferenze in merito alla ricezione di comunicazioni di marketing e newsletter. Il consenso all'invio di comunicazioni di marketing è facoltativo e può essere revocato in qualsiasi momento.</p>

    <h2>Protezione e Sicurezza dei Dati</h2>
    <p>La sicurezza dei dati personali è una priorità. L'autenticazione a due fattori è disponibile per garantire un livello aggiuntivo di sicurezza nell'accesso all'account dell'utente.</p>

    <p>Si prega di prendere visione delle informazioni fornite e di contattare il responsabile della protezione dei dati per eventuali richieste o chiarimenti in merito alla gestione della privacy e dei dati personali.</p>

</div>


<%@ include file="./WEB-INF/footer.jsp" %>

</body>
</html>

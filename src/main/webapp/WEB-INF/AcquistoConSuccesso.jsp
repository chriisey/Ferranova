<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Acquistato correttamente Ferranova</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/acquistoConSuccessoStyle.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footerStyle.css"/>
</head>
<body>

    <%@ include file="navbar.jsp" %>

<div class="container">
    <div class="acquisto-corretto">
        <img src="${pageContext.request.contextPath}/images/AcquistoConSuccesso.png" alt="Acquisto con successo">
    </div>

    <div>
        <a class="back-home" href="${pageContext.request.contextPath}/index.jsp">Continua ad acquistare</a>
    </div>
</div>

    <%@ include file="footer.jsp" %>

</body>
</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="it">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> <!--per rendere la pagina respobsive-->
    <title>Ferranova</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ferraStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footerStyle.css"/>

</head>

<body>

<%@ include file="/WEB-INF/navbar.jsp" %>


<div class="container">
    <div class="catcontainer">
            <div class="categoria">
                <a href="catalogo?categoria=Ferramenta"><img src="images/Ferramenta.png" alt="ferramenta" width="400" height="400"></a>
            </div>
            <div class="categoria">
                <a href="catalogo?categoria=Edilizia"><img src="images/Edilizia.png" alt="edilizia" width="400" height="400"></a>
            </div>
            <div class="categoria">
                <a href="catalogo?categoria=Giardinaggio"><img src="images/Giardinaggio.png" alt="agricoltura" width="400" height="400"></a>
            </div>
    </div>
</div>


<%@ include file="./WEB-INF/footer.jsp" %>


</body>

</html>
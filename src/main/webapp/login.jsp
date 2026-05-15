<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Ferranova</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/log_regStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>

<body>

<%@ include file="/WEB-INF/navbar.jsp" %>

<div class="wrapper">
    <form action="login" method="POST" onsubmit="return validateForm()">
        <h3>Accedi al sito</h3>

        <div class="login">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" required><br>
            <label for="password">Password</label>
            <div class="password-container">
                <input type="password" id="password" name="password" required><br>
                <button type="button" class="mostraPassword" id="mostraPassword" aria-label="mostraPassword" tabindex="0" onkeydown="togglePassword()" onclick="togglePassword()"><i class="fa-regular fa-eye"></i></button>
            </div>

            <div id="alert" class="alert" style="display:none;"></div>

            <c:if test="${param.error == 1}">
                <div id="alert" class="alert" style="display:block;">Email e/o password errate</div>
                <br>
            </c:if>

            <input type="submit" id="submit" value="Login"/>
        </div>

        <div class="registrazione">
            <p>Sei nuovo su Ferranova?</p>
            <a href="registrazione.jsp">registrati</a>
        </div>

    </form>
</div>

<script src="${pageContext.request.contextPath}/js/viewPassword.js"></script>

</body>
</html>

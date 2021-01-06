<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Se connecter</title>
</head>
<body>
<%@ include file="HeaderSansNavInclusion.jsp" %>

<main>
<form action="Connexion" method="post">
    <div>
        <label for="identifiant">Identifiant :</label>
        <input type="text"  name="identifiant">
    </div>
    <div>
        <label for="mdp">Mot de passe :</label>
        <input type="text" name="mdp">
    </div>
    <input type="checkbox" name="memorisation" checked > Se souvenir de moi
<input type="submit" value="Connexion">
</form>

<a href = "<c:url value = 'PageInscription'/>">Créer un compte</a>

</main>

<%@ include file="FooterInclusion.jsp" %>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Creer un compte</title>
</head>
<body>
<%@ include file="HeaderSansNavInclusion.jsp" %>
<main>
<form action="Inscription" method="post">
    <div>
        <label for="pseudo">Pseudo :</label>
        <input type="text"  name="pseudo">
    </div>
    <div>
        <label for="nom">Nom :</label>
        <input type="text" name="nom">
    </div>
    <div>
        <label for="prenom">Prénom :</label>
        <input type="text" name="prenom">
    </div>
    <div>
        <label for="email">Email :</label>
        <input type="text" name="email">
    </div>  
    <div>
        <label for="telephone">Teléphone :</label>
        <input type="text" name="telephone">
    </div>
    <div>
        <label for="rue">Rue :</label>
        <input type="text" name="rue">
    </div>
    <div>
        <label for="codePostal">Code Postal :</label>
        <input type="text" name="codePostal">
    </div>

    <div>
        <label for="ville">Ville :</label>
        <input type="text" name="ville">
    </div>
    <div>
        <label for="mdp">Mot de passe :</label>
        <input type="text" name="mdp">
    </div>
    <div>
        <label for="confirmation">Confirmation :</label>
        <input type="text" name="confirmation">
    </div>

<input type="submit" value="Créer">
</form>

<form action="Accueil" method="get">
<input type="submit" value="Annuler">
</form>
<c:out value="${ erreur }"></c:out>

</main>

<%@ include file="FooterInclusion.jsp" %>
</body>
</html>
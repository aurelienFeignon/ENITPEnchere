<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/bootstrap-4.4.1.css" rel="stylesheet">
<link href="css/styleProfil.css" rel="stylesheet">
<title>Informations du profil</title>
</head>
<body>
<%@ include file="HeaderSansNavInclusion.jsp" %>
<div class="container">

<h1 class='text-center'>Informations du profil </h1>
<main>

<ul>
<li>Pseudo : <c:out value="${utilisateur.pseudo}" /></li>
<li>Nom : <c:out value="${utilisateur.nom}" /></li>
<li>Prenom : <c:out value="${utilisateur.prenom}" /></li>
<li>Email : <c:out value="${utilisateur.email}" /></li>
<li>Téléphone : <c:out value="${utilisateur.telephone}" /></li>
<li>Rue : <c:out value="${utilisateur.rue}" /></li>
<li>Code postal : <c:out value="${utilisateur.code_postal}" /></li>
<li>Ville : <c:out value="${utilisateur.ville}" /></li>
</ul>



</main>

<c:out value="${message}"></c:out>
</div>
<%@ include file="FooterInclusion.jsp" %>
</body>
<!-- commentaire test -->
</html>
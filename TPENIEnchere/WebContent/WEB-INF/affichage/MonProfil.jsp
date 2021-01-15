<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.9.0/css/all.css">
<link href="css/bootstrap-4.4.1.css" rel="stylesheet">
<link href="css/styles.css" rel="stylesheet">
<link rel="icon" type="image/png" sizes="16x16" href="images/logo.png">
<title>Mon Profil</title>
</head>
<body>
<%@ include file="HeaderInclusion.jsp" %>

<main>
<h1 class="text-center mt-5 mb-5">Informations du profil </h1>

<div class="container">
		<ul class="list-group mt-5 mb-5">
			<li class="list-group-item">Pseudo : <c:out value="${sessionScope.utilisateur.pseudo}" /></li>
			<li class="list-group-item">Nom : <c:out value="${sessionScope.utilisateur.nom}" /></li>
			<li class="list-group-item">Prenom : <c:out value="${sessionScope.utilisateur.prenom}" /></li>
			<li class="list-group-item">Email : <c:out value="${sessionScope.utilisateur.email}"/></li>
			<li class="list-group-item">Téléphone : <c:out value="${sessionScope.utilisateur.telephone}" /></li>
			<li class="list-group-item">Rue : <c:out value="${sessionScope.utilisateur.rue}" /></li>
			<li class="list-group-item">Code postal : <c:out value="${sessionScope.utilisateur.code_postal}" /></li>
			<li class="list-group-item">Ville : <c:out value="${sessionScope.utilisateur.ville}" /></li>
			<li class="list-group-item">Crédit : <c:out value="${utilisateurCredit.credit}" /></li>
		</ul>
</div>
<div class="text-center">
<div class="btn-group" role="group" aria-label="groupe boutons">
<form class="container" action="<c:url value =" ModifierSonProfil "/>" method="get">
		<button type="submit" class="btn btn-primary">Modifier</button>
</form>
<form class="container" action="<c:url value =" VersAchatCredits "/>" method="get">
		<button type="submit" class="btn btn-danger">Achat de crédit</button>
</form>
</div>
</div>
</main>

<%@ include file="FooterInclusion.jsp" %>
<script src="js/jquery-3.4.1.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap-4.4.1.js"></script>
</body>

</html>
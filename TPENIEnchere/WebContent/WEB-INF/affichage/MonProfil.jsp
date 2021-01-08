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
<title>Mon Profil</title>
</head>
<body>
<%@ include file="HeaderInclusion.jsp" %>

<main>
<h1>Informations du profil </h1>

<div class="container">
		<ul class="list-group">
			<li class="list-group-item">Pseudo : <c:out value="${sessionScope.utilisateur.pseudo}" /></li>
			<li class="list-group-item">Nom : <c:out value="${sessionScope.utilisateur.nom}" /></li>
			<li class="list-group-item">Prenom : <c:out value="${sessionScope.utilisateur.prenom}" /></li>
			<li class="list-group-item">Email : <c:out value="${sessionScope.utilisateur.email}"/></li>
			<li class="list-group-item">Téléphone : <c:out value="${sessionScope.utilisateur.telephone}" /></li>
			<li class="list-group-item">Rue : <c:out value="${sessionScope.utilisateur.rue}" /></li>
			<li class="list-group-item">Code postal : <c:out value="${sessionScope.utilisateur.code_postal}" /></li>
			<li class="list-group-item">Ville : <c:out value="${sessionScope.utilisateur.ville}" /></li>
			<li class="list-group-item">Crédit : <c:out value="${sessionScope.utilisateur.credit}" /></li>
		</ul>
</div>
<form class="container" action="ServletVersModificationProfil" method="get">
		<button type="submit" class="btn btn-primary">Modifier</button>
</form>


</main>



<%@ include file="FooterInclusion.jsp" %>
<script src="js/jquery-3.4.1.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap-4.4.1.js"></script>
</body>
<!-- commentaire test -->
</html>
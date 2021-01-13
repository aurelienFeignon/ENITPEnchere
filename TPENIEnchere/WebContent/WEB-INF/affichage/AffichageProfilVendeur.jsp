<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/bootstrap-4.4.1.css" rel="stylesheet">
<link href="css/styles.css" rel="stylesheet">
<title>Informations du profil</title>
<link rel="icon" type="image/png" sizes="16x16" href="images/logo.png">
</head>
<body>
	<%@ include file="HeaderInclusion.jsp"%>

	<main>
		<h1>Informations du profil</h1>
		<div class="container">
			<ul class="list-group mt-5">
				<li class="list-group-item">Pseudo : <c:out
						value="${utilisateur.pseudo}" /></li>
				<li class="list-group-item">Nom : <c:out
						value="${utilisateur.nom}" /></li>
				<li class="list-group-item">Prenom : <c:out
						value="${utilisateur.prenom}" /></li>
				<li class="list-group-item">Email : <c:out
						value="${utilisateur.email}" /></li>
				<li class="list-group-item">Téléphone : <c:out
						value="${utilisateur.telephone}" /></li>
				<li class="list-group-item">Rue : <c:out
						value="${utilisateur.rue}" /></li>
				<li class="list-group-item">Code postal : <c:out
						value="${utilisateur.code_postal}" /></li>
				<li class="list-group-item">Ville : <c:out
						value="${utilisateur.ville}" /></li>
			</ul>
		</div>
	</main>

	<%@ include file="FooterInclusion.jsp"%>
	<script src="js/jquery-3.4.1.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap-4.4.1.js"></script>
</body>

</html>
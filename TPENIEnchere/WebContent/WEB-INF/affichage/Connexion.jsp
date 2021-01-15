<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/bootstrap-4.4.1.css" rel="stylesheet">
<link href="css/signin.css" rel="stylesheet">
<link rel="icon" type="image/png" sizes="16x16" href="images/logo.png">
<title>Se connecter</title>
</head>


<body class="text-center">

	<main class="form-signin">
		<form action="<c:url value =" Connexion "/>" method="post">
			<img class="mb-4" src="images/logo.png" alt="logo eni" width="150" height="150">
			<h1 class="h3 mb-3 fw-normal">connectez vous</h1>
			<label for="identifiant" class="visually-hidden">Identifiant</label>
			<input type="text" name="identifiant" class="form-control" placeholder="Identifiant" value="${ identifiant }" required autofocus> 
			<label for="mdp" class="visually-hidden">Mot de passe</label> 
			<input type="password" name="mdp" class="form-control" placeholder="Mot de passe" value="${ mdp }" required>
			<div class="checkbox mb-3">
				<label> <input type="checkbox" name="memorisation" checked> Se souvenir de moi </label>
			</div>
			<button class="w-100 btn btn-lg btn-primary" type="submit" value="Connexion">Connexion</button>
			<a href="PageNouveauMotDePasse">Mot de passe oublié</a>
			<p class="mt-5 mb-3 text-muted">&copy; ENI-Encheres-2021</p>
		</form>
		
		
		<c:if test="${!empty erreurs}">
			<div class="alert alert-danger row" role="alert">
				<strong>Erreur!</strong>
				<ul>
					<c:forEach var="message" items="${erreurs}">
						<li>${message}</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>
	</main>
<script src="js/jquery-3.4.1.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap-4.4.1.js"></script>
</body>
</html>
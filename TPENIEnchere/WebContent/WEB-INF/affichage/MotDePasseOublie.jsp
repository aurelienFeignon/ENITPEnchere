<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Requete Mot De Passe</title>
<!-- La librairie fontawesome pour les icones, fichier CSS et bootstrap -->
<link rel="stylesheet"	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.9.0/css/all.css">
<link href="css/bootstrap-4.4.1.css" rel="stylesheet">
<link href="css/styles.css" rel="stylesheet">
<link rel="icon" type="image/png" sizes="16x16" href="images/logo.png">
</head>
<body>

	<%@ include file="HeaderInclusion.jsp"%>

	<h1>Envoyer une demande de nouveau mot de passe à l'administrateur</h1>
<div class="d-flex justify-content-center">
<!--  formulaire d'envoie vers une adresse mail -->
	<form action="mailto:administrateur@eni-enchere.test" 
		  enctype="text/plain">
		Pseudo:<br> <input type="text" name="nom" class="form-group col-md-6"><br> 
		Votre adresse Email:<br> <input type="text" name="email" class="form-group col-md-6"><br>
		Nouveau Mot De Passe:<br> <input type="text" name="nouveauMdp" size="50" class="form-group col-md-6"><br>
		<br> <button type="submit" class="btn btn-primary">Envoyer une demande a l'administrateur </button>
			 <button type="reset" class="btn btn-primary">Reinitialiser les champs </button>
	</form>
</div>
	<%@ include file="FooterInclusion.jsp"%>
</body>
</html>
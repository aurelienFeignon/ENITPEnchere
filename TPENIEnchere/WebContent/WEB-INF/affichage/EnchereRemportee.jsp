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
<link href="css/bootstrap-4.4.1.css" rel="stylesheet">
<link rel="icon" type="image/png" sizes="16x16" href="images/logo.png">
<title>Résultat enchère</title>
</head>
<body>
	<main>
		<%@ include file="HeaderInclusion.jsp"%>
		<div class="container">
		
			<c:if test="${!empty enchere }">
				<h1 class='text-center'> <c:out value="${encherisseur.pseudo}" /> a remporté l'enchère </h1>
			</c:if>
			
			<c:if test="${empty enchere}">
				<h1 class='text-center'>La vente est terminée</h1>
			</c:if>
			
			<ul class="list-group mt-5">
				<li class="list-group-item"><c:out value="${article.nom_article}" /></li>
				<li class="list-group-item">Description : <c:out value="${article.description}" /></li>
				<li class="list-group-item">Meilleure offre : <c:if test="${!empty enchere }"> <c:out value="${article.prix_vente}" /> par <c:out value="${encherisseur.pseudo}" /> </c:if> </li>
				<c:if test="${empty enchere}">Il n'y a pas eu d'enchere</c:if>
				<li class="list-group-item">Mise à prix : <c:out value="${article.prix_initial}" /></li>
				<li class="list-group-item">Fin de l'enchère : <c:out value="${article.date_fin_encheres}" /></li>
				<li class="list-group-item">Retrait : 
					<c:out value="${retraits.rue}" /> 
					<c:out value="${retraits.code_postal}" />
					<c:out value="${retraits.ville}" />
			    </li>
				<li class="list-group-item mb-5">Vendeur : <c:out value="${utilisateur.pseudo}" /></li>
			</ul>
		</div>

		<form action="<c:url value =" PageAccueil "/>" method="get" class="text-center">
			<input type="submit" value="Retour" name="retour"
				class="btn btn-primary r">
		</form>

	</main>
	<%@ include file="FooterInclusion.jsp"%>
	<script src="js/jquery-3.4.1.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap-4.4.1.js"></script>
</body>
</html>
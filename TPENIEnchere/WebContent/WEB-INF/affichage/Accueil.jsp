<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Page d'acceuil</title>
<!-- La librairie fontawesome pour les icones, fichier CSS et bootstrap -->
<link rel="stylesheet"	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.9.0/css/all.css">
<link href="css/bootstrap-4.4.1.css" rel="stylesheet">
<link href="css/styles.css" rel="stylesheet">
<link rel="icon" type="image/png" sizes="16x16" href="images/logo.png">
</head>
<body>

	<%@ include file="HeaderInclusion.jsp"%>

	<main>

		<h1>Liste des Enchères</h1>

		<section>
			<h2 class="col-sm-12 d-flex justify-content-center">Filtres</h2>
			<div class="container">
				<form action="Accueil" method="post" novalidate="novalidate">
					<div class="row">
						<div class="col-lg-12">
							<div class="row">
								<div class="col-lg-4 col-md-4 col-sm-12 p-0">
									<input type="text" class="form-control search-slt" name="recherche" placeholder="nom de l'article">
								</div>
								<div class="col-lg-4 col-md-4 col-sm-12 p-0">
									<select class="form-control search-slt"	id="exampleFormControlSelect1" name="categorie">
										<OPTION value="0">Toutes</OPTION>
										<c:forEach var="categories" items="${categories}"> 
											<OPTION value="${categories.no_categorie}">${categories.libelle}</OPTION>
										</c:forEach>
										
									</select>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-12 p-0">
									<button type="submit" class="btn btn-danger wrn-btn">rechercher</button>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</section>

		<!-- Section permettant de selectionner plus finements les filtres -->
		<section class="row d-flex justify-content-center">
			<form id="filtreRechercheFin" action="<c:url value =" AcceuilFiltreRecherche "/>" method="post" class="form-inline my-2 my-lg-0">
				<c:if test="${ !empty sessionScope.utilisateur }">
					<input type="hidden" id="noUtilisateur" name="noUtilisateur" value="${utilisateur.no_utilisateur}">
					<div class="col-sm-6">
						<input type="radio" id="achat" name="menu" value="achat">
						<label for="achat">Achats</label><br> 
						<input type="checkbox" id="encheresOuvertes" name="encheresOuvertes"> 
						<label for="encheresOuvertes">enchères ouvertes</label> 
						<input type="checkbox" id="encheresEnCours" name="encheresEnCours">
						<label for="encheresEnCours">mes enchéres en cours</label> 
						<input type="checkbox" id="encheresRemportees" name="encheresRemportees">
						<label for="encheresRemportees">mes enchères remportées</label>
					</div>
					<div class="col-sm-6">
						<input type="radio" id="vente" name="menu" value="vente"
							<c:if test="${venteChecked}">checked</c:if>> 
							<label for="vente">Mes ventes</label><br> 
							<input type="checkbox" id="ventesEnCours" name="ventesEnCours"> 
							<label for="ventesEnCours">mes ventes en cours</label> 
							<input type="checkbox" id="ventesNonDebutees" name="ventesNonDebutees">
						<label for="ventesNonDebutees">ventes non débutèes</label> 
						<input type="checkbox" id="venteTerminees" name="venteTerminees">
						<label for="venteTerminees">ventes terminées</label>
					</div>
				</c:if>
			</form>
		</section>
		<!-- Section affichant la liste d'article en BDD -->

		<section class="row d-flex justify-content-around">

			<c:forEach items="${listeArticle}" var="article">
				<div class="card col-sm-5">
					<img class="card-img-top"
						src="https://media.giphy.com/media/TKMZAVkrx2OFALkLKf/giphy.gif"
						alt="Image de l'article en vente.">
					<div class="card-body">
					<c:if test="${!empty article.cheminImg}">	
					<img src="<c:url value="/webcontent/${article.cheminImg}"/>" alt="image de l'article">
					</c:if>
						<c:choose>
							<c:when test="${ !empty sessionScope.utilisateur }">
								<h5 class="card-title">
									<a href="<c:url value =" ServletGeneraleAfficherEnchere?idArticle=${article.no_article} "/>">
										<c:out value="${article.nom_article} " />
									</a>
								</h5>
							</c:when>
							<c:otherwise>
								<h5>
									<c:out value="${article.nom_article} " />
								</h5>
							</c:otherwise>
						</c:choose>
						<p class="card-text">
							Prix :
							<c:out value="${article.prix_vente} " />
						</p>
						<fmt:parseDate pattern="yyyy-MM-dd"
							value="${article.date_fin_encheres}" var="DateAffichage" />
						<p class="card-text">
							Fin de l'enchère :
							<fmt:formatDate value="${DateAffichage}" pattern="dd-MM-yyyy" />
						</p>
						<c:choose>
							<c:when test="${ !empty sessionScope.utilisateur }">
								<a href=" <c:url value =" LeProfilVendeur?nombre=${article.no_utilisateur} "/>" class="btn btn-primary">Profil du vendeur</a>
							</c:when>
							<c:otherwise>
								<p class="card-text"></p>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</c:forEach>
			<c:if test="${utilisateur.administrateur}">
				<a href="<c:url value ="Admin"/>" class="float"><i class="fas fa-user-cog my-float"></i></a>
			</c:if>
		</section>
	</main>

	<%@ include file="FooterInclusion.jsp"%>
	<!-- js et bootstrap -->
	<script src="js/jquery-3.4.1.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap-4.4.1.js"></script>
	<script src="js/app.js"></script>
</body>
</html>
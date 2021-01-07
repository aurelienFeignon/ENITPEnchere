<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Page d'acceuil</title>

<!-- La librairie fontawesome pour les icones -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.9.0/css/all.css">
<link href="css/bootstrap-4.4.1.css" rel="stylesheet">
</head>
<body>

<!--Inclue une jsp non declaré dans web.xml à cette endroit de la jsp -->

<%@ include file="HeaderInclusion.jsp" %>

<main>

<h1>Liste des Enchères</h1>

<!-- Section permettant de selectionner les filtres -->

<section>
<h2>Filtres</h2>
<form action="Accueil" method="post">
<div>
        <label for="recherche"> <i class="fas fa-search"></i></label>
        <input type="text" name="recherche" placeholder="Le nom de l'article contient">
</div>
<div>
	<label for="categorie">Categorie : </label>
	<SELECT name="categorie">
	<OPTION>Toutes
	<OPTION>Informatique
	<OPTION>Ameublement
	<OPTION>Vetement
	<OPTION>Sport&Loisirs
	</SELECT>
</div>  
<c:if test="${ !empty sessionScope.utilisateur }">
<div>
	<input type="radio" id="achat" name="menu" value="achat">
	<label for="achat">Achats</label><br>
	<input type="checkbox" id="encheresOuvertes" name="encheresOuvertes">
   <label for="encheresOuvertes">enchères ouvertes</label>
	<input type="checkbox" id="encheresEnCours" name="encheresEnCours">
   <label for="encheresEnCours">mes enchéres en cours</label>
   <input type="checkbox" id="encheresRemportees" name="encheresRemportees">
   <label for="encheresRemportees">mes enchères remportées</label>
</div>
<div>
	<input type="radio" id="vente" name="menu" value="vente">
	<label for="vente">Mes ventes</label><br>  
	<input type="checkbox" id="ventesEnCours" name="ventesEnCours">
   <label for="ventesEnCours">mes ventes en cours</label>
	<input type="checkbox" id="ventesNonDebutees" name="ventesNonDebutees">
   <label for="ventesNonDebutees">ventes non débutèes</label>
   <input type="checkbox" id="venteTerminees" name="venteTerminees">
   <label for="venteTerminees">ventes terminées</label>
</div>	    	 
</c:if>  
<input type="submit" value="Rechercher">
</form>
</section>

<!-- Section affichant la liste d'article en BDD -->

<section>

<c:forEach items="${listeArticle}" var="article">
<div class="card">
<img class="card-img-top" style="height: 50px; width: 50px;" src="https://media.giphy.com/media/TKMZAVkrx2OFALkLKf/giphy.gif" alt="Card image cap">
<div class="card-body">
<h5 class="card-title"><c:out value="${article.nom_article} "/></h5>
<p class="card-text">Prix : <c:out value="${article.prix_initial} "/></p>
<p class="card-text">Fin de l'enchère (a modifier) : <c:out value="${article.date_fin_encheres} "/></p>
<a href="#" class="btn btn-primary">Vendeur</a>
</div>
</div>
</c:forEach>

</section>
</main>

<%@ include file="FooterInclusion.jsp" %>
    <!-- js et bootstrap --> 
<script src="js/jquery-3.4.1.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap-4.4.1.js"></script>
</body>
</html>
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
<!-- La librairie fontawesome pour les icones, fichier CSS et bootstrap -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.9.0/css/all.css">
<link href="css/bootstrap-4.4.1.css" rel="stylesheet">
<link href="css/styles.css" rel="stylesheet">
</head>
<body>

<!--Inclue une jsp non declaré dans web.xml à cette endroit de la jsp -->

<%@ include file="HeaderInclusion.jsp" %>

<main>

<h1 class"row">Liste des Enchères</h1>

<section >
    <div class="container">
        <form action="Accueil" method="post" novalidate="novalidate">
            <div class="row">
                <div class="col-lg-12">
                    <div class="row">
                        <div class="col-lg-4 col-md-4 col-sm-12 p-0">
                            <input type="text" class="form-control search-slt" name="recherche" placeholder="nom de l'article">
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-12 p-0">
                            <select class="form-control search-slt" id="exampleFormControlSelect1" name="categorie">
                                <OPTION value="0">Toutes</OPTION>
								<OPTION value="1">Informatique</OPTION>
								<OPTION value="2">Ameublement</OPTION>
								<OPTION value="3">Vetement</OPTION>
								<OPTION value="4">Sport&Loisirs</OPTION>
	
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

<!-- Section permettant de selectionner les filtres -->


<section class="row d-flex justify-content-center">

<h2 class="col-sm-12 d-flex justify-content-center">Filtres</h2>
<form action="Accueil" method="post" class="form-inline my-2 my-lg-0">
<div>
        <label for="recherche"> <i class="fas fa-search"></i></label>
        <input class="form-control mr-sm-3" aria-label="Search" type="text" name="recherche" placeholder="Le nom de l'article contient">
</div>
<div class="mr-sm-3">
	<label for="categorie">Categorie : </label>
	<SELECT name="categorie">
	<OPTION value="0">Toutes
	<OPTION value="1">Informatique
	<OPTION value="2">Ameublement
	<OPTION value="3">Vetement
	<OPTION value="4">Sport&Loisirs
	</SELECT>
</div> 


<input class="btn btn-outline-success my-2 my-sm-2 col-sm-5" type="submit" value="Rechercher">
</form>
<form action="#" method="post" class="form-inline my-2 my-lg-0">
<c:if test="${ !empty sessionScope.utilisateur }">
<div class="col-sm-6" >
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
</form>
</section>

<!-- Section affichant la liste d'article en BDD -->

<section class="row d-flex justify-content-around">

<c:forEach items="${listeArticle}" var="article">
<div class="card col-sm-5">
<img class="card-img-top" src="https://media.giphy.com/media/TKMZAVkrx2OFALkLKf/giphy.gif" alt="Image de l'article en vente.">
<div class="card-body">
<h5 class="card-title"><c:out value="${article.nom_article} "/></h5>
<p class="card-text">Prix : <c:out value="${article.prix_initial} "/></p>
<p class="card-text">Fin de l'enchère (à modifier) : <c:out value="${article.date_fin_encheres} "/></p>
<c:choose>
 <c:when test="${ !empty sessionScope.utilisateur }">
<a href="#" class="btn btn-primary">Vendeur : <c:out value="${article.no_utilisateur} "/></a>
 </c:when>
<c:otherwise>
<p class="card-text"> Vendeur : <c:out value="${article.no_utilisateur} "/></p>
</c:otherwise>
 </c:choose>

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
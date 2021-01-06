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
<form action="#" method="post">
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
<input type="submit" value="Rechercher">
</form>
</section>

<!-- Section affichant la liste d'article en BDD -->

<section>


</section>
</main>

<%@ include file="FooterInclusion.jsp" %>
    <!-- js et bootstrap --> 
<script src="js/jquery-3.4.1.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap-4.4.1.js"></script>
</body>
</html>
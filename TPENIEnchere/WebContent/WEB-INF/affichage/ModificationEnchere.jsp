+<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<title>Modifier une vente</title>
<!-- La librairie fontawesome pour les icones, fichier CSS et bootstrap -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.9.0/css/all.css">
<link href="css/bootstrap-4.4.1.css" rel="stylesheet">
<link rel="icon" type="image/png" sizes="16x16" href="images/logo.png">
</head>
<body>
<%@ include file="HeaderInclusion.jsp" %>
<main class="justify-content-center">
<div class="col-md-6">
<img alt="Photo de l'article mis en vente." src="https://media.giphy.com/media/Zcc3ZeeZ5ztdw1oNSB/giphy.gif">
</div>
<div class="col-md-6">
<form action="<c:url value =" ServletModifierEnchere "/>" method="post">
	  <div class="form-row">
	    <div class="form-group col-md-6">
	      <label for="article">Article</label>
	      <input type="text" class="form-control" name="article" value="${article.nom_article}">
	    </div>
     </div>
	  <div class="form-row">
	    <div class="form-group col-md-6">
	      <label for="description">Description</label>
	      <textarea id="description" name="description" rows="4" cols="60">${article.description}</textarea>
	    </div>
     </div>
     <div class="mr-sm-3">
	<label for="categorie">Categorie : </label>
	<SELECT name="categorie">
	<OPTION value="1" ${article.no_categorie =='1'? 'selected="true"' : '' }>Informatique </OPTION>
	<OPTION value="2" ${article.no_categorie =='2'? 'selected="true"' : '' } >Ameublement </OPTION>
	<OPTION value="3" ${article.no_categorie =='3'? 'selected="true"' : '' }>Vetement </OPTION>
	<OPTION value="4" ${article.no_categorie =='4'? 'selected="true"' : '' }>Sport&Loisirs </OPTION>
	</SELECT>
	</div>  
	 <div class="form-row">
	    <div class="form-group col-md-6">
	      <label for="photo">Photo de l'article</label>
	      <input type="text" class="form-control" name="photo" value="uploader">
	    </div>
     </div>
  		 <div class="form-row">
	    <div class="form-group col-md-6">
	      <label for="photo">Mise à prix</label>
	      <input type="number" id="prixInitial" name="prixInitial" min="0" value="${article.prix_initial}">
	    </div>
     </div>
     <div class="control-group">
        <label class="control-label input-label" for="debutEnchere">Début de l'enchère</label>
        <div>
            <input type="date" id="debutEnchere" name="debutEnchere" value="${article.date_debut_encheres}" required/>
            <i class="icon-time"></i>
        </div>
    </div>
     <div class="control-group">
        <label class="control-label input-label" for="finEnchere">Fin de l'enchère</label>
        <div>
            <input type="date" id="finEnchere" name="finEnchere" value="${article.date_fin_encheres}" required/>
            <i class="icon-time"></i>
        </div>
    </div>
<fieldset>
    <legend>Retrait</legend>
 	<div class="form-row">
	    <div class="form-group col-md-6">
	      <label for="rue">Rue</label>
	      <input type="text" class="form-control" name="rue" value="${ retraits.rue }">
	    </div>
     </div>
     <div class="form-row">
	    <div class="form-group col-md-6">
	      <label for="codePostal">Code postal</label>
	      <input type="text" class="form-control" name="codePostal" value="${ retraits.code_postal }">
	    </div>
     </div>
          <div class="form-row">
	    <div class="form-group col-md-6">
	      <label for="ville">Ville</label>
	      <input type="text" class="form-control" name="ville" value="${ retraits.ville }">
	    </div>
     </div>
     <input type="hidden" name="idArticle" value="${article.no_article}">
</fieldset>
     <div class="form-row align-self-center">
     <input type="hidden" name="numeroUtilisateur" value="${ utilisateur.no_utilisateur }">
		  <button type="submit" class="btn btn-primary">Enregistrer</button>
		  <button type="reset" class="btn btn-primary">Annuler</button>
	   </div>
</form>
</div>
</main>
<%@ include file="FooterInclusion.jsp" %>
    <!-- js et bootstrap --> 
<script src="js/jquery-3.4.1.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap-4.4.1.js"></script>
</body>
</html>
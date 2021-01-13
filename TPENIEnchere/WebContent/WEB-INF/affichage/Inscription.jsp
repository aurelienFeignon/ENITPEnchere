<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Creer un compte</title>
</head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/bootstrap-4.4.1.css" rel="stylesheet">
<link rel="icon" type="image/png" sizes="16x16" href="images/logo.png">
<title>Creer un compte</title>
</head>
<body>
<%@ include file="HeaderInclusion.jsp" %>
<form action="<c:url value =" Inscription "/>" method="post">
	  <div class="form-row">
	    <div class="form-group col-md-6">
	      <label for="pseudo">Pseudo</label>
	      <input type="text" class="form-control" name="pseudo" placeholder="Pseudo">
	    </div>
	    <div class="form-group col-md-6">
	      <label for="email">Email</label>
	      <input type="email" class="form-control" name="email" placeholder="Email">
	    </div>
	   </div>
	   <div class="form-row">
	    <div class="form-group col-md-6">
	      <label for="mdp">Mot de passe</label>
	      <input type="text" class="form-control" name="mdp" placeholder="Mot de passe">
	    </div>
	     <div class="form-group col-md-6">
		   <label for="confirmation">Confirmation</label>
		     <input type="text" class="form-control" name="confirmation" placeholder="Mot de passe">
		  </div>
	   </div>
	   <div class="form-row">
		  <div class="form-group col-md-6">
		    <label for="prenom">Prénom</label>
		    <input type="text" class="form-control" name="prenom" placeholder="Prénom">
		  </div>
		
		  <div class="form-group col-md-6">
		    <label for="nom">Nom</label>
		    <input type="text" class="form-control" name="nom" placeholder="Nom">
		  </div>
		  <div class="form-group col-md-6">
		    <label for="telephone">Telephone</label>
		    <input type="text" class="form-control" name="telephone" placeholder="Telephone">
		  </div>
		  <div class="form-group col-md-6">
		      <label for="rue">Rue</label>
		      <input type="text" class="form-control" name="rue" placeholder="Rue">
	    </div>
		</div>
	  <div class="form-row">
	    
	    <div class="form-group col-md-6">
	      <label for="codePostal">Code Postal</label>
	      <input type="text" class="form-control" name="codePostal" placeholder="Code Postal">
	    </div>
	    <div class="form-group col-md-6">
	      <label for="ville">Ville</label>
	      <input type="text" class="form-control" name="ville" placeholder="Ville">
	    </div>
	    </div>
	  <div class="form-group form-row">
	    <div class="form-check">
	      <input class="form-check-input" type="checkbox" id="gridCheck">
	      <label class="form-check-label" for="gridCheck">
	        Se souvenir de moi
	      </label>
	    </div>
	   </div>
	  
	  <div class="form-row align-self-center">
		  <button type="submit" class="btn btn-primary">Créer</button>
		  <button type="reset" class="btn btn-primary">Annuler</button>
	   </div>
	 
</form>

<div class="">
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
</div>
	
<%@ include file="FooterInclusion.jsp" %>
<script src="js/jquery-3.4.1.min.js"></script>
  <script src="js/popper.min.js"></script>
  <script src="js/bootstrap-4.4.1.js"></script>
</body>
</html>
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
<link href="css/styles.css" rel="stylesheet">
<link rel="icon" type="image/png" sizes="16x16" href="images/logo.png">
<title>Mon profil</title>
</head>
<body>
<%@ include file="HeaderInclusion.jsp" %>
<div class="container">
<h1 class='text-center mt-5 mb-5'>Mon Profil</h1>
<main>

<form action="<c:url value =" modificationProfil "/>" method="post" class="mt-5">

<div class="form-row mt-5">
    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
        <label for="pseudo">Pseudo : </label>
        <input type="text" class="form-control col-auto" name="pseudo" value="${sessionScope.utilisateur.pseudo}">
    </div>
        <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
        <label for="nom">Nom : </label>
        <input type="text" class="form-control col-auto" name="nom" value="${sessionScope.utilisateur.nom}">
    </div>
 </div>
    <br/>
 <div class="form-row">
    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
        <label for="prenom">Prénom : </label>
        <input type="text" class="form-control col-auto" name="prenom" value="${sessionScope.utilisateur.prenom}">
    </div>
    <br/>
            <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
        <label for="email">Email : </label>
        <input type="text" class="form-control col-auto" name="email" value="${sessionScope.utilisateur.email}">
    </div> 
 </div>
       <br/>
        
 <div class="form-row">
    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
        <label for="telephone">Teléphone : </label>
        <input type="text" class="form-control col-auto" name="telephone" value="${sessionScope.utilisateur.telephone}">
    </div>
    <br/>
           <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
        <label for="rue">Rue : </label>
        <input type="text" class="form-control col-auto" name="rue" value="${sessionScope.utilisateur.rue}">
    </div>
 </div>
    <br/>
    
 <div class="form-row">
    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
        <label for="codePostal">Code Postal : </label>
        <input type="text" class="form-control col-auto" name="codePostal" value="${sessionScope.utilisateur.code_postal}">
    </div>
    <br/>
        <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
        <label for="ville">Ville : </label>
        <input type="text" class="form-control col-auto" name="ville" value="${sessionScope.utilisateur.ville}">
    </div>
 </div>
  <br/>
    
  <div class="form-row">
    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
        <label for="mdp">Mot de passe actuel : </label>
        <input type="text" class="form-control col-auto" name="mdp" value="${sessionScope.utilisateur.mot_de_passe}">
    </div>
    
 </div>
 <br/>
    
  <div class="form-row">
      <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
        <label for="newMdp">Nouveau mot de passe : </label>
        <input type="text" class="form-control col-auto" name="newMdp">
    </div>
   
    
     <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
    <label for="confirmation">Confirmation : </label>
    <input type="text" class="form-control col-auto" name="confirmation">
     </div> 
     
  </div>
  <br/> 
     


<div>
 <br/>Crédit : ${sessionScope.utilisateur.credit} <input type="hidden" name="credit" value="${sessionScope.utilisateur.credit}">
 
  <input type="hidden" name="id" value="${sessionScope.utilisateur.no_utilisateur}">
        <br/>
</div>  

<!-- Bouton enregistrer modifications -->

<div class="text-center">
<div class="btn-group" role="group" aria-label="groupe boutons">
         <div>
        <input type="submit" value="Enregistrer" class="btn btn-success mr-3">
        </div> 
</form>

<!-- Bouton supprimer profil avec id caché -->
<form action="<c:url value =" suppressionProfil "/>" method="post">
<input type="submit"  value="Supprimer" class="btn btn-danger">
</form>
</div>
</div>

</main>
</div>

<%@ include file="FooterInclusion.jsp" %>
<script src="js/jquery-3.4.1.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap-4.4.1.js"></script>
</body>

</html>
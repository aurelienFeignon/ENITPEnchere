<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mon profil</title>
</head>
<body>
<%@ include file="HeaderSansNavInclusion.jsp" %>
<main>
<h1>Mon Profil</h1>
<form action="http://localhost:8080/TPPersoEnchereV0.2/Servlet/ServletModificationProfil" method="post">


    <div>
        <label for="pseudo">Pseudo :</label>
        <input type="text"  name="pseudo" value="${sessionScope.utilisateur.pseudo}">
    </div>
        <div>
        <label for="nom">Nom :</label>
        <input type="text" name="nom" value="${sessionScope.utilisateur.nom}">>
    </div>
    <div>
        <label for="prenom">Prénom :</label>
        <input type="text" name="prenom" value="${sessionScope.utilisateur.prenom}">>
    </div>
            <div>
        <label for="email">Email :</label>
        <input type="text" name="email" value="${sessionScope.utilisateur.email}">>
    </div> 
    <div>
        <label for="telephone">Teléphone :</label>
        <input type="text" name="telephone" value="${sessionScope.utilisateur.telephone}">>
    </div>
           <div>
        <label for="rue">Rue :</label>
        <input type="text" name="rue" value="${sessionScope.utilisateur.rue}">>
    </div>
    <div>
        <label for="codePostal">Code Postal :</label>
        <input type="text" name="codePostal" value="${sessionScope.utilisateur.codePostal}">>
    </div>
        <div>
        <label for="ville">Ville :</label>
        <input type="text" name="ville" value="${sessionScope.utilisateur.ville}">>
    </div>
    <div>
        <label for="mdp">Mot de passe actuel :</label>
        <input type="text" name="mdp" value="${sessionScope.utilisateur.mdp}">>
    </div>
      <div>
        <label for="mdp">Nouveau mot de passe :</label>
        <input type="text" name="newMdp">
    </div>
    <div>
    <label for="confirmation">Confirmation :</label>
    <input type="text" name="confirmation">
     </div>  

<div>
 <br/>Crédit : ${sessionScope.utilisateur.mdp}
        <br/>
</div>    
         <div>
        <input type="submit" value="Enregistrer">
        </div> 
</form>

<form action="http://localhost:8080/TPPersoEnchereV0.2/Servlet/ServletSuppressionProfil" method="get">
<input type="submit" value="Supprimer">
</form>


</main>
<%@ include file="FooterInclusion.jsp" %>
</body>
</html>
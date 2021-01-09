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
<title>Détail de la vente</title>
</head>
<body>
<%@ include file="HeaderSansNavInclusion.jsp" %>

<main>
<div class="container">
<h1 class='text-center'>Détail de la vente</h1>
<ul class="list-group mt-5">
<li class="list-group-item"><c:out value="${article.nomArticle}" /></li>
<li class="list-group-item">Description : <c:out value="${article.description}" /></li>
<li class="list-group-item">Catégorie: <c:out value="${categories.libelle}" /></li> 
<li class="list-group-item">Meilleure offre : <c:out value="${encheres.montant_enchere}"/> par <c:out value="${encherisseur.pseudo}"/></li> 
<li class="list-group-item">Mise à prix : <c:out value="${article.miseAPrix}" /></li>
<li class="list-group-item">Fin de l'enchère : <c:out value="${article.dateFinEncheres}" /></li>
<li class="list-group-item">Retrait : <c:out value="${retraits.rue}" /> <c:out value="${retraits.code_postal}" /> <c:out value="${retraits.ville}" /></li>
<li class="list-group-item mb-5">Vendeur : <c:out value="${utilisateur.pseudo}" /></li> 


<form action="ServletEncherir" method="post" class="text-center">
<label for="proposition">Ma proposition :</label>
<input type="number" name="proposition" id="proposition" value="${montantMin}" max="${sessionScope.utilisateur.credit}" min="${montantMin}" class="col-2 ml-4">
<input type="hidden" name="credit" value="${sessionScope.utilisateur.credit}">
<input type="submit" value="Enchérir" class="btn btn-primary ml-3 ">
</form>

</ul>
</div>
</main>

<%@ include file="FooterInclusion.jsp" %>
<script src="js/jquery-3.4.1.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap-4.4.1.js"></script>
</body>
</html>
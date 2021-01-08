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
<link href="css/styleProfil.css" rel="stylesheet">
<title>Résultat enchère</title>
</head>
<body>
<%@ include file="HeaderSansNavInclusion.jsp" %>
<div class="container">
<h1 class='text-center'><c:out value="${encherisseur.pseudo}" /> a remporté l'enchère</h1>
<main>
<ul>
<li><c:out value="${article.nomArticle}" /></li>
<li>Description : <c:out value="${article.description}" /></li>
<li>Meilleure offre : <c:out value="${article.prixVente}" /> <c:out  "${encherisseur.pseudo}" />
<li>Mise à prix : <c:out value="${article.miseAPrix}" /></li>
<li>Fin de l'enchère : <c:out value="${article.dateFinEncheres}" /></li>
<li>Retrait : <c:out value="${retraits.rue}" /> <c:out value="${retraits.code_postal}" /> <c:out value="${retraits.ville}" /></li>
<li>Vendeur : <c:out value="${utilisateur.pseudo}" /></li>

</main>
<%@ include file="FooterInclusion.jsp" %>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mon Profil</title>
</head>
<body>
<%@ include file="HeaderSansNavInclusion.jsp" %>

<main>
<h1>Informations du profil </h1>

<ul>
<li>Pseudo : <c:out value="${sessionScope.utilisateur.pseudo}" /></li>
<li>Nom : <c:out value="${sessionScope.utilisateur.nom}" /></li>
<li>Prenom : <c:out value="${sessionScope.utilisateur.prenom}" /></li>
<li>Email : <c:out value="${sessionScope.utilisateur.email}"/></li>
<li>Téléphone : <c:out value="${sessionScope.utilisateur.telephone}" /></li>
<li>Rue : <c:out value="${sessionScope.utilisateur.rue}" /></li>
<li>Code postal : <c:out value="${sessionScope.utilisateur.code_postal}" /></li>
<li>Ville : <c:out value="${sessionScope.utilisateur.ville}" /></li>
<li>Crédit : <c:out value="${sessionScope.utilisateur.credit}" /></li>
</ul>


<form action="ServletVersModificationProfil" method="get">
<input type="submit" value="Modifier">
</form>

</main>



<%@ include file="FooterInclusion.jsp" %>
</body>
<!-- commentaire test -->
</html>
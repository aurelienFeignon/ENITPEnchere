<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Informations du profil</title>
</head>
<body>
<%@ include file="HeaderSansNavInclusion.jsp" %>

<main>
<h1>Informations du profil </h1>

<ul>
<li>Pseudo : <c:out value="${utilisateur.pseudo}" /></li>
<li>Nom : <c:out value="${utilisateur.nom}" /></li>
<li>Prenom : <c:out value="${utilisateur.prenom}" /></li>
<li>Email : <c:out value="${utilisateur.prenom}" /></li>
<li>Téléphone : <c:out value="${utilisateur.prenom}" /></li>
<li>Rue : <c:out value="${utilisateur.prenom}" /></li>
<li>Code postal : <c:out value="${utilisateur.prenom}" /></li>
<li>Ville : <c:out value="${utilisateur.prenom}" /></li>
</ul>



</main>

<c:out value="${message}"></c:out>

<%@ include file="FooterInclusion.jsp" %>
</body>
</html>
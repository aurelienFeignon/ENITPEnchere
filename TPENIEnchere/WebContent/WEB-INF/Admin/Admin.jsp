<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page Administrateur</title>
<link href="css/bootstrap-4.4.1.css" rel="stylesheet">
<link href="css/styles.css" rel="stylesheet">
<link rel="icon" type="image/png" sizes="16x16" href="images/logo.png">
</head>

<body>
	<%@ include file="../affichage/HeaderInclusion.jsp"%>
	<h1> 1) Supprimer des comptes d'utilisateurs</h1>
	<form action="Admin" method="post">
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th scope="col">Ligne</th>
					<th scope="col">Pseudo</th>
					<th scope="col">Nom</th>
					<th scope="col">Prenom</th>
					<th scope="col">Email</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listeUtilisateur}" var="autreUtilisateur">
					<c:set var="count" value="${count + 1}" scope="page" />
					<tr>
						<th scope="row"><c:out value="${count}" /></th>
						<td>${autreUtilisateur.pseudo }</td>
						<td>${autreUtilisateur.nom }</td>
						<td>${autreUtilisateur.prenom }</td>
						<td>${autreUtilisateur.email }</td>
						<td>
							<button type="submit" class="btn btn-warning"
									value="${autreUtilisateur.no_utilisateur }" name="autreUtiliasteurId">Supprimer</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>
	
	<h1> 2) Ajouter des catégories</h1>
	
	<form action="Administrateur" method="post">
		<div class="form-group col-md-6">
		      <label for="rue">Nom de la catégorie</label>
		      <input type="text" class="form-control" name="cat" placeholder="catégorie">
	    </div>
	    <button type="submit" class="btn btn-primary">Créer une catégorie</button>
	</form>
	
	<%@ include file="../affichage/FooterInclusion.jsp"%>
	
</body>

</html>
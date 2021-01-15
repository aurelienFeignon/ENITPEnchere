<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page Administrateur</title>
</head>

<body>

	<table class="table">
		<thead>
			<tr>
				<th scope="col">Pseudo</th>
				<th scope="col">Nom</th>
				<th scope="col">Prenom</th>
				<th scope="col">Email</th>
				<th scope="col">Action</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${listeUtilisateur}" var="autreUtilisateur">	
				<tr>
					<th scope="row">1</th>
					<td>${autreUtilisateur.pseudo }</td>
					<td>${autreUtilisateur.nom }</td>
					<td>${autreUtilisateur.prenom }</td>
					<td>${autreUtilisateur.email }</td>
					<td> <button type="button" class="btn btn-warning">Warning</button> </td>
				</tr>	
		</c:forEach>
		</tbody>
	</table>

</body>

</html>
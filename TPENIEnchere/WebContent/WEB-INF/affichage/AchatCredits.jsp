<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/bootstrap-4.4.1.css" rel="stylesheet">
<link href="css/styles.css" rel="stylesheet">
<title>Achat de crédits</title>
</head>
<body>
<%@ include file="HeaderInclusion.jsp"%>
<h1>Achat de crédits</h1>
<main>
		<div class="container">
		<p class="mt-5 text-center">1 crédit = 1 euro</p>
		<p class="mt-5 text-center">Votre montant actuel de crédits s'élève à <c:out value="${utilisateur.credit}"></c:out></p>
		<form action="AcheterCredits" method="post" class="mt-5">
		<div class="form-row mt-5 text-center">
    	<div class="form-group col-12">
		<label for="credits">Quantité de crédits à acheter </label>
		<input type="number" name="credits"  id="credits">
</div>
</div>	
		
		<div class="text-center mt-5">
		<input type="hidden" name="id" value="${utilisateur.no_utilisateur}"> 
		<input type="submit" value="Acheter" class="btn btn-danger" >
		</div>
		</form>

</div>
</main>
	<%@ include file="FooterInclusion.jsp"%>
	<script src="js/jquery-3.4.1.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap-4.4.1.js"></script>
</body>
</html>
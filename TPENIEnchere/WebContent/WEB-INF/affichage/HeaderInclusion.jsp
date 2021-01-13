<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<header>

 <!--Verifier si l'utilisateur est connecté -->
      <c:choose>
      
       <c:when test="${ !empty sessionScope.utilisateur }">
		 <nav class="navbar navbar-expand-lg navbar-light bg-light">
				<a href="<c:url value =" Accueil "/>"><img src="images/logo.png" width="30" height="30" alt="logo eni"></a>
			  	<a class="navbar-brand" href="Accueil">ENI-Encheres</a>
			  	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			    	<span class="navbar-toggler-icon"></span>
			  	</button>
			
			  <div class="collapse navbar-collapse" id="navbarSupportedContent">
			    <ul class="navbar-nav mr-auto">
				      <li class="nav-item active">
				       	<a class="nav-link" href="<c:url value =" Accueil "/>">Accueil <span class="sr-only">(current)</span></a>
				      </li>
				      <li class="nav-item">
				        <a class="nav-link" href="<c:url value =" Vente "/>">Vendre un article</a>
				      </li>
				      <li class="nav-item">
				        <a class="nav-link" href="<c:url value =" monProfil "/>">Mon profil</a>
				      </li>
						<li class="nav-item">
				        <a class="nav-link" href="<c:url value =" Deconnexion "/>">Déconnexion</a>
				      </li>
				    </ul>
				    <p> ${ sessionScope.utilisateur.pseudo } est connecté(e) </p>
				    <c:out value="${ erreur }"></c:out> <c:out value="${ réussite }"></c:out> <c:out value="${ réussiteMdp }"></c:out>
			  </div>
		</nav>
       
       </c:when>
       
       <c:otherwise>
       <nav class="navbar navbar-expand-lg navbar-light bg-light">
				<img src="images/logo.png" width="30" height="30" alt="logo eni">
			  	<a class="navbar-brand" href="Accueil">ENI-Encheres</a>
			  	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			    	<span class="navbar-toggler-icon"></span>
			  	</button>
			  	 <div class="collapse navbar-collapse" id="navbarSupportedContent">
				    <ul class="navbar-nav mr-auto">
					      <li class="nav-item active">
					       	<a class="nav-link" href="<c:url value =" Accueil "/>">Accueil <span class="sr-only">(current)</span></a>
					      </li>
					   	  <li class="nav-item">
					   	  	<a class="nav-link"  href = "<c:url value = 'Connexion'/>">Se connecter</a>
					   	  </li>  
					   	  <li class="nav-item">
					   	  	<a class="nav-link"  href = "<c:url value = 'PageInscription'/>">S'inscrire</a>
					   	  </li>  
	       			</ul>
       			</div>
       </nav>
       </c:otherwise>
       
       </c:choose>
</header>
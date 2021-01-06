<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<header>

 <!--Verifier si l'utilisateur est connecté -->
      <c:choose>
      
       <c:when test="${ !empty sessionScope.identifiant }">
		 <nav class="navbar navbar-expand-lg navbar-light bg-light">
				<a href="Accueil"><img src="images/logo.jpg" width="30" height="30" alt="logo eni"></a>
			  	<a class="navbar-brand" href="#">ENI-Encheres</a>
			  	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			    	<span class="navbar-toggler-icon"></span>
			  	</button>
			
			  <div class="collapse navbar-collapse" id="navbarSupportedContent">
			    <ul class="navbar-nav mr-auto">
				      <li class="nav-item active">
				       	<a class="nav-link" href="#">Accueil <span class="sr-only">(current)</span></a>
				      </li>
				      <li class="nav-item">
				        <a class="nav-link" href="#">Enchères</a>
				      </li>
				      <li class="nav-item">
				        <a class="nav-link" href="#">Mon profil</a>
				      </li>
						<li class="nav-item">
				        <a class="nav-link" href="Deconnexion">Déconnexion</a>
				      </li>
				    </ul>
				    <p> ${ sessionScope.identifiant } est connecté(e) </p>
				    <form class="form-inline my-2 my-lg-0">
					      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
					      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
				    </form>
			  </div>
		</nav>
       
       </c:when>
       
       <c:otherwise>
       <nav class="navbar navbar-expand-lg navbar-light bg-light">
				<img src="images/logo.jpg" width="30" height="30" alt="logo eni">
			  	<a class="navbar-brand" href="#">ENI-Encheres</a>
			  	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			    	<span class="navbar-toggler-icon"></span>
			  	</button>
			  	 <div class="collapse navbar-collapse" id="navbarSupportedContent">
				    <ul class="navbar-nav mr-auto">
					      <li class="nav-item active">
					       	<a class="nav-link" href="Accueil">Accueil <span class="sr-only">(current)</span></a>
					      </li>
					   	  <li class="nav-item">
					   	  	<a class="nav-link"  href = "<c:url value = 'PageConnexion'/>">Se connecter</a>
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
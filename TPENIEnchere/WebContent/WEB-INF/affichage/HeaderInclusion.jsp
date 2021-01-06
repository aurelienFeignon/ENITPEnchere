<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<header>
<img alt="Logo du site ENI-Encheres" src="images/logo.jpg">

 <!--Verifier si l'utilisateur est connecté -->
      <c:choose>
      
       <c:when test="${ !empty sessionScope.utilisateur }">
        <nav>
            <ul>
                <li><a href="#">Enchères</a></li>
                <li><a href="#">Vendre un article</a></li>
                <li><a href="#">Mon profil</a></li>
                <li><a href="Deconnexion">Déconnexion</a></li>
            </ul>
        </nav>
       	 <p> ${ sessionScope.utilisateur.pseudo } est connecté(e) </p>
       </c:when>
       
       <c:otherwise>
       <nav>
       <a href = "<c:url value = 'PageConnexion'/>">S'inscrire- Se connecter</a>
       </nav>
       </c:otherwise>
       
       </c:choose>
</header>
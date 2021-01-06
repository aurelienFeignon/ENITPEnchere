package src.fr.eni.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Session;

import fr.eni.bll.UtilisateurManageur;
import fr.eni.bo.Utilisateur;

//UTILISATION : Récupère les infos du vendeur en fonction de l'id (récupéré via un clic sur le pseudo depuis l'accueil).
// TO DO : Manque l'obtention de l'id depuis l'accueil + tests

@WebServlet("/ServletAffichageProfilVendeur")
public class ServletAffichageProfilVendeur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id;
		// obtention id via jsp accueil - A FAIRE
		
		
		
		//recupère les infos de l'utilisateur
		 Utilisateur utilisateur = new Utilisateur();
		 UtilisateurManageur utilisateurManageur = new UtilisateurManageur();
		utilisateur = utilisateurManageur.select(id);
		request.setAttribute("utilisateur", utilisateur);
		
		//envoi vers la page de profil
		RequestDispatcher rd = request.getRequestDispatcher("/PageProfil");
				rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}

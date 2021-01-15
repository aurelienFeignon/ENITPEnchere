package fr.eni.servlets;

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
import fr.eni.utils.BusinessException;



@WebServlet("/LeProfilVendeur")
public class ServletAffichageProfilVendeur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// obtention id via url depuis l'accueil
		
		int idUser = Integer.parseInt(request.getParameter("nombre"));
		
		//recupère les infos de l'utilisateur
		 Utilisateur utilisateur = new Utilisateur();
		 UtilisateurManageur utilisateurManageur = new UtilisateurManageur();
		try {
			utilisateur = utilisateurManageur.selectId(idUser);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("utilisateur", utilisateur);
		
		//envoi vers la page de profil
		RequestDispatcher rd = request.getRequestDispatcher("/PageProfil");
				rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}

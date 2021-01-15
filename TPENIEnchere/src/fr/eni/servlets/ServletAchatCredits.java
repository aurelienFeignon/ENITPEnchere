package fr.eni.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.bll.UtilisateurManageur;
import fr.eni.bo.Utilisateur;
import fr.eni.utils.BusinessException;


@WebServlet("/AcheterCredits")
public class ServletAchatCredits extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ajout de crédits à l'user
		int idUser = Integer.parseInt(request.getParameter("id"));
		double credit = Double.parseDouble(request.getParameter("credits"));
		
		UtilisateurManageur utilisateurManageur = new UtilisateurManageur();
		Utilisateur utilisateur = new Utilisateur();
		
		try {
			utilisateur = utilisateurManageur.selectId(idUser);
		} catch (BusinessException e1) {
			e1.printStackTrace();
		}
		try {
			utilisateurManageur.AjouterCredit(utilisateur, (int) credit);
		} catch (BusinessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//On deconnecte et reconnecte avec nouvelle modif de compte
	    HttpSession session = request.getSession();
		session.setAttribute("utilisateur", utilisateur);
	//retour sur la page d'accueil + message de réussite
	    String message ="Ajout de crédit réussi";
		request.setAttribute("réussite", message);
		this.getServletContext().getRequestDispatcher("/PageAccueil").forward(request, response);
	}
	


}

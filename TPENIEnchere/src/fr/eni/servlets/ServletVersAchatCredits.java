package fr.eni.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import fr.eni.bll.UtilisateurManageur;
import fr.eni.bo.Utilisateur;
import fr.eni.utils.BusinessException;


@WebServlet("/VersAchatCredits")
public class ServletVersAchatCredits extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		int utilisateurId = Integer.parseInt(request.getSession(false).getAttribute("utilisateurId").toString());
		
		Utilisateur utilisateur = new Utilisateur();
		UtilisateurManageur utilisateurManageur = new UtilisateurManageur();
		try {
			utilisateur = utilisateurManageur.selectId(utilisateurId);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
				
		
		request.setAttribute("utilisateur", utilisateur);
		
		this.getServletContext().getRequestDispatcher("/AchatCredits").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}

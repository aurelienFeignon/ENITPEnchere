package fr.eni.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.bll.UtilisateurManageur;
import fr.eni.bo.Utilisateur;
import fr.eni.utils.BusinessException;

//UTILISATION : Dirige vers la jsp "MonProfil".
@WebServlet("/monProfil")
public class ServletVersMonProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// sert a l'affichage dynamique des credits

		int utilisateurId = Integer.parseInt(request.getSession(false).getAttribute("utilisateurId").toString());
		
		 Utilisateur utilisateurCredit = new Utilisateur();
		 UtilisateurManageur utilisateurManageur = new UtilisateurManageur();
		try {
			utilisateurCredit = utilisateurManageur.selectId(utilisateurId);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		request.setAttribute("utilisateurCredit", utilisateurCredit);
		
		this.getServletContext().getRequestDispatcher("/MonProfil").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

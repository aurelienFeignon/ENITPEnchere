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
import fr.eni.utils.BusinessException;


@WebServlet("/suppressionProfil")
public class ServletSuppressionProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	UtilisateurManageur utilisateurManageur = new UtilisateurManageur();
	
	//suppresion de l'utilisateur en BDD	
		try {
			utilisateurManageur.delete(Integer.parseInt(request.getSession(false).getAttribute("utilisateurId").toString()));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		//suppression de la session
		 HttpSession session = request.getSession();
	        session.invalidate();
	        
	    //redirection sur l'accueil    
		String message ="Suppression du profil réussie";
    	request.setAttribute("réussite", message);
		this.getServletContext().getRequestDispatcher("/PageAccueil").forward(request, response);
	}
	}



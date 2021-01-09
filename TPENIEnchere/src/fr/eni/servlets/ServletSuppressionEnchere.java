package fr.eni.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.bll.EnchereManageur;
import fr.eni.bll.articleManageur;

@WebServlet("/ServletSuppressionEnchere")
public class ServletSuppressionEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int numeroEnchere = Integer.parseInt(request.getParameter("idEnchere"));
		int numeroArticle = Integer.parseInt(request.getParameter("idArticle"));
		
		//suppression article
		articleManageur articleManageur = new articleManageur();
		articleManageur.delete(numeroArticle);
		//suppression enchere
		EnchereManageur enchereManageur = new EnchereManageur();
		enchereManageur.delete(numeroEnchere);
		
		String message ="La suppresion de votre enchère a été réalisée avec succès.";
    	request.setAttribute("reussite", message);
		
		this.getServletContext().getRequestDispatcher("/PageAccueil").forward(request, response);
	}

}

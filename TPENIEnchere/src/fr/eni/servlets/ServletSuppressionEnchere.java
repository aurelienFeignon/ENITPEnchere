package fr.eni.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.bll.EnchereManageur;
import fr.eni.bll.ArticleManageur;
import fr.eni.utils.BusinessException;

@WebServlet("/ServletSuppressionEnchere")
public class ServletSuppressionEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int numeroArticle = Integer.parseInt(request.getParameter("idArticle"));
		
		//suppression article
		ArticleManageur articleManageur = new ArticleManageur();
		try {
			articleManageur.delete(numeroArticle);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	
		
		String message ="La suppresion de votre enchère a été réalisée avec succès.";
    	request.setAttribute("réussite", message);
		
		this.getServletContext().getRequestDispatcher("/PageAccueil").forward(request, response);
	}

}

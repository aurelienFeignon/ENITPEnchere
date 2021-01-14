package fr.eni.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.bll.ArticleManageur;
import fr.eni.bo.Article;
import fr.eni.utils.BusinessException;

/**
 * Servlet implementation class ServletDeconnexion
 */
@WebServlet("/Deconnexion")
public class ServletDeconnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 /* Récupération et destruction de la session en cours */
        HttpSession session = request.getSession();
        session.invalidate();

      //Afficher les articles de la base de données
      		ArticleManageur articleManager  = new ArticleManageur();
      		List<Article> listeArticle =null;
      		try {
      			listeArticle = articleManager.selectAll();
      		} catch (BusinessException e) {
      			e.printStackTrace();
      		}
      		request.setAttribute("listeArticle", listeArticle);
      		this.getServletContext().getRequestDispatcher("/PageAccueil").forward(request, response);
    }
	
}

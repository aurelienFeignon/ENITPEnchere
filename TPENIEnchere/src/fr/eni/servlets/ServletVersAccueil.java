package fr.eni.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.bll.articleManageur;
import fr.eni.bo.Article;
import fr.eni.utils.BusinessException;

/**
 * Servlet implementation class ServletVersAccueil
 */
@WebServlet("/Accueil")
public class ServletVersAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Afficher les articles de la base de données
		articleManageur articleManager  = new articleManageur();
		List<Article> listeArticle =null;
		try {
			listeArticle = articleManager.selectAll();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("listeArticle", listeArticle);
		this.getServletContext().getRequestDispatcher("/PageAccueil").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//On recup les données
		String recherche = request.getParameter("recherche"); //Il faut un select where %recherche%
		String categorie = request.getParameter("categorie");
		String menu = request.getParameter("menu");
		if(request.getParameter("encheresOuvertes") != null){
			
		}
		if(request.getParameter("encheresEnCours") != null){
			
		}
		this.getServletContext().getRequestDispatcher("/PageAccueil").forward(request, response);
	}
	
}

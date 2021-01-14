package fr.eni.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.bll.ArticleManageur;
import fr.eni.bll.CategoriesManageur;
import fr.eni.bo.Article;
import fr.eni.bo.Categories;
import fr.eni.utils.BusinessException;

/**
 * Servlet implementation class ServletVersAccueil
 */
@WebServlet("/Accueil")
public class ServletVersAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ArticleManageur articleManageur = null;
	CategoriesManageur categoriesManageur = null;

	@Override
	public void init() throws ServletException {
		super.init();
		articleManageur = new ArticleManageur();
		categoriesManageur = new CategoriesManageur();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Afficher les articles de la base de données

		try {
			List<Article> listeArticle = articleManageur.selectAll();
			request.setAttribute("listeArticle", listeArticle);
			List<Categories> categories = categoriesManageur.selectAll();
			request.setAttribute("categories", categories);

		} catch (BusinessException | SQLException e) {
			e.printStackTrace();
		}

		this.getServletContext().getRequestDispatcher("/PageAccueil").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Categories> categories;
		try {
			categories = categoriesManageur.selectAll();
			request.setAttribute("categories", categories);
		} catch (BusinessException | SQLException e1) {
			e1.printStackTrace();
		}

		// On recup les données
		String recherche = request.getParameter("recherche");
		Integer categorie = null;
		if (request.getParameter("categorie") != null) {
			categorie = Integer.parseInt(request.getParameter("categorie"));
		}

		// Si rien dans la recherche
		if (recherche == null && categorie == null) {
			// Afficher tous les articles de la base de données
			ArticleManageur articleManager = new ArticleManageur();
			List<Article> listeArticle = null;
			try {
				listeArticle = articleManager.selectAll();
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			request.setAttribute("listeArticle", listeArticle);
		} else if (recherche != null && categorie == 0) { // Si seul le champs recherche est remplie
			ArticleManageur articleManager = new ArticleManageur();
			List<Article> listeArticle = null;
			try {
				listeArticle = articleManager.selectRechercher(recherche);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			request.setAttribute("listeArticle", listeArticle);
		} else if (categorie != 0 && recherche.equals("")) { // Si seul le champs categorie est remplie
			ArticleManageur articleManager = new ArticleManageur();
			List<Article> listeArticle = null;
			try {
				listeArticle = articleManager.selectCategorie(categorie);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			request.setAttribute("listeArticle", listeArticle);
		} else if (categorie != 0 && recherche != null) { // Si les deux sont remplies
			ArticleManageur articleManager = new ArticleManageur();
			List<Article> listeArticle = null;
			try {
				listeArticle = articleManager.selectCategorieRechercher(recherche, categorie);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			request.setAttribute("listeArticle", listeArticle);
		} // Ajouter un else vers une page d'erreur
			// retour vers l'accueil
		this.getServletContext().getRequestDispatcher("/PageAccueil").forward(request, response);
	}

}

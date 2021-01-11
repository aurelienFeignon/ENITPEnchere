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
 * Servlet implementation class ServletMenuAccueilConnecté
 */
@WebServlet("/AcceuilFiltreRecherche")
public class ServletMenuAccueilConnecté extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Pas utilisé pour l'instant...
		this.getServletContext().getRequestDispatcher("/Accueil").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noUtilisateur = Integer.parseInt(request.getParameter("noUtilisateur"));
		System.out.println(noUtilisateur);
		System.out.println(request.getParameter("ventesEnCours"));
		if(request.getParameter("encheresOuvertes") != null){
			articleManageur articleManager  = new articleManageur();
			List<Article> listeArticle =null;
			try {
				listeArticle = articleManager.selectAchatAll(noUtilisateur);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			request.setAttribute("listeArticle", listeArticle);
		}
		
		if(request.getParameter("encheresEnCours") != null){
			articleManageur articleManager  = new articleManageur();
			List<Article> listeArticle =null;
			try {
				listeArticle = articleManager.selectAchatEnchereEnCour(noUtilisateur);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			request.setAttribute("listeArticle", listeArticle);
		}
		
		
		if(request.getParameter("encheresRemportees") != null){
			articleManageur articleManager  = new articleManageur();
			List<Article> listeArticle =null;
			try {
				listeArticle = articleManager.selectAchatEnchereRemporte(noUtilisateur);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			request.setAttribute("listeArticle", listeArticle);
		}
		
		if(request.getParameter("ventesEnCours") != null){
			articleManageur articleManager  = new articleManageur();
			List<Article> listeArticle =null;
			try {
				listeArticle = articleManager.selectVenteEnCour(noUtilisateur);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			request.setAttribute("listeArticle", listeArticle);
		}
		
		if(request.getParameter("ventesNonDebutees") != null){
			articleManageur articleManager  = new articleManageur();
			List<Article> listeArticle =null;
			try {
				listeArticle = articleManager.selectVenteNonDebute(noUtilisateur);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			request.setAttribute("listeArticle", listeArticle);
		}
		
		if(request.getParameter("venteTerminees") != null){
			articleManageur articleManager  = new articleManageur();
			List<Article> listeArticle =null;
			try {
				listeArticle = articleManager.selectVenteTermine(noUtilisateur);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			request.setAttribute("listeArticle", listeArticle);
		}
		this.getServletContext().getRequestDispatcher("/Accueil").forward(request, response);
	}

}

package fr.eni.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.bll.RetraitManageur;
import fr.eni.bll.UtilisateurManageur;
import fr.eni.bll.ArticleManageur;
import fr.eni.bo.Article;
import fr.eni.bo.Retraits;
import fr.eni.bo.Utilisateur;
import fr.eni.utils.BusinessException;


@WebServlet("/ServletVersModificationEnchere")
public class ServletVersModificationEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//récupération infos article
		Article article = new Article();
		ArticleManageur articleManageur = new ArticleManageur();
		
		try {
			article = articleManageur.selectId(Integer.parseInt(request.getParameter("idArticle")));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
				
				request.setAttribute("article", article);
		
		//récupération infos retraits
		RetraitManageur retraitManageur = new RetraitManageur();
		Retraits retraits = new Retraits();
		try {
			retraits = retraitManageur.selectId(Integer.parseInt(request.getParameter("idRetrait")));
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (BusinessException e1) {
			e1.printStackTrace();
		}
		
		request.setAttribute("retraits", retraits);
		
		//récupération infos utilisateur 
		UtilisateurManageur utilisateurManageur = new UtilisateurManageur();
		Utilisateur utilisateur = new Utilisateur();
		try {
			utilisateur = utilisateurManageur.selectId(Integer.parseInt(request.getParameter("idVendeur")));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("utilisateur", utilisateur);
		
		
				this.getServletContext().getRequestDispatcher("/ModificationEnchere").forward(request, response);
		
	}

}

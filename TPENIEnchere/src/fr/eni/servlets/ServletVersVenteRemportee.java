package src.fr.eni.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.bll.EnchereManageur;
import fr.eni.bll.RetraitManageur;
import fr.eni.bll.UtilisateurManageur;
import fr.eni.bo.Article;
import fr.eni.bo.Encheres;
import fr.eni.bo.Retraits;


@WebServlet("/ServletVersVenteRemportee")
public class ServletVersVenteRemportee extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//récupération infos article
		Article article = new Article();
		ArticleManageur articleVenduManageur = new ArticleManageur();
		
		article = articleManageur.selectId(id)
				
				request.setAttribute("article", article);
		
		//récupération infos retraits
		RetraitManageur retraitManageur = new RetraitManageur();
		Retraits retraits = new Retraits();
		retraits = retraitManageur.selectId(article.getNo_retrait());
		
		request.setAttribute("retraits", retraits);
		
		//récupération infos vendeur
		UtilisateurManageur utilisateurManageur = new UtilisateurManageur();
		Utilisateur utilisateur = new Utilisateur();
		utilisateur = utilisateurManageur.selectId(article.getNo_utilisateur());
		
		request.setAttribute("utilisateur", utilisateur);
		
		
		
		this.getServletContext().getRequestDispatcher("/ResultatVente").forward(request, response);
		
	}


			
			request.setAttribute("article", article);
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}

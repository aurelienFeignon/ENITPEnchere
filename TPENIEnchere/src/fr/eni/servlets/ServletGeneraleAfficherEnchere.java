package fr.eni.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import fr.eni.bll.EnchereManageur;
import fr.eni.bll.RetraitManageur;
import fr.eni.bll.UtilisateurManageur;
import fr.eni.bll.articleManageur;
import fr.eni.bll.categoriesManageur;
import fr.eni.bo.Article;
import fr.eni.bo.Categories;
import fr.eni.bo.Encheres;
import fr.eni.bo.Retraits;
import fr.eni.bo.Utilisateur;


@WebServlet("/ServletGeneraleAfficherEnchere")
public class ServletGeneraleAfficherEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//récupération idArticle via clic;
		int idArticle;
		HttpSession session= request.getSession(false);
		int idUser = (int) session.getAttribute("id");
		
		//recuperation infos article
		Article article = new Article();
		articleManageur articleManageur = new articleManageur();
		
		article = articleManageur.selectId(idArticle);
		
		request.setAttribute("article", article);
		
		//récupération infos retraits
		RetraitManageur retraitManageur = new RetraitManageur();
		Retraits retraits = new Retraits();
		retraits = retraitManageur.selectId(article.getNo_retrait());
		
		request.setAttribute("retraits", retraits);

		//récupération infos categorie
		
		categoriesManageur categoriesManageur = new categoriesManageur();
		Categories categories = new Categories();
		categories = categoriesManageur.selectId(article.getNo_categorie());
		
		request.setAttribute("categories", categories);
		
        //récupération infos vendeur
		UtilisateurManageur utilisateurManageur = new UtilisateurManageur();
		Utilisateur utilisateur = new Utilisateur();
		utilisateur = utilisateurManageur.selectId(article.getNo_utilisateur());
		
		request.setAttribute("utilisateur", utilisateur);
		
		
		//récupération infos enchererisseur
		EnchereManageur enchereManageur = new EnchereManageur();
		Encheres encheres = new Encheres();
		encheres = enchereManageur.selectId(article.getNo_enchere());
		
		Utilisateur encherisseur = new Utilisateur();
		encherisseur = utilisateurManageur.selectId(encheres.getNo_utilisateur());
		request.setAttribute("encherisseur", encherisseur);
		
		
		
		boolean etatVente = article.getEtatVente();
		int prix_vente = article.getPrix_vente();
		
		// si vente en cours
		if (etatVente = false)
		{
			//récupération infos enchère et encherisseur
			double montant_enchere = 0;
			double montantMin = 0;
			
			montant_enchere = encheres.getMontant_enchere();
			if (montant_enchere > 0)
			{
				montantMin = montant_enchere;
			}
			
			else
			{
				montantMin = 0;
			}
			
			request.setAttribute("encheres", encheres);
			request.setAttribute("montantMin", montantMin);
			
			
			
			this.getServletContext().getRequestDispatcher("/DetailEnchere").forward(request, response);
		}
		
		//Vente fermée
		else {
			
			//si prix de vente est égal à 0, alors la vente n'a pas débuté. Si vente pas débuté + user = vendeur.
			if(prix_vente == 0 && idUser == article.getNo_utilisateur())
			{
				
					this.getServletContext().getRequestDispatcher("/DetailEnchere").forward(request, response);
			
				
			}
			
			//vente terminée
			else
			{
				// si user est le vendeur => jsp vente réussie
				if(idUser == article.getNo_utilisateur())
				{
		
					
					this.getServletContext().getRequestDispatcher("/ResultatEnchere").forward(request, response);
				}
				
				// si user est le gagnant de l'enchere => jsp enchere remportée
				if(idUser == encheres.getNo_utilisateur())
				{
					this.getServletContext().getRequestDispatcher("/ResultatVente").forward(request, response);
				}
				
				else
				{
					this.getServletContext().getRequestDispatcher("/ResultatEnchere").forward(request, response);
				}
					
			}
			
		}
		
		
	}

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}

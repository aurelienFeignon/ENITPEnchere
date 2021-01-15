package fr.eni.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.bll.EnchereManageur;
import fr.eni.bll.RetraitManageur;
import fr.eni.bll.UtilisateurManageur;
import fr.eni.bll.ArticleManageur;
import fr.eni.bll.CategoriesManageur;
import fr.eni.bo.Article;
import fr.eni.bo.Categories;
import fr.eni.bo.Encheres;
import fr.eni.bo.Retraits;
import fr.eni.bo.Utilisateur;
import fr.eni.utils.BusinessException;


@WebServlet("/ServletGeneraleAfficherEnchere")
public class ServletGeneraleAfficherEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LocalDate localDate = LocalDate.now();
	private boolean nonDebutee = false;
       
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//récupération idArticle
		int idArticle = Integer.parseInt(request.getParameter("idArticle"));
		int idGagnant = 0;
		//récuperation id utilisateur
		HttpSession session= request.getSession(false);
		Utilisateur User = (Utilisateur) session.getAttribute("utilisateur");
		int idUser = User.getNo_utilisateur();
		
		//recuperation infos de l'article 
		Article article = new Article();
		ArticleManageur articleManageur = new ArticleManageur();
		
		try {
			article = articleManageur.selectId(idArticle);
		} catch (BusinessException e1) {
			e1.printStackTrace();
		}
		
		request.setAttribute("article", article);
		
		//récupération infos retraits
		RetraitManageur retraitManageur = new RetraitManageur();
		Retraits retraits = new Retraits();
		try {
			retraits = retraitManageur.selectId(article.getNo_retrait());
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("retraits", retraits);

		//récupération infos categories
		
		CategoriesManageur CategoriesManageur = new CategoriesManageur();
		Categories categories = new Categories();
		try {
			categories = CategoriesManageur.selectId(article.getNo_categorie());
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("categories", categories);
		
        //récupération infos vendeur
		UtilisateurManageur utilisateurManageur = new UtilisateurManageur();
		Utilisateur utilisateur = new Utilisateur();
		try {
			utilisateur = utilisateurManageur.selectId(article.getNo_utilisateur());
		} catch (BusinessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		request.setAttribute("utilisateur", utilisateur);
		
		
	//récupération infos enchere + encherisseur SI ENCHERE
		Encheres enchere = null;
		Utilisateur encherisseur = new Utilisateur();
		Utilisateur encherisseurs = new Utilisateur();
	
		int no_encherisseur;
		EnchereManageur enchereManageur= new EnchereManageur();
		List<Encheres> listEncheres= new ArrayList<>();
				
				
		
		try {
			listEncheres = enchereManageur.selectHistoriqueArticleDecroissant(idArticle);
		} catch (BusinessException e2) {
			e2.printStackTrace();
		}
		
		
		if(!listEncheres.isEmpty()) {
		try {
			
			enchere= listEncheres.get(0);
			encherisseur = utilisateurManageur.selectId(enchere.getNo_utilisateur());
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		
		request.setAttribute("listEncheres", listEncheres);
		request.setAttribute("enchere", enchere);
		request.setAttribute("encherisseur", encherisseur);
		}
		
		
		boolean etatVente = article.getEtatVente();
		
		
		
		// pas débutée ou en cours
		if (etatVente == false)
		{
			//en cours
			if (article.getDate_debut_encheres().toLocalDate().isBefore(localDate) | article.getDate_debut_encheres().toLocalDate().isEqual(localDate))
					{
				
				double montant_enchere = 0;
				double montantMin = 0;
				
				//s'il y a déjà eu une enchere
				if(!listEncheres.isEmpty())
				{
					
					montant_enchere = enchere.getMontant_enchere();
					montantMin = montant_enchere;
					
				}
				
				// pas encore d'enchere
				else
				{
					montantMin = article.getPrix_initial();
				}
				
				
				request.setAttribute("montantMin", montantMin);
				
				this.getServletContext().getRequestDispatcher("/DetailEnchere").forward(request, response);
					}
			
			//pas encore en vente - seul le vendeur peut y accéder
			else
			{
				if(article.getDate_debut_encheres().toLocalDate().isAfter(localDate) && idUser == article.getNo_utilisateur()) 
				{
					this.nonDebutee  = true;
					request.setAttribute("nonDebutee", nonDebutee);
						this.getServletContext().getRequestDispatcher("/DetailEnchere").forward(request, response);
				
					
				}
			}
			
		}
		
		//vente terminée
		else
			
		{
			//on recherche si il y a une enchere + l'enchere gagnante
			
			if(!listEncheres.isEmpty()) {
			idGagnant = enchere.getNo_utilisateur();
			}
			
			//si gagnant
			if (idUser == idGagnant)
			{
				this.getServletContext().getRequestDispatcher("/ResultatVente").forward(request, response);
			}
			
			
			else 
			{
				this.getServletContext().getRequestDispatcher("/ResultatEnchere").forward(request, response);
				
			}
		}
		
		
		
	
		
		
	}

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
	}
	


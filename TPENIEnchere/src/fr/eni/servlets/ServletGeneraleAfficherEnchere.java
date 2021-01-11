package fr.eni.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.bll.RetraitManageur;
import fr.eni.bll.UtilisateurManageur;
import fr.eni.bll.articleManageur;
import fr.eni.bll.categoriesManageur;
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
		//récupération idArticle et de l'id de l'encherisseur gagnant via clic si enchere terminée??;
		int idArticle = Integer.parseInt(request.getParameter("idArticle"));
		int idGagnant = Integer.parseInt(request.getParameter("idGagnant"));
		HttpSession session= request.getSession(false);
		Utilisateur User = (Utilisateur) session.getAttribute("utilisateur");
		int idUser = User.getNo_utilisateur();
		
		//recuperation infos article
		Article article = new Article();
		articleManageur articleManageur = new articleManageur();
		
		try {
			article = articleManageur.selectId(idArticle);
		} catch (BusinessException e1) {
			// TODO Auto-generated catch block
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

		//récupération infos categorie
		
		categoriesManageur categoriesManageur = new categoriesManageur();
		Categories categories = new Categories();
		try {
			categories = categoriesManageur.selectId(article.getNo_categorie());
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
		
		
	//récupération infos enchererisseur
		Encheres encheres = new Encheres();
		Utilisateur encherisseur = new Utilisateur();
/*		try {
			encherisseur = utilisateurManageur.selectId(encheres.getNo_utilisateur());
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("encherisseur", encherisseur);
*/	
		
		
		boolean etatVente = article.getEtatVente();
		int prix_vente = article.getPrix_vente();
		
		
		// si vente en cours
		if (article.getDate_debut_encheres().toLocalDate().isBefore(localDate) && etatVente == false)
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
				montantMin = article.getPrix_initial();
			}
			
			request.setAttribute("encheres", encheres);
			request.setAttribute("montantMin", montantMin);
			
			this.getServletContext().getRequestDispatcher("/DetailEnchere").forward(request, response);
		}
		
		//Vente fermée
		else {
			
			//si prix de vente est égal à prix initial, alors la vente n'a pas débuté. Si vente pas débuté + user = vendeur.
			if(article.getDate_debut_encheres().toLocalDate().isAfter(localDate) && idUser == article.getNo_utilisateur())
			{
				this.nonDebutee  = true;
				request.setAttribute("nonDebutee", nonDebutee);
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
				
				// si user est le gagnant de l'enchere => jsp enchere remportée ATTENTION FAIRE PASSER L'IDGAGNANT PRECEDEMMENT
				
				if(idUser == idGagnant)
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

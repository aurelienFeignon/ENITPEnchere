package src.fr.eni.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.bll.EnchereManageur;
import fr.eni.bll.UtilisateurManageur;
import fr.eni.bll.articleManageur;
import fr.eni.bo.Article;
import fr.eni.bo.Encheres;
import fr.eni.bo.Utilisateur;


@WebServlet("/ServletEncherir")
public class ServletEncherir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 int idEncherisseur = Integer.parseInt(request.getParameter("id"));
		 
		 int idArticle = Integer.parseInt(request.getParameter("idArticle"));
		
		
		
		//récupération infos enchère 
		EnchereManageur enchereManageur = new EnchereManageur();
		Encheres enchere = new Encheres();
		enchere = enchereManageur.selectId(article.getNo_enchere());
		
		//normalement il est impossible de miser en dessous de l'enchere actuelle via la jsp mais vérification ici aussi au ca où 
		double proposition = Double.parseDouble(request.getParameter("proposition"));
		double creditUser = (Double.parseDouble(request.getParameter("credit")));
		double montant_enchere = (double)enchere.getMontant_enchere();
		
		
		if (proposition > montant_enchere && proposition <= creditUser)
			
		{
			Utilisateur ancienEncherisseur = new Utilisateur();
			Utilisateur nouveauEncherisseur = new Utilisateur();
			 UtilisateurManageur utilisateurManageur = new UtilisateurManageur();	
			
			 if(montant_enchere > 0) {
			 //on rend le crédit à l'ancien encherisseur s'il ya eu une enchère précédente
			ancienEncherisseur = utilisateurManageur.selectId(enchere.getNo_utilisateur());
			utilisateurManageur.AjouterCredit(ancienEncherisseur, (int) montant_enchere);
			 }
			
			 //on enleve le crédit au nouvel encherisseur
			nouveauEncherisseur = utilisateurManageur.selectId(idEncherisseur);
			utilisateurManageur.enleveCredit(nouveauEncherisseur, (int) proposition);
			
			//update enchere - FORMAT DATE A CHANGER
			enchere.setDate_enchere(SystemClockFactory.getDatetime());
 	        enchere.setMontant_enchere((int) proposition);
 	        enchere.setNo_utilisateur(idEncherisseur);
 	        
			
			enchereManageur.update(enchere,enchere.getNo_enchere());
			
			//update les infos de l'article (prixdevente)
			Article article = new Article();
			articleManageur articleManageur = new articleManageur();
			article = articleManageur.selectId(idArticle);
			article.setPrix_vente((int) proposition);
				articleManageur.update(article, article.getNo_article());
		}

		
		else 
		{
			String message ="Impossible d'effectuer l'action désirée";
        	request.setAttribute("erreur", message);
			this.getServletContext().getRequestDispatcher("/DetailEnchere").forward(request, response);
		}
		
		
		String message ="Votre enchère a été réalisée avec succès.";
    	request.setAttribute("reussite", message);
		this.getServletContext().getRequestDispatcher("/PageAccueil").forward(request, response);
		
	}
		
		
		
	}



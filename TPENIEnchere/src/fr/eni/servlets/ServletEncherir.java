 	package fr.eni.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.bll.EnchereManageur;
import fr.eni.bll.UtilisateurManageur;
import fr.eni.bll.ArticleManageur;
import fr.eni.bo.Article;
import fr.eni.bo.Encheres;
import fr.eni.bo.Utilisateur;
import fr.eni.utils.BusinessException;


@WebServlet("/ServletEncherir")
public class ServletEncherir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	@SuppressWarnings("null")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idEncherisseur = Integer.parseInt(request.getParameter("id"));
		 
		 int idArticle = Integer.parseInt(request.getParameter("idArticle"));
		
		
		
		//récupération infos enchère 
		EnchereManageur enchereManageur = new EnchereManageur();
		
		Encheres derniereEnchere = new Encheres();
		List<Encheres> listEncheres = new ArrayList<>();
		ArticleManageur articleManageur= new ArticleManageur();
		
		
		//normalement il est impossible de miser en dessous de l'enchere actuelle via la jsp mais vérification ici aussi au ca où 
		double proposition = Double.parseDouble(request.getParameter("proposition"));
		double creditUser = (Double.parseDouble(request.getParameter("credit")));
		
		Utilisateur nouveauEncherisseur = new Utilisateur();
		UtilisateurManageur utilisateurManageur = new UtilisateurManageur();	
		
		try {
			listEncheres = enchereManageur.selectHistoriqueArticle(idArticle);
			 
			 nouveauEncherisseur = utilisateurManageur.selectId(idEncherisseur);
			 if(listEncheres.size() != 0) {
					derniereEnchere = listEncheres.get(listEncheres.size()-1);
					 Utilisateur ancienEncherisseur = utilisateurManageur.selectId(derniereEnchere.getNo_utilisateur());
					
					utilisateurManageur.AjouterCredit(ancienEncherisseur, derniereEnchere.getMontant_enchere());
					utilisateurManageur.enleveCredit(nouveauEncherisseur, (int) proposition);
			}else {
				utilisateurManageur.enleveCredit(nouveauEncherisseur, (int) proposition);
			}
			 	Encheres nouvelleEnchere = new Encheres();
			 	nouvelleEnchere.setDate_enchere(Date.valueOf(LocalDate.now()));
				nouvelleEnchere.setMontant_enchere((int) proposition);
				nouvelleEnchere.setNo_article(idArticle);
				nouvelleEnchere.setNo_utilisateur(idEncherisseur);
				System.out.println(nouvelleEnchere.toString());
				enchereManageur.insert(nouvelleEnchere);
				
				//update prixdevente
				articleManageur.updatePrixVente(idArticle, (int) proposition);
		} catch (BusinessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String message ="Impossible d'effectuer l'action désirée";
        	request.setAttribute("erreur", message);
			this.getServletContext().getRequestDispatcher("/DetailEnchere").forward(request, response);
		}
		String message ="Votre enchère a été réalisée avec succès.";
    	request.setAttribute("reussite", message);
		this.getServletContext().getRequestDispatcher("/PageAccueil").forward(request, response);
		
	}
		
		
		
	}



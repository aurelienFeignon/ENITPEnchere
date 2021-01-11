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
import fr.eni.bll.articleManageur;
import fr.eni.bo.Encheres;
import fr.eni.bo.Utilisateur;
import fr.eni.utils.BusinessException;


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
		
		Encheres derniereEnchere = new Encheres();
		List<Encheres> listEncheres = new ArrayList<>();
		
		try {
			listEncheres = enchereManageur.selectHistoriqueArticle(idArticle);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(listEncheres.size() != 0) {
		derniereEnchere = listEncheres.get(listEncheres.size());
		}else {
			derniereEnchere.setMontant_enchere(0);
		}
			
		
		
		
		//normalement il est impossible de miser en dessous de l'enchere actuelle via la jsp mais vérification ici aussi au ca où 
		double proposition = Double.parseDouble(request.getParameter("proposition"));
		double creditUser = (Double.parseDouble(request.getParameter("credit")));
		double montant_enchere = (double)derniereEnchere.getMontant_enchere();
		
		
		if (proposition > montant_enchere && proposition <= creditUser)
			
		{
			Utilisateur ancienEncherisseur = new Utilisateur();
			Utilisateur nouveauEncherisseur = new Utilisateur();
			 UtilisateurManageur utilisateurManageur = new UtilisateurManageur();	
			
			 if(montant_enchere > 0) {
			 //on rend le crédit à l'ancien encherisseur s'il ya eu une enchère précédente
			try {
				ancienEncherisseur = utilisateurManageur.selectId(derniereEnchere.getNo_utilisateur());
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				utilisateurManageur.AjouterCredit(ancienEncherisseur, (int) montant_enchere);
			} catch (BusinessException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 }
			
			 //on enleve le crédit au nouvel encherisseur
			try {
				nouveauEncherisseur = utilisateurManageur.selectId(idEncherisseur);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				utilisateurManageur.enleveCredit(nouveauEncherisseur, (int) proposition);
			} catch (BusinessException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			//création nouvelle enchère
			// 	+ generated key ?
			Encheres nouvelleEnchere = null;
			nouvelleEnchere.setDate_enchere(Date.valueOf(LocalDate.now()));
			nouvelleEnchere.setMontant_enchere((int) proposition);
			nouvelleEnchere.setNo_article(idArticle);
			nouvelleEnchere.setNo_utilisateur(idEncherisseur);
			try {
				enchereManageur.insert(nouvelleEnchere);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			//update prixdevente
			articleManageur articleManageur = new articleManageur();
			try {
				articleManageur.updatePrixVente(idArticle, (int) proposition);
			} catch (BusinessException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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



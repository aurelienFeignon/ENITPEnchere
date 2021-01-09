package src.fr.eni.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.RequestDispatcher;

import fr.eni.bll.EnchereManageur;
import fr.eni.bll.RetraitManageur;
import fr.eni.bll.UtilisateurManageur;
import fr.eni.bll.articleManageur;
import fr.eni.bo.Article;
import fr.eni.bo.Categories;
import fr.eni.bo.Encheres;
import fr.eni.bo.Retraits;
import fr.eni.bo.Utilisateur;


@WebServlet("/ServletVersAfficherEnchere")
public class ServletVersAfficherEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
       



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//récupération de l'id article via clic précédent  
		int id;
		//récupération infos article
				Article article = new Article();
				articleManageur articleManageur = new articleManageur();
				
				article = articleManageur.selectId(id);
						
						request.setAttribute("article", article);
						
				//récupération infos retraits
						RetraitManageur retraitManageur = new RetraitManageur();
						Retraits retraits = new Retraits();
						retraits = retraitManageur.selectId(article.getNo_retrait());
						
						request.setAttribute("retraits", retraits);
		
						//récupération infos categorie
						
						CategorieManageur categorieManageur = new CategorieManageur();
						Categories categories = new Categories();
						categories = categoriesManageur.selectId(article.getNo_categorie());
						
						request.setAttribute("categories", categories);
						
				//récupération infos vendeur
						UtilisateurManageur utilisateurManageur = new UtilisateurManageur();
						Utilisateur utilisateur = new Utilisateur();
						utilisateur = utilisateurManageur.selectId(article.getNo_utilisateur());
						
						request.setAttribute("utilisateur", utilisateur);
						
				//récupération infos enchère et encherisseur
						EnchereManageur enchereManageur = new EnchereManageur();
						Encheres encheres = new Encheres();
						encheres = enchereManageur.selectId(article.getNo_enchere());
						double montantMin =+ encheres.getMontant_enchere();
						request.setAttribute("encheres", encheres);
						request.setAttribute("montantMin", montantMin);
						
						Utilisateur encherisseur = new Utilisateur();
						encherisseur = utilisateurManageur.selectId(encheres.getNo_utilisateur());
						request.setAttribute("encherisseur", encherisseur);
						
						this.getServletContext().getRequestDispatcher("/DetailEnchere").forward(request, response);
	}
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		
		
	}

}

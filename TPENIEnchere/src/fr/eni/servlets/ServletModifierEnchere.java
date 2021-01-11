package fr.eni.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.bll.RetraitManageur;
import fr.eni.bll.articleManageur;
import fr.eni.bo.Article;
import fr.eni.bo.Retraits;
import fr.eni.message.LecteurMessage;
import fr.eni.utils.BusinessException;


@WebServlet("/ServletModifierEnchere")
public class ServletModifierEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Recuperer les parametres
				int idArticle = Integer.parseInt(request.getParameter("idArticle"));
				String article = request.getParameter("article");
				String description = request.getParameter("description");
				int categorie = Integer.parseInt(request.getParameter("categorie"));
				int miseAPrix = Integer.parseInt(request.getParameter("prixInitial"));
				String rue = request.getParameter("rue");
				String codePostal = request.getParameter("codePostal");
				String ville = request.getParameter("ville");
				String debutEnchere = request.getParameter("debutEnchere");
				String finEnchere  = request.getParameter("finEnchere");
				int numeroUtilisateur = Integer.parseInt(request.getParameter("numeroUtilisateur"));
				Boolean etat = false;
				Date dateDebutEnchere = Date.valueOf(debutEnchere);
				Date dateFinEnchere = Date.valueOf(finEnchere);
				System.out.println(numeroUtilisateur);
			//Je cree un objet 
				 Article unArticle = new Article();
				 articleManageur articleManageur = new articleManageur();
				 Retraits unRetrait = new Retraits();
				 RetraitManageur retraitManageur = new RetraitManageur();
				 //Je recup les données saisies dans le formulaire et je les attributs à l'objet
				 unArticle.setNom_article(article);
				 unArticle.setDescription(description);
				 unArticle.setDate_debut_encheres(dateDebutEnchere);
				 unArticle.setDate_fin_encheres(dateFinEnchere);
				 unArticle.setPrix_initial(miseAPrix);
				 unArticle.setPrix_vente(0);
				 unArticle.setNo_utilisateur(numeroUtilisateur);
				 unArticle.setNo_categorie(categorie);
				 unArticle.setNo_retrait(unRetrait.getNo_retrait());
				 unArticle.setEtatVente(etat);		
				 
				 unRetrait.setRue(rue);
				 unRetrait.setVille(ville);
				 unRetrait.setCode_postal(codePostal);
				 try {
					articleManageur.update(unArticle, idArticle);
				} catch (BusinessException e) {
					e.printStackTrace();
					List<Integer> codeErreur= e.getListeCodesErreur();
					List<String> messageErreur= new ArrayList<String>();
					if(!codeErreur.isEmpty()) {
						for(Integer code: codeErreur) {
							messageErreur.add(LecteurMessage.getMessageErreur(code));
						}
						request.setAttribute("erreurs", messageErreur);
					}
					this.getServletContext().getRequestDispatcher("/ModificationEnchere").forward(request, response);
					return;
				}
				  String MESSAGEREUSSITE = "Vente modifiée avec succès" ;
				 request.setAttribute("réussite", MESSAGEREUSSITE);
				 this.getServletContext().getRequestDispatcher("/PageAccueil").forward(request, response);
				 
			}

		
		
		
	}

}

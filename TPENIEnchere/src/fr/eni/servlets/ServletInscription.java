package fr.eni.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.bll.UtilisateurManageur;
import fr.eni.bo.Utilisateur;
import fr.eni.utils.BusinessException;



/**
 * Servlet implementation class ServletInscription
 */
@WebServlet("/Inscription")
public class ServletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/PageInscription").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Quand je clique sur creer(un utilisateur) je veux inserer un utilisateur dans ma bdd
		if(request.getParameter("mdp").equals(request.getParameter("confirmation"))) {
		//Je verifie si mon mdp est identique au mdp à confirmer
				//Je creer un objet utilisateur (via ma classe bo)
				 Utilisateur utilisateur = new Utilisateur();
				 UtilisateurManageur utilisateurManageur = new UtilisateurManageur();
				 //Je recup les données saisies dans le formulaire et je les attributs à l'objet
			        utilisateur.setPseudo(request.getParameter("pseudo"));
			        utilisateur.setNom(request.getParameter("nom"));
			        utilisateur.setPrenom(request.getParameter("prenom"));
			        utilisateur.setEmail(request.getParameter("email"));
			        utilisateur.setTelephone(request.getParameter("telephone"));
			        utilisateur.setRue(request.getParameter("rue"));
			        utilisateur.setCode_postal(request.getParameter("codePostal"));
			        utilisateur.setVille(request.getParameter("ville"));
			        utilisateur.setMot_de_passe(request.getParameter("mdp"));
			        utilisateur.setCredit((double)100);
			        utilisateur.setAdministrateur(false);
			    //J'insere l'objet dans la bdd (via ma classe DAL)
			        try {
						utilisateurManageur.insert(utilisateur);
					} catch (BusinessException e) {
						e.printStackTrace();
					}
			   //Je renvoie vers la page d'acceuil un utilisateur connecté (dupliquer code de la servlet connection)
			        this.getServletContext().getRequestDispatcher("/Accueil").forward(request, response);
		}else {
			String message = "Echec de l'inscription, le mot de passe saisie est différent du mot de passe confirmé.";
			request.setAttribute("erreur", message);
			this.getServletContext().getRequestDispatcher("/PageInscription").forward(request, response);
		}
	}

}

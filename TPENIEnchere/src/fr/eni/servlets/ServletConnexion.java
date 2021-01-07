package fr.eni.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.bll.UtilisateurManageur;
import fr.eni.bo.Utilisateur;
import fr.eni.utils.BusinessException;



/**
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/Connexion")
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.sendRedirect("Connexion");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Je recup les données saisies par l'utilisateur
		String identifiant = request.getParameter("identifiant");
		String mdp = request.getParameter("mdp");
		//On crée un utilisateur qui ne doit pas être null si id et mdp present dans la bdd
		Utilisateur utilisateur = new Utilisateur();
		UtilisateurManageur utilisateurManageur = new UtilisateurManageur();
		try {
			utilisateur = utilisateurManageur.VerifConnexion(identifiant, mdp);
		} catch (BusinessException e) {

			e.printStackTrace();
		}
		
		//On verifie leur existance dans la base de donnée

		if(utilisateur.getPseudo()!= null & utilisateur.getMot_de_passe()!= null) {
			//On stocke dans une session
			HttpSession session = request.getSession();
			//session.setMaxInactiveInterval(30);   // session timeout si utilisateur inactif en secondes
			session.setAttribute("utilisateur", utilisateur);
			//redirige vers l'acceuil
		this.getServletContext().getRequestDispatcher("/PageAccueil").forward(request, response);
		} else {
			this.getServletContext().getRequestDispatcher("/PageConnexion").forward(request, response);
		}
/*		
		 //On stock en cookie si "se souvenir de moi" cocher
		if(request.getParameter("memorisation") == null){
		    //checkbox not checked donc on fait rien
		}else{
		    //checkbox checked
			
		}
*/
	}

}

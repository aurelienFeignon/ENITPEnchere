package fr.eni.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.bll.UtilisateurManageur;
import fr.eni.bo.Utilisateur;
import fr.eni.message.LecteurMessage;
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
       //Recup tous les cookies et recherche ceux que j'ai stocké
		Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("identifiant")) {
                    request.setAttribute("identifiant", cookie.getValue());
                }
                if (cookie.getName().equals("mdp")) {
                    request.setAttribute("mdp", cookie.getValue());
                }     
            }
        }
		this.getServletContext().getRequestDispatcher("/PageConnexion").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Je recup les données saisies par l'utilisateur
		String identifiant = request.getParameter("identifiant");
		String mdp = request.getParameter("mdp");
		//On crée un utilisateur qui ne doit pas être null si id et mdp present dans la bdd
		Utilisateur utilisateur = null;
		UtilisateurManageur utilisateurManageur = new UtilisateurManageur();
		try {
			utilisateur = utilisateurManageur.VerifConnexion(identifiant, mdp);
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
		}
		
		//On verifie leur existance dans la base de donnée

		if(utilisateur != null) {
			//On stocke dans une session
			HttpSession session = request.getSession();
			session.setAttribute("utilisateur", utilisateur);
			session.setAttribute("utilisateurId", utilisateur.getNo_utilisateur());
			 //On stock en cookie si "se souvenir de moi" cocher
			if(request.getParameter("memorisation") != null){
				 Cookie cookieIdentifiant = new Cookie("identifiant", identifiant);
				 cookieIdentifiant.setMaxAge(60 * 60 * 24 * 30);
			        response.addCookie(cookieIdentifiant);
			     Cookie cookieMdp = new Cookie("mdp", mdp);
			     cookieMdp.setMaxAge(60 * 60 * 24 * 30);
			        response.addCookie(cookieMdp);	
			}
			//redirige vers l'acceuil
			
			this.getServletContext().getRequestDispatcher("/Accueil").forward(request, response);
		} else {
			this.getServletContext().getRequestDispatcher("/PageConnexion").forward(request, response);
		}

	}

}

package src.fr.eni.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import fr.eni.bll.UtilisateurManageur;
import fr.eni.bo.Utilisateur;
import fr.eni.utils.BusinessException;


//UTILISATION : Récupère les infos du formulaire de la jsp "modification profil" puis update la dal.
//TO DO : ajout suite code + ajouter d'autres messages d'erreur ? + tests !


@WebServlet("/ServletModificationProfil")
public class ServletModificationProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mdp = request.getParameter("mdp");
		String newMdp = request.getParameter("newMdp");
		String confirmation = request.getParameter("confirmation");
		
		//on récupère la session et l'id pour la passage en parametre de update
		HttpSession session = request.getSession(true);  
		 int id = (int)session.getAttribute("id");
		 
		
		Utilisateur utilisateur = new Utilisateur();
		 UtilisateurManageur utilisateurManageur = new UtilisateurManageur();
		 

		//CAS 1 - pas de modification du mdp
	        if(newMdp.isEmpty() && confirmation.isEmpty())
	        {
	        	utilisateur.setPseudo(request.getParameter("pseudo"));
	 	        utilisateur.setNom(request.getParameter("nom"));
	 	        utilisateur.setPrenom(request.getParameter("prenom"));
	 	        utilisateur.setEmail(request.getParameter("email"));
	 	        utilisateur.setTelephone(request.getParameter("telephone"));
	 	        utilisateur.setRue(request.getParameter("rue"));
	 	        utilisateur.setCode_postal(request.getParameter("codePostal"));
	 	        utilisateur.setVille(request.getParameter("ville"));
	 	        utilisateur.setMot_de_passe(request.getParameter("mdp"));
	 	        
	 		   //update DAL
		        try {
					utilisateurManageur.update(utilisateur,id);
				} catch (BusinessException e) {
					e.printStackTrace();
				}
		        
		   //retour sur la page d'accueil + message de réussite
		        String message ="Modification des informations réussie";
	        	request.setAttribute("réussite", message);
			RequestDispatcher rd = request.getRequestDispatcher("/PageAccueil");
			rd.forward(request, response);
	 	        
	        }
	        
	        
	        
	        else {
	       //CAS 2 - modification du mot de passe
	        	
	      //vérification de la concordance entre le nouveau mot de passe et sa confirmation
	        if(request.getParameter("newMdp").equals(request.getParameter("confirmation"))) {
		 
	        utilisateur.setPseudo(request.getParameter("pseudo"));
	        utilisateur.setNom(request.getParameter("nom"));
	        utilisateur.setPrenom(request.getParameter("prenom"));
	        utilisateur.setEmail(request.getParameter("email"));
	        utilisateur.setTelephone(request.getParameter("telephone"));
	        utilisateur.setRue(request.getParameter("rue"));
	        utilisateur.setCode_postal(request.getParameter("codePostal"));
	        utilisateur.setVille(request.getParameter("ville"));
	        utilisateur.setMot_de_passe(request.getParameter("newMdp"));
	        
	        
	        try {
	  					utilisateurManageur.update(utilisateur,id);
	  				} catch (BusinessException e) {
	  					e.printStackTrace();
	  				}
	  		        
	  		   //retour sur la page d'accueil + message de réussite
	  		        String message ="Modification des informations et du mot de passe réussie";
	  	        	request.setAttribute("réussiteMdp", message);
	  			RequestDispatcher rd = request.getRequestDispatcher("/PageAccueil");
	  			rd.forward(request, response);
	  	 	       
	        }
	        
	        
	        
	        //message d'erreur et retour sur la page de modification
	        else  {
	        	String message ="Modification impossible. Le nouveau mot de passe est différent de la confirmation";
	        	request.setAttribute("erreur", message);
				this.getServletContext().getRequestDispatcher("/Modificationprofil").forward(request, response);
	        }

	        
		 

	
	}
	
}
}



package fr.eni.servlets;

import java.io.IOException;
import java.sql.SQLException;

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
		
		Utilisateur utilisateur1 = new Utilisateur();
		 UtilisateurManageur utilisateurManageur = new UtilisateurManageur();		 

		//CAS 1 - pas de modification du mdp
	        if(newMdp.isEmpty() && confirmation.isEmpty())
	        {
	        	utilisateur1.setPseudo(request.getParameter("pseudo"));
	 	        utilisateur1.setNom(request.getParameter("nom"));
	 	        utilisateur1.setPrenom(request.getParameter("prenom"));
	 	        utilisateur1.setEmail(request.getParameter("email"));
	 	        utilisateur1.setTelephone(request.getParameter("telephone"));
	 	        utilisateur1.setRue(request.getParameter("rue"));
	 	        utilisateur1.setCode_postal(request.getParameter("codePostal"));
	 	        utilisateur1.setVille(request.getParameter("ville"));
	 	        utilisateur1.setMot_de_passe(request.getParameter("mdp"));
	 	        utilisateur1.setCredit(Double.parseDouble(request.getParameter("credit")));
	 		   //update DAL
		        try {
					utilisateurManageur.update(utilisateur1,Integer.parseInt(request.getParameter("id")));
				} catch (BusinessException | SQLException e) {
					e.printStackTrace();
				}
		        
		   //retour sur la page d'accueil + message de réussite
		        String message ="Modification des informations réussie";
	        	request.setAttribute("réussite", message);
	        	this.getServletContext().getRequestDispatcher("/PageAccueil").forward(request, response);
	 	        
	        }
	        
	        
	        
	        else {
	       //CAS 2 - modification du mot de passe
	        	
	      //vérification de la concordance entre le nouveau mot de passe et sa confirmation
	        if(request.getParameter("newMdp").equals(request.getParameter("confirmation"))) {
		 
	        utilisateur1.setPseudo(request.getParameter("pseudo"));
	        utilisateur1.setNom(request.getParameter("nom"));
	        utilisateur1.setPrenom(request.getParameter("prenom"));
	        utilisateur1.setEmail(request.getParameter("email"));
	        utilisateur1.setTelephone(request.getParameter("telephone"));
	        utilisateur1.setRue(request.getParameter("rue"));
	        utilisateur1.setCode_postal(request.getParameter("codePostal"));
	        utilisateur1.setVille(request.getParameter("ville"));
	        utilisateur1.setMot_de_passe(request.getParameter("newMdp"));
	        
	        try {
	  					utilisateurManageur.update(utilisateur1,Integer.parseInt(request.getParameter("id")));
	  				} catch (BusinessException | SQLException e) {
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



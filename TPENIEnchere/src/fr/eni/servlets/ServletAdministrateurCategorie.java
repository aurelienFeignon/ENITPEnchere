package fr.eni.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.bll.CategoriesManageur;
import fr.eni.bll.UtilisateurManageur;
import fr.eni.bo.Utilisateur;
import fr.eni.utils.BusinessException;

/**
 * Servlet implementation class ServletAdministrateurCategorie
 */
@WebServlet("/Administrateur")
public class ServletAdministrateurCategorie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtilisateurManageur utilisateurManageur = null;
	CategoriesManageur categoriesManageur = null;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Afficher le tableau d'utilisateur a l'admin 
		utilisateurManageur = new UtilisateurManageur();
		try {
			List<Utilisateur> listeUtilisateur = utilisateurManageur.selectAll();
			request.setAttribute("listeUtilisateur", listeUtilisateur);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher("/PageAdmin").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		categoriesManageur = new CategoriesManageur();
		try {
			categoriesManageur.insert(request.getParameter("cat"));
		} catch (BusinessException | SQLException e) {
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/PageAdmin").forward(request, response);
	}

}

package fr.eni.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.bll.ArticleManageur;
import fr.eni.bll.CategoriesManageur;
import fr.eni.bll.UtilisateurManageur;
import fr.eni.bo.Article;
import fr.eni.bo.Utilisateur;
import fr.eni.utils.BusinessException;

/**
 * Servlet implementation class ServletAdminUtilisateur
 */
@WebServlet("/Admin")
public class ServletAdminUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UtilisateurManageur utilisateurManageur = null;

	@Override
	public void init() throws ServletException {
		super.init();
		utilisateurManageur = new UtilisateurManageur();

	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

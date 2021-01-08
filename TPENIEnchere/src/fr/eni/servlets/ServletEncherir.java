package src.fr.eni.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.bll.EnchereManageur;
import fr.eni.bo.Encheres;


@WebServlet("/ServletEncherir")
public class ServletEncherir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		//récupération infos enchère 
		EnchereManageur enchereManageur = new EnchereManageur();
		Encheres encheres = new Encheres();
		encheres = enchereManageur.selectId(article.getNo_enchere());
		
		//normalement il est impossible de miser en dessous de l'enchere actuelle via la jsp mais vérification ici aussi au ca où 
		double proposition = Double.parseDouble(request.getParameter("proposition"));
		double creditUser = (Double.parseDouble(request.getParameter("credit")));
		double montant_enchere = (double)encheres.getMontant_enchere();
		
		if (proposition > montant_enchere && proposition <= creditUser)
		{// TERMINER CE WEEK END
			enchereManegeur.update
		}
		
		
		else 
		{
			String message ="Impossible d'effectuer l'action désirée";
        	request.setAttribute("erreur", message);
			this.getServletContext().getRequestDispatcher("/DetailEnchere").forward(request, response);
		}
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}

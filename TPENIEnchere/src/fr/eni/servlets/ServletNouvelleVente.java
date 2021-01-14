package fr.eni.servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import fr.eni.bll.ArticleManageur;
import fr.eni.bll.CategoriesManageur;
import fr.eni.bll.UtilisateurManageur;
import fr.eni.bo.Article;
import fr.eni.bo.Categories;
import fr.eni.bo.Retraits;
import fr.eni.bo.Utilisateur;
import fr.eni.message.LecteurMessage;
import fr.eni.utils.BusinessException;
import fr.eni.utils.FileSave;
import fr.eni.utils.TokenGenerator;

/**
 * Servlet implementation class ServletNouvelleVente
 */
@WebServlet("/Vente")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 10, // 10 MB
		maxRequestSize = 1024 * 1024 * 15, // 15 MB
		location = "C:\\tmp")

public class ServletNouvelleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final int TAILLE_TAMPON = 10240;
	public static final String IMAGES_FOLDER = "/Images";
	public String uploadPath;
	UtilisateurManageur utilisateurManageur = null;
	Article unArticle = null;
	Retraits unRetrait = null;
	ArticleManageur articleManageur = null;
	CategoriesManageur categoriesManageur = null;

	@Override
	public void init() throws ServletException {
		utilisateurManageur = new UtilisateurManageur();
		unArticle = new Article();
		unRetrait = new Retraits();
		articleManageur = new ArticleManageur();
		categoriesManageur = new CategoriesManageur();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<Categories> categories = categoriesManageur.selectAll();
			request.setAttribute("categories", categories);
		} catch (BusinessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/NouvelleVente").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// obtention du chemin absolue de l'application
		String appPath = request.getServletContext().getRealPath("/WebContent/images");

		// Recuperer les parametres

		String article = request.getParameter("article");
		String description = request.getParameter("description");
		int categorie = Integer.parseInt(request.getParameter("categorie"));
		int miseAPrix = Integer.parseInt(request.getParameter("prixInitial"));
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		String debutEnchere = request.getParameter("debutEnchere");
		String finEnchere = request.getParameter("finEnchere");
		int numeroUtilisateur = Integer.parseInt(request.getParameter("numeroUtilisateur"));
		Date dateDebutEnchere = Date.valueOf(debutEnchere);
		Date dateFinEnchere = Date.valueOf(finEnchere);
		// Récupération et sauvegarde du contenu de l'image.
		Part part = request.getPart("photo");
		if (part != null && part.getSize() > 0) {

			String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
			;
			// refines the fileName in case it is an absolute path
			String[] fn = fileName.split("(\\.)");
			String ext = fn[(fn.length - 1)];
			if (!ext.isEmpty()) {
				TokenGenerator token = new TokenGenerator();
				Utilisateur utilisateur;
				try {
					utilisateur = utilisateurManageur.selectId(numeroUtilisateur);
					fileName = token.generateToken(utilisateur.getPseudo()).toLowerCase() + "." + ext;
					InputStream fileContent = part.getInputStream();
					String sContext = "C:\\Users\\aurel\\git" + request.getContextPath() + "/WebContent";
					File f = new File(sContext + "/images/" + fileName);
					part.write(sContext);
					FileSave.receiveFile(fileContent, f);
					unArticle.setCheminImg(fileName);
				} catch (BusinessException e) {
					e.printStackTrace();
				}

			}

		}

		// Je recup les données saisies dans le formulaire et je les attributs à l'objet
		unArticle.setNom_article(article);
		unArticle.setDescription(description);
		unArticle.setDate_debut_encheres(dateDebutEnchere);
		unArticle.setDate_fin_encheres(dateFinEnchere);
		unArticle.setPrix_initial(miseAPrix);
		unArticle.setPrix_vente(miseAPrix);
		unArticle.setNo_utilisateur(numeroUtilisateur);
		unArticle.setNo_categorie(categorie);

		unRetrait.setRue(rue);
		unRetrait.setVille(ville);
		unRetrait.setCode_postal(codePostal);
		try {
			articleManageur.insert(unArticle, unRetrait);
		} catch (BusinessException e) {
			e.printStackTrace();
			List<Integer> codeErreur = e.getListeCodesErreur();
			List<String> messageErreur = new ArrayList<String>();
			if (!codeErreur.isEmpty()) {
				for (Integer code : codeErreur) {
					messageErreur.add(LecteurMessage.getMessageErreur(code));
				}
				request.setAttribute("erreurs", messageErreur);
			}
			this.getServletContext().getRequestDispatcher("/NouvelleVente").forward(request, response);
			return;
		}
		String MESSAGEREUSSITE = "Nouvelle article mis en vente avec succès";
		request.setAttribute("réussite", MESSAGEREUSSITE);
		// Afficher les articles de la base de données
		ArticleManageur articleManager = new ArticleManageur();
		List<Article> listeArticle = null;
		try {
			listeArticle = articleManager.selectAll();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		request.setAttribute("listeArticle", listeArticle);

		this.getServletContext().getRequestDispatcher("/PageAccueil").forward(request, response);

	}
}

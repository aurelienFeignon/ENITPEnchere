package fr.eni.servlets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import fr.eni.bll.articleManageur;
import fr.eni.bo.Article;
import fr.eni.bo.Retraits;
import fr.eni.message.LecteurMessage;
import fr.eni.utils.BusinessException;

/**
 * Servlet implementation class ServletNouvelleVente
 */
@WebServlet("/Vente")
@MultipartConfig(
		fileSizeThreshold   = 1024 * 1024 * 1,  // 1 MB
		maxFileSize         = 1024 * 1024 * 10, // 10 MB
		maxRequestSize      = 1024 * 1024 * 15, // 15 MB
		location			="/tmp"
		)

public class ServletNouvelleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final int TAILLE_TAMPON=10240;
	public static final String IMAGES_FOLDER = "/Images";
	
	
	
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/NouvelleVente").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Recuperer les parametres
		
		
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
		//Récupération et sauvegarde du contenu de l'image.
		/*if(request.getPart("photo") != null) {
			String uploadPath= getServletContext().getRealPath( IMAGES_FOLDER );
			Part part= request.getPart("photo");
			String fileName = getFileName( part );
	        String fullPath = uploadPath + File.separator + fileName;
	        part.write( fullPath );
		}*/
		
		
	//Je cree un objet 
		 Article unArticle = new Article();
		 Retraits unRetrait = new Retraits();
		 articleManageur articleManageur = new articleManageur();

		 //Je recup les données saisies dans le formulaire et je les attributs à l'objet
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
			List<Integer> codeErreur= e.getListeCodesErreur();
			List<String> messageErreur= new ArrayList<String>();
			if(!codeErreur.isEmpty()) {
				for(Integer code: codeErreur) {
					messageErreur.add(LecteurMessage.getMessageErreur(code));
				}
				request.setAttribute("erreurs", messageErreur);
			}
			this.getServletContext().getRequestDispatcher("/NouvelleVente").forward(request, response);
			return;
		}
		  String MESSAGEREUSSITE = "Nouvelle article mis en vente avec succès" ;
		 request.setAttribute("réussite", MESSAGEREUSSITE);
		//Afficher les articles de la base de données
			articleManageur articleManager  = new articleManageur();
			List<Article> listeArticle =null;
			try {
				listeArticle = articleManager.selectAll();
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			request.setAttribute("listeArticle", listeArticle);
		 
		 this.getServletContext().getRequestDispatcher("/PageAccueil").forward(request, response);
		 
	}

	   /*
     * Récupération du nom du fichier dans la requête.
     */
    private String getFileName( Part part ) {
        for ( String content : part.getHeader( "content-disposition" ).split( ";" ) ) {
            if ( content.trim().startsWith( "filename" ) )
                return content.substring( content.indexOf( "=" ) + 2, content.length() - 1 );
        }
        return "Default.file";
    }
	
	private void ecrireFichier(Part part, String nomFichier, String cheminFichiers) throws IOException {
		BufferedInputStream entree=null;
		BufferedOutputStream sortie = null;
		
		try {
			entree= new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
			sortie= new BufferedOutputStream(new FileOutputStream(new File(cheminFichiers + nomFichier)), TAILLE_TAMPON);
			
			byte[] tampon = new byte[TAILLE_TAMPON];
			int longeur;
			while((longeur= entree.read(tampon))>0) {
				sortie.write(tampon, 0, longeur);
			}
		
	}finally {
		sortie.close();
		entree.close();
	}}


}

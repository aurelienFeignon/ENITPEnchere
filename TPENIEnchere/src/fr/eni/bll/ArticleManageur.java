package fr.eni.bll;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.bo.Article;
import fr.eni.bo.Retraits;
import fr.eni.dal.ArticleDao;
import fr.eni.dal.DAOFactory;
import fr.eni.utils.BusinessException;

public class ArticleManageur {
	private ArticleDao articleDao;

	/**
	 * 
	 */
	public ArticleManageur() {
		this.articleDao = DAOFactory.getArticle();
	}

	public void delete(int id) throws BusinessException {
		this.articleDao.delete(id);

	}

	public void insert(Article article, Retraits retrait) throws BusinessException {
		BusinessException businessException = new BusinessException();
		RetraitManageur retraitManageur = new RetraitManageur();
		retrait = retraitManageur.insert(retrait);
		if (article.getCheminImg() == null) {
			this.validerArticle(article, businessException);
			if (!businessException.hasErreurs()) {
				article.setEtatVente(false);
				article.setNo_retrait(retrait.getNo_retrait());
				this.articleDao.insert(article);
			} else {
				this.validerArticle(article, businessException);
				if (!businessException.hasErreurs()) {
					article.setEtatVente(false);
					article.setNo_retrait(retrait.getNo_retrait());
					this.articleDao.insertAvecCheminImg(article);
				}
			}
		} else {
			this.validerArticle(article, businessException);
			if (!businessException.hasErreurs()) {
				article.setEtatVente(false);
				article.setNo_retrait(retrait.getNo_retrait());
				this.articleDao.insertAvecCheminImg(article);
			} else {
				throw businessException;
			}
		}
	}

	public Article selectId(int id) throws BusinessException {
		Article article = new Article();
		article = this.articleDao.selectId(id);
		return article;
	}

	public List<Article> selectCategorie(int noCategorie) throws BusinessException {
		List<Article> articles = new ArrayList<>();
		articles = this.articleDao.selectCategorie(noCategorie);
		return articles;
	}

	public List<Article> selectRechercher(String rechercher) throws BusinessException {
		List<Article> articles = new ArrayList<>();
		articles = this.articleDao.selectRechercher(rechercher);
		return articles;
	}

	public List<Article> selectCategorieRechercher(String rechercher, int noCategorie) throws BusinessException {
		List<Article> article = new ArrayList<>();
		article = this.articleDao.selectCategorieRechercher(rechercher, noCategorie);
		return article;
	}

	public List<Article> selectAll() throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		articles = this.articleDao.selectAll();
		return articles;
	}

	public List<Article> selectAchatAll(int noUtilisateur) throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		articles = this.articleDao.selectAchatAll(noUtilisateur);
		return articles;
	}

	public List<Article> selectAchatEnchereEnCour(int noUtilisateur) throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		articles = this.articleDao.selectAchatEnchereEnCour(noUtilisateur);
		return articles;
	}

	public List<Article> selectAchatEnchereRemporte(int noUtilisateur) throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		articles = this.articleDao.selectAchatEnchereRemporte(noUtilisateur);
		return articles;
	}

	public List<Article> selectVenteAll(int noUtilisateur) throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		articles = this.articleDao.selectVenteAll(noUtilisateur);
		return articles;
	}

	public List<Article> selectVenteTermine(int noUtilisateur) throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		articles = this.articleDao.selectVenteTermine(noUtilisateur);
		return articles;
	}

	public List<Article> selectVenteEnCour(int noUtilisateur) throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		articles = this.articleDao.selectVenteEnCour(noUtilisateur);
		return articles;
	}

	public List<Article> selectVenteNonDebute(int noUtilisateur) throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		articles = this.articleDao.selectVenteNonDebute(noUtilisateur);
		return articles;
	}

	public void update(Article article, int id) throws BusinessException, SQLException {

		BusinessException businessException = new BusinessException();

		this.validerArticle(article, businessException);
		if (!businessException.hasErreurs()) {
			this.articleDao.update(article, id);
		} else {
			throw businessException;
		}
	}

	public void updatePrixVente(int id, int prixDeVente) throws BusinessException, SQLException {

		BusinessException businessException = new BusinessException();

		this.validerArticleModifPrixDeVente(id, prixDeVente, businessException);
		if (!businessException.hasErreurs()) {
			this.articleDao.updatePrixVente(id, prixDeVente);
		} else {
			throw businessException;
		}
	}

	private void validerArticleModifPrixDeVente(int id, int prixDeVente, BusinessException businessException)
			throws BusinessException {
		Article article = this.selectId(id);
		if (prixDeVente < article.getPrix_vente()) {
			businessException.ajouterErreur(CodeResultatBll.ENCHERE_INFERIEUR_PRIX_DE_VENTE);
		}

	}

	private void validerArticle(Article article, BusinessException businessException) {
		this.validerNomArticle(article, businessException);
		this.validerDescription(article, businessException);
		this.validerDebutEnchere(article, businessException);
		this.validerFinEnchere(article, businessException);
		this.validerPrixInitial(article, businessException);
		this.validerPrixVente(article, businessException);
		this.validerNoUtilisateur(article, businessException);
		this.validerNoCategorie(article, businessException);
		this.validerNoRetrait(article, businessException);

	}

	private void validerNoRetrait(Article article, BusinessException businessException) {
		if (article.getNo_retrait() < 0) {
			businessException.ajouterErreur(CodeResultatBll.NO_RETRAIT_INVALIDE);
		}
	}

	private void validerNoCategorie(Article article, BusinessException businessException) {
		if (article.getNo_categorie() < 0) {
			businessException.ajouterErreur(CodeResultatBll.NO_CATEGORIE_INVALIDE);
		}
	}

	private void validerNoUtilisateur(Article article, BusinessException businessException) {
		if (article.getNo_utilisateur() < 0) {
			businessException.ajouterErreur(CodeResultatBll.NO_UTILISATEUR_INVALIDE);
		}
	}

	private void validerPrixVente(Article article, BusinessException businessException) {
		if (article.getPrix_vente() < 0) {
			businessException.ajouterErreur(CodeResultatBll.PRIX_VENTE_INVALIDE);
		}
		if (article.getPrix_initial() > article.getPrix_vente()) {
			businessException.ajouterErreur(CodeResultatBll.PRIX_VENTE_INITIAL_INFERIEUR);
		}

	}

	private void validerPrixInitial(Article article, BusinessException businessException) {
		if (article.getPrix_initial() < 0) {
			businessException.ajouterErreur(CodeResultatBll.PRIX_VENTE_INITIAL_INVALIDE);
		}

	}

	private void validerFinEnchere(Article article, BusinessException businessException) {
		if (article.getDate_debut_encheres().after(article.getDate_fin_encheres())) {
			businessException.ajouterErreur(CodeResultatBll.DATE_DEBUT_INFERIEUR);
		}

	}

	private void validerDebutEnchere(Article article, BusinessException businessException) {
		LocalDate localDate = LocalDate.now().minusDays(1);
		if (article.getDate_debut_encheres().equals(new Date())
				|| article.getDate_debut_encheres().toLocalDate().isBefore(localDate)) {
			businessException.ajouterErreur(CodeResultatBll.DATE_DEBUT_INFERIEUR_JOUR);
		}
	}

	private void validerDescription(Article article, BusinessException businessException) {
		if (article.getDescription().length() > 300 || article.getDescription() == null) {
			businessException.ajouterErreur(CodeResultatBll.DESCRITPION_INVALIDE);
		}

	}

	private void validerNomArticle(Article article, BusinessException businessException) {
		if (article.getNom_article().length() > 30) {
			businessException.ajouterErreur(CodeResultatBll.ARTICLE_NOM_INVALIDE);
		}
	}
}
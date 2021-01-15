package fr.eni.bll;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.bo.Encheres;
import fr.eni.dal.DAOFactory;
import fr.eni.dal.EncheresDao;
import fr.eni.dal.GeneriqueDao;
import fr.eni.utils.BusinessException;

/**
 * Classe en charge d'acceder au methode de la dal lié au enchere, elle verifie
 * aussi que les encheres sont conforme au exigence du projet avant insertion ou
 * modification
 * 
 * @author aurel
 * @version TPENIEnchere - v1.0
 * @date 15 janv. 2021 - 12:31:30
 */
public class EnchereManageur implements GeneriqueDao<Encheres> {
	EncheresDao enchereDao;

	/**
	 * Constructeur permetant d'obtenir une instance de EnchereDaoImpl en passant
	 * pas le Dao factory
	 */
	public EnchereManageur() {
		this.enchereDao = DAOFactory.getEnchere();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(int id) throws BusinessException {
		this.enchereDao.delete(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insert(Encheres enchere) throws BusinessException {
		BusinessException businessException = new BusinessException();
		this.validerEncheres(enchere, businessException);
		if (!businessException.hasErreurs()) {
			this.enchereDao.insert(enchere);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Encheres selectId(int id) throws BusinessException {
		Encheres enchere = this.enchereDao.selectId(id);
		return enchere;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Encheres> selectAll() throws BusinessException {
		List<Encheres> encheres = new ArrayList<Encheres>();
		encheres = this.enchereDao.selectAll();
		return encheres;
	}

	/**
	 * Méthode en charge de retourner une liste d'enchere lié à un article
	 * 
	 * @param noArticle numero de l'article
	 * @return liste d'enchere filtré
	 * @throws BusinessException
	 */
	public List<Encheres> selectHistoriqueArticle(int noArticle) throws BusinessException {
		List<Encheres> encheres = new ArrayList<Encheres>();
		encheres = this.enchereDao.selectHistoriqueArticle(noArticle);
		return encheres;
	}

	/**
	 * Méthode en charge de retourner une liste d'enchere de maniere decroissante
	 * lié à un article
	 * 
	 * @param noArticle numero de l'article
	 * @return liste d'enchere filtré
	 * @throws BusinessException
	 */
	public List<Encheres> selectHistoriqueArticleDecroissant(int noArticle) throws BusinessException {
		List<Encheres> encheres = new ArrayList<Encheres>();
		encheres = this.enchereDao.selectHistoriqueArticleDecroissant(noArticle);
		return encheres;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(Encheres enchere, int id) throws BusinessException, SQLException {
		BusinessException businessException = new BusinessException();
		this.validerEncheres(enchere, businessException);
		if (!businessException.hasErreurs()) {
			this.enchereDao.update(enchere, id);
		}

	}

	/**
	 * Méthode en charge de verifier si l'enchere proposé respecte les condition à
	 * son insertion ou modification
	 * 
	 * @param enchere           enchere a verifier
	 * @param businessException
	 */
	private void validerEncheres(Encheres enchere, BusinessException businessException) {
		this.validerDateEnchere(enchere, businessException);
		this.validerMontantEnchere(enchere, businessException);
		this.validerNoArticle(enchere, businessException);
		this.validerNoUtilisateur(enchere, businessException);

	}

	/**
	 * Méthode en charge de verifier si le numero d'utilisateur est superieur à 0
	 * 
	 * @param enchere           enchere à verifier
	 * @param businessException
	 */
	private void validerNoUtilisateur(Encheres enchere, BusinessException businessException) {
		if (enchere.getNo_utilisateur() < 0) {
			businessException.ajouterErreur(CodeResultatBll.NO_UTILISATEUR_INVALIDE);
		}
	}

	/**
	 * Méthode en charge de verifier si le numero d'article est superieur à 0
	 * 
	 * @param enchere           enchere à verifier
	 * @param businessException
	 */
	private void validerNoArticle(Encheres enchere, BusinessException businessException) {
		if (enchere.getNo_article() < 0) {
			businessException.ajouterErreur(CodeResultatBll.NO_ARTICLE_INVALIDE);
		}

	}

	/**
	 * Méthode en charge de verifier si le montant de l'enchere est superieur à 0
	 * 
	 * @param enchere           enchere à verifier
	 * @param businessException
	 */
	private void validerMontantEnchere(Encheres enchere, BusinessException businessException) {
		if (enchere.getMontant_enchere() < 0) {
			businessException.ajouterErreur(CodeResultatBll.MONTANT_INVALIDE);
		}

	}

	/**
	 * Méthode en charge de verifier si la date de l'enchere est egal ou superieur à
	 * la date du jour
	 * 
	 * @param enchere           enchere à verifier
	 * @param businessException
	 */
	private void validerDateEnchere(Encheres enchere, BusinessException businessException) {
		LocalDate localDate = LocalDate.now().minusDays(1);
		if (enchere.getDate_enchere().toLocalDate().isBefore(localDate)) {
			businessException.ajouterErreur(CodeResultatBll.DATE_DEBUT_INFERIEUR_JOUR);
		}
	}
}

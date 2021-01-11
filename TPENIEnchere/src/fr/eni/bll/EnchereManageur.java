package fr.eni.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.bo.Encheres;
import fr.eni.dal.DAOFactory;
import fr.eni.dal.EncheresDao;
import fr.eni.dal.GeneriqueDao;
import fr.eni.utils.BusinessException;

public class EnchereManageur implements GeneriqueDao<Encheres> {
	EncheresDao enchereDao;
	
	
	/**
	 * 
	 */
	public EnchereManageur() {
		this.enchereDao = DAOFactory.getEnchere();
	}

	@Override
	public void delete(int id) throws BusinessException {
		this.enchereDao.delete(id);
	}

	@Override
	public void insert(Encheres enchere) throws BusinessException {
		BusinessException businessException= new BusinessException();
		this.validerEncheres(enchere, businessException);
		if(!businessException.hasErreurs()) {
			this.enchereDao.insert(enchere);
		}
		
	}

	
	@Override
	public Encheres selectId(int id) throws BusinessException {
		Encheres enchere= this.enchereDao.selectId(id);
		return enchere;
	}

	@Override
	public List<Encheres> selectAll() throws BusinessException {
		List<Encheres> encheres= new ArrayList<Encheres>();
		encheres= this.enchereDao.selectAll();
		return encheres;
	}
	
	public List<Encheres> selectHistoriqueArticle(int noArticle) throws BusinessException {
		List<Encheres> encheres= new ArrayList<Encheres>();
		encheres= this.enchereDao.selectHistoriqueArticle(noArticle);
		return encheres;
	}

	
	@Override
	public void update(Encheres enchere, int id) throws BusinessException, SQLException {
		BusinessException businessException= new BusinessException();
		this.validerEncheres(enchere, businessException);
		if(!businessException.hasErreurs()) {
			this.enchereDao.update(enchere, id);
		}
		
	}

	private void validerEncheres(Encheres enchere, BusinessException businessException) {
		this.validerDateEnchere(enchere, businessException);
		this.validerMontantEnchere(enchere, businessException);
		this.validerNoArticle(enchere, businessException);
		this.validerNoUtilisateur(enchere, businessException);
		
	}

	private void validerNoUtilisateur(Encheres enchere, BusinessException businessException) {
		if(enchere.getNo_utilisateur()<0) {
			businessException.ajouterErreur(CodeResultatBll.NO_UTILISATEUR_INVALIDE);
		}
	}

	private void validerNoArticle(Encheres enchere, BusinessException businessException) {
		if(enchere.getNo_article()<0) {
			businessException.ajouterErreur(CodeResultatBll.NO_ARTICLE_INVALIDE);
		}
		
	}

	private void validerMontantEnchere(Encheres enchere, BusinessException businessException) {
		if(enchere.getMontant_enchere()<0) {
			businessException.ajouterErreur(CodeResultatBll.MONTANT_INVALIDE);
		}
		
	}

	private void validerDateEnchere(Encheres enchere, BusinessException businessException) {
		if(enchere.getDate_enchere().before(new Date())) {
			businessException.ajouterErreur(CodeResultatBll.DATE_DEBUT_INFERIEUR_JOUR);
		}
	}
}

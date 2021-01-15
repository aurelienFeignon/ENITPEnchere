package fr.eni.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.bo.Retraits;
import fr.eni.dal.DAOFactory;
import fr.eni.dal.RetraitDao;
import fr.eni.utils.BusinessException;

/**
 * Classe en charge d'acceder au methode de la dal lié au retrait, cette classe
 * verifie aussi les données avant insertion et update
 * 
 * @author aurel
 * @version TPENIEnchere - v1.0
 * @date 15 janv. 2021 - 14:12:20
 */
public class RetraitManageur {
	private RetraitDao retraitDao;

	/**
	 * 
	 */
	/**
	 * Constructeur permetant d'obtenir une instance de retraitDaoImpl
	 */
	public RetraitManageur() {
		this.retraitDao = DAOFactory.getRetrait();
	}

	/**
	 * Méthode en charge de supprimer un retrait
	 * 
	 * @param id id du retrait a supprimer
	 * @throws BusinessException
	 */
	public void delete(int id) throws BusinessException {

		this.retraitDao.delete(id);
	}

	/**
	 * Méthode en charge de d'inserer un retrait
	 * 
	 * @param retrait retrait à inserer
	 * @return
	 * @throws BusinessException
	 */
	public Retraits insert(Retraits retrait) throws BusinessException {
		BusinessException businessException = new BusinessException();
		this.validerRetrait(retrait, businessException);
		if (!businessException.hasErreurs()) {
			retrait = this.retraitDao.insert(retrait);
		}
		return retrait;
	}

	/**
	 * Méthode en charge de selectionner un retrait par son id
	 * 
	 * @param id du retrait
	 * @return le retrait
	 * @throws BusinessException
	 */
	public Retraits selectId(int id) throws BusinessException {
		Retraits retrait = this.retraitDao.selectId(id);
		return retrait;
	}

	/**
	 * Méthode en charge de selectionner tout les retraits
	 * 
	 * @return la liste de retrait
	 * @throws BusinessException
	 */
	public List<Retraits> selectAll() throws BusinessException {
		List<Retraits> retraits = new ArrayList<Retraits>();
		retraits = this.retraitDao.selectAll();
		return retraits;
	}

	/**
	 * Méthode en charge d'update un retrait
	 * 
	 * @param retrait retrait à modifier
	 * @param id      du retrait à modifier
	 * @throws BusinessException
	 * @throws SQLException
	 */
	public void update(Retraits retrait, int id) throws BusinessException, SQLException {
		BusinessException businessException = new BusinessException();
		this.validerRetrait(retrait, businessException);
		if (!businessException.hasErreurs()) {
			this.retraitDao.update(retrait, id);
		}
	}

	/**
	 * Méthode en charge de valider le retrait
	 * 
	 * @param retrait           retrait a valider
	 * @param businessException
	 */
	private void validerRetrait(Retraits retrait, BusinessException businessException) {
		this.validerRue(retrait, businessException);
		this.validerCodePostal(retrait, businessException);
		this.validerVille(retrait, businessException);
	}

	/**
	 * Méthode en charge de valider que l'attribut rue n'est pas null et qu'il ne
	 * fait pas plus de 30 lettres
	 * 
	 * @param retrait
	 * @param businessException
	 */
	private void validerRue(Retraits retrait, BusinessException businessException) {
		if (retrait.getRue() == null || retrait.getRue().length() > 30) {
			businessException.ajouterErreur(CodeResultatBll.RUE_INVALIDE);
		}
	}

	/**
	 * Méthode en charge de verifier que le code postal est composé de 5 chiffres
	 * 
	 * @param retrait
	 * @param businessException
	 */
	private void validerCodePostal(Retraits retrait, BusinessException businessException) {
		if (retrait.getCode_postal() == null || !(retrait.getCode_postal().length() == 5)
				|| !retrait.getCode_postal().matches("-?\\d+(\\.\\d+)?")) {
			businessException.ajouterErreur(CodeResultatBll.CODE_POSTAL_INVALIDE);
		}
	}

	/**
	 * Méthode en charge de de verifier que la ville
	 * 
	 * @param retrait
	 * @param businessException
	 */
	private void validerVille(Retraits retrait, BusinessException businessException) {
		if (retrait.getVille() == null || retrait.getVille().length() > 30) {
			businessException.ajouterErreur(CodeResultatBll.VILLE_INVALIDE);
		}
	}
}

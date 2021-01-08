package fr.eni.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.bo.Retraits;
import fr.eni.dal.DAOFactory;
import fr.eni.dal.GeneriqueDao;
import fr.eni.dal.RetraitDao;
import fr.eni.utils.BusinessException;

public class RetraitManageur implements GeneriqueDao<Retraits> {
	private RetraitDao retraitDao;
	
	
	
	/**
	 * 
	 */
	public RetraitManageur() {
		this.retraitDao= DAOFactory.getRetrait();
	}

	@Override
	public void delete(int id) throws BusinessException {
		
		this.retraitDao.delete(id);
	}

	@Override
	public void insert(Retraits retrait) throws BusinessException {
		BusinessException businessException= new BusinessException();
		this.validerRetrait(retrait, businessException);
		if(!businessException.hasErreurs()) {
			this.retraitDao.insert(retrait);
		}
	}

	

	

	@Override
	public Retraits selectId(int id) throws BusinessException {
		Retraits retrait= this.retraitDao.selectId(id);
		return retrait;
	}

	@Override
	public List<Retraits> selectAll() throws BusinessException {
		List<Retraits> retraits= new ArrayList<Retraits>();
		retraits= this.retraitDao.selectAll();
		return retraits;
	}

	@Override
	public void update(Retraits retrait, int id) throws BusinessException, SQLException {
		BusinessException businessException= new BusinessException();
		this.validerRetrait(retrait, businessException);
		if(!businessException.hasErreurs()) {
			this.retraitDao.update(retrait, id);
		}
	}

	private void validerRetrait(Retraits retrait, BusinessException businessException) {
		this.validerRue(retrait, businessException);
		this.validerCodePostal(retrait, businessException);
		this.validerVille(retrait, businessException);
	}

	private void validerRue(Retraits retrait, BusinessException businessException) {
		if(retrait.getRue()==null||retrait.getRue().length()>30) {
			businessException.ajouterErreur(CodeResultatBll.RUE_INVALIDE);
		}
	}
	//verifie que le numero de telephone est composé de 5 chiffres
	private void validerCodePostal(Retraits retrait, BusinessException businessException) {
		if(retrait.getCode_postal()==null||!(retrait.getCode_postal().length()==5)||!retrait.getCode_postal().matches("-?\\d+(\\.\\d+)?")) {
			businessException.ajouterErreur(CodeResultatBll.CODE_POSTAL_INVALIDE);
		}
	}

	private void validerVille(Retraits retrait, BusinessException businessException) {
		if(retrait.getVille()==null||retrait.getVille().length()>30) {
			businessException.ajouterErreur(CodeResultatBll.VILLE_INVALIDE);
		}
	}
}

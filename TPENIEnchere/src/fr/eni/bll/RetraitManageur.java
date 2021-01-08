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
		this.retraitDao.insert(retrait);
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
		this.retraitDao.update(retrait, id);
	}

}

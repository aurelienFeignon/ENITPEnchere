package fr.eni.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.bo.Encheres;

import fr.eni.utils.BusinessException;

public interface EncheresDao extends GeneriqueDao<Encheres>{

	@Override
	public void delete(int id) throws BusinessException ;
	@Override
	public void insert(Encheres enchere) throws BusinessException ;

	@Override
	public Encheres selectId(int id) throws BusinessException;

	@Override
	public List<Encheres> selectAll() throws BusinessException ;

	@Override
	public void update(Encheres enchere, int id) throws BusinessException, SQLException ;
	
	public List<Encheres> selectHistoriqueArticle(int noArticle)throws BusinessException;
	

}

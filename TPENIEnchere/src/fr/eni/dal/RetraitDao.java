package fr.eni.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.bo.Retraits;
import fr.eni.utils.BusinessException;

public interface RetraitDao  {

	
	public void delete(int id) throws BusinessException ;
	
	public Retraits insert(Retraits t) throws BusinessException;

	
	public Retraits selectId(int id) throws BusinessException ;
	
	public List<Retraits> selectAll() throws BusinessException ;

	
	public void update(Retraits t, int id) throws BusinessException, SQLException ;

}

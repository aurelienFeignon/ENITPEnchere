package fr.eni.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.bo.Retraits;
import fr.eni.utils.BusinessException;

public interface RetraitDao extends GeneriqueDao<Retraits> {

	@Override
	public void delete(int id) throws BusinessException ;
	@Override
	public void insert(Retraits t) throws BusinessException;

	@Override
	public Retraits selectId(int id) throws BusinessException ;
	@Override
	public List<Retraits> selectAll() throws BusinessException ;

	@Override
	public void update(Retraits t, int id) throws BusinessException, SQLException ;

}

package fr.eni.dal;

import java.sql.SQLException;
import java.util.List;


import fr.eni.utils.BusinessException;

public interface GeneriqueDao <T>{

	public void delete(int id) throws BusinessException;
	public void insert(T t) throws BusinessException;
	public T selectId(int id) throws BusinessException;
	public List<T> selectAll() throws BusinessException;
	public void update(T t, int id) throws BusinessException, SQLException;
}

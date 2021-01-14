package fr.eni.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.bo.Categories;
import fr.eni.utils.BusinessException;

public interface CategoriesDao {

	public Categories selectId(int noCategorie) throws BusinessException;

	public List<Categories> selectAll() throws BusinessException, SQLException;
}

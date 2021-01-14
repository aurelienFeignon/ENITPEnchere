package fr.eni.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.bo.Categories;
import fr.eni.utils.BusinessException;

/**
 * Interface en charge de la gestion en bd des categories
 * 
 * @author aurel
 * @version TPENIEnchere - v1.0
 * @date 14 janv. 2021 - 15:59:29
 */
public interface CategoriesDao {

	/**
	 * Méthode en charge de
	 * 
	 * @param noCategorie
	 * @return
	 * @throws BusinessException
	 */
	public Categories selectId(int noCategorie) throws BusinessException;

	public List<Categories> selectAll() throws BusinessException, SQLException;
}

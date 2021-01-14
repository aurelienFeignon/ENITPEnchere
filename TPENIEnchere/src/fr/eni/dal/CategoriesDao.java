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
	 * Méthode en charge de selectionner une categorie en fonction du numero de
	 * categorie
	 * 
	 * @param noCategorie numero de categorie
	 * @return une categorie
	 * @throws BusinessException
	 */
	public Categories selectId(int noCategorie) throws BusinessException;

	/**
	 * Méthode en charge de selectionner toutes les categories
	 * 
	 * @return la liste complete des categories
	 * @throws BusinessException
	 * @throws SQLException
	 */
	public List<Categories> selectAll() throws BusinessException, SQLException;

	/**
	 * Méthode en charge d'inserer en bd une nouvelle categorie
	 * 
	 * @param categorie nom de la categorie a ajouter
	 * @throws BusinessException
	 * @throws SQLException
	 */
	public void insert(String categorie) throws BusinessException, SQLException;
}

package fr.eni.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.bo.Categories;
import fr.eni.dal.CategoriesDao;
import fr.eni.dal.DAOFactory;
import fr.eni.utils.BusinessException;

/**
 * Classe en charge d'acceder methode de la dal lié au categorie
 * 
 * @author aurel
 * @version TPENIEnchere - v1.0
 * @date 14 janv. 2021 - 16:24:29
 */
public class CategoriesManageur {
	private CategoriesDao categorieDao;

	/**
	 * 
	 */
	/**
	 * Constructeur permetant d'obtenir une instance de categorie dao impl
	 */
	public CategoriesManageur() {
		this.categorieDao = DAOFactory.getCategorie();
	}

	/**
	 * Méthode en charge de selectioner une categorie par son numero de categorie
	 * 
	 * @param noCategorie numero de categorie
	 * @return un objet categorie
	 * @throws BusinessException
	 */
	public Categories selectId(int noCategorie) throws BusinessException {
		Categories categorie = this.categorieDao.selectId(noCategorie);
		return categorie;

	}

	/**
	 * Méthode en charge de selectionner toutes les categories
	 * 
	 * @return une liste de categorie
	 * @throws BusinessException
	 * @throws SQLException
	 */
	public List<Categories> selectAll() throws BusinessException, SQLException {
		List<Categories> categories = this.categorieDao.selectAll();
		return categories;
	}

	/**
	 * Méthode en charge d'inserer une categorie en bd
	 * 
	 * @param categorie nom de la categorie
	 * @throws BusinessException
	 * @throws SQLException
	 */
	public void insert(String nomCategorie) throws BusinessException, SQLException {
		this.categorieDao.insert(nomCategorie);
	}
}

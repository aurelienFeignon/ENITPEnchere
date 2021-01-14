package fr.eni.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.bo.Categories;
import fr.eni.dal.CategoriesDao;
import fr.eni.dal.DAOFactory;
import fr.eni.utils.BusinessException;

public class CategoriesManageur {
	private CategoriesDao categorieDao;

	/**
	 * 
	 */
	public CategoriesManageur() {
		this.categorieDao = DAOFactory.getCategorie();
	}

	public Categories selectId(int noCategorie) throws BusinessException {
		Categories categorie = this.categorieDao.selectId(noCategorie);
		return categorie;

	}

	public List<Categories> selectAll() throws BusinessException, SQLException {
		List<Categories> categories = this.categorieDao.selectAll();
		return categories;
	}
}

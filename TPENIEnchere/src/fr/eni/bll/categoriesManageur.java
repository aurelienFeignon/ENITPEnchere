package fr.eni.bll;

import fr.eni.bo.Categories;
import fr.eni.dal.DAOFactory;
import fr.eni.dal.categoriesDao;
import fr.eni.utils.BusinessException;

public class categoriesManageur {
	private categoriesDao categorieDao;

	/**
	 * 
	 */
	public categoriesManageur() {
		this.categorieDao= DAOFactory.getCategorie();
	}
	
	public Categories selectId(int noCategorie) throws BusinessException {
		Categories categorie= this.categorieDao.selectId(noCategorie);
		return categorie;
		
	}

}

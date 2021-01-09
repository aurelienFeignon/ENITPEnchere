package fr.eni.dal;

import fr.eni.bo.Categories;
import fr.eni.utils.BusinessException;

public interface categoriesDao {

	public Categories selectId(int noCategorie) throws BusinessException;
}

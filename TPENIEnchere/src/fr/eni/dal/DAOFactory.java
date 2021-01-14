package fr.eni.dal;

/**
 * Classe en charge d'obtenir des instances des classes implementé dans la dal
 * 
 * @author aurel
 * @version TPENIEnchere - v1.0
 * @date 14 janv. 2021 - 16:41:41
 */
public abstract class DAOFactory {

	/**
	 * Méthode en charge d'obetnir une instance de utilisateurDaoImpl
	 * 
	 * @return instance de utilisateurDaoImpl
	 */
	public static UtilisateurDao getUtilisateur() {
		return new UtilisateurDaoImpl();
	}

	/**
	 * Méthode en charge d'obetnir une instance de enchereDaoImpl
	 * 
	 * @return une instance de enchereDaoImpl
	 */
	public static EncheresDao getEnchere() {
		return new EncheresDaoImpl();
	}

	/**
	 * Méthode en charge d'obtenir une instance de retraitDaoImpl
	 * 
	 * @return une instance de retraitDaoImpl
	 */
	public static RetraitDao getRetrait() {
		return new RetraitDaoImpl();
	}

	/**
	 * Méthode en charge d'obtenir une instance de articleDaoImpl
	 * 
	 * @return une instance de articleDaoImpl
	 */
	public static ArticleDao getArticle() {
		return new ArticleDaoImpl();
	}

	/**
	 * Méthode en charge d'obtenir une instance de categorieDaoImpl
	 * 
	 * @return une instance de categorieDaoImpl
	 */
	public static CategoriesDao getCategorie() {
		return new CategoriesDaoImpl();
	}
}

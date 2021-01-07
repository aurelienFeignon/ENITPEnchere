package fr.eni.dal;

public abstract class DAOFactory {
	
	public static UtilisateurDao getUtilisateur() {
		return new UtilisateurDaoImpl();
	}
	
	public static EncheresDao getEnchere() {
		return new EncheresDaoImpl();
	}
	
	public static RetraitDao getRetrait() {
		return new RetraitDaoImpl();
	}
	public static ArticleDao getArticle() {
		return new ArticleDaoImpl();
	}
}

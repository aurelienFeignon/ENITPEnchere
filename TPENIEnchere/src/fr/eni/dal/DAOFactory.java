package fr.eni.dal;

public abstract class DAOFactory {
	
	public static UtilisateurDao getUtilisateur() {
		return new UtilisateurDaoImpl();
	}

}

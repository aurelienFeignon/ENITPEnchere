package fr.eni.dal;


import fr.eni.bo.Utilisateur;
import fr.eni.utils.BusinessException;

public interface UtilisateurDao {
	
	public void delete(int id) throws BusinessException;
	public void insert(Utilisateur utilisateur) throws BusinessException;
}

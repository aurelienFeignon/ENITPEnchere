package fr.eni.dal;


import java.sql.SQLException;
import java.util.List;

import fr.eni.bo.Utilisateur;
import fr.eni.utils.BusinessException;

public interface UtilisateurDao {
	
	public void delete(int id) throws BusinessException;
	public void insert(Utilisateur utilisateur) throws BusinessException;
	public Utilisateur selectId(int id) throws BusinessException;
	public Utilisateur selectPseudo(String pseudo) throws BusinessException;
	public List<Utilisateur> selectAll() throws BusinessException;
	public void update(Utilisateur utilisateur, int id) throws BusinessException, SQLException;
}

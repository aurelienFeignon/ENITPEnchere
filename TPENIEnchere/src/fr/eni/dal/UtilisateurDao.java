package fr.eni.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.bo.Utilisateur;
import fr.eni.utils.BusinessException;

/**
 * Classe en charge de la gestion en bd des utilisateurs
 * 
 * @author aurel
 * @version TPENIEnchere - v1.0
 * @date 15 janv. 2021 - 14:56:27
 */
public interface UtilisateurDao extends GeneriqueDao<Utilisateur> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(int id) throws BusinessException;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insert(Utilisateur utilisateur) throws BusinessException;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Utilisateur selectId(int id) throws BusinessException;

	/**
	 * Méthode en charge de selectionner un utilisateur par son pseudo
	 * 
	 * @param pseudo pseudo de l'utilisateur
	 * @return un utilisateur si il existe en bd
	 * @throws BusinessException
	 */
	public Utilisateur selectPseudo(String pseudo) throws BusinessException;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Utilisateur> selectAll() throws BusinessException;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(Utilisateur utilisateur, int id) throws BusinessException, SQLException;

	/**
	 * Méthode en charge d'update le prix de vente
	 * 
	 * @param utilisateur utilisateur à update
	 * @throws BusinessException
	 * @throws SQLException
	 */
	public void updateCredit(Utilisateur utilisateur) throws BusinessException, SQLException;
}

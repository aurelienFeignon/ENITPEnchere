package fr.eni.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.bo.Retraits;
import fr.eni.utils.BusinessException;

public interface RetraitDao {

	/**
	 * Méthode en charge de supprimer en bd un retrait par son id
	 * 
	 * @param id id du retrait à supprimer
	 * @throws BusinessException
	 */
	public void delete(int id) throws BusinessException;

	/**
	 * Méthode en charge de d'inserer un retrait en bd
	 * 
	 * @param retrait à inserer
	 * @return l'objet retrait avec son id
	 * @throws BusinessException
	 */
	public Retraits insert(Retraits retrait) throws BusinessException;

	/**
	 * Méthode en charge de selectionner un retrait par son id
	 * 
	 * @param id du retrait
	 * @return un objet retrait
	 * @throws BusinessException
	 */
	public Retraits selectId(int id) throws BusinessException;

	/**
	 * Méthode en charge de selectionner tout les retraits
	 * 
	 * @return liste de retrait
	 * @throws BusinessException
	 */
	public List<Retraits> selectAll() throws BusinessException;

	/**
	 * Méthode en charge d'update un retrait
	 * 
	 * @param retrait le retrait a update
	 * @param id      l'id du retrait à modifier
	 * @throws BusinessException
	 * @throws SQLException
	 */
	public void update(Retraits retrait, int id) throws BusinessException, SQLException;

}

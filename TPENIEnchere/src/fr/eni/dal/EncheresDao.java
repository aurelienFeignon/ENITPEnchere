package fr.eni.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.bo.Encheres;
import fr.eni.utils.BusinessException;

public interface EncheresDao extends GeneriqueDao<Encheres> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(int id) throws BusinessException;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insert(Encheres enchere) throws BusinessException;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Encheres selectId(int id) throws BusinessException;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Encheres> selectAll() throws BusinessException;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(Encheres enchere, int id) throws BusinessException, SQLException;

	/**
	 * Méthode en charge de selectionner toute les encheres faites sur un article
	 * 
	 * @param noArticle numero de l'article
	 * @return une liste d'enchere filtré
	 * @throws BusinessException
	 */
	public List<Encheres> selectHistoriqueArticle(int noArticle) throws BusinessException;

	/**
	 * Méthode en charge de selectionner toute les encheres faites sur un article
	 * dans l'ordre decroissant du montant des encheres
	 * 
	 * @param noArticle numero de l'article
	 * @return une liste d'enchere filtré
	 * @throws BusinessException
	 */
	public List<Encheres> selectHistoriqueArticleDecroissant(int noArticle) throws BusinessException;
}

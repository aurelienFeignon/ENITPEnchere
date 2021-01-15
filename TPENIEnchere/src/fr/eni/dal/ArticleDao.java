package fr.eni.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.bo.Article;
import fr.eni.utils.BusinessException;

/**
 * Classe en charge de la gestion en bd des article
 * 
 * @author aurel
 * @version TPENIEnchere - v1.0
 * @date 14 janv. 2021 - 13:42:47
 */
public interface ArticleDao extends GeneriqueDao<Article> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(int id) throws BusinessException;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insert(Article t) throws BusinessException;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Article selectId(int id) throws BusinessException;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Article> selectAll() throws BusinessException;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(Article t, int id) throws BusinessException, SQLException;

	/**
	 * Méthode en charge de selectionner les articles en fonction d'une categorie
	 * 
	 * @param noCategorie à filtrer
	 * @return La liste d'article filtré par le no de categorie
	 * @throws BusinessException
	 */
	public List<Article> selectCategorie(int noCategorie) throws BusinessException;

	/**
	 * Méthode en charge de selectionner les articles par mot clef
	 * 
	 * @param String du mot clef à rechercher
	 * @return La liste d'article filtré par le mot clef
	 * @throws BusinessException
	 */
	public List<Article> selectRechercher(String rechercher) throws BusinessException;

	/**
	 * Méthode en charge de selectionner les articles par mot clef et no de
	 * categorie
	 * 
	 * @param rechercher  mot clef à rechercher
	 * @param noCategorie La liste d'article filtré par le no de categorie
	 * @return La liste d'article filtré par le mot clef et le no de categorie
	 * @throws BusinessException
	 */
	public List<Article> selectCategorieRechercher(String rechercher, int noCategorie) throws BusinessException;

	/**
	 * Méthode en charge de selectionner les articles des autres utilisateurs
	 * 
	 * @param noUtilisateur no utilisateur de l'acheteur
	 * @return La liste d'article à acheter par l'utilisateur filtré
	 * @throws BusinessException
	 */
	public List<Article> selectAchatAll(int noUtilisateur) throws BusinessException;

	/**
	 * Méthode en charge d'update en bd le prix de vente d'un article
	 * 
	 * @param prixDeVente prix de la proposition
	 * @param id          id de l'article à modifier
	 * @throws BusinessException
	 * @throws SQLException
	 */
	public void updatePrixVente(int prixDeVente, int id) throws BusinessException, SQLException;

	/**
	 * Méthode en charge de selectionner les articles sur lequel l'utilisateur à
	 * encherie
	 * 
	 * @param noUtilisateur numero de l'utilisateur
	 * @return La liste d'objet filtré
	 * @throws BusinessException
	 */
	public List<Article> selectAchatEnchereEnCour(int noUtilisateur) throws BusinessException;

	/**
	 * Méthode en charge de selectionner les encheres remporté par l'utilisateur
	 * 
	 * @param noUtilisateur numero d'utilisateur
	 * @return La liste d'article filtré
	 * @throws BusinessException
	 */
	public List<Article> selectAchatEnchereRemporte(int noUtilisateur) throws BusinessException;

	/**
	 * Méthode en charge de selectionner tout les articles que l'utilisateur à mis
	 * en vente
	 * 
	 * @param noUtilisateur numero d'utilisateur
	 * @return La liste d'article filtré
	 * @throws BusinessException
	 */
	public List<Article> selectVenteAll(int noUtilisateur) throws BusinessException;

	/**
	 * Méthode en charge de selectionner tout les articles mise en vente par
	 * l'utilisateur ou la date de fin d'enchere est passé
	 * 
	 * @param noUtilisateur numero d'utilisateur
	 * @return La liste d'article filtré
	 * @throws BusinessException
	 */
	public List<Article> selectVenteTermine(int noUtilisateur) throws BusinessException;

	/**
	 * Méthode en charge de selectionner tout les articles mise en vente par
	 * l'utilisateur ou l'enchere est en cour
	 * 
	 * @param noUtilisateur
	 * @return La liste d'article filtré
	 * @throws BusinessException
	 */
	public List<Article> selectVenteEnCour(int noUtilisateur) throws BusinessException;

	/**
	 * Méthode en charge de selectionner tout les articles mise en vente par
	 * l'utilisateur ou l'enchere n'a pas debutée
	 * 
	 * @param noUtilisateur numero utilisateur
	 * @return La liste d'article filtré
	 * @throws BusinessException
	 */
	public List<Article> selectVenteNonDebute(int noUtilisateur) throws BusinessException;

	/**
	 * Méthode en charge d'inserer un article avec le chemin de l'image
	 * 
	 * @param article article à inserer
	 * @throws BusinessException
	 */
	public void insertAvecCheminImg(Article article) throws BusinessException;
}

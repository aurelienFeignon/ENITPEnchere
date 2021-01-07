package fr.eni.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.bo.Article;
import fr.eni.dal.ArticleDao;
import fr.eni.dal.DAOFactory;
import fr.eni.dal.GeneriqueDao;
import fr.eni.utils.BusinessException;

public class articleManageur implements GeneriqueDao<Article> {
	private ArticleDao articleDao;
	

	/**
	 * 
	 */
	public articleManageur() {
		this.articleDao= DAOFactory.getArticle();
	}

	@Override
	public void delete(int id) throws BusinessException {
		this.articleDao.delete(id);
		
	}

	@Override
	public void insert(Article article) throws BusinessException {
		BusinessException businessException = new BusinessException();
		
		this.validerArticle(article, businessException);
		if(!businessException.hasErreurs()) {
		this.articleDao.insert(article);
		}else {
			throw businessException;
		}
	}

	

	@Override
	public Article selectId(int id) throws BusinessException {
		Article article = new Article();
		article= this.articleDao.selectId(id);
		return article;
	}

	@Override
	public List<Article> selectAll() throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		articles = this.articleDao.selectAll();
		return articles;
	}

	@Override
	public void update(Article article, int id) throws BusinessException, SQLException {
		
	BusinessException businessException = new BusinessException();
			
	this.validerArticle(article, businessException);
	if(!businessException.hasErreurs()) {
		this.articleDao.update(article, id);
	}else {
		throw businessException;
	}
	}

	private void validerArticle(Article article, BusinessException businessException) {
		this.validerNomArticle(article, businessException);
		this.validerDescription(article, businessException);
		this.validerDebutEnchere(article, businessException);
		this.validerFinEnchere(article, businessException);
		this.validerPrixInitial(article, businessException);
		this.validerPrixVente(article, businessException);
		this.validerNoUtilisateur(article, businessException);
		this.validerNoCategorie(article, businessException);
		this.validerNoRetrait(article, businessException);
		
	}

	private void validerNoRetrait(Article article, BusinessException businessException) {
		// TODO Auto-generated method stub
		
	}

	private void validerNoCategorie(Article article, BusinessException businessException) {
		// TODO Auto-generated method stub
		
	}

	private void validerNoUtilisateur(Article article, BusinessException businessException) {
		// TODO Auto-generated method stub
		
	}

	private void validerPrixVente(Article article, BusinessException businessException) {
		// TODO Auto-generated method stub
		
	}

	private void validerPrixInitial(Article article, BusinessException businessException) {
		// TODO Auto-generated method stub
		
	}

	private void validerFinEnchere(Article article, BusinessException businessException) {
		// TODO Auto-generated method stub
		
	}

	private void validerDebutEnchere(Article article, BusinessException businessException) {
		// TODO Auto-generated method stub
		
	}

	private void validerDescription(Article article, BusinessException businessException) {
		// TODO Auto-generated method stub
		
	}

	private void validerNomArticle(Article article, BusinessException businessException) {
		// TODO Auto-generated method stub
		
	}
}

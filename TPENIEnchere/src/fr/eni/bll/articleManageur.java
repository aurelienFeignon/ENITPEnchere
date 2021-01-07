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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Article t) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Article selectId(int id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> selectAll() throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		articles = this.articleDao.selectAll();
		return articles;
	}

	@Override
	public void update(Article t, int id) throws BusinessException, SQLException {
		// TODO Auto-generated method stub
		
	}

}

package fr.eni.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.bo.Article;
import fr.eni.utils.BusinessException;

public class ArticleDaoImpl implements ArticleDao {
	private static final String DELETE="delete from ARTICLES_VENDUS where no_article=?";
	private static final String INSERT="insert into ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, no_retrait ) "+
			"values(?,?,?,?,?,?,?,?,?)";
	private static final String SELECT_ID="select * from ARTICLES_VENDUS where no_article=?";
	private static final String SELECT_ALL="select * from ARTICLES_VENDUS";
	private static final String SELECT_NO_CATEGORIE="select * from ARTICLES_VENDUS where no_categorie=?";
	private static final String SELECT_RECHERCHER="select * from ARTICLES_VENDUS where nom_article like '%' + ? + '%' ";
	private static final String SELECT_RECHERCHER_CATEGORIE="select * from ARTICLES_VENDUS where nom_article like '%' + ? + '%' and no_categorie=?";
	private static final String SELECT_ACHAT_ALL="select * from ARTICLES_VENDUS where no_utilisateur not in ?";
	private static final String UPDATE="update UTILISATEURS Set nom_article= ?,description=?, date_debut_encheres=?, date_fin_encheres=?, prix_initial=?,prix_vente=?, no_utilisateur=?,no_categorie=?, no_retrait=? where no_article=?";
	private static final String UPDATE_PRIX_DE_VENTE="update UTILISATEURS Set  prix_vente=? where no_article=?";

	@Override
	public void delete(int id) throws BusinessException {
		try(Connection cnx= ConnectionProvider.getConnection()){
			PreparedStatement stm= cnx.prepareStatement(DELETE);
			stm.setInt(1, id);
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SUPPRESSION_ARTICLE);
			throw businessException;
		}

	}

	@Override
	public void insert(Article article) throws BusinessException {
		if(article==null)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		try(Connection cnx= ConnectionProvider.getConnection()){
			PreparedStatement stm = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			stm.setString(1, article.getNom_article());
			stm.setString(2, article.getDescription());
			stm.setDate(3, article.getDate_debut_encheres());
			stm.setDate(4, article.getDate_fin_encheres());
			stm.setInt(5, article.getPrix_initial());
			stm.setInt(6, article.getPrix_vente());
			stm.setInt(7, article.getNo_utilisateur());
			stm.setInt(8, article.getNo_categorie());
			stm.setInt(9, article.getNo_retrait());
			
			stm.executeUpdate();
			ResultSet rs = stm.getGeneratedKeys();
			
			if(rs.next()) {
				article.setNo_utilisateur(rs.getInt(1));
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERTION_ARTICLE);
			throw businessException;
		}
	}

	@Override
	public Article selectId(int id) throws BusinessException {
		Article article= null;
		try (Connection cnx= ConnectionProvider.getConnection()){
			PreparedStatement stm = cnx.prepareStatement(SELECT_ID);
			stm.setInt(1, id);
			ResultSet rs= stm.executeQuery();
			if(rs.next()) {
				article=this.articleConstructeur(rs);
			}else {
				BusinessException businessException= new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.SELECT_ID_INEXISTANT);
				throw businessException;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException= new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.CONNECTION_DAL);
			throw businessException;
		}
		
		return article;
	}
	
	public List<Article> selectCategorie(int noCategorie) throws BusinessException {
		List<Article> articles= new ArrayList<Article>();
		try (Connection cnx= ConnectionProvider.getConnection()){
			PreparedStatement stm = cnx.prepareStatement(SELECT_NO_CATEGORIE);
			stm.setInt(1, noCategorie);
			ResultSet rs= stm.executeQuery();
			while(rs.next()) {
				articles.add(this.articleConstructeur(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException= new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.CONNECTION_DAL);
			throw businessException;
		}
		
		return articles;
	}
	
	public List<Article> selectRechercher(String rechercher) throws BusinessException {
		List<Article> articles= new ArrayList<Article>();
		try (Connection cnx= ConnectionProvider.getConnection()){
			PreparedStatement stm = cnx.prepareStatement(SELECT_RECHERCHER);
			stm.setString(1, rechercher);
			ResultSet rs= stm.executeQuery();
			while(rs.next()) {
				articles.add(this.articleConstructeur(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException= new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.CONNECTION_DAL);
			throw businessException;
		}
		
		return articles;
	}
	
	public List<Article> selectCategorieRechercher(String rechercher, int noCategorie) throws BusinessException {
		List<Article> articles= new ArrayList<Article>();
		try (Connection cnx= ConnectionProvider.getConnection()){
			PreparedStatement stm = cnx.prepareStatement(SELECT_RECHERCHER_CATEGORIE);
			stm.setString(1, rechercher);
			stm.setInt(2, noCategorie);
			ResultSet rs= stm.executeQuery();
			while(rs.next()) {
				articles.add(this.articleConstructeur(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException= new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.CONNECTION_DAL);
			throw businessException;
		}
		
		return articles;
	}

	public List<Article> selectAchatAll(int noUtilisateur) throws BusinessException {
		List<Article> articles= new ArrayList<Article>();
		try (Connection cnx= ConnectionProvider.getConnection()){
			PreparedStatement stm = cnx.prepareStatement(SELECT_ACHAT_ALL);
			stm.setInt(1, noUtilisateur);
			ResultSet rs= stm.executeQuery();
			while(rs.next()) {
				articles.add(this.articleConstructeur(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException= new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.CONNECTION_DAL);
			throw businessException;
		}
		
		return articles;
	}
	
	@Override
	public List<Article> selectAll() throws BusinessException {
		List<Article> articles= new ArrayList<Article>();
		try(Connection cnx= ConnectionProvider.getConnection()){
			PreparedStatement stm = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs= stm.executeQuery();
			while(rs.next()) {
				articles.add(this.articleConstructeur(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articles;
	}

	@Override
	public void update(Article article, int id) throws BusinessException, SQLException {
		if(article==null)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJET_NULL);
			throw businessException;
		}
		try(Connection cnx= ConnectionProvider.getConnection()){
			PreparedStatement stm = cnx.prepareStatement(UPDATE);
			stm.setString(1, article.getNom_article());
			stm.setString(2, article.getDescription());
			stm.setDate(3, article.getDate_debut_encheres());
			stm.setDate(4, article.getDate_fin_encheres());
			stm.setInt(5, article.getPrix_initial());
			stm.setInt(6, article.getPrix_vente());
			stm.setInt(7, article.getNo_utilisateur());
			stm.setInt(8, article.getNo_categorie());
			stm.setInt(9, article.getNo_retrait());
			stm.setInt(10, id);
			stm.executeUpdate();
					
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	public void updatePrixVente(int id, int prixDeVente) throws BusinessException, SQLException {
		
		try(Connection cnx= ConnectionProvider.getConnection()){
			PreparedStatement stm = cnx.prepareStatement(UPDATE);
			stm.setInt(1, prixDeVente);
			stm.setInt(2, id);
			stm.executeUpdate();
					
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}

	private Article articleConstructeur(ResultSet rs) throws SQLException {
		Article article= new Article();
		article.setNo_article(rs.getInt("no_article"));
		article.setNom_article(rs.getString("nom_article"));
		article.setDescription(rs.getString("description"));
		article.setDate_debut_encheres(rs.getDate("date_debut_encheres"));
		article.setDate_fin_encheres(rs.getDate("date_fin_encheres"));
		article.setPrix_initial(rs.getInt("prix_initial"));
		article.setPrix_vente(rs.getInt("prix_vente"));
		article.setNo_utilisateur(rs.getInt("no_utilisateur"));
		article.setNo_categorie(rs.getInt("no_categorie"));
		article.setNo_retrait(rs.getInt("no_retrait"));
		return article;
	}
}

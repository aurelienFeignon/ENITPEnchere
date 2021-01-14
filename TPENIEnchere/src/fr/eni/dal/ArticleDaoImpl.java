package fr.eni.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.bll.EnchereManageur;
import fr.eni.bll.UtilisateurManageur;
import fr.eni.bo.Article;
import fr.eni.bo.Encheres;
import fr.eni.bo.Utilisateur;
import fr.eni.utils.BusinessException;

public class ArticleDaoImpl implements ArticleDao {
	private static final String DELETE = "delete from ARTICLES_VENDUS where no_article=?";
	private static final String INSERT = "insert into ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, no_retrait, etatVente) "
			+ "values(?,?,?,?,?,?,?,?,?,?)";
	private static final String INSERT_AVEC_CHEMIN_IMG = "insert into ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, no_retrait, etatVente, cheminImg) "
			+ "values(?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SELECT_ID = "select * from ARTICLES_VENDUS where no_article=?";
	private static final String SELECT_ALL = "select * from ARTICLES_VENDUS";
	private static final String SELECT_NO_CATEGORIE = "select * from ARTICLES_VENDUS where no_categorie=? and etatVente=0";
	private static final String SELECT_RECHERCHER = "select * from ARTICLES_VENDUS where nom_article like '%' + ? + '%' and etatVente=0";
	private static final String SELECT_RECHERCHER_CATEGORIE = "select * from ARTICLES_VENDUS where nom_article like '%' + ? + '%' and no_categorie=? and etatVente=0";
	private static final String SELECT_ACHAT_ALL = "select * from ARTICLES_VENDUS where no_utilisateur not in (?) and etatVente=0";
	private static final String SELECT_ACHAT_ENCHERE_EN_COURS = "select ARTICLES_VENDUS.no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial,prix_vente, ARTICLES_VENDUS.no_utilisateur,ARTICLES_VENDUS.no_categorie,ARTICLES_VENDUS.no_retrait,etatVente"
			+ " from ARTICLES_VENDUS inner join ENCHERES  on ARTICLES_VENDUS.no_article=ENCHERES.no_article where ENCHERES.no_utilisateur=? and etatVente=0 and ARTICLES_VENDUS.no_utilisateur not in (?)"
			+ " group by ARTICLES_VENDUS.no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial,prix_vente, ARTICLES_VENDUS.no_utilisateur,ARTICLES_VENDUS.no_categorie,ARTICLES_VENDUS.no_retrait,etatVente";
	private static final String SELECT_ACHAT_ENCHERE_REMPORTE = "select ARTICLES_VENDUS.no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial,prix_vente, ARTICLES_VENDUS.no_utilisateur,ARTICLES_VENDUS.no_categorie,ARTICLES_VENDUS.no_retrait,etatVente"
			+ " from ARTICLES_VENDUS inner join ENCHERES  on ARTICLES_VENDUS.no_article=ENCHERES.no_article where ENCHERES.no_utilisateur=? and etatVente=1 and ARTICLES_VENDUS.no_utilisateur not in (?)"
			+ " group by ARTICLES_VENDUS.no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial,prix_vente, ARTICLES_VENDUS.no_utilisateur,ARTICLES_VENDUS.no_categorie,ARTICLES_VENDUS.no_retrait,etatVente";
	private static final String SELECT_VENTE_ALL = "select * from ARTICLES_VENDUS where no_utilisateur=?";
	private static final String SELECT_VENTE_EN_COURS = "select * from ARTICLES_VENDUS where no_utilisateur=? and etatVente=0";
	private static final String SELECT_VENTE_TERMINE = "select * from ARTICLES_VENDUS where no_utilisateur=? and etatVente=1";
	private static final String UPDATE = "update ARTICLES_VENDUS Set nom_article= ?,description=?, date_debut_encheres=?, date_fin_encheres=?, prix_initial=?,prix_vente=?, no_utilisateur=?,no_categorie=?, no_retrait=? where no_article=?";
	private static final String UPDATE_PRIX_DE_VENTE = "update ARTICLES_VENDUS Set  prix_vente=? where no_article=?";
	private static final String UPDATE_ETAT_VENTE = "update ARTICLES_VENDUS Set  etatVente=? where no_article=?";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(int id) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(DELETE);
			stm.setInt(1, id);
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SUPPRESSION_ARTICLE);
			throw businessException;
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insert(Article article) throws BusinessException {
		if (article == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		try (Connection cnx = ConnectionProvider.getConnection()) {
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
			stm.setBoolean(10, article.getEtatVente());

			stm.executeUpdate();
			ResultSet rs = stm.getGeneratedKeys();

			if (rs.next()) {
				article.setNo_utilisateur(rs.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERTION_ARTICLE);
			throw businessException;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insertAvecCheminImg(Article article) throws BusinessException {
		if (article == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(INSERT_AVEC_CHEMIN_IMG,
					PreparedStatement.RETURN_GENERATED_KEYS);
			stm.setString(1, article.getNom_article());
			stm.setString(2, article.getDescription());
			stm.setDate(3, article.getDate_debut_encheres());
			stm.setDate(4, article.getDate_fin_encheres());
			stm.setInt(5, article.getPrix_initial());
			stm.setInt(6, article.getPrix_vente());
			stm.setInt(7, article.getNo_utilisateur());
			stm.setInt(8, article.getNo_categorie());
			stm.setInt(9, article.getNo_retrait());
			stm.setBoolean(10, article.getEtatVente());
			stm.setString(11, article.getCheminImg());

			stm.executeUpdate();
			ResultSet rs = stm.getGeneratedKeys();

			if (rs.next()) {
				article.setNo_utilisateur(rs.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERTION_ARTICLE);
			throw businessException;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Article selectId(int id) throws BusinessException {
		Article article = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(SELECT_ID);
			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				article = this.articleConstructeur(rs);
			} else {
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.SELECT_ID_INEXISTANT);
				throw businessException;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.CONNECTION_DAL);
			throw businessException;
		}

		return article;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Article> selectCategorie(int noCategorie) throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(SELECT_NO_CATEGORIE);
			stm.setInt(1, noCategorie);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				articles.add(this.articleConstructeur(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.CONNECTION_DAL);
			throw businessException;
		}

		return articles;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Article> selectRechercher(String rechercher) throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(SELECT_RECHERCHER);
			stm.setString(1, rechercher);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				articles.add(this.articleConstructeur(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.CONNECTION_DAL);
			throw businessException;
		}

		return articles;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Article> selectCategorieRechercher(String rechercher, int noCategorie) throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(SELECT_RECHERCHER_CATEGORIE);
			stm.setString(1, rechercher);
			stm.setInt(2, noCategorie);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				articles.add(this.articleConstructeur(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.CONNECTION_DAL);
			throw businessException;
		}

		return articles;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Article> selectAchatAll(int noUtilisateur) throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(SELECT_ACHAT_ALL);
			stm.setInt(1, noUtilisateur);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				articles.add(this.articleConstructeur(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.CONNECTION_DAL);
			throw businessException;
		}

		return articles;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Article> selectAchatEnchereEnCour(int noUtilisateur) throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(SELECT_ACHAT_ENCHERE_EN_COURS);
			stm.setInt(1, noUtilisateur);
			stm.setInt(2, noUtilisateur);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				articles.add(this.articleConstructeur(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.CONNECTION_DAL);
			throw businessException;
		}

		return articles;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Article> selectAchatEnchereRemporte(int noUtilisateur) throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		EnchereManageur enchereManageur = new EnchereManageur();
		Article article = null;
		List<Encheres> encheres = new ArrayList<>();
		Encheres enchere = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(SELECT_ACHAT_ENCHERE_REMPORTE);
			stm.setInt(1, noUtilisateur);
			stm.setInt(2, noUtilisateur);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				article = this.articleConstructeur(rs);
				encheres = enchereManageur.selectHistoriqueArticle(article.getNo_article());
				if (!encheres.isEmpty()) {
					enchere = encheres.get(encheres.size() - 1);
					if (enchere.getNo_utilisateur() == noUtilisateur) {
						articles.add(article);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.CONNECTION_DAL);
			throw businessException;
		}

		return articles;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Article> selectVenteAll(int noUtilisateur) throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(SELECT_VENTE_ALL);
			stm.setInt(1, noUtilisateur);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				articles.add(this.articleConstructeur(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.CONNECTION_DAL);
			throw businessException;
		}

		return articles;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Article> selectVenteEnCour(int noUtilisateur) throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(SELECT_VENTE_EN_COURS);
			stm.setInt(1, noUtilisateur);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				articles.add(this.articleConstructeur(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.CONNECTION_DAL);
			throw businessException;
		}

		return articles;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Article> selectVenteNonDebute(int noUtilisateur) throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		Article articleCourant = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(SELECT_VENTE_EN_COURS);
			stm.setInt(1, noUtilisateur);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				articleCourant = this.articleConstructeur(rs);
				if (articleCourant.getDate_debut_encheres().after(new Date())) {
					articles.add(articleCourant);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.CONNECTION_DAL);
			throw businessException;
		}

		return articles;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Article> selectVenteTermine(int noUtilisateur) throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(SELECT_VENTE_TERMINE);
			stm.setInt(1, noUtilisateur);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				articles.add(this.articleConstructeur(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.CONNECTION_DAL);
			throw businessException;
		}

		return articles;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Article> selectAll() throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		Article articleCourant = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				articleCourant = this.articleConstructeur(rs);
				if (!articleCourant.getEtatVente()) {
					articles.add(articleCourant);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articles;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(Article article, int id) throws BusinessException, SQLException {
		if (article == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJET_NULL);
			throw businessException;
		}
		try (Connection cnx = ConnectionProvider.getConnection()) {
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updatePrixVente(int id, int prixDeVente) throws BusinessException, SQLException {

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(UPDATE_PRIX_DE_VENTE);
			stm.setInt(1, prixDeVente);
			stm.setInt(2, id);
			stm.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Méthode en charge d'update l'etat de vente de l'article en bd passant en
	 * vente à vendu
	 * 
	 * @param article article a modifier
	 * @throws BusinessException
	 * @throws SQLException
	 */
	public void updateEtatVente(Article article) throws BusinessException, SQLException {

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(UPDATE_ETAT_VENTE);
			stm.setBoolean(1, true);
			stm.setInt(2, article.getNo_article());
			stm.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Méthode en charge de constuire les articles après extraction de la bd. la
	 * methode verifie aussi que la date de fin d'enchere a expiré, si c'est le cas
	 * elle update en bd le statut de la vente et verifie si une enchere a été passé
	 * sur l'article. Auquel cas elle credite le vendeur du montant de l'offre
	 * 
	 * @param rs resulset après extraction en bd
	 * @return un article
	 * @throws SQLException
	 * @throws BusinessException
	 */
	private Article articleConstructeur(ResultSet rs) throws SQLException, BusinessException {
		Article article = new Article();
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
		article.setEtatVente(rs.getBoolean("etatVente"));
		article.setCheminImg(rs.getString("cheminImg"));
		if (article.getDate_fin_encheres().toLocalDate().isBefore(LocalDate.now().plusDays(1))) {
			this.updateEtatVente(article);
			article.setEtatVente(true);
			EnchereManageur enchereManageur = new EnchereManageur();
			List<Encheres> encheres = enchereManageur.selectHistoriqueArticle(article.getNo_article());
			if (!encheres.isEmpty()) {
				UtilisateurManageur utilisateurManageur = new UtilisateurManageur();
				Utilisateur utilisateur = null;
				utilisateur = utilisateurManageur.selectId(article.getNo_utilisateur());
				utilisateurManageur.AjouterCredit(utilisateur, article.getPrix_vente());
			}
		}
		return article;
	}
}

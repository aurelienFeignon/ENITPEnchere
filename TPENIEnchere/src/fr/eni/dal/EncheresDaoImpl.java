package fr.eni.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.bo.Encheres;
import fr.eni.utils.BusinessException;

/**
 * Classe en charge de faire les traitements sur la bd des encheres
 * 
 * @author aurel
 * @version TPENIEnchere - v1.0
 * @date 14 janv. 2021 - 16:48:40
 */
public class EncheresDaoImpl implements EncheresDao {
	private static final String DELETE = "delete from ENCHERES where no_enchere=?";
	private static final String INSERT = "insert into ENCHERES (date_enchere, montant_enchere, no_article, no_utilisateur) "
			+ "values(?,?,?,?)";
	private static final String SELECT_ID = "select * from ENCHERES where no_enchere=?";
	private static final String SELECT_HISTORIQUE_ARTICLE = "select * from ENCHERES where no_article=?";
	private static final String SELECT_HISTORIQUE_ARTICLE_DECROISSANT = "select * from ENCHERES where no_article=? order by montant_enchere desc";
	private static final String SELECT_ALL = "select * from ENCHERES";
	private static final String UPDATE = "update ENCHERES Set date_enchere= ?, montant_enchere=?, no_article=?, no_utilisateur=? where no_enchere=?";

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
			businessException.ajouterErreur(CodesResultatDAL.SUPPRESSION_ENCHERE);
			throw businessException;
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insert(Encheres enchere) throws BusinessException {
		if (enchere == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			stm.setDate(1, enchere.getDate_enchere());
			stm.setInt(2, enchere.getMontant_enchere());
			stm.setInt(3, enchere.getNo_article());
			stm.setInt(4, enchere.getNo_utilisateur());

			stm.executeUpdate();
			ResultSet rs = stm.getGeneratedKeys();

			if (rs.next()) {
				enchere.setNo_enchere(rs.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERTION_ENCHERE);
			throw businessException;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Encheres selectId(int id) throws BusinessException {
		Encheres enchere = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(SELECT_ID);
			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				enchere = this.encheresConstructeur(rs);
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

		return enchere;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Encheres> selectHistoriqueArticle(int noArticle) throws BusinessException {
		List<Encheres> encheres = new ArrayList<>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(SELECT_HISTORIQUE_ARTICLE);
			stm.setInt(1, noArticle);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				encheres.add(this.encheresConstructeur(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.CONNECTION_DAL);
			throw businessException;
		}

		return encheres;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Encheres> selectAll() throws BusinessException {
		List<Encheres> encheres = new ArrayList<Encheres>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				encheres.add(this.encheresConstructeur(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return encheres;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(Encheres enchere, int id) throws BusinessException, SQLException {
		if (enchere == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJET_NULL);
			throw businessException;
		}
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(UPDATE);
			stm.setDate(1, enchere.getDate_enchere());
			stm.setInt(2, enchere.getMontant_enchere());
			stm.setInt(3, enchere.getNo_article());
			stm.setInt(4, enchere.getNo_utilisateur());
			stm.setInt(5, id);
			stm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Méthode en charge de construire des encheres après extraction en bd
	 * 
	 * @param rs resultset d'extraction de bd
	 * @return un objet de type enchere
	 * @throws SQLException
	 */
	private Encheres encheresConstructeur(ResultSet rs) throws SQLException {
		Encheres enchere = new Encheres();
		enchere.setNo_enchere(rs.getInt("no_enchere"));
		enchere.setDate_enchere(rs.getDate("date_enchere"));
		enchere.setMontant_enchere(rs.getInt("montant_enchere"));
		enchere.setNo_article(rs.getInt("no_article"));
		enchere.setNo_utilisateur(rs.getInt("no_utilisateur"));
		return enchere;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Encheres> selectHistoriqueArticleDecroissant(int noArticle) throws BusinessException {
		List<Encheres> encheres = new ArrayList<>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(SELECT_HISTORIQUE_ARTICLE_DECROISSANT);
			stm.setInt(1, noArticle);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				encheres.add(this.encheresConstructeur(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.CONNECTION_DAL);
			throw businessException;
		}

		return encheres;
	}
}

package fr.eni.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.bo.Categories;
import fr.eni.utils.BusinessException;

public class CategoriesDaoImpl implements CategoriesDao {
	private static final String SELECT_ID = "select * from CATEGORIES where no_categorie=?";
	private static final String SELECT_ALL = "select * from CATEGORIES";
	private static final String INSERT = "insert into CATEGORIES (libelle) values (?)";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Categories selectId(int noCategorie) throws BusinessException {
		Categories categorie = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(SELECT_ID);
			stm.setInt(1, noCategorie);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				categorie = this.categorieConstructeur(rs);
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
		return categorie;
	}

	/**
	 * Méthode en charge de construire un objet categorie après extraction en bd
	 * 
	 * @param rs resultset de l'extraction
	 * @return un objet categorie
	 * @throws SQLException
	 */
	private Categories categorieConstructeur(ResultSet rs) throws SQLException {
		Categories categorie = new Categories();
		categorie.setNo_categorie(rs.getInt("no_categorie"));
		categorie.setLibelle(rs.getString("libelle"));
		return categorie;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Categories> selectAll() throws BusinessException, SQLException {
		List<Categories> categories = new ArrayList<Categories>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				categories.add(this.categorieConstructeur(rs));
			}
		}
		return categories;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws SQLException
	 */
	@Override
	public void insert(String nomCategorie) throws BusinessException, SQLException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(INSERT);
			stm.setString(1, nomCategorie);
			stm.executeUpdate();
		}
	}

}

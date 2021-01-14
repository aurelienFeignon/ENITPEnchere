package fr.eni.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.bo.Retraits;
import fr.eni.utils.BusinessException;

public class RetraitDaoImpl implements RetraitDao {
	private static final String DELETE = "delete from RETRAITS where no_retrait=?";
	private static final String INSERT = "insert into RETRAITS (rue, code_postal, ville) " + "values(?,?,?)";
	private static final String SELECT_ALL = "select * from RETRAITS";
	private static final String SELECT_ID = "select * from RETRAITS where no_retrait=?";
	private static final String SELECT_VERIF_EXISTANCE = "select * from RETRAITS where rue=? and code_postal=? and ville=? ";
	private static final String UPDATE = "update RETRAITS Set rue= ?,code_postal=?, ville=?  where no_retrait=?";

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
			businessException.ajouterErreur(CodesResultatDAL.SUPPRESSION_UTILISATEUR);
			throw businessException;
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Retraits insert(Retraits retrait) throws BusinessException {
		if (retrait == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		Retraits retraitCourant = this.selectVerifExistant(retrait);

		if (retraitCourant == null) {
			try (Connection cnx = ConnectionProvider.getConnection()) {
				PreparedStatement stm = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
				stm.setString(1, retrait.getRue());
				stm.setString(2, retrait.getCode_postal());
				stm.setString(3, retrait.getVille());
				stm.executeUpdate();
				ResultSet rs = stm.getGeneratedKeys();

				if (rs.next()) {
					retrait.setNo_retrait(rs.getInt(1));
				}

			} catch (SQLException e) {
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.INSERTION_RETRAIT);
				throw businessException;
			}
			return retrait;
		} else {
			return retraitCourant;
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Retraits selectId(int id) throws BusinessException {
		Retraits retraits = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(SELECT_ID);
			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				retraits = this.retraitsConstructeur(rs);
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

		return retraits;
	}

	/**
	 * Méthode en charge de verifier si un retrait est existant
	 * 
	 * @param retrait à verifier
	 * @return null si il n'existe pas et retourne un retait si il existe
	 * @throws BusinessException
	 */
	public Retraits selectVerifExistant(Retraits retrait) throws BusinessException {
		if (retrait != null) {
			try (Connection cnx = ConnectionProvider.getConnection()) {
				PreparedStatement stm = cnx.prepareStatement(SELECT_VERIF_EXISTANCE);
				stm.setString(1, retrait.getRue());
				stm.setString(2, retrait.getCode_postal());
				stm.setString(3, retrait.getVille());
				ResultSet rs = stm.executeQuery();
				if (rs.next()) {
					retrait = this.retraitsConstructeur(rs);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.CONNECTION_DAL);
				throw businessException;
			}
			if (retrait.getNo_retrait() == null) {
				retrait = null;
			}

		}
		return retrait;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Retraits> selectAll() throws BusinessException {
		List<Retraits> retraits = new ArrayList<Retraits>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				retraits.add(this.retraitsConstructeur(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retraits;
	}

	/**
	 * Méthode en charge de construire un retrait après extraction en bd
	 * 
	 * @param rs resultset de l'extraction
	 * @return un retrait
	 * @throws SQLException
	 */
	private Retraits retraitsConstructeur(ResultSet rs) throws SQLException {
		Retraits retrait = new Retraits();
		retrait.setNo_retrait(rs.getInt("no_retrait"));
		retrait.setRue(rs.getString("rue"));
		retrait.setCode_postal(rs.getString("code_postal"));
		retrait.setVille(rs.getString("ville"));
		return retrait;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(Retraits retraits, int id) throws BusinessException, SQLException {
		if (retraits == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJET_NULL);
			throw businessException;
		}
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(UPDATE);
			stm.setString(1, retraits.getRue());
			stm.setString(2, retraits.getCode_postal());
			stm.setString(3, retraits.getVille());
			stm.setInt(4, id);
			stm.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

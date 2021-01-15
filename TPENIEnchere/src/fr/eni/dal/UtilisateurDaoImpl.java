package fr.eni.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.bo.Utilisateur;
import fr.eni.utils.BusinessException;

/**
 * Classe en charge de la gestion en bd des article
 * 
 * @author aurel
 * @version TPENIEnchere - v1.0
 * @date 15 janv. 2021 - 15:03:04
 */
public class UtilisateurDaoImpl implements UtilisateurDao {
	private static final String DELETE = "delete from UTILISATEURS where no_utilisateur=?";
	private static final String INSERT = "insert into UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur ) "
			+ "values(?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SELECT_ID = "select * from UTILISATEURS where no_utilisateur=?";
	private static final String SELECT_PSEUDO = "select * from UTILISATEURS where pseudo=? or email=?";
	private static final String SELECT_ALL = "select * from UTILISATEURS";
	private static final String UPDATE = "update UTILISATEURS Set pseudo= ?,nom=?, prenom=?, email=?, telephone=?,rue=?, code_postal=?,ville=?, mot_de_passe=?, credit=? where no_utilisateur=?";
	private static final String UPDATE_CREDIT = "update UTILISATEURS Set credit=? where no_utilisateur=?";

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
	public void insert(Utilisateur utilisateur) throws BusinessException {
		if (utilisateur == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			stm.setString(1, utilisateur.getPseudo());
			stm.setString(2, utilisateur.getNom());
			stm.setString(3, utilisateur.getPrenom());
			stm.setString(4, utilisateur.getEmail());
			stm.setString(5, utilisateur.getTelephone());
			stm.setString(6, utilisateur.getRue());
			stm.setString(7, utilisateur.getCode_postal());
			stm.setString(8, utilisateur.getVille());
			stm.setString(9, utilisateur.getMot_de_passe());
			stm.setDouble(10, utilisateur.getCredit());
			stm.setBoolean(11, utilisateur.isAdministrateur());
			stm.executeUpdate();
			ResultSet rs = stm.getGeneratedKeys();

			if (rs.next()) {
				utilisateur.setNo_utilisateur(rs.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERTION_UTILISATEUR);
			throw businessException;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Utilisateur selectId(int id) throws BusinessException {
		Utilisateur utilisateur = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(SELECT_ID);
			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				utilisateur = this.utilisateurConstructeur(rs);
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

		return utilisateur;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Utilisateur selectPseudo(String pseudo) throws BusinessException {
		Utilisateur utilisateur = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(SELECT_PSEUDO);
			stm.setString(1, pseudo);
			stm.setString(2, pseudo);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				utilisateur = this.utilisateurConstructeur(rs);
			} else {
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.SELECT_PSEUDO_INEXISTANT);
				throw businessException;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.CONNECTION_DAL);
			throw businessException;
		}
		return utilisateur;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Utilisateur> selectAll() throws BusinessException {
		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				utilisateurs.add(this.utilisateurConstructeur(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utilisateurs;
	}

	/**
	 * Méthode en charge de construire un utilisateur après extraction en bd
	 * 
	 * @param rs resulset d'extraction
	 * @return L'utilisateur
	 * @throws SQLException
	 */
	private Utilisateur utilisateurConstructeur(ResultSet rs) throws SQLException {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setNo_utilisateur(rs.getInt("no_utilisateur"));
		utilisateur.setPseudo(rs.getString("pseudo"));
		utilisateur.setNom(rs.getString("nom"));
		utilisateur.setPrenom(rs.getString("prenom"));
		utilisateur.setEmail(rs.getString("email"));
		utilisateur.setTelephone(rs.getString("telephone"));
		utilisateur.setRue(rs.getString("rue"));
		utilisateur.setCode_postal(rs.getString("code_postal"));
		utilisateur.setVille(rs.getString("ville"));
		utilisateur.setMot_de_passe(rs.getString("mot_de_passe"));
		utilisateur.setCredit(rs.getDouble("credit"));
		utilisateur.setAdministrateur(rs.getBoolean("administrateur"));
		return utilisateur;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(Utilisateur utilisateur, int id) throws BusinessException {
		if (utilisateur == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJET_NULL);
			throw businessException;
		}
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(UPDATE);
			stm.setString(1, utilisateur.getPseudo());
			stm.setString(2, utilisateur.getNom());
			stm.setString(3, utilisateur.getPrenom());
			stm.setString(4, utilisateur.getEmail());
			stm.setString(5, utilisateur.getTelephone());
			stm.setString(6, utilisateur.getRue());
			stm.setString(7, utilisateur.getCode_postal());
			stm.setString(8, utilisateur.getVille());
			stm.setString(9, utilisateur.getMot_de_passe());
			stm.setDouble(10, utilisateur.getCredit());
			stm.setInt(11, id);
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
	public void updateCredit(Utilisateur utilisateur) throws BusinessException {
		if (utilisateur == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJET_NULL);
			throw businessException;
		}
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stm = cnx.prepareStatement(UPDATE_CREDIT);
			stm.setDouble(1, utilisateur.getCredit());
			stm.setInt(2, utilisateur.getNo_utilisateur());
			stm.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package fr.eni.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.bo.Utilisateur;
import fr.eni.utils.BusinessException;



public class UtilisateurDaoImpl implements UtilisateurDao {
	private static final String DELETE="delete from UTILISATEURS where no_utilisateur=?";
	private static final String INSERT="insert into UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur ) "+
	"values(?,?,?,?,?,?,?,?,?,?,?)";
	
	@Override
	public void delete(int id) throws BusinessException {
		try(Connection cnx= ConnectionProvider.getConnection()){
			PreparedStatement stm= cnx.prepareStatement(DELETE);
			stm.setInt(1, id);
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SUPPRESSION_UTILISATEUR);
			throw businessException;
		}

	}

	@Override
	public void insert(Utilisateur utilisateur) throws BusinessException {
		if(utilisateur==null)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		try(Connection cnx= ConnectionProvider.getConnection()){
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
			
			if(rs.next()) {
				utilisateur.setNo_utilisateur(rs.getInt(1));
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERTION);
			throw businessException;
		}
	}

}

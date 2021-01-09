package fr.eni.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.bo.Categories;
import fr.eni.utils.BusinessException;

public class categoriesDaoImpl implements categoriesDao {
	private static final String SELECT_ID="select * from CATEGORIES where no_categorie=?";

	@Override
	public Categories selectId(int noCategorie) throws BusinessException {
		Categories categorie= null;
		try (Connection cnx= ConnectionProvider.getConnection()){
			PreparedStatement stm = cnx.prepareStatement(SELECT_ID);
			stm.setInt(1, noCategorie);
			ResultSet rs= stm.executeQuery();
			if(rs.next()) {
				categorie=this.categorieConstructeur(rs);
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
		return categorie;
	}

	private Categories categorieConstructeur(ResultSet rs) throws SQLException {
		Categories categorie= new Categories();
		categorie.setNo_categorie(rs.getInt("no_categorie"));
		categorie.setLibelle(rs.getString("libelle"));
		return categorie;
	}

}

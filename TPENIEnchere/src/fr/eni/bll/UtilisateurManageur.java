package fr.eni.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.bo.Utilisateur;
import fr.eni.dal.DAOFactory;
import fr.eni.dal.UtilisateurDao;
import fr.eni.utils.BusinessException;


public class UtilisateurManageur {
private UtilisateurDao utilisateurDao;

/**
 * @param utilisateurDao
 */
public UtilisateurManageur() {
	
	this.utilisateurDao = DAOFactory.getUtilisateur();
}

public void insert(Utilisateur utilisateur) throws BusinessException {
	BusinessException businessException = new BusinessException();
	
	this.validerUtilisateur(utilisateur, businessException);
	
	if(!businessException.hasErreurs()) {
		
			this.utilisateurDao.insert(utilisateur);
		
	}else {
		throw businessException;
	}
}

public Utilisateur selectId(int id)throws BusinessException {
	Utilisateur utilisateur = null;
	utilisateur= this.utilisateurDao.selectId(id);
	return utilisateur;
}

public Utilisateur selectPseudo(String pseudo)throws BusinessException {
	Utilisateur utilisateur = null;
	utilisateur= this.utilisateurDao.selectPseudo(pseudo);
	return utilisateur;
}

public void update(Utilisateur utilisateur, int id)throws BusinessException, SQLException {
	BusinessException businessException = new BusinessException();
	
	this.validerUtilisateur(utilisateur, businessException);
	
	if(!businessException.hasErreurs()) {
		
			this.utilisateurDao.update(utilisateur, id);
		
	}else {
		throw businessException;
	}
}

private void validerPseudo(Utilisateur utilisateur, BusinessException businessException) throws BusinessException {
	//Vérification de l'existence de Pseudo
		List<Utilisateur> utilisateurs= new ArrayList<Utilisateur>();
		utilisateurs= this.utilisateurDao.selectAll();
		boolean existant= false;
		if(utilisateurs!= null) {
			for (Utilisateur utilisateurCourant: utilisateurs ) {
				if(utilisateurCourant.getPseudo().equals(utilisateur.getPseudo())) {
					existant= true;
				}
			}
		}
		if(existant) {
			businessException.ajouterErreur(CodeResultatBll.PSEUDO_EXISTANT);
		}
		
		//verifie si le pseudo n'est pas null, qu'il fait moins de 30 lettres et qu'il est en charactere alphanumerique
	if(utilisateur.getPseudo()==null||utilisateur.getPseudo().length()>30|| !utilisateur.getPseudo().matches("[A-Za-z0-9]+")) {
		businessException.ajouterErreur(CodeResultatBll.PSEUDO_INVALIDE);
	}
}

private void validerNom(Utilisateur utilisateur, BusinessException businessException) {
	if(utilisateur.getNom()==null||utilisateur.getNom().length()>30) {
		businessException.ajouterErreur(CodeResultatBll.NOM_INVALIDE);
	}
}

private void validerPrenom(Utilisateur utilisateur, BusinessException businessException) {
	if(utilisateur.getPrenom()==null||utilisateur.getPrenom().length()>30) {
		businessException.ajouterErreur(CodeResultatBll.PRENOM_INVALIDE);
	}
}

private void validerEmail(Utilisateur utilisateur, BusinessException businessException) throws BusinessException {
	//Vérification de l'existence de l'email
	List<Utilisateur> utilisateurs= new ArrayList<Utilisateur>();
	utilisateurs= this.utilisateurDao.selectAll();
	boolean existant= false;
	if(utilisateurs!= null) {
		for (Utilisateur utilisateurCourant: utilisateurs ) {
			if(utilisateurCourant.getEmail().equals(utilisateur.getEmail())) {
				existant= true;
			}
		}
	}
	if(existant) {
		businessException.ajouterErreur(CodeResultatBll.EMAIL_EXISTANT);
	}
	//verifie si l'email n'est pas null, qu'il fait moins de 20 lettres et qu'il contient un @
	if(utilisateur.getEmail()==null||utilisateur.getEmail().length()>20|| !utilisateur.getEmail().contains("@")) {
		businessException.ajouterErreur(CodeResultatBll.EMAIL_INVALIDE);
	}
}

private void validerTelephone(Utilisateur utilisateur, BusinessException businessException) {
	if(utilisateur.getTelephone()==null||utilisateur.getEmail().length()>15) {
		businessException.ajouterErreur(CodeResultatBll.TELEPHONE_INVALIDE);
	}
}

private void validerRue(Utilisateur utilisateur, BusinessException businessException) {
	if(utilisateur.getRue()==null||utilisateur.getRue().length()>30) {
		businessException.ajouterErreur(CodeResultatBll.RUE_INVALIDE);
	}
}

private void validerCodePostal(Utilisateur utilisateur, BusinessException businessException) {
	if(utilisateur.getCode_postal()==null||utilisateur.getCode_postal().length()>10) {
		businessException.ajouterErreur(CodeResultatBll.CODE_POSTAL_INVALIDE);
	}
}

private void validerVille(Utilisateur utilisateur, BusinessException businessException) {
	if(utilisateur.getVille()==null||utilisateur.getVille().length()>30) {
		businessException.ajouterErreur(CodeResultatBll.VILLE_INVALIDE);
	}
}
private void validerMotDePasse(Utilisateur utilisateur, BusinessException businessException) {
	if(utilisateur.getMot_de_passe()==null||utilisateur.getMot_de_passe().length()>30) {
		businessException.ajouterErreur(CodeResultatBll.MDP_INVALIDE);
	}
}

private void validerCredit(Utilisateur utilisateur, BusinessException businessException) {
	if(utilisateur.getCredit()<0) {
		businessException.ajouterErreur(CodeResultatBll.CREDIT_INVALIDE);
	}
}

private void validerUtilisateur(Utilisateur utilisateur, BusinessException businessException) throws BusinessException {

	this.validerPseudo(utilisateur, businessException);
	this.validerNom(utilisateur, businessException);
	this.validerPrenom(utilisateur, businessException);
	this.validerEmail(utilisateur, businessException);
	this.validerTelephone(utilisateur, businessException);
	this.validerRue(utilisateur, businessException);
	this.validerCodePostal(utilisateur, businessException);
	this.validerVille(utilisateur, businessException);
	this.validerMotDePasse(utilisateur, businessException);
	this.validerCredit(utilisateur, businessException);
}

}

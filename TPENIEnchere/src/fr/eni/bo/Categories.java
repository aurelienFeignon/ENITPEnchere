package fr.eni.bo;

public class Categories {
	int no_categorie;
	String libelle;
	/**
	 * 
	 */
	public Categories() {
		super();
	}
	/**
	 * @param no_categorie
	 * @param libelle
	 */
	public Categories(int no_categorie, String libelle) {
		super();
		this.no_categorie = no_categorie;
		this.libelle = libelle;
	}
	public int getNo_categorie() {
		return no_categorie;
	}
	public void setNo_categorie(int no_categorie) {
		this.no_categorie = no_categorie;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

}

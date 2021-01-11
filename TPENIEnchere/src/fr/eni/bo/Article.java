package fr.eni.bo;

import java.sql.Date;

public class Article {
int no_article;
String nom_article;
String description;
Date date_debut_encheres;
Date date_fin_encheres;
int prix_initial;
int prix_vente;
int no_utilisateur;
int no_categorie;
int no_retrait;
boolean etatVente;
String CheminImg;
/**
 * 
 */
public Article() {
	super();
}
/**
 * @param no_article
 * @param nom_article
 * @param description
 * @param date_debut_encheres
 * @param date_fin_encheres
 * @param prix_initial
 * @param prix_vente
 * @param no_utilisateur
 * @param no_categorie
 * @param no_retrait
 */
public Article(int no_article, String nom_article, String description, Date date_debut_encheres, Date date_fin_encheres,
		int prix_initial, int prix_vente, int no_utilisateur, int no_categorie, int no_retrait) {
	super();
	this.no_article = no_article;
	this.nom_article = nom_article;
	this.description = description;
	this.date_debut_encheres = date_debut_encheres;
	this.date_fin_encheres = date_fin_encheres;
	this.prix_initial = prix_initial;
	this.prix_vente = prix_vente;
	this.no_utilisateur = no_utilisateur;
	this.no_categorie = no_categorie;
	this.no_retrait = no_retrait;
}
public int getNo_article() {
	return no_article;
}
public void setNo_article(int no_article) {
	this.no_article = no_article;
}
public String getNom_article() {
	return nom_article;
}
public void setNom_article(String nom_article) {
	this.nom_article = nom_article;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public Date getDate_debut_encheres() {
	return date_debut_encheres;
}
public void setDate_debut_encheres(Date date_debut_encheres) {
	this.date_debut_encheres = date_debut_encheres;
}
public Date getDate_fin_encheres() {
	return date_fin_encheres;
}
public void setDate_fin_encheres(Date date_fin_encheres) {
	this.date_fin_encheres = date_fin_encheres;
}
public int getPrix_initial() {
	return prix_initial;
}
public void setPrix_initial(int prix_initial) {
	this.prix_initial = prix_initial;
}
public int getPrix_vente() {
	return prix_vente;
}
public void setPrix_vente(int prix_vente) {
	this.prix_vente = prix_vente;
}
public int getNo_utilisateur() {
	return no_utilisateur;
}
public void setNo_utilisateur(int no_utilisateur) {
	this.no_utilisateur = no_utilisateur;
}
public int getNo_categorie() {
	return no_categorie;
}
public void setNo_categorie(int no_categorie) {
	this.no_categorie = no_categorie;
}
public int getNo_retrait() {
	return no_retrait;
}
public void setNo_retrait(int no_retrait) {
	this.no_retrait = no_retrait;
}
public boolean getEtatVente() {
	return etatVente;
}
public void setEtatVente(boolean etatVente) {
	this.etatVente = etatVente;
}
public String getCheminImg() {
	return CheminImg;
}
public void setCheminImg(String cheminImg) {
	CheminImg = cheminImg;
}



}

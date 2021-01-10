package fr.eni.bo;

public class Retraits {
Integer no_retrait;
String rue;
String code_postal;
String ville;
/**
 * 
 */
public Retraits() {
	super();
}
/**
 * @param no_retrait
 * @param rue
 * @param code_postal
 * @param ville
 */
public Retraits(int no_retrait, String rue, String code_postal, String ville) {
	super();
	this.no_retrait = no_retrait;
	this.rue = rue;
	this.code_postal = code_postal;
	this.ville = ville;
}
public Integer getNo_retrait() {
	return no_retrait;
}
public void setNo_retrait(int no_retrait) {
	this.no_retrait = no_retrait;
}
public String getRue() {
	return rue;
}
public void setRue(String rue) {
	this.rue = rue;
}
public String getCode_postal() {
	return code_postal;
}
public void setCode_postal(String code_postal) {
	this.code_postal = code_postal;
}
public String getVille() {
	return ville;
}
public void setVille(String ville) {
	this.ville = ville;
}
}

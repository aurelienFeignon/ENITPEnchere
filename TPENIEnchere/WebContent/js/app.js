/**
 * Gestion des boutons radio et des checkbox associées.
 */
window.onload = function(){
var achat = document.getElementById("achat");
var encheresOuvertes = document.getElementById("encheresOuvertes");
var encheresEnCours = document.getElementById("encheresEnCours");
var encheresRemportees = document.getElementById("encheresRemportees");

var vente = document.getElementById("vente");
var ventesEnCours = document.getElementById("ventesEnCours");
var ventesNonDebutees = document.getElementById("ventesNonDebutees");
var venteTerminees = document.getElementById("venteTerminees");

if(vente.checked == false){
achat.checked = true;

ventesEnCours.disabled = true;
ventesNonDebutees.disabled = true;
venteTerminees.disabled = true;
}else{
  encheresOuvertes.disabled = true;
  encheresEnCours.disabled = true;
  encheresRemportees.disabled = true;
}

achat.onclick = achatClicker;
vente.onclick = venteClicker;

function achatClicker(){
	
 encheresOuvertes.disabled = false;
 encheresEnCours.disabled = false;
 encheresRemportees.disabled = false;
	
 encheresOuvertes.checked = false;
 encheresEnCours.checked = false;
 encheresRemportees.checked = false;

 vente.checked = false;
 ventesEnCours.checked = false;
 ventesNonDebutees.checked = false;
 venteTerminees.checked = false;
 ventesEnCours.disabled = true;
 ventesNonDebutees.disabled = true;
 venteTerminees.disabled = true;

}

function venteClicker(){
	
 ventesEnCours.disabled = false;
 ventesNonDebutees.disabled = false;
 venteTerminees.disabled = false;

 ventesEnCours.checked = false;
 ventesNonDebutees.checked = false;
 venteTerminees.checked = false;

 achat.checked = false;
 encheresOuvertes.checked = false;
 encheresEnCours.checked = false;
 encheresRemportees.checked = false;
 encheresOuvertes.disabled = true;
 encheresEnCours.disabled = true;
 encheresRemportees.disabled = true;
 
}
 encheresOuvertes.onclick = AutoSubmit;
 encheresEnCours.onclick = AutoSubmit;
 encheresRemportees.onclick = AutoSubmit;
 ventesEnCours.onclick = AutoSubmit;
 ventesNonDebutees.onclick = AutoSubmit;
 venteTerminees.onclick = AutoSubmit;

function AutoSubmit() {
  document.getElementById("filtreRechercheFin").submit();
}

}
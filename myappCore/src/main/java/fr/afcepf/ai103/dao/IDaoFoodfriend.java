package fr.afcepf.ai103.dao;

import java.util.Date;
import java.util.List;

import fr.afcepf.ai103.data.Foodfriend;
import fr.afcepf.ai103.data.Utilisateur;

public interface IDaoFoodfriend {
	
	// ______________________ FONCTION DE BASE CRUD _____________________________// 
		// rechercher une relation foodfriend
	public Foodfriend rechercherFoodFriendParId(int num);
	public List<Foodfriend> rechercherTousLesFoodfriend();

		// maj relation foodfriend

		// supprimer une relation foodfriend
	public void supprimerFoodFriend(int num);
	
	// ______________________ FONCTION RECHERCHE _____________________________//
	

		// obtenir la liste des demandes en attente
	public List<Foodfriend> obtenirlisteFoodfriendEnvoyees(int userid);
		// obtenir la liste des demandes
	public List<Foodfriend> obtenirlisteDemandesDeFoodfriendRecues(int userid);

	
	// ______________________ FONCTION INSERT _____________________________// 
		
		// Envoyer une demande de foodfriend
	public Foodfriend insererNouveauFoodfriend(Foodfriend f);
	
	// ______________________ FONCTION UPDATE _____________________________// 
	public void majRelationFoodfriend(Foodfriend f);
	// Accepter une demande de foodfriend
	public void accepterDemandeFoodfriend(Foodfriend f);
		// Refuser une demande de foodfriend
	public void refuserDemandeFoodfriend(Foodfriend f);
	
	// ______________________ FONCTION TEST _____________________________// 
		// obtenir la liste des foodfriend
	public List<Foodfriend> obtenirlisteFoodfriend(int userid);



}

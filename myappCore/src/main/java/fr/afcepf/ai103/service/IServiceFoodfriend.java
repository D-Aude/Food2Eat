package fr.afcepf.ai103.service;

import java.util.List;

import fr.afcepf.ai103.data.Annulation;
import fr.afcepf.ai103.data.Foodfriend;



public interface IServiceFoodfriend {
	
	
	// _________________METHODE DE TEST ___________________________________________________________
	// Méthode de test
	public Foodfriend obtenirRelationFoodfriend(int num);
	
	
	// Méthode de test
	public List<Foodfriend> rechercherTousLesFoodfriend();
	
	
	// _________________METHODE D'AFFICHAGE ___________________________________________________________
	// Obtenir la liste des foodfriend d'un utilisateur
	public List<Foodfriend> rechercherMesFoodfriend(int numUtilisateur);
	
	
	// Obtenir la liste de mes demandes de foodfriend recues
	public List<Foodfriend> rechercherMesDemandesFoodfriendRecues(int numUtilisateur);
	
	// Obtenir la liste des demandes de foodfriend envoyees
	public List<Foodfriend> rechercherMesDemandesFFEnvoyees(int numUtilisateur);

	
	// _______________METHODE D'INSERTION _____________________________________________________________
	// Accepter une demande de foodfriend
	public Foodfriend saveOrUpdate(Foodfriend ff);
	

	// Envoyer une demande de foodfriend
	public Foodfriend save(Foodfriend ff);
	
	// Test
	public Long rechercherNotifDemandesrecues(int u);


	

}

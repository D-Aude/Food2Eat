package fr.afcepf.ai103.service;

import java.util.List;

import fr.afcepf.ai103.data.Utilisateur;


public interface IServiceUtilisateur {

	Utilisateur rechercherUtilisateur(String pseudo, String mdp);
	Utilisateur authentificationUtilisateur(String pseudo, String mdp);
	
	// rechercher la liste des utilisateur pour leur envoyer des invitations foodfriend
	List<Utilisateur> rechercherListUtilisateurs(int idUser);
	
	// rechercher utilisateur par idUtilisateur
	Utilisateur rechercherUtilisateurParId(int idUtilisateur);
	
}

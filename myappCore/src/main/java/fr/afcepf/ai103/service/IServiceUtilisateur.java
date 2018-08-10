package fr.afcepf.ai103.service;

import fr.afcepf.ai103.data.Utilisateur;


public interface IServiceUtilisateur {

	Utilisateur rechercherUtilisateur(String pseudo, String mdp);
	Utilisateur authentificationUtilisateur(String pseudo, String mdp);
	
}

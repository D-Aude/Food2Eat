package fr.afcepf.ai103.dao;

import java.util.List;

import fr.afcepf.ai103.data.Utilisateur;

public interface IDaoUtilisateur {

	Utilisateur rechercherUtilisateur(String pseudo, String mdp);

	Utilisateur authentificationUtilisateur(String pseudo, String mdp);
	

}

package fr.afcepf.ai103.dao;

import java.util.List;

import fr.afcepf.ai103.data.Annonce;
import fr.afcepf.ai103.data.Annulation;
import fr.afcepf.ai103.data.Foodfriend;
import fr.afcepf.ai103.data.Repannonce;
import fr.afcepf.ai103.data.Utilisateur;

public interface IDaoAnnulation {

	/**********************MES ANNULATIONS *************************************/
	// Inserer une nouvelle annulation

	public Annulation insererUneNouvelleAnnulation (Annulation annulation) ;
	
	
	
	public List <Annulation> rechercherToutesLesAnnulations();


	
}

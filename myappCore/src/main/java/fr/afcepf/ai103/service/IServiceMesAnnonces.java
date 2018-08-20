package fr.afcepf.ai103.service;

import java.util.List;

import fr.afcepf.ai103.data.Annonce;
import fr.afcepf.ai103.data.Repannonce;

public interface IServiceMesAnnonces {

	
	/********************* MES ANNONCES *************************************/
	Annonce creerAnnonce(Annonce annonce);
	List<Annonce> rechercherToutesLesAnnonces();
	List<Annonce> rechercherAnnoncesEnCoursUtilisateur(int idUtilisateur);
	List<Annonce> rechercherAnnoncesTermineesUtilisateur(int idUtilisateur);
	List<Annonce> rechercherAnnoncesAValideesUtilisateur(int idUtilisateur);
	List<Annonce> rechercherAnnoncesAutresUtilisateur(int idUtilisateur);	
	Annonce modifierMonAnnonce(Annonce idAnnonce);
	public Annonce UneAnnonce(int idAnnonce);
	List <Annonce>rechercherAnnonceTermineesNonAnnulees(int idUtilisateur);
	List <Annonce>rechercherAnnonceTermineesCarAnnulees(int idUtilisateur);
	
	/********************* MES ENVIES *************************************/
	
	List<Annonce> rechercherMesEnvies(int utilisateur );
	List<Annonce> rechercherMesEnviesConfirmes(int utilisateur );
	
	
	List<Annonce> rechercherMesEnviesTermines(int utilisateur );
	List<Annonce> rechercherMesEnviesTerminesAEvaluer(int utilisateur );
	List<Annonce> rechercherMesEnviesCloturees(int utilisateur);
	
	Long CountAnnonceParid (int idutilisateur);
	Long CountAnnonceTotal ();
	
	List<Repannonce> essaiReponse (int utilisateur);
	
	// Récupérer les annonces ayant des reponses
	List<Annonce> rechercherAnnoncesAvecReponses(int iduser);
	

}

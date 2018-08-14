package fr.afcepf.ai103.dao;

import java.util.List;

import fr.afcepf.ai103.data.Annonce;
import fr.afcepf.ai103.data.Repannonce;
import fr.afcepf.ai103.data.Utilisateur;

public interface IDaoMesAnnonce {

	/**********************MES ANNONCES *************************************/
	// public InserermaNouvelleAnnonce ( Annonce annonce)
	public List <Annonce> rechercherToutesLesAnnonces();
	public List <Annonce> rechercherMesAnnoncesEnCours(int idUtilisateur);
	public List <Annonce> rechercherAnnoncesAValides(int idUtilisateur);
	public List <Annonce> rechercherAnnoncesTermines(int idUtilisateur);
	public List <Annonce> rechercherAnnoncesDesAutresUtilisateurs(int idUtilisateur);
	public void mettreAJourMonAnnonce(Annonce idAnnonce);
	public Annonce AnnoncesParId(int Annonce);
	
	
	/**********************MES ENVIES *************************************/
	public List <Annonce> rechercherMesEnvies( int utilisateur );
	public List <Annonce> rechercherMesEnviesConfirmes( int utilisateur );
	public List <Annonce> rechercherMesEnviesTermines( int utilisateur );
	public List <Annonce> rechercherMesEnviesTerminesAEvaluer (int utilisateur);
	

	public List <Repannonce> voirReponse (int utilisateur);
	
	// Recupérer la liste des annonces avec au moins une annonce
	public List<Annonce> recupererAnnonceAvecReponse(int iduser);
	
}

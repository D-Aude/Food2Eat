package fr.afcepf.ai103.service;

import java.util.List;

import fr.afcepf.ai103.data.Repannonce;


public interface IServiceReponses {

	List<Repannonce> rechercherReponsesAnnonces(int id);

	List<Repannonce> rechercherReponseParAnnonce(int utilisateurCourant);
	
	List <Repannonce> repParIdAnnonce(int idAnnonce);

	// Insérer une nouvelle réponse dans la base
	Repannonce save(Repannonce rep);

	// Récupérer la liste des réponses pour une annonce en particulière
	List<Repannonce> rechercherReponsesPourAnnonce(int id);

	// Récupérer une réponse par son id
	Repannonce rechercherReponseParId(int id);

	// maj réponse
	Repannonce update(Repannonce rep);
	
	List <Repannonce> rechercheReponsesParIdUtilisateurSansEval(int idUtilisateur );
	
	List <Repannonce> rechercherReponsesParIdUtilisateurAvecEval (int idUtilisateur);

	List <Repannonce> rechercherReponseParIdUtilisateurRdvAVenir (int idUtilisateur);
	List <Repannonce> rechercherReponseParIdUtilisateurEnvieEnAttente (int idUtilisateur);

	Long CountNotifAcceptationReponse(int idUtilisateur);
	Long CountReponseAnnonce(int idUtilisateur);
	Long CountNotifSouhaitEnAttente(int idUtilisateur);
	Long CountNotifDonTotauxrecus(int idUtilisateur);

	// Rechercher les RDV à venir de mes annonces
	List<Repannonce> chercherMesAnnoncesRdvAVenir(int idUtilisateur);
	// reponses à valider + req rdv à venir
}

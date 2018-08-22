package fr.afcepf.ai103.dao;

import java.util.List;

import fr.afcepf.ai103.data.Annonce;
import fr.afcepf.ai103.data.Repannonce;

public interface IDaoReponses {
	
	public List<Repannonce> rechercherReponsesAnnonces(int idUtilisateur);
	

	public List <Repannonce> RepannonceParIdAnnonce(int idAnnonce);
	
	public List <Repannonce> RepannonceParIdUtilisateurEvalAFaire(int idUtilisateur);
	
	public List <Repannonce> RepannoncePArIdUtilisateurEvalComplete(int idUtilisateur);
	
	public List <Repannonce> RepannoncesParIdUtilisateurRdvAvenir(int idUtilisateur);
	
	public List <Repannonce> RepannonceParIdEnvieEnAttente( int idUtilisateur);

	// Insertion dans la base de données
	public Repannonce insererNouvelleReponse(Repannonce rep);

	// Récupérer la liste de toutes les réponses d'une annonce en particulière
	public List<Repannonce> rechercherReponsesPourAnnonce(int id);

	// Récupérer une réponse par son id
	public Repannonce rechercherReponseParId(int id);

	// Maj une réponse dans la base
	public Repannonce mettreAjourReponse(Repannonce rep);


	public Long countNotifEnvieValide(int idUtilisateur);
	
	public Long countNotifReponseAnnonce(int idUtilisateur);
	
	public Long countNotifSouhaitEnAttente (int idUtilisateur);
	
	public Long countNotifDonTotauxRecus(int idUtilisateur);

	// Chercher les RDV à venir de mes annonces
	public List<Repannonce> repannonceMesAnnoncesRdvAVenir(int idUtilisateur);


	// reponses à valider + req rdv à venir
}

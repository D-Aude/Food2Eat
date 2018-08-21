package fr.afcepf.ai103.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.ejb.Stateless;

import fr.afcepf.ai103.dao.IDaoReponses;
import fr.afcepf.ai103.dao.IDaoStock;
import fr.afcepf.ai103.data.Repannonce;
import fr.afcepf.ai103.data.Stock;
@Stateless
@Local
public class ServiceReponses implements IServiceReponses {
	
	@EJB
	private IDaoReponses daoReponses;


	@Override
	public List<Repannonce> rechercherReponsesAnnonces(int idUtilisateur) {
		return daoReponses.rechercherReponsesAnnonces(idUtilisateur);
	}


	@Override
	public List<Repannonce> rechercherReponseParAnnonce(int utilisateurCourant) {
		return daoReponses.rechercherReponsesAnnonces(utilisateurCourant);
	}



	@Override
	public List <Repannonce> repParIdAnnonce(int idAnnonce) {
		System.out.println("je passe par le service" + idAnnonce);
		return daoReponses.RepannonceParIdAnnonce(idAnnonce);
	}
	// Insérer une réponse dans la base de données
	@Override
	public Repannonce save(Repannonce rep) {
		return daoReponses.insererNouvelleReponse(rep);
	}

	// Récupérer toutes les réponses d'une annonce
	@Override
	public List<Repannonce> rechercherReponsesPourAnnonce(int id) {
		return daoReponses.rechercherReponsesPourAnnonce(id);
	}


	// rechercher réponse par son id
	@Override
	public Repannonce rechercherReponseParId(int id) {
		return daoReponses.rechercherReponseParId(id);
	}

	// maj reponse dans la base
	@Override
	public Repannonce update(Repannonce rep) {
		return daoReponses.mettreAjourReponse(rep);

	}




	@Override
	public List<Repannonce> rechercheReponsesParIdUtilisateurSansEval(int idUtilisateur) {
		System.out.println("je suis dans le reponse rest  sans eval" + idUtilisateur);
		return daoReponses.RepannonceParIdUtilisateurEvalAFaire(idUtilisateur);
	}


	@Override
	public List<Repannonce> rechercherReponsesParIdUtilisateurAvecEval(int idUtilisateur) {
	
		return daoReponses.RepannoncePArIdUtilisateurEvalComplete(idUtilisateur);
	}


	@Override
	public List<Repannonce> rechercherReponseParIdUtilisateurRdvAVenir(int idUtilisateur) {
		
		return daoReponses.RepannoncesParIdUtilisateurRdvAvenir(idUtilisateur);
	}


	@Override
	public List<Repannonce> rechercherReponseParIdUtilisateurEnvieEnAttente(int idUtilisateur) {
		System.out.println("je suis dans le reponse rest avec en attente " + idUtilisateur);
		return daoReponses.RepannonceParIdEnvieEnAttente(idUtilisateur);
	}


	@Override
	public Long CountNotifAcceptationReponse(int idUtilisateur) {
		
		return daoReponses.countNotifEnvieValidé(idUtilisateur);
	}


	@Override
	public Long CountReponseAnnonce(int idUtilisateur) {
		
		return daoReponses.countNotifReponseAnnonce(idUtilisateur);
	}

}

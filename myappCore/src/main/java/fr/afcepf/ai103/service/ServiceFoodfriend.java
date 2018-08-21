package fr.afcepf.ai103.service;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import fr.afcepf.ai103.dao.IDaoFoodfriend;
import fr.afcepf.ai103.data.Evaluation;
import fr.afcepf.ai103.data.Foodfriend;
import fr.afcepf.ai103.data.Utilisateur;

@Stateless
@Local
public class ServiceFoodfriend implements IServiceFoodfriend {
	
	private static final Date date_null = null;
	@EJB
	private IDaoFoodfriend daoff;
	
	// _________________METHODE DE TEST ___________________________________________________________
	// Méthode de test
	@Override
	public Foodfriend obtenirRelationFoodfriend(int num) {
		return daoff.rechercherFoodFriendParId(num);
	}
	
	// Méthode de test
	@Override
	public List<Foodfriend> rechercherTousLesFoodfriend() {
		System.out.println("rechercher tous les food friend SERVICE");
		return daoff.rechercherTousLesFoodfriend();
	}
	
	// _________________METHODE D'AFFICHAGE ___________________________________________________________
	// Obtenir la liste des foodfriend d'un utilisateur
	@Override
	public List<Foodfriend> rechercherMesFoodfriend(int numUtilisateur) {
		System.out.println("affichage utilisateur SERVICE" + numUtilisateur);
		return daoff.obtenirlisteFoodfriend(numUtilisateur);
	}
	
	// Obtenir la liste de mes demandes de foodfriend recues
	@Override
	public List<Foodfriend> rechercherMesDemandesFoodfriendRecues(int numUtilisateur) {
		return daoff.obtenirlisteDemandesDeFoodfriendRecues(numUtilisateur);
	}
	
	// Obtenir la liste des demandes de foodfriend envoyees
	@Override
	public List<Foodfriend> rechercherMesDemandesFFEnvoyees(int numUtilisateur) {
		return daoff.obtenirlisteFoodfriendEnvoyees(numUtilisateur);
	}


	
	// _______________METHODE D'INSERTION _____________________________________________________________
	// Accepter une demande de foodfriend
	@Override
	public Foodfriend saveOrUpdate(Foodfriend ff) {
		daoff.majRelationFoodfriend(ff);		
		return ff;
	}

	// Envoyer une demande de foodfriend
	@Override
	public Foodfriend save(Foodfriend ff) {
		ff = daoff.insererNouveauFoodfriend(ff);
		return ff;
	}

	// test
	@Override
	public Long rechercherNotifDemandesrecues(int u) {
		// TODO Auto-generated method stub
		return daoff.rechercherNotifDemandesrecues(u);
	}

	
	
	
	
}

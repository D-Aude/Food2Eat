package fr.afcepf.ai103.service;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import fr.afcepf.ai103.dao.IDaoFoodfriend;
import fr.afcepf.ai103.data.Foodfriend;
import fr.afcepf.ai103.data.Utilisateur;

@Stateless
@Local
public class ServiceFoodfriend {
	
	private static final Date date_null = null;
	@EJB
	private IDaoFoodfriend daoff;
	
	// _________________METHODE DE TEST ___________________________________________________________
	// Méthode de test
	public Foodfriend obtenirRelationFoodfriend(int num) {
		return daoff.rechercherFoodFriendParId(num);
	}
	
	// Méthode de test
	public List<Foodfriend> rechercherTousLesFoodfriend() {
		System.out.println("rechercher tous les food friend SERVICE");
		return daoff.rechercherTousLesFoodfriend();
	}
	
	// _________________METHODE D'AFFICHAGE ___________________________________________________________
	// Obtenir la liste des foodfriend d'un utilisateur
	public List<Foodfriend> rechercherMesFoodfriend(int numUtilisateur) {
		System.out.println("affichage utilisateur SERVICE" + numUtilisateur);
		return daoff.obtenirlisteFoodfriend(numUtilisateur);
	}
	
	// Obtenir la liste de mes demandes de foodfriend recues
	public List<Foodfriend> rechercherMesDemandesFoodfriendRecues(int numUtilisateur) {
		return daoff.obtenirlisteDemandesDeFoodfriendRecues(numUtilisateur);
	}
	
	// Obtenir la liste des demandes de foodfriend envoyees
	public List<Foodfriend> rechercherMesDemandesFFEnvoyees(int numUtilisateur) {
		return daoff.obtenirlisteFoodfriendEnvoyees(numUtilisateur);
	}


	
	// _______________METHODE D'INSERTION _____________________________________________________________
	// Envoyer une demande de foodfriend
	public Foodfriend saveOrUpdate(Foodfriend ff) {
		if (ff.getIdFoodfriend()==0) {
			ff = daoff.insererNouveauFoodfriend(ff);
		} else {
			daoff.majRelationFoodfriend(ff);
		}
		
		return ff;
	}

	
	
}

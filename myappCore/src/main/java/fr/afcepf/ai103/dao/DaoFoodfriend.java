package fr.afcepf.ai103.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.afcepf.ai103.data.Foodfriend;
import fr.afcepf.ai103.data.Utilisateur;

@Stateless
@Local
public class DaoFoodfriend implements IDaoFoodfriend {

	// ______________ entity manager _____________________________________
	@PersistenceContext(unitName="myappCore")
	private EntityManager entityManager;
	
	// _______________constructeur _______________________________________
	public DaoFoodfriend() {
		
	}
	
	// _________________Méthode de test ___________________________________
	@Override
	public Foodfriend rechercherFoodFriendParId(int num) {
		return entityManager.find(Foodfriend.class,num);
	}
	
	@Override
	public List<Foodfriend> rechercherTousLesFoodfriend() {
//		return entityManager.createQuery("SELECT f FROM Foodfriend f",Foodfriend.class)
//				.getResultList();
		System.out.println("rechercher tous les foodfriend DAO");
		return entityManager.createNamedQuery("Foodfriend.findAll", Foodfriend.class)
				.getResultList();
	}
	
	// __________________ Méthode de recherche ______________________________
	
	// Liste de mes foodfriend
	@Override
	public List<Foodfriend> obtenirlisteFoodfriend(int userId) {
		System.out.println("rechercher utilisateur DAO = " + userId);
		return entityManager.createNamedQuery("Foodfriend.utilisateur1",Foodfriend.class)
				.setParameter("utilisateur1", userId)
				.getResultList();
	}	
	
	// Liste des demandes envoyées
	@Override
	public List<Foodfriend> obtenirlisteFoodfriendEnvoyees(int userid) {
		return entityManager.createNamedQuery("Foodfriend.demandesenvoyees",Foodfriend.class)
				.setParameter("utilisateur1", userid)
				.getResultList();
	}

	// Liste des demandes reçues
	@Override
	public List<Foodfriend> obtenirlisteDemandesDeFoodfriendRecues(int userid) {
		return entityManager.createNamedQuery("Foodfriend.demandesrecues",Foodfriend.class)
				.setParameter("utilisateur2", userid)
				.getResultList();
	}
	
	// __________________ Méthode d'insertion _______________________________
	
	@Override
	public Foodfriend insererNouveauFoodfriend(Foodfriend f) {
		entityManager.persist(f);
		return f;
	}
	


	
	// ___________________Méthode de mise à jour ____________________________

	
	@Override
	public Foodfriend majRelationFoodfriend(Foodfriend f) {
		entityManager.merge(f);
		return f;
		
	}
	// ___________________Méthode de suppression ____________________________



	@Override
	public void supprimerFoodFriend(int num) {
		Foodfriend f = entityManager.find(Foodfriend.class, num);
		entityManager.remove(f);
		
	}





	@Override
	public void accepterDemandeFoodfriend(Foodfriend f) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refuserDemandeFoodfriend(Foodfriend f) {
		// TODO Auto-generated method stub
		
	}

	
	










}

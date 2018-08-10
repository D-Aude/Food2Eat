package fr.afcepf.ai103.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.afcepf.ai103.data.Foodfriend;
import fr.afcepf.ai103.data.Utilisateur;

//@Stateless
@Singleton //depuis la version 3.1 des EJB (comme @Stateless mais 
// avec la garantie d'avoir une seule instance de la classe d'EJB 
// fabriquée par le serveur JEE)

@Local
public class DaoUtilisateur implements IDaoUtilisateur {

	@PersistenceContext(unitName="myappCore")
	private EntityManager entityManager;
	
	public DaoUtilisateur() {
		
	}
	
	public Utilisateur insererNouveauStock(Utilisateur u) {
		entityManager.persist(u);
		return u;
	}

	@Override
	public Utilisateur rechercherUtilisateur(String pseudo, String mdp) {
		return null;
	}

	@Override
	public Utilisateur authentificationUtilisateur(String pseudo, String mdp) {
		Utilisateur user = new Utilisateur();
		try {
			user = entityManager.createNamedQuery("Utilisateur.Authentification", Utilisateur.class)
			        .setParameter("pseudo", pseudo)
			        .setParameter("mdp", mdp)
			        .getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	// FOODFRIEND : Rechercher la liste des utilisateurs (pour leur envoyer des invitations foodfriend)
	@Override
	public List<Utilisateur> rechercherListUtilisateurs(int idUser) {

		List<Utilisateur> listFoodfriend = new ArrayList<Utilisateur>();

		
		// Récupérer les utilisateurs join foodfriend
		listFoodfriend.addAll(entityManager.createNamedQuery("Utilisateur.foodfriend",Utilisateur.class)
				.setParameter("idUtilisateur", idUser)
				.getResultList());

				
		return listFoodfriend;
	}
	
}

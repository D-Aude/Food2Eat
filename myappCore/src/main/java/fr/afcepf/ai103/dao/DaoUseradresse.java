package fr.afcepf.ai103.dao;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.afcepf.ai103.data.Foodfriend;
import fr.afcepf.ai103.data.Useradresse;

@Stateless
@Local
public class DaoUseradresse implements IDaoUseradresse {
	
	// ______________ entity manager _____________________________________
	@PersistenceContext(unitName="myappCore")
	private EntityManager entityManager;
	
	
	// _______________constructeur _______________________________________
		public DaoUseradresse() {
			
		}

	
	// rechercher adresse principale d'un utilisateur
	@Override
	public Useradresse rechercherAdressePrincipaleUser(int iduser) {
		return entityManager.createNamedQuery("Utilisateur.adressePrincipale",Useradresse.class)
				.setParameter("utilisateur", iduser)
				.getSingleResult();
	
	}
	
	


}

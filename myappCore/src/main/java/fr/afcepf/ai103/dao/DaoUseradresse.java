package fr.afcepf.ai103.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.afcepf.ai103.data.Useradresse;

//@Stateless
@Singleton //depuis la version 3.1 des EJB (comme @Stateless mais 
// avec la garantie d'avoir une seule instance de la classe d'EJB 
// fabriquée par le serveur JEE)

@Local
public class DaoUseradresse implements IDaoUseradresse {

	@PersistenceContext(unitName="myappCore")
	private EntityManager entityManager;
	
	public DaoUseradresse() {
		
	}


	@Override
	public List<Useradresse> rechercherAdresseUtilisateur(int idUtilisateur) {
		return entityManager.createNamedQuery("Useradresse.parUtilisateur", Useradresse.class)
	            .setParameter("idUtilisateur", idUtilisateur)
	            .getResultList();
	}

	

}

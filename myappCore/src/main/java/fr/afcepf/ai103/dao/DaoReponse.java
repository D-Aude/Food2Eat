package fr.afcepf.ai103.dao;

import java.util.List;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.afcepf.ai103.data.Foodfriend;
import fr.afcepf.ai103.data.Repannonce;
import fr.afcepf.ai103.data.Stock;

//@Stateless
@Singleton //depuis la version 3.1 des EJB (comme @Stateless mais 
//avec la garantie d'avoir une seule instance de la classe d'EJB 
//fabriquée par le serveur JEE)
@Local
public class DaoReponse implements IDaoReponses {

	@PersistenceContext(unitName="myappCore")
	private EntityManager entityManager;
	
	public DaoReponse() {
		
	}
	
	@Override
	public List<Repannonce> rechercherReponsesAnnonces(int idUtilisateur) {
		
		return entityManager.createNamedQuery("Repannonce.parUtilisateur", Repannonce.class)
				.setParameter("id", idUtilisateur)
				.getResultList();
	}

	@Override
	public List<Repannonce> rechercherToutesLesReponses() {
		return entityManager.createNamedQuery("Repannonce.findAll", Repannonce.class)
				.getResultList();
	}

	@Override
	public Repannonce rechercherReponseParId(int id) {
		return entityManager.find(Repannonce.class,id);
	}

	@Override
	public Repannonce insererNouvelleReponse(Repannonce rep) {
		entityManager.persist(rep);
		return rep;
	}

}

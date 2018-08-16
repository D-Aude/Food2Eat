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

@Stateless
//@Singleton //depuis la version 3.1 des EJB (comme @Stateless mais 
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
	public List <Repannonce> RepannonceParIdAnnonce(int idAnnonce) {
		System.out.println("je passe par le dao" + idAnnonce);
		return entityManager.createNamedQuery("Repannonce.parIdAnnonce2", Repannonce.class)
				.setParameter("idAnnonce", idAnnonce)
				.getResultList();
		
	}


	// Insertion ________________________________________________________________________________
	@Override
	public Repannonce insererNouvelleReponse(Repannonce rep) {
		entityManager.persist(rep);
		return rep;
	}
	
	// Mise à Jour dans la base
	@Override
	public Repannonce mettreAjourReponse(Repannonce rep) {
		entityManager.merge(rep);
		return rep;
	}

	// Récupérer toutes les réponses d'une annonce en particulière
	@Override
	public List<Repannonce> rechercherReponsesPourAnnonce(int id) {
				
		List<Repannonce> resultat = entityManager.createNamedQuery("Repannonce.parIdAnnonce", Repannonce.class)
				.setParameter("idAnnonce", id)
				.getResultList();		
		
		return resultat;
	}

	// Récupérer une réponse par son id
	@Override
	public Repannonce rechercherReponseParId(int id) {
		return entityManager.find(Repannonce.class,id);
	}
	



}

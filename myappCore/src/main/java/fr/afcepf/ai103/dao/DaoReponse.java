package fr.afcepf.ai103.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.afcepf.ai103.data.Repannonce;
import fr.afcepf.ai103.data.Stock;

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

}

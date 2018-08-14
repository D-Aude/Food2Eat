package fr.afcepf.ai103.dao;


import java.util.List;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.afcepf.ai103.data.Annulation;
import fr.afcepf.ai103.data.Repannonce;
import fr.afcepf.ai103.data.Stock;

@Stateless
@Local
public class DaoAnnulation implements IDaoAnnulation {

	@PersistenceContext(unitName="myappCore")
	private EntityManager entityManager;
	
	public DaoAnnulation() {
		
	}
	
	
	@Override
	public List<Annulation> rechercherToutesLesAnnulations() {

		return entityManager.createNamedQuery("Annulation.findAll", Annulation.class)
				.getResultList();
	}


	@Override
	public Annulation insererUneNouvelleAnnulation(Annulation annulation) {
		entityManager.persist(annulation);
		return annulation;
	}
	

}

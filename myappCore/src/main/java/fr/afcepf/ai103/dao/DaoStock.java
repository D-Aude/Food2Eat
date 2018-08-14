package fr.afcepf.ai103.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.afcepf.ai103.data.Stock;

//@Stateless
@Singleton //depuis la version 3.1 des EJB (comme @Stateless mais 
// avec la garantie d'avoir une seule instance de la classe d'EJB 
// fabriquée par le serveur JEE)

@Local
public class DaoStock implements IDaoStock {

	@PersistenceContext(unitName="myappCore")
	private EntityManager entityManager;
	
	public DaoStock() {
		
	}
	
	public List<Stock> rechercherStocks() {
		return entityManager.createQuery("SELECT s FROM Compte s",Stock.class)
	            .getResultList();
	}
	
	@Override
	public Stock insererNouveauStock(Stock s) {
		entityManager.persist(s);
		return s;
	}
	
	@Override
	public void mettreAJourStock(Stock stock) {
		entityManager.merge(stock);
	}

	@Override
	public List<Stock> rechercherStockUtilisateur(int idUtilisateur) {
		return entityManager.createNamedQuery("Stock.parUtilisateur", Stock.class)
	            .setParameter("idUtilisateur", idUtilisateur)
	            .getResultList();
	}

	@Override
	public List<Stock> rechercherStockModeConservation(int idUtilisateur, String nomModeConservation) {
		switch (nomModeConservation) {
		case "Frigo":
			return entityManager.createNamedQuery("Stock.Frigo", Stock.class)
		            .setParameter("idUtilisateur", idUtilisateur)
		            .setParameter("frais", "frais")
		            .setParameter("epicerie_frais", "épicerie frais")
		            .setParameter("conserve", "conserve")
		            .getResultList();
			
		case "Congelateur":
			return entityManager.createNamedQuery("Stock.Congelateur", Stock.class)
		            .setParameter("idUtilisateur", idUtilisateur)
		            .setParameter("nomModeConservation", "surgelé")
		            .getResultList();
			

		default:
			return entityManager.createNamedQuery("Stock.Placard", Stock.class)
		            .setParameter("idUtilisateur", idUtilisateur)
		            .setParameter("epicerie", "épicerie")
		            .setParameter("epicerie_fine", "épicerie fine")
		            .setParameter("conserve", "conserve")
		            .setParameter("epicerie_frais", "épicerie frais")
		            .getResultList();
			
			
		}
		
	}

}

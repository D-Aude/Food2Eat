package fr.afcepf.ai103.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.afcepf.ai103.data.Produit;

//@Stateless
@Singleton //depuis la version 3.1 des EJB (comme @Stateless mais 
// avec la garantie d'avoir une seule instance de la classe d'EJB 
// fabriquée par le serveur JEE)

@Local
public class DaoProduit implements IDaoProduit {

	@PersistenceContext(unitName="myappCore")
	private EntityManager entityManager;
	
	public DaoProduit() {
		
	}

	@Override
	public List<Produit> rechercherTousLesProduits() {
		return entityManager.createNamedQuery("Produit.findAll", Produit.class)
	            .getResultList();
	}



}

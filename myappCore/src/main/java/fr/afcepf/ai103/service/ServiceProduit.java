package fr.afcepf.ai103.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.ejb.Stateless;

import fr.afcepf.ai103.dao.IDaoProduit;
import fr.afcepf.ai103.data.Produit;
@Stateless
@Local
public class ServiceProduit implements IServiceProduit {
	
	@EJB
	private IDaoProduit daoProduit;

	@Override
	public List<Produit> rechercherTousLesProduits() {
		return daoProduit.rechercherTousLesProduits();
	}

	

}

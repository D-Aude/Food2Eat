package fr.afcepf.ai103.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.ejb.Stateless;

import fr.afcepf.ai103.dao.IDaoStock;
import fr.afcepf.ai103.data.Stock;
@Stateless
@Local
public class ServiceStock implements IServiceStock {
	
	@EJB
	private IDaoStock daoStock;

	@Override
	public List<Stock> rechercherStockUtilisateur(int idUtilisateur) {
		return daoStock.rechercherStockUtilisateur(idUtilisateur);
		
	}
	
	@Override
	public List<Stock> rechercherStockModeConservation(int idUtilisateur, String nomModeConservation) {
		return daoStock.rechercherStockModeConservation(idUtilisateur, nomModeConservation);
		
	}

}

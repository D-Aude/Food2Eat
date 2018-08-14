package fr.afcepf.ai103.service;

import java.util.List;

import fr.afcepf.ai103.data.Stock;

public interface IServiceStock {
	
	List<Stock> rechercherStockUtilisateur(int idUtilisateur);

	List<Stock> rechercherStockModeConservation(int idUtilisateur, String nomModeConservation);

	Stock saveOrUpdateStock(Stock stock);
	
}

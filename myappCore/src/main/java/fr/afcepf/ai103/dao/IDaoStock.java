package fr.afcepf.ai103.dao;

import java.util.List;

import fr.afcepf.ai103.data.Stock;

public interface IDaoStock {
	public Stock insererNouveauStock(Stock s);
	public List<Stock> rechercherStockUtilisateur(int idUtilisateur);
	public List<Stock> rechercherStockModeConservation(int idUtilisateur, String nomModeConservation);
	public void mettreAJourStock(Stock stock);
	public int rechercherStockPerimeUtilisateur(int idUtilisateur);

}

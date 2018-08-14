package fr.afcepf.ai103.service;

import java.util.List;

import fr.afcepf.ai103.data.Annulation;



public interface IServiceAnnulation {

	List<Annulation> rechercherAnnulation();
Annulation insererNouvelleAnnulation(Annulation annulation);

	

}

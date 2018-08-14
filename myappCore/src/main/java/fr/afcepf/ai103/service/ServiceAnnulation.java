package fr.afcepf.ai103.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.ejb.Stateless;

import fr.afcepf.ai103.dao.IDaoAnnulation;
import fr.afcepf.ai103.dao.IDaoReponses;
import fr.afcepf.ai103.dao.IDaoStock;
import fr.afcepf.ai103.data.Annulation;
import fr.afcepf.ai103.data.Repannonce;
import fr.afcepf.ai103.data.Stock;
@Stateless
@Local
public class ServiceAnnulation implements IServiceAnnulation {
	
	@EJB
	private IDaoAnnulation daoAnnulation;


	@Override
	public List<Annulation> rechercherAnnulation() {
		
		return daoAnnulation.rechercherToutesLesAnnulations();
	}


	@Override
	public Annulation insererNouvelleAnnulation(Annulation annulation) {
		annulation = daoAnnulation.insererUneNouvelleAnnulation(annulation);
		return annulation;
	}






}

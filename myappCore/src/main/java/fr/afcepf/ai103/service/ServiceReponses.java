package fr.afcepf.ai103.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.ejb.Stateless;

import fr.afcepf.ai103.dao.IDaoReponses;
import fr.afcepf.ai103.dao.IDaoStock;
import fr.afcepf.ai103.data.Repannonce;
import fr.afcepf.ai103.data.Stock;
@Stateless
@Local
public class ServiceReponses implements IServiceReponses {
	
	@EJB
	private IDaoReponses daoReponses;


	@Override
	public List<Repannonce> rechercherReponsesAnnonces(int idUtilisateur) {
		return daoReponses.rechercherReponsesAnnonces(idUtilisateur);
	}

}

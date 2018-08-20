package fr.afcepf.ai103.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.ejb.Stateless;

import fr.afcepf.ai103.dao.IDaoUseradresse;
import fr.afcepf.ai103.data.Useradresse;
@Stateless
@Local
public class ServiceUseradresse implements IServiceUseradresse {
	
	@EJB
	private IDaoUseradresse daoUseradresse;

	@Override
	public List<Useradresse> rechercherAdresseUtilisateur(int idUtilisateur) {
		return daoUseradresse.rechercherAdresseUtilisateur(idUtilisateur);
		
	}
	
}

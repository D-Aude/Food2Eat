package fr.afcepf.ai103.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.ejb.Stateless;

import fr.afcepf.ai103.dao.IDaoUtilisateur;
import fr.afcepf.ai103.data.Utilisateur;
@Stateless
@Local
public class ServiceUtilisateur implements IServiceUtilisateur {
	
	@EJB
	private IDaoUtilisateur daoUtilisateur;

	@Override
	public Utilisateur rechercherUtilisateur(String pseudo, String mdp) {
		
		return daoUtilisateur.rechercherUtilisateur(pseudo, mdp);
	}

	@Override
	public Utilisateur authentificationUtilisateur(String pseudo, String mdp) {
		return daoUtilisateur.authentificationUtilisateur(pseudo, mdp);
	}

	@Override
	public List<Utilisateur> rechercherListUtilisateurs(int idUser) {
		return daoUtilisateur.rechercherListUtilisateurs(idUser);
	}

}

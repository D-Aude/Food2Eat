package fr.afcepf.ai103.service;



import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import fr.afcepf.ai103.dao.IDaoMesAnnonce;
import fr.afcepf.ai103.data.Annonce;
import fr.afcepf.ai103.data.Repannonce;

@Stateless
@Local
public class ServiceMesAnnonces implements IServiceMesAnnonces {

	
	@EJB
	private IDaoMesAnnonce daoAnnonces;
	


	@Override
	public Annonce UneAnnonce(int idAnnonce) {
		System.out.println("Dans le service IdAnnonce : " +idAnnonce) ;
		return daoAnnonces.AnnoncesParId( idAnnonce);

	}



	@Override
	public List<Annonce> rechercherAnnoncesEnCoursUtilisateur(int idUtilisateur) {
		
		
		System.out.println("Dans le service,idUtilistauer : " +idUtilisateur) ;
		
		return daoAnnonces.rechercherMesAnnoncesEnCours(idUtilisateur);
	}



	@Override
	public List<Annonce> rechercherAnnoncesTermineesUtilisateur(int idUtilisateur) {
		System.out.println("plop");
		return daoAnnonces.rechercherAnnoncesTermines(idUtilisateur);
	}



	@Override
	public List<Annonce> rechercherAnnoncesAValideesUtilisateur(int idUtilisateur) {
		System.out.println("je passe par le service" + idUtilisateur);
		return daoAnnonces.rechercherAnnoncesAValides(idUtilisateur);
		
	}



	@Override
	public List<Annonce> rechercherAnnoncesAutresUtilisateur(int idUtilisateur) {
		System.out.println(" service annonces autre que : " +idUtilisateur);
		return daoAnnonces.rechercherAnnoncesDesAutresUtilisateurs(idUtilisateur);
	}



	@Override
	public List<Annonce> rechercherToutesLesAnnonces() {

		return daoAnnonces.rechercherToutesLesAnnonces();
	}



	@Override
	public Annonce modifierMonAnnonce(Annonce idAnnonce) {
		
		daoAnnonces.mettreAJourMonAnnonce(idAnnonce);
		return (idAnnonce);
	}



	@Override
	public List<Annonce> rechercherMesEnvies( int utilisateur ) {
	
		System.out.println("service mes service idUtilistaeur"+ utilisateur );
		return daoAnnonces.rechercherMesEnvies(utilisateur );
	}



	@Override
	public List<Repannonce> essaiReponse(int utilisateur) {
		System.out.println("service"+utilisateur);
		return daoAnnonces.voirReponse(utilisateur);
	}



	@Override
	public List<Annonce> rechercherMesEnviesConfirmes( int utilisateur) {
		
		return daoAnnonces.rechercherMesEnviesConfirmes(utilisateur);
	}



	@Override
	public List<Annonce> rechercherMesEnviesTermines(int utilisateur) {
		
		return daoAnnonces.rechercherMesEnviesTermines(utilisateur);
	}



	@Override
	public List<Annonce> rechercherMesEnviesTerminesAEvaluer(int utilisateur) {
	
		return daoAnnonces.rechercherMesEnviesTerminesAEvaluer(utilisateur);
	}

	@Override
	public List<Annonce> rechercherMesEnviesCloturees(int utilisateur) {
		
		return daoAnnonces.rechercherMesEnviesCloturees(utilisateur);
	}

	@Override
	public List<Annonce> rechercherAnnonceTermineesNonAnnulees(int idUtilisateur) {
	
		return daoAnnonces.rechercherMesAnnoncesTerminesNonAnnulees(idUtilisateur);
	}
	
	// Annonces ayant des réponses
	@Override
	public List<Annonce> rechercherAnnoncesAvecReponses(int iduser) {
		// TODO Auto-generated method stub
		return daoAnnonces.recupererAnnonceAvecReponse(iduser);

	}


	@Override
	public List<Annonce> rechercherAnnonceTermineesCarAnnulees(int idUtilisateur) {
		
		return daoAnnonces.rechercherMesAnnoncesTerminesCarAnnulees(idUtilisateur);
	}



	@Override

	public Long CountAnnonceParid(int idutilisateur) {

		return daoAnnonces.CountAnnonceParId(idutilisateur);
	}



	@Override
	public Long CountAnnonceTotal() {
		
		return daoAnnonces.CountAnnonceTermines();

	}
	public Annonce creerAnnonce(Annonce annonce) {

			annonce = daoAnnonces.creerAnnonce(annonce);
			return annonce;

	}



	


}

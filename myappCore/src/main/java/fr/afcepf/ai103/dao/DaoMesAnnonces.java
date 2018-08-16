package fr.afcepf.ai103.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.afcepf.ai103.data.Annonce;
import fr.afcepf.ai103.data.Repannonce;
import fr.afcepf.ai103.data.Utilisateur;

@Stateless
@Local
public class DaoMesAnnonces implements IDaoMesAnnonce {

	@PersistenceContext(unitName="myappCore")
	private EntityManager entityManager;
	
	@Override
	public List<Annonce> rechercherMesAnnoncesEnCours(int idUtilisateur) {
		System.out.println("Dans le dao,idUtilistauer : " +idUtilisateur ) ;
		return entityManager.createNamedQuery("Annonce.listeMesAnnoncesEnCours",Annonce.class)
				.setParameter("idUtilisateur", idUtilisateur)
				.getResultList();
	}

	@Override
	public List<Annonce> rechercherAnnoncesAValides(int idUtilisateur) {
		System.out.println("je passe par le dao" + idUtilisateur);
		return entityManager.createNamedQuery("Annonce.listeMesAValidees",Annonce.class)
				.setParameter("idUtilisateur", idUtilisateur)
				.getResultList();
	}

	@Override
	public List<Annonce> rechercherAnnoncesTermines(int idUtilisateur) {
		
		return entityManager.createNamedQuery("Annonce.listeMesAnnoncesTerminees",Annonce.class)
				.setParameter("idUtilisateur", idUtilisateur)
				.getResultList();
	}

	@Override
	public void mettreAJourMonAnnonce(Annonce idAnnonce) {
		//return entityManager.createNamedQuery("Annonce.updateAnnonce",Annonce.class)
				//.setParameter("idAnnonce",idAnnonce).getSingleResult();
		 entityManager.merge(idAnnonce);
	}

@Override
	public List<Annonce> rechercherToutesLesAnnonces() {
		return entityManager.createNamedQuery("Annonce.ListeToutesLesAnnonces",Annonce.class).getResultList();
	}

// "idAnnonce" =>Celui qui est en parametre dans la requete, idAnnonce =>c''st mon attribut java
	@Override
	public Annonce AnnoncesParId(int idAnnonce) {
		
		System.out.println("Dans le dao,idAnnonce : " +idAnnonce) ;
		return entityManager.createNamedQuery("Annonce.annonceParId",Annonce.class)
				.setParameter("idAnnonce",idAnnonce).getSingleResult();
	}

	@Override
	public List<Annonce> rechercherAnnoncesDesAutresUtilisateurs(int idUtilisateur) {
		
		System.out.println(" dao annonces autre que : " +idUtilisateur);
		return entityManager.createNamedQuery("Annonce.ListeAnnonceEnCoursSaufUtilisateur",Annonce.class)
				.setParameter("idUtilisateur", idUtilisateur)
				.getResultList();
	}

 
	@Override
	public List<Annonce> rechercherMesEnvies(int utilisateur ) {
		System.out.println("dao mes nevies dUtilistaeur" +  utilisateur);
		
		return entityManager.createNamedQuery("Annonce.listeMesEnvies",Annonce.class)
									.setParameter("idUtilisateur", utilisateur )
									.getResultList();
	}


	@Override
	public List <Repannonce> voirReponse(int utilisateur) {
	
		System.out.println(utilisateur);
		return entityManager.createNamedQuery("Repannonce.essai",Repannonce.class).setParameter("idUtilisateur", utilisateur).getResultList();
	}

	@Override
	public List<Annonce> rechercherMesEnviesConfirmes(int utilisateur) {
		
		return entityManager.createNamedQuery("Annonce.listeMesEnviesConfirmes", Annonce.class)
												.setParameter("idUtilisateur", utilisateur )
												.getResultList();
	}
// Mes envies TERMINES QUI SONT EVALUE
	@Override
	public List<Annonce> rechercherMesEnviesTermines(int utilisateur) {
		
		return entityManager.createNamedQuery("Annonce.listeMesEnviesTermines",Annonce.class)
												.setParameter("idUtilisateur", utilisateur )
												.getResultList();
	}
// MES envies TERMINES QUI SONT A EVALUE
	@Override
	public List<Annonce> rechercherMesEnviesTerminesAEvaluer(int utilisateur) {
		
		return entityManager.createNamedQuery("Annonce.listeMesEnviesTerminesAEvaluees",Annonce.class)
												.setParameter("idUtilisateur", utilisateur )
												.getResultList();
	}
// MES ENVIES TERMINES QUI SONT A EVALUER ET EVALUER
	
public List<Annonce> rechercherMesEnviesCloturees(int utilisateur) {
		
		return entityManager.createNamedQuery("Annonce.listeMesEnviesCloturees",Annonce.class)
												.setParameter("idUtilisateur", utilisateur )
												.getResultList();
}
	
	
	public List<Annonce> rechercherMesAnnoncesTerminesNonAnnulees(int utilisateur)
	{
		return entityManager.createNamedQuery("Annonce.listeMesAnnoncesTermineesNonAnnulees",Annonce.class)
				.setParameter("idUtilisateur",utilisateur)
				.getResultList();
	}



	@Override
	public List<Annonce> rechercherMesAnnoncesTerminesCarAnnulees(int idUtilisateur) {
		
		return entityManager.createNamedQuery("Annonce.listeMesAnnoncesTermineesCarAnnulees",Annonce.class)
				.setParameter("idUtilisateur",idUtilisateur)
				.getResultList();
	}
	
}

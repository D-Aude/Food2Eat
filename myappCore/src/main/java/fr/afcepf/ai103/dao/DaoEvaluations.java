package fr.afcepf.ai103.dao;

import java.util.List;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.afcepf.ai103.data.Annulation;
import fr.afcepf.ai103.data.Evaluation;
import fr.afcepf.ai103.data.Repannonce;
import fr.afcepf.ai103.data.Stock;

@Stateless
//@Singleton //depuis la version 3.1 des EJB (comme @Stateless mais 
//avec la garantie d'avoir une seule instance de la classe d'EJB 
//fabriquée par le serveur JEE)
@Local
public class DaoEvaluations implements IDaoEvaluations {

	@PersistenceContext(unitName="myappCore")
	private EntityManager entityManager;
	
	public DaoEvaluations() {
		
	}
	
	@Override
	public List<Evaluation> rechercherEvaluations(int id) {
		
		return entityManager.createNamedQuery("Evaluations.avecNoteEtCommentaire", Evaluation.class)
				.setParameter("idReponse", id)
				.getResultList();
	}
	
	
	@Override
	public List<Evaluation> rechercherAnnoncesAvecEvaluation(int id) {
		
		return entityManager.createNamedQuery("Evaluations.annoncesAvecEvaluationetNote", Evaluation.class)
				.setParameter("idReponse", id)
				.getResultList();
	}

	@Override
	public List<Evaluation> rechercherEvaluationSansCommentaire(int id) {
		
		return entityManager.createNamedQuery("Evaluations.avecNoteSansCommentaire", Evaluation.class)
				.setParameter("idReponse", id)
				.getResultList();
	}

	@Override
	public List<Evaluation> rechercherTouteslesEval() {
		return entityManager.createNamedQuery("Evaluation.findAll", Evaluation.class)
				.getResultList();
	
	}

	@Override
	public Evaluation insererNouvelleEvaluation(Evaluation evaluation) {
	
			entityManager.persist(evaluation);
			return evaluation;
	}

	@Override
	public List<Evaluation> rechercherEvalParIdUtilisateur(int idUtilisateur) {
		return entityManager.createNamedQuery("Evaluations.avecNoteEtCommentairepParUt", Evaluation.class)
		 .getResultList();
		
	}	
	
	
}

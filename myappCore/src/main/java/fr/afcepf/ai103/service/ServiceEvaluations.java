package fr.afcepf.ai103.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.ejb.Stateless;

import fr.afcepf.ai103.dao.IDaoEvaluations;
import fr.afcepf.ai103.dao.IDaoReponses;
import fr.afcepf.ai103.dao.IDaoStock;
import fr.afcepf.ai103.data.Evaluation;
import fr.afcepf.ai103.data.Repannonce;
import fr.afcepf.ai103.data.Stock;
@Stateless
@Local
public class ServiceEvaluations implements IServiceEvaluations {
	
	@EJB
	private IDaoEvaluations daoEvaluations;

	@Override
	public List<Evaluation> rechercherEvaluations(int id) {
		return daoEvaluations.rechercherEvaluations(id);
	}

	@Override
	public List<Evaluation> rechercherAnnoncesAvecEvaluation(int id) {
		return daoEvaluations.rechercherAnnoncesAvecEvaluation(id);
	}

	@Override
	public List<Evaluation> rechercherEvaluationSansCommentaire(int id) {
		return daoEvaluations.rechercherEvaluationSansCommentaire(id);
	}

	@Override
	public List<Evaluation> rechercherTouteslesEval() {
		
		return daoEvaluations.rechercherTouteslesEval();
	}

	@Override
	public Evaluation insererNouvelleEvaluation(Evaluation evaluation) {
		evaluation = daoEvaluations.insererNouvelleEvaluation(evaluation);
		return evaluation;
	}

	@Override
	public List<Evaluation> rechercherEvalparIdUti(int utilisateur) {
		
		return daoEvaluations.rechercherEvalParIdUtilisateur(utilisateur);
	}

}

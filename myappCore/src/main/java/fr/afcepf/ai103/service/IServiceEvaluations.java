package fr.afcepf.ai103.service;

import java.util.List;

import fr.afcepf.ai103.data.Evaluation;


public interface IServiceEvaluations {
	
	List <Evaluation> rechercherEvaluations(int id);

	List<Evaluation> rechercherAnnoncesAvecEvaluation(int id);

	List<Evaluation> rechercherEvaluationSansCommentaire(int id);
	
	List<Evaluation> rechercherTouteslesEval();
	
	Evaluation insererNouvelleEvaluation(Evaluation evaluation);
	
	List <Evaluation> rechercherEvalparIdUti (int utilisateur);
}

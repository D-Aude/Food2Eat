package fr.afcepf.ai103.service;

import java.util.List;

import fr.afcepf.ai103.data.Evaluation;
import fr.afcepf.ai103.data.Stock;

public interface IServiceEvaluations {
	
	List <Evaluation> rechercherEvaluations(int id);

	List<Evaluation> rechercherAnnoncesAvecEvaluation(int id);

	List<Evaluation> rechercherEvaluationSansCommentaire(int id);
	
}

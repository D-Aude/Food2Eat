package fr.afcepf.ai103.dao;

import java.util.List;

import fr.afcepf.ai103.data.Evaluation;
import fr.afcepf.ai103.data.Repannonce;

public interface IDaoEvaluations {
	
	public List<Evaluation> rechercherEvaluations(int id);

	public List<Evaluation> rechercherAnnoncesAvecEvaluation(int id);

	public List<Evaluation> rechercherEvaluationSansCommentaire(int id);

}

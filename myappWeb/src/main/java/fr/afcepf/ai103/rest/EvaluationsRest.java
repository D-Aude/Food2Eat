package fr.afcepf.ai103.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import fr.afcepf.ai103.data.Evaluation;
import fr.afcepf.ai103.service.IServiceEvaluations;

@Path("eval")
@Produces("application/json")

public class EvaluationsRest {

	
	@Inject
	private IServiceEvaluations serviceEvaluations;
	
	@Path("{idReponse}")
	@GET
	
	public List<Evaluation> rechercherEvaluations(@PathParam("idReponse")int id){
		return serviceEvaluations.rechercherEvaluations(id);
	}
	
	@Path("annonces/{idAnnonce}")
	@GET
	
	public List<Evaluation> rechercherAnnoncesAvecEvaluation(@PathParam("idAnnonce")int id){
		return serviceEvaluations.rechercherAnnoncesAvecEvaluation(id);
	}
	
	
	@Path("sansCommentaire/{idReponse}")
	@GET
	
	public List<Evaluation> rechercherEvaluationSansCommentaire(@PathParam("idReponse")int id){
		return serviceEvaluations.rechercherEvaluationSansCommentaire(id);
	}
	@Path ("EvalCompleteIdUt/{idUtilisateur}")
	@GET
	public List<Evaluation> rechercherEvaluationCompleteParIdUti(@PathParam("idUtilisateur") int idUtilisateur)
	{
		return serviceEvaluations.rechercherEvalparIdUti(idUtilisateur);
	}
	
	
}

package fr.afcepf.ai103.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import fr.afcepf.ai103.data.Foodfriend;
import fr.afcepf.ai103.data.Repannonce;
import fr.afcepf.ai103.service.IServiceReponses;

@Path("reponses")
@Produces("application/json")

public class MesReponsesRecues {

	
	@Inject
	private IServiceReponses serviceReponses;
	
	@Path("/{idUtilisateur}")
	@GET
	
	public List<Repannonce> rechercherReponsesAnnonces(@PathParam("idUtilisateur")int id){
		return serviceReponses.rechercherReponsesAnnonces(id);	
	}
	
	// requête test : afficher toutes les reponses
	//http://localhost:8080/myappWeb/services/rest/reponses/tous
	@Path("/tous")
	@GET
	public List<Repannonce> rechercherToutesLesReponses() {
		return serviceReponses.rechercherToutesLesReponses();
	}
	
	// requête test : afficher foodfriend selon idfoodfriend
	//http://localhost:8080/myappWeb/services/rest/reponses/tous/1
	@Path("tous/{idReponse}")
	@GET
	public Repannonce rechercherFoodFriendparIdFoodfriend(@PathParam("idReponse")int num) {
		return serviceReponses.rechercherReponsesParId(num);
	}
	
	@Path("/envoieDemande")
	@POST
	@Consumes("application/json")
	public Repannonce postNewFoodfriend(Repannonce rep) {
		System.out.println("_______________________________________________________Méthode postNewReponse ______________________________________________________________");		
		rep = serviceReponses.save(rep);
		return rep;
	}
	
	
}

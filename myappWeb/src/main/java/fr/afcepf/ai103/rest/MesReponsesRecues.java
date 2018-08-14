package fr.afcepf.ai103.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

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
	
	
}

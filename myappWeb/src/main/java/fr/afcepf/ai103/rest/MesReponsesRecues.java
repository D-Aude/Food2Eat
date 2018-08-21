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
	
	
	// rechercher une reponse
	//http://localhost:8080/myappWeb/services/rest/reponses/uneReponse/21
	@Path("/uneReponse/{idReponse}")
	@GET
	public Repannonce rechercherReponseParId(@PathParam("idReponse")int id){
		return serviceReponses.rechercherReponseParId(id);	
	}
	
	
	// Recuperer toutes les reponses d'une annonce en particulière
	@Path("reponsesRecues/{idAnnonce}") //http://localhost:8080/myappWeb/services/rest/reponses/reponsesRecues/15
	@GET
	public List<Repannonce> rechercherReponsesPourAnnonce(@PathParam("idAnnonce")int id) {
		return serviceReponses.rechercherReponsesPourAnnonce(id);	
	}
	
	// Insertion nouvelle reponse
	@Path("/nouvelleReponse") 
	@POST
	@Consumes("application/json")
	public Repannonce postNewReponse(Repannonce rep) {
		
		rep = serviceReponses.save(rep);
		return rep;
	}
	
	//Maj réponse
	@Path("/maj")
	@POST
	@Consumes("application/json")
	public Repannonce postReponse(Repannonce rep) {
		rep = serviceReponses.update(rep);
		return rep;
	}
	
	@Path("reponseSansEval/{idUtilisateur}")
	@GET
	public List <Repannonce> chercherReponsesSansEval(@PathParam("idUtilisateur")int idUtilisateur){
		System.out.println("je suis dans le reponse rest  sans eval" + idUtilisateur);
		return serviceReponses.rechercheReponsesParIdUtilisateurSansEval(idUtilisateur);	
	}
	
	@Path("reponseAvecEval/{idUtilisateur}")
	@GET
	public List <Repannonce> chercherReponsesAvecEval(@PathParam("idUtilisateur")int idUtilisateur){
		
		System.out.println("je suis dans le reponse rest avec Eval " + idUtilisateur);
		return serviceReponses.rechercheReponsesParIdUtilisateurSansEval(idUtilisateur);	
	}
	// http://localhost:8080/myappWeb/services/rest/reponses/rdvAVenir/5
	@Path("rdvAVenir/{idUtilisateur}")
	@GET
	public List <Repannonce> chercherReponsesRdvAVenir(@PathParam("idUtilisateur")int idUtilisateur){
		
		System.out.println("je suis dans le reponse rest rdv à venir " + idUtilisateur);
		return serviceReponses.rechercherReponseParIdUtilisateurRdvAVenir(idUtilisateur);	
	}
	
	
	//http://localhost:8080/myappWeb/services/rest/reponses/envieAttente/1
	@Path("envieAttente/{idUtilisateur}")
	@GET
	public List <Repannonce> chercherReponsesEnvieAttente(@PathParam("idUtilisateur")int idUtilisateur){
		
		System.out.println( "envie en attente dans le rest " +idUtilisateur);
		return serviceReponses.rechercherReponseParIdUtilisateurEnvieEnAttente(idUtilisateur);	
	}

	//http://localhost:8080/myappWeb/services/rest/reponses/notificationAcceptationReponse/2
	@Path("notificationAcceptationReponse/{idUtilisateur}")
	@GET
	public Long notificationAcceptationReponse(@PathParam("idUtilisateur") int idUtilisateur) {
		return serviceReponses.CountNotifAcceptationReponse(idUtilisateur);
	}
	
	//http://localhost:8080/myappWeb/services/rest/reponses/NotifReponseAnnonce/2
	@Path("NotifReponseAnnonce/{idUtilisateur}")
	@GET
	public Long notificationReponseAnnonce(@PathParam("idUtilisateur") int idUtilisateur) {
		return serviceReponses.CountReponseAnnonce(idUtilisateur);
	}

	
	//http://localhost:8080/myappWeb/services/rest/reponses/mesAnnoncesRdvAVenir/7
	@Path("/mesAnnoncesRdvAVenir/{idUtilisateur}")
	@GET
	public List <Repannonce> chercherMesAnnoncesRdvAVenir(@PathParam("idUtilisateur")int idUtilisateur){
		
		return serviceReponses.chercherMesAnnoncesRdvAVenir(idUtilisateur);	
	}
	
	// reponses à valider + req rdv à venir
	
	
}

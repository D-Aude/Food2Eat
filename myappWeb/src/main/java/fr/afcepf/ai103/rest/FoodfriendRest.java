package fr.afcepf.ai103.rest;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import fr.afcepf.ai103.data.Foodfriend;
import fr.afcepf.ai103.data.Utilisateur;
import fr.afcepf.ai103.service.ServiceFoodfriend;

@Path("foodfriend")
@Produces("application/json")

public class FoodfriendRest {
	
	@Inject
	private ServiceFoodfriend serviceff;
	
	// requête test : afficher foodfriend selon idfoodfriend
	//http://localhost:8080/myappWeb/services/rest/foodfriend/1
	@Path("/{idFoodfriend}")
	@GET
	public Foodfriend rechercherFoodFriendQuiVaBien(@PathParam("idFoodfriend")int num) {
		return serviceff.obtenirRelationFoodfriend(num);
	}
	
	// requête test : afficher tous les foodfriend
	//http://localhost:8080/myappWeb/services/rest/tousLesFoodfriend
	@Path("/tous")
	@GET
	public List<Foodfriend> rechercherFoodfriend() {
		System.out.println("rechercher tous les foodfriend REST");
		return serviceff.rechercherTousLesFoodfriend();
	}
	
	// Afficher la liste de mes foodfriend ______________________________________________________
	//http://localhost:8080/myappWeb/services/rest/mesfoodfriend?utilisateur1=1
	@Path("/mesfoodfriend")
	@GET
	public List<Foodfriend> rechercherMesFoodfriend(@QueryParam("utilisateur1")int num) {
		System.out.println("affichage utilisateur REST = " + num);
		return serviceff.rechercherMesFoodfriend(num);
	}
	
	// Afficher la liste de mes demandes de ff reçues _____________________________________________
	//http://localhost:8080/myappWeb/services/rest/mesdemandesrecues?utilisateur2=1
	@Path("/mesdemandesrecues")
	@GET
	public List<Foodfriend> rechercherMesDemandesFoodfriend(@QueryParam("utilisateur2")int num) {
		System.out.println("affichage utilisateur REST = " + num);
		return serviceff.rechercherMesDemandesFoodfriendRecues(num);
	}
	
	// Afficher la liste des demandes de ff que j'ai envoyé ________________________________________
	//http://localhost:8080/myappWeb/services/rest/mesdemandesenvoyees?utilisateur2=1
	@Path("/mesdemandesenvoyees")
	@GET
	public List<Foodfriend> rechercherMesDemandesFFEnvoyees(@QueryParam("utilisateur1")int num) {
		return serviceff.rechercherMesDemandesFFEnvoyees(num);
	}
	
	// Envoyer une demande de foodfriend ___________________________________________________________
	@Path("")
	@POST
	@Consumes("application/json")
	public Foodfriend postFoodfriend(Foodfriend ff) {
		System.out.println("envoie d'une demande de foodfriend " + ff.toString());
		ff = serviceff.saveOrUpdate(ff);
		return ff;
	}
	

	
	
	

	

	
	

}

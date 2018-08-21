package fr.afcepf.ai103.rest;

import java.time.LocalDateTime;
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

import fr.afcepf.ai103.data.Annonce;
import fr.afcepf.ai103.data.Foodfriend;
import fr.afcepf.ai103.data.Utilisateur;
import fr.afcepf.ai103.service.IServiceFoodfriend;
import fr.afcepf.ai103.service.IServiceMesAnnonces;
import fr.afcepf.ai103.service.IServiceUtilisateur;
import fr.afcepf.ai103.service.ServiceFoodfriend;
import fr.afcepf.ai103.service.ServiceMesAnnonces;
import fr.afcepf.ai103.service.ServiceUtilisateur;

@Path("foodfriend")
@Produces("application/json")

public class FoodfriendRest {
	
	@Inject
	private IServiceFoodfriend serviceff;
	private IServiceUtilisateur serviceUti;
	
	// requête test : afficher foodfriend selon idfoodfriend
	//http://localhost:8080/myappWeb/services/rest/foodfriend/1
	@Path("/{idFoodfriend}")
	@GET
	public Foodfriend rechercherFoodFriendparIdFoodfriend(@PathParam("idFoodfriend")int num) {
		return serviceff.obtenirRelationFoodfriend(num);
	}
	
	// requête test : afficher tous les foodfriend
	//http://localhost:8080/myappWeb/services/rest/foodfriend/tous
	@Path("/tous")
	@GET
	public List<Foodfriend> rechercherFoodfriend() {
		System.out.println("rechercher tous les foodfriend REST");
		return serviceff.rechercherTousLesFoodfriend();
	}
	
	// Afficher la liste de mes foodfriend ______________________________________________________
	//http://localhost:8080/myappWeb/services/rest/foodfriend/mesfoodfriend?iduser=1
	@Path("/mesfoodfriend")
	@GET
	public List<Foodfriend> rechercherMesFoodfriend(@QueryParam("iduser")int num) {
		System.out.println("affichage utilisateur REST = " + num);
		return serviceff.rechercherMesFoodfriend(num);
	}
	
	
	
	
	// Afficher la liste de mes demandes de ff reçues _____________________________________________
	//http://localhost:8080/myappWeb/services/rest/foodfriend/mesdemandesrecues?iduser=1
	@Path("/mesdemandesrecues")
	@GET
	public List<Foodfriend> rechercherMesDemandesFoodfriend(@QueryParam("iduser")int num) {
		System.out.println("affichage utilisateur REST = " + num);
		return serviceff.rechercherMesDemandesFoodfriendRecues(num);
	}
	
	
	
	
	// Afficher la liste des demandes de ff que j'ai envoyé ________________________________________
	//http://localhost:8080/myappWeb/services/rest/foodfriend/mesdemandesenvoyees?iduser=1
	@Path("/mesdemandesenvoyees")
	@GET
	public List<Foodfriend> rechercherMesDemandesFFEnvoyees(@QueryParam("iduser")int num) {
		return serviceff.rechercherMesDemandesFFEnvoyees(num);
	}
	

	
	// Envoyer une demande de foodfriend ___________________________________________________________
	@Path("")
	@POST
	@Consumes("application/json")
	public Foodfriend postFoodfriend(Foodfriend ff) {
		ff = serviceff.saveOrUpdate(ff);
		return ff;
	}
	
	@Path("/envoieInvitation")
	@POST
	@Consumes("application/json")
	public Foodfriend postNewFoodfriend(Foodfriend ff) {		
		ff = serviceff.save(ff);
		return ff;
	}
	
	
	//http://localhost:8080/myappWeb/services/rest/foodfriend/notif/7
//	@Path("/test")
//	@GET
//	public Long test(@QueryParam("iduser")int num) {
//		return serviceff.test(num);
//	}
	
	@Path("notif/{idFoodfriend}")
	@GET
	public Long rechercherNotifDemandesrecues(@PathParam("idFoodfriend")int num) {
		return serviceff.rechercherNotifDemandesrecues(num);
	}

		

}

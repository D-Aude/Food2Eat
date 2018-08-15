package fr.afcepf.ai103.rest;

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
import fr.afcepf.ai103.data.Annulation;
import fr.afcepf.ai103.data.Repannonce;
import fr.afcepf.ai103.service.IServiceAnnulation;
import fr.afcepf.ai103.service.IServiceMesAnnonces;

@Path("mesAnnoncesPostees")
@Produces("application/json")


public class MesAnnoncesPosteesRest {

	
	@Inject
	private IServiceMesAnnonces serviceAnnonce;
	
	@Inject
	private IServiceAnnulation serviceAnnulation;
	/*
	@Path("")
	@GET
	public List <Annonce> rechercherAnnonce ()
	{
		return serviceAnnonce.rechercherToutesLesAnnonces();
	}
	*/
	
	@Path("enCours/{idUtilisateur}")
	@GET
	public List <Annonce> mesAnnoncesEnCours(@PathParam ("idUtilisateur")int idUtilisateur)
	{
		System.out.println("Dans le REST ,idUtilistauer : " +idUtilisateur) ;
		return serviceAnnonce.rechercherAnnoncesEnCoursUtilisateur(idUtilisateur);
		
	}
	
	
	@Path("uneAnnonce/{idAnnonce}")
	@GET
	public Annonce monAnnonce(@PathParam ("idAnnonce")int idAnnonce)
	{
		
		return serviceAnnonce.UneAnnonce(idAnnonce);
	}
	
	@Path("terminees/{idUtilisateur}")
	@GET
	public List <Annonce> mesAnnoncesTerminees(@PathParam("idUtilisateur") int idUtilisateur)
	{
		return serviceAnnonce.rechercherAnnoncesTermineesUtilisateur(idUtilisateur);
		
		
	}
	
	@Path("avalidees/{idUtilisateur}")
	@GET
	public List <Annonce> mesAnnoncesValidees (@PathParam("idUtilisateur") int idUtilisateur)
	{System.out.println("je passe par le reste" + idUtilisateur);
		return serviceAnnonce.rechercherAnnoncesAValideesUtilisateur(idUtilisateur);
		
	}
	
	@Path("autresAnnonces/{idUtilisateur}")
	@GET
	public List <Annonce> autresAnnonces (@PathParam("idUtilisateur") int idUtilisateur)
	{System.out.println(" REST annonces autre que : " +idUtilisateur);
		return serviceAnnonce.rechercherAnnoncesAutresUtilisateur(idUtilisateur);
	}
	
	@Path("toutesLesAnnonces")
	@GET
	public List<Annonce>toutesLesAnnonces()
	{
		return serviceAnnonce.rechercherToutesLesAnnonces();
	}
	
	
	@Path("mesEnvies/{idUtilisateur}")
	@GET
	public List<Annonce>toutesMesEnvies(@PathParam("idUtilisateur") int utilisateur )
	{
		System.out.println("rest :idUtilistaeur"+ utilisateur );
		return serviceAnnonce.rechercherMesEnvies( utilisateur );
	}
	
	@Path("reponse/{idUtilisateur}")
	@GET
	public List<Repannonce> PutainEssai (@PathParam("idUtilisateur") int utilisateur)
	{
		System.out.println("resr"+utilisateur);
		return serviceAnnonce.essaiReponse(utilisateur);
	}
	
	@Path("mesEnviesConfirmes/{idUtilisateur}")
	@GET
	public List <Annonce> mesEnviesConfirmes( @PathParam("idUtilisateur") int utilisateur )
	{
		return serviceAnnonce.rechercherMesEnviesConfirmes(utilisateur);
	}
	
	@Path("mesEnviesTermines/{idUtilisateur}")
	@GET
	public List <Annonce> mesEnviesTermines( @PathParam("idUtilisateur") int utilisateur )
	{
		return serviceAnnonce.rechercherMesEnviesTermines(utilisateur);
	}
	
	@Path("mesEnviesTerminesAEval/{idUtilisateur}")
	@GET
	public List <Annonce> mesEnviesTerminesAEval( @PathParam("idUtilisateur") int utilisateur )
	{
		return serviceAnnonce.rechercherMesEnviesTerminesAEvaluer(utilisateur);
	}
	
	@Path("mesAnnoncesTerminesNonAnnulees/{idUtilisateur}")
	@GET
	public List <Annonce> mesEnviesTerminesNonAnnulées (@PathParam("idUtilisateur") int utilisateur )
	{
		return serviceAnnonce.rechercherAnnonceTermineesNonAnnulees(utilisateur);
	}
	
	@Path("mesAnnoncesTerminesCarAnnulees/{idUtilisateur}")
	@GET
	public List <Annonce> mesEnviesTerminesCarAnnulées (@PathParam("idUtilisateur") int utilisateur )
	{
		return serviceAnnonce.rechercherAnnonceTermineesCarAnnulees(utilisateur);
	}
	// http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/annulation
	@Path("annulation")
	@GET
	public List <Annulation> annulation()
	{
		return serviceAnnulation.rechercherAnnulation();
	}
	
	@Path("insererAnnulation")
	@POST
	@Consumes("application/json")
	
	public Annulation insererAnnulation(Annulation annulation)
	{
		annulation = serviceAnnulation.insererNouvelleAnnulation(annulation);
		return annulation;
	}
	
	@Path("")
	@POST
	@Consumes("application/json")
	public Annonce annonceModif( Annonce annonce)
	{ System.out.println("annonce modifier annonce" + annonce.toString());
		annonce = serviceAnnonce.modifierMonAnnonce(annonce);
	return annonce;
}
	
	// Récupérer les annonces de l'utilisateur session ayant au moins une réponse __________________________
	// http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/reponsesRecues/1
	@Path("reponsesRecues/{idUtilisateur}")
	@GET
	public List <Annonce> test(@PathParam("idUtilisateur") int utilisateur )
	{
		return serviceAnnonce.rechercherAnnoncesAvecReponses(utilisateur);
	}
	

	
	

}


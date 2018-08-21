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

import fr.afcepf.ai103.data.Stock;
import fr.afcepf.ai103.data.Utilisateur;
import fr.afcepf.ai103.service.IServiceStock;

/*
 * classe java du WS REST lié au client
 */

@Path("stock") //avant dernière partie de l'URL
@Produces("application/json") //pour convertir automatiquement réponse java en réponse json
public class StockRest {
    
	//@EJB ne fonctionne pas ici , il faut utiliser  @Inject (plus moderne de CDI) à la place
	@Inject //@Inject de l'api CDI (Container Dependency Injection) ne fonctionne que si
	        //le fichier WEB-INF/beans.xml est présent dans l'application.
	private IServiceStock serviceStock; 
	
	@Path("/{idUtilisateur}") //dernière partie de l'URL
	@GET // GET pour lecture , recherche unique par id/clef primaire
	//URL = http://localhost:8080/myappWeb/services/rest/client/1
	public List<Stock> rechercherStockUtilisateur(@PathParam("idUtilisateur")int id) {
		return serviceStock.rechercherStockUtilisateur(id);
	}
	
	@Path("") //dernière partie de l'URL
	@GET // GET pour lecture , recherche multiple via critère(s) de recherche
	//URL = http://localhost:8080/myappWeb/services/rest/stock?id=1&mode=Frigo
	public List<Stock> rechercherStockModeConservation(@QueryParam("id")int id, @QueryParam("mode")String mode) {
		if(mode!=null)
			return serviceStock.rechercherStockModeConservation(id, mode);
		else
			return serviceStock.rechercherStockUtilisateur(id);
	}
	
	@Path("produitPostDLC/{idUtilisateur}") //dernière partie de l'URL
	@GET // GET pour lecture , recherche unique par id/clef primaire
	//URL = http://localhost:8080/myappWeb/services/rest/stock/produitPostDLC/1
	public int rechercherStockPerimeUtilisateur(@PathParam("idUtilisateur")int id) {
		return serviceStock.rechercherStockPerimeUtilisateur(id);
	}
	
	@Path("postStock") //dernière partie de l'URL
	@POST 
	//URL = http://localhost:8080/myappWeb/services/rest/stock/postStock
	@Consumes("application/json")
	public Stock postStock(Stock stock) {
		return serviceStock.saveOrUpdateStock(stock);
		
	}
	
}

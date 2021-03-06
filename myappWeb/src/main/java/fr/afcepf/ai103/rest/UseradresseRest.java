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

import fr.afcepf.ai103.data.Useradresse;
import fr.afcepf.ai103.service.IServiceUseradresse;

/*
 * classe java du WS REST lié au client
 */

@Path("useradresse") //avant dernière partie de l'URL
@Produces("application/json") //pour convertir automatiquement réponse java en réponse json
public class UseradresseRest {
    
	//@EJB ne fonctionne pas ici , il faut utiliser  @Inject (plus moderne de CDI) à la place
	@Inject //@Inject de l'api CDI (Container Dependency Injection) ne fonctionne que si
	        //le fichier WEB-INF/beans.xml est présent dans l'application.
	private IServiceUseradresse serviceUseradresse; 
	
	@Path("/{idUtilisateur}") //dernière partie de l'URL
	@GET // GET pour lecture , recherche unique par id/clef primaire
	//URL = http://localhost:8080/myappWeb/services/rest/useradresse/1
	public List<Useradresse> rechercherAdresseUtilisateur(@PathParam("idUtilisateur")int id) {
		return serviceUseradresse.rechercherAdresseUtilisateur(id);
	}


	
}

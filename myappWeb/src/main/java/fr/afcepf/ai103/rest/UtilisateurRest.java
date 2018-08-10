package fr.afcepf.ai103.rest;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import fr.afcepf.ai103.data.Foodfriend;
import fr.afcepf.ai103.data.Utilisateur;
import fr.afcepf.ai103.service.IServiceUtilisateur;

/*
 * classe java du WS REST lié au client
 */

@Path("utilisateur") //avant dernière partie de l'URL
@Produces("application/json") //pour convertir automatiquement réponse java en réponse json
public class UtilisateurRest {
    
	//@EJB ne fonctionne pas ici , il faut utiliser  @Inject (plus moderne de CDI) à la place
	@Inject //@Inject de l'api CDI (Container Dependency Injection) ne fonctionne que si
	        //le fichier WEB-INF/beans.xml est présent dans l'application.
	private IServiceUtilisateur serviceUtilisateur; 
	
	@Context private HttpServletRequest request;
	
//	@Path("") //dernière partie de l'URL
//	@GET // GET pour lecture , recherche multiple via critère(s) de recherche
//	//URL = http://localhost:8080/myappWeb/services/rest/utilisateur?pseudo=...&mdp=amin
//	public Utilisateur rechercherUtilisateur(@QueryParam("pseudo")String pseudo, @QueryParam("mdp")String mdp) {
//
//			return serviceUtilisateur.rechercherUtilisateur(pseudo, mdp);
//	}
	
	@Path("") //dernière partie de l'URL
	@GET // GET pour lecture , recherche multiple via critère(s) de recherche
	//URL = http://localhost:8080/myappWeb/services/rest/utilisateur?pseudo=...&mdp=amin
	public Utilisateur authentificationUtilisateur(@QueryParam("pseudo")String pseudo, @QueryParam("mdp")String mdp) {

		Utilisateur user = serviceUtilisateur.authentificationUtilisateur(pseudo, mdp);
		
		//Si utilisateur identifié : ouvrir session utilisateur
		if(user.getIdUtilisateur() != 0)
		{
			HttpSession session = request.getSession();
			
			Utilisateur utilisateurCourant = user;
			session.setAttribute("Utilisateur", utilisateurCourant );
			
			//----Recuperation des parametres utilisateurs courant avec----//
			//Utilisateur user = (Utilisateur) session.getAttribute("Utilisateur");
		}
			return user;
	}
	
	@Path("/search")
	@GET //URL = http://localhost:8080/myappWeb/services/rest/utilisateur/search?iduser=5
	public List<Utilisateur> rechercherDesUtilisateurs(@QueryParam("iduser")int num) {
		System.out.println("affichage liste des utilisateurs REST = " + num);
		return serviceUtilisateur.rechercherListUtilisateurs(num);
	}
	
}

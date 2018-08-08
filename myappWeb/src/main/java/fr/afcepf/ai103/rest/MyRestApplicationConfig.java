package fr.afcepf.ai103.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/*
 * classe de configuration des WS REST de l'application
 * selon norme JAX-RS , JEE
 */

//URL en  http://localhost:8080/myappWeb/*.jsf --> JSF (FacesServlet)
//URL en  http://localhost:8080/myappWeb/services/rest/.... --> WS REST
@ApplicationPath("/services/rest")
public class MyRestApplicationConfig extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> classes = new HashSet<Class<?>>();
		// register root resource(s):
		classes.add(StockRest.class);
		classes.add(MesAnnoncesPosteesRest.class);
		classes.add(FoodfriendRest.class);
		//classes.add(ServiceRest2.class);
		return classes;
	}
}

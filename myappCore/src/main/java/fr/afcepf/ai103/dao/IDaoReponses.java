package fr.afcepf.ai103.dao;

import java.util.List;

import fr.afcepf.ai103.data.Annonce;
import fr.afcepf.ai103.data.Repannonce;

public interface IDaoReponses {
	
	public List<Repannonce> rechercherReponsesAnnonces(int idUtilisateur);
	

	public List <Repannonce> RepannonceParIdAnnonce(int idAnnonce);
	


	// Insertion dans la base de données
	public Repannonce insererNouvelleReponse(Repannonce rep);

	// Récupérer la liste de toutes les réponses d'une annonce en particulière
	public List<Repannonce> rechercherReponsesPourAnnonce(int id);

	// Récupérer une réponse par son id
	public Repannonce rechercherReponseParId(int id);

	// Maj une réponse dans la base
	public Repannonce mettreAjourReponse(Repannonce rep);


}

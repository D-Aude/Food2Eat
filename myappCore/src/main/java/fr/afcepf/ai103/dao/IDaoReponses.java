package fr.afcepf.ai103.dao;

import java.util.List;

import fr.afcepf.ai103.data.Repannonce;

public interface IDaoReponses {
	
	public List<Repannonce> rechercherReponsesAnnonces(int idUtilisateur);

	public List<Repannonce> rechercherToutesLesReponses();

	public Repannonce rechercherReponseParId(int id);

	public Repannonce insererNouvelleReponse(Repannonce rep);

}

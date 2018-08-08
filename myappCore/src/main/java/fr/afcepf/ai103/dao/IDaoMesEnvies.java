package fr.afcepf.ai103.dao;

import java.util.List;

import fr.afcepf.ai103.data.Annonce;
import fr.afcepf.ai103.data.Utilisateur;

public interface IDaoMesEnvies {

	public List <Annonce> rechercherMesPotentiellesEnvies(Utilisateur id);
	public List <Annonce> rechercherMesEnviesAttenteValidation(Utilisateur id);
	public List <Annonce> rechercherMesEnviesTerminesTermine(Utilisateur id);
	
}

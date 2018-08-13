package fr.afcepf.ai103.service;

import java.util.List;

import fr.afcepf.ai103.data.Repannonce;


public interface IServiceReponses {

	List<Repannonce> rechercherReponsesAnnonces(int id);

	List<Repannonce> rechercherReponseParAnnonce(int utilisateurCourant);

	List<Repannonce> rechercherToutesLesReponses();

	Repannonce rechercherReponsesParId(int id);

	Repannonce save(Repannonce rep);


}

package fr.afcepf.ai103.dao;

import java.util.List;

import fr.afcepf.ai103.data.Useradresse;

public interface IDaoUseradresse {

	List<Useradresse> rechercherAdresseUtilisateur(int idUtilisateur);



}

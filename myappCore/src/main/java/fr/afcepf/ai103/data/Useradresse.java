package fr.afcepf.ai103.data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the useradresse database table.
 * 
 */
@Entity
@NamedQuery(name="Useradresse.findAll", query="SELECT u FROM Useradresse u")
public class Useradresse implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UseradressePK id;

	private byte principale;

	//bi-directional many-to-one association to Adresse
	@ManyToOne
	@JoinColumn(name="ID_ADRESSE", insertable = false, updatable = false)
	private Adresse adresse;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="ID_UTILISATEUR", insertable = false, updatable = false)
	private Utilisateur utilisateur;

	public Useradresse() {
	}

	public UseradressePK getId() {
		return this.id;
	}

	public void setId(UseradressePK id) {
		this.id = id;
	}

	public byte getPrincipale() {
		return this.principale;
	}

	public void setPrincipale(byte principale) {
		this.principale = principale;
	}

	public Adresse getAdresse() {
		return this.adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}
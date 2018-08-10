package fr.afcepf.ai103.data;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the useradresse database table.
 * 
 */
@Embeddable
public class UseradressePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ID_UTILISATEUR", insertable=false, updatable=false)
	private int idUtilisateur;

	@Column(name="ID_ADRESSE", insertable=false, updatable=false)
	private int idAdresse;

	public UseradressePK() {
	}
	public int getIdUtilisateur() {
		return this.idUtilisateur;
	}
	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	public int getIdAdresse() {
		return this.idAdresse;
	}
	public void setIdAdresse(int idAdresse) {
		this.idAdresse = idAdresse;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UseradressePK)) {
			return false;
		}
		UseradressePK castOther = (UseradressePK)other;
		return 
			(this.idUtilisateur == castOther.idUtilisateur)
			&& (this.idAdresse == castOther.idAdresse);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idUtilisateur;
		hash = hash * prime + this.idAdresse;
		
		return hash;
	}
}
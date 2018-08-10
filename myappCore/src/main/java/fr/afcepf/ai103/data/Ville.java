package fr.afcepf.ai103.data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the ville database table.
 * 
 */
@Entity
@NamedQuery(name="Ville.findAll", query="SELECT v FROM Ville v")
public class Ville implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="VILLE_IDVILLE_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="VILLE_IDVILLE_GENERATOR")
	@Column(name="ID_VILLE")
	private int idVille;

	@Column(name="CODE_POSTAL")
	private String codePostal;

	private String ville;

	@OneToMany(mappedBy="ville", fetch=FetchType.LAZY)
	private List<Adresse> adresses;
	
	public Ville() {
	}

	public int getIdVille() {
		return this.idVille;
	}

	public void setIdVille(int idVille) {
		this.idVille = idVille;
	}

	public String getCodePostal() {
		return this.codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return this.ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

}
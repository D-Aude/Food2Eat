package fr.afcepf.ai103.data;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the annulation database table.
 * 
 */
@Entity
@NamedQuery(name="Annulation.findAll", query="SELECT a FROM Annulation a")
public class Annulation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ANNULATION_IDANNULATION_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ANNULATION_IDANNULATION_GENERATOR")
	@Column(name="ID_ANNULATION")
	private int idAnnulation;

	@Column(name="NOM_ANNULATION")
	private String nomAnnulation;

	//bi-directional many-to-one association to Annonce
		
	@OneToMany(mappedBy="annulation", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Annonce> annonces;

	public Annulation() {
	}

	public int getIdAnnulation() {
		return this.idAnnulation;
	}

	public void setIdAnnulation(int idAnnulation) {
		this.idAnnulation = idAnnulation;
	}

	public String getNomAnnulation() {
		return this.nomAnnulation;
	}

	public void setNomAnnulation(String nomAnnulation) {
		this.nomAnnulation = nomAnnulation;
	}

	public List<Annonce> getAnnonces() {
		return this.annonces;
	}

	public void setAnnonces(List<Annonce> annonces) {
		this.annonces = annonces;
	}

	public Annonce addAnnonce(Annonce annonce) {
		getAnnonces().add(annonce);
		annonce.setAnnulation(this);

		return annonce;
	}

	public Annonce removeAnnonce(Annonce annonce) {
		getAnnonces().remove(annonce);
		annonce.setAnnulation(null);

		return annonce;
	}

}
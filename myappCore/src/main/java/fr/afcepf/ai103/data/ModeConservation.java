package fr.afcepf.ai103.data;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the mode_conservation database table.
 * 
 */
@Entity
@Table(name="mode_conservation")
@NamedQuery(name="ModeConservation.findAll", query="SELECT m FROM ModeConservation m")
public class ModeConservation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MODE_CONSERVATION_IDMODECONSERVATION_GENERATOR", sequenceName="ORDER_SEQUENCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MODE_CONSERVATION_IDMODECONSERVATION_GENERATOR")
	@Column(name="ID_MODE_CONSERVATION")
	private int idModeConservation;

	@Column(name="JOURS_EXTENSION_CONSERVATION")
	private int joursExtensionConservation;

	@Column(name="NOM_MODE_CONSERVATION")
	private String nomModeConservation;

	//bi-directional many-to-one association to Produit
	@OneToMany(mappedBy="modeConservation", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Produit> produits;

	public ModeConservation() {
	}

	public int getIdModeConservation() {
		return this.idModeConservation;
	}

	public void setIdModeConservation(int idModeConservation) {
		this.idModeConservation = idModeConservation;
	}

	public int getJoursExtensionConservation() {
		return this.joursExtensionConservation;
	}

	public void setJoursExtensionConservation(int joursExtensionConservation) {
		this.joursExtensionConservation = joursExtensionConservation;
	}

	public String getNomModeConservation() {
		return this.nomModeConservation;
	}

	public void setNomModeConservation(String nomModeConservation) {
		this.nomModeConservation = nomModeConservation;
	}

	public List<Produit> getProduits() {
		return this.produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	public Produit addProduit(Produit produit) {
		getProduits().add(produit);
		produit.setModeConservation(this);

		return produit;
	}

	public Produit removeProduit(Produit produit) {
		getProduits().remove(produit);
		produit.setModeConservation(null);

		return produit;
	}

}
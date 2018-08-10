package fr.afcepf.ai103.data;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the mesure database table.
 * 
 */
@Entity
@NamedQuery(name="Mesure.findAll", query="SELECT m FROM Mesure m")
public class Mesure implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MESURE_IDMESURE_GENERATOR", sequenceName="ORDER_SEQUENCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MESURE_IDMESURE_GENERATOR")
	@Column(name="ID_MESURE")
	private int idMesure;

	private byte denombrable;

	@Column(name="NOM_MESURE")
	private String nomMesure;

	//bi-directional many-to-one association to Produit
	@OneToMany(mappedBy="mesure", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Produit> produits;

	public Mesure() {
	}

	public int getIdMesure() {
		return this.idMesure;
	}

	public void setIdMesure(int idMesure) {
		this.idMesure = idMesure;
	}

	public byte getDenombrable() {
		return this.denombrable;
	}

	public void setDenombrable(byte denombrable) {
		this.denombrable = denombrable;
	}

	public String getNomMesure() {
		return this.nomMesure;
	}

	public void setNomMesure(String nomMesure) {
		this.nomMesure = nomMesure;
	}

	public List<Produit> getProduits() {
		return this.produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	public Produit addProduit(Produit produit) {
		getProduits().add(produit);
		produit.setMesure(this);

		return produit;
	}

	public Produit removeProduit(Produit produit) {
		getProduits().remove(produit);
		produit.setMesure(null);

		return produit;
	}

}
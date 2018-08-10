package fr.afcepf.ai103.data;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the categorie database table.
 * 
 */
@Entity
@NamedQuery(name="Categorie.findAll", query="SELECT c FROM Categorie c")
public class Categorie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CATEGORIE_IDCATEGORIE_GENERATOR", sequenceName="ORDER_SEQUENCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CATEGORIE_IDCATEGORIE_GENERATOR")
	@Column(name="ID_CATEGORIE")
	private int idCategorie;

	@Column(name="JOURS_EXTENSION_CATEGORIE")
	private int joursExtensionCategorie;

	@Column(name="NOM_CATEGORIE")
	private String nomCategorie;

	//bi-directional many-to-one association to Groupe
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ID_GROUPE")
	@JsonIgnore
	private Groupe groupe;

	//bi-directional many-to-one association to Produit
	
	@OneToMany(mappedBy="categorie", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Produit> produits;

	public Categorie() {
	}

	public int getIdCategorie() {
		return this.idCategorie;
	}

	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}

	public int getJoursExtensionCategorie() {
		return this.joursExtensionCategorie;
	}

	public void setJoursExtensionCategorie(int joursExtensionCategorie) {
		this.joursExtensionCategorie = joursExtensionCategorie;
	}

	public String getNomCategorie() {
		return this.nomCategorie;
	}

	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}

	public Groupe getGroupe() {
		return this.groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}

	public List<Produit> getProduits() {
		return this.produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	public Produit addProduit(Produit produit) {
		getProduits().add(produit);
		produit.setCategorie(this);

		return produit;
	}

	public Produit removeProduit(Produit produit) {
		getProduits().remove(produit);
		produit.setCategorie(null);

		return produit;
	}

}
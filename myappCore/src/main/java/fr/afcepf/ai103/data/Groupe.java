package fr.afcepf.ai103.data;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the groupe database table.
 * 
 */
@Entity
@NamedQuery(name="Groupe.findAll", query="SELECT g FROM Groupe g")
public class Groupe implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="GROUPE_IDGROUPE_GENERATOR", sequenceName="ORDER_SEQUENCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GROUPE_IDGROUPE_GENERATOR")
	@Column(name="ID_GROUPE")
	private int idGroupe;

	@Column(name="NOM_GROUPE")
	private String nomGroupe;

	//bi-directional many-to-one association to Categorie
	@OneToMany(mappedBy="groupe", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Categorie> categories;

	public Groupe() {
	}

	public int getIdGroupe() {
		return this.idGroupe;
	}

	public void setIdGroupe(int idGroupe) {
		this.idGroupe = idGroupe;
	}

	public String getNomGroupe() {
		return this.nomGroupe;
	}

	public void setNomGroupe(String nomGroupe) {
		this.nomGroupe = nomGroupe;
	}

	public List<Categorie> getCategories() {
		return this.categories;
	}

	public void setCategories(List<Categorie> categories) {
		this.categories = categories;
	}

	public Categorie addCategory(Categorie category) {
		getCategories().add(category);
		category.setGroupe(this);

		return category;
	}

	public Categorie removeCategory(Categorie category) {
		getCategories().remove(category);
		category.setGroupe(null);

		return category;
	}

}
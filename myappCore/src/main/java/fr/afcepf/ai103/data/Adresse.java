package fr.afcepf.ai103.data;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the adresse database table.
 * 
 */
@Entity
@NamedQuery(name="Adresse.findAll", query="SELECT a FROM Adresse a")
public class Adresse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ADRESSE_IDADRESSE_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ADRESSE_IDADRESSE_GENERATOR")
	@Column(name="ID_ADRESSE")
	private int idAdresse;

	@Column(name="COMPLEMENT_ADRESSE")
	private String complementAdresse;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_DEBUT_VALIDITE")
	private Date dateDebutValidite;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_FIN_VALIDITE")
	private Date dateFinValidite;


	@ManyToOne
	@JoinColumn(name="ID_VILLE")
	private Ville ville;

	@Column(name="NOM_VOIE")
	private String nomVoie;

	@Column(name="NUMERO_VOIE")
	private int numeroVoie;

	@Column(name="TYPE_VOIE")
	private String typeVoie;

	private double x;

	private double y;

	//bi-directional many-to-one association to Useradresse
	@OneToMany(mappedBy="adresse", fetch=FetchType.LAZY)
	@JsonIgnore
	private List<Useradresse> useradresses;

	public Adresse() {
	}

	public int getIdAdresse() {
		return this.idAdresse;
	}

	public void setIdAdresse(int idAdresse) {
		this.idAdresse = idAdresse;
	}

	public String getComplementAdresse() {
		return this.complementAdresse;
	}

	public void setComplementAdresse(String complementAdresse) {
		this.complementAdresse = complementAdresse;
	}

	public Date getDateDebutValidite() {
		return this.dateDebutValidite;
	}

	public void setDateDebutValidite(Date dateDebutValidite) {
		this.dateDebutValidite = dateDebutValidite;
	}

	public Date getDateFinValidite() {
		return this.dateFinValidite;
	}

	public void setDateFinValidite(Date dateFinValidite) {
		this.dateFinValidite = dateFinValidite;
	}


	public Ville getVille() {
		return this.ville;
	}



	public void setVille(Ville ville) {

		this.ville = ville;
	}

	public String getNomVoie() {
		return this.nomVoie;
	}

	public void setNomVoie(String nomVoie) {
		this.nomVoie = nomVoie;
	}

	public int getNumeroVoie() {
		return this.numeroVoie;
	}

	public void setNumeroVoie(int numeroVoie) {
		this.numeroVoie = numeroVoie;
	}

	public String getTypeVoie() {
		return this.typeVoie;
	}

	public void setTypeVoie(String typeVoie) {
		this.typeVoie = typeVoie;
	}

	public double getX() {
		return this.x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return this.y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public List<Useradresse> getUseradresses() {
		return this.useradresses;
	}

	public void setUseradresses(List<Useradresse> useradresses) {
		this.useradresses = useradresses;
	}

	public Useradresse addUseradress(Useradresse useradress) {
		getUseradresses().add(useradress);
		useradress.setAdresse(this);

		return useradress;
	}

	public Useradresse removeUseradress(Useradresse useradress) {
		getUseradresses().remove(useradress);
		useradress.setAdresse(null);

		return useradress;
	}

}
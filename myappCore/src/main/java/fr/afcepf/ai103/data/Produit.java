package fr.afcepf.ai103.data;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the produit database table.
 * 
 */
@Entity
@NamedQuery(name="Produit.findAll", query="SELECT p FROM Produit p ORDER BY p.nomProduit")
public class Produit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PRODUIT_IDPRODUIT_GENERATOR", sequenceName="ORDER_SEQUENCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUIT_IDPRODUIT_GENERATOR")
	@Column(name="ID_PRODUIT")
	private int idProduit;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_ACTIVATION")
	private Date dateActivation;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_DESACTIVATION")
	private Date dateDesactivation;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_PROPOSITION")
	private Date dateProposition;

	@Column(name="NOM_PRODUIT")
	private String nomProduit;

	@Column(name="NOMBRE_UNITE")
	private int nombreUnite;

	//bi-directional many-to-one association to Categorie
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name="ID_CATEGORIE")
	private Categorie categorie;

	//bi-directional many-to-one association to Mesure
	@ManyToOne
	@JoinColumn(name="ID_MESURE")
	private Mesure mesure;

	//bi-directional many-to-one association to ModeConservation
	@ManyToOne
	@JoinColumn(name="ID_MODE_CONSERVATION")
	private ModeConservation modeConservation;

	//bi-directional many-to-one association to Stock
	@OneToMany(mappedBy="produit", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Stock> stocks;

	public Produit() {
	}

	public int getIdProduit() {
		return this.idProduit;
	}

	public void setIdProduit(int idProduit) {
		this.idProduit = idProduit;
	}

	public Date getDateActivation() {
		return this.dateActivation;
	}

	public void setDateActivation(Date dateActivation) {
		this.dateActivation = dateActivation;
	}

	public Date getDateDesactivation() {
		return this.dateDesactivation;
	}

	public void setDateDesactivation(Date dateDesactivation) {
		this.dateDesactivation = dateDesactivation;
	}

	public Date getDateProposition() {
		return this.dateProposition;
	}

	public void setDateProposition(Date dateProposition) {
		this.dateProposition = dateProposition;
	}

	public String getNomProduit() {
		return this.nomProduit;
	}

	public void setNomProduit(String nomProduit) {
		this.nomProduit = nomProduit;
	}

	public int getNombreUnite() {
		return this.nombreUnite;
	}

	public void setNombreUnite(int nombreUnite) {
		this.nombreUnite = nombreUnite;
	}

	public Categorie getCategorie() {
		return this.categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Mesure getMesure() {
		return this.mesure;
	}

	public void setMesure(Mesure mesure) {
		this.mesure = mesure;
	}

	public ModeConservation getModeConservation() {
		return this.modeConservation;
	}

	public void setModeConservation(ModeConservation modeConservation) {
		this.modeConservation = modeConservation;
	}

	public List<Stock> getStocks() {
		return this.stocks;
	}

	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}

	public Stock addStock(Stock stock) {
		getStocks().add(stock);
		stock.setProduit(this);

		return stock;
	}

	public Stock removeStock(Stock stock) {
		getStocks().remove(stock);
		stock.setProduit(null);

		return stock;
	}

}
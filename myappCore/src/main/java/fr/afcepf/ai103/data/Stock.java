package fr.afcepf.ai103.data;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the stock database table.
 * 
 */
@Entity
@NamedQueries({
		@NamedQuery(name="Stock.findAll", query="SELECT s FROM Stock s"),
		
		@NamedQuery(name="Stock.parUtilisateur", query="SELECT s FROM Stock s "
															+ "left join s.annonces a "
														+ "WHERE s.utilisateur.idUtilisateur = :idUtilisateur "
															+ "and s.dateJeter is null "
															+ "and s.dateManger is null "
															+ "and (s.annonces is empty or a.dateFinAnnonce is null) "
														+ "ORDER BY s.dlc"),
		
		@NamedQuery(name="Stock.parModeConservation", query="SELECT s FROM Stock s "
																+ "left join s.annonces a "
															+ "WHERE s.utilisateur.idUtilisateur = :idUtilisateur "
																+ "and s.produit.modeConservation.nomModeConservation = :nomModeConservation "
																+ "and s.dateJeter is null "
																+ "and s.dateManger is null "
																+ "and (s.annonces is empty or a.dateFinAnnonce is null) "
															+ "ORDER BY s.dlc"),
		
		@NamedQuery(name="Stock.Congelateur", query="SELECT s FROM Stock s "
														+ "left join s.annonces a "
													+ "WHERE s.utilisateur.idUtilisateur = :idUtilisateur "
														+ "and s.produit.modeConservation.nomModeConservation = :nomModeConservation "
														+ "and s.dateJeter is null "
														+ "and s.dateManger is null "
														+ "and (s.annonces is empty or a.dateFinAnnonce is null) "
													+ "ORDER BY s.dlc"),
		
		@NamedQuery(name="Stock.Frigo", query="SELECT s FROM Stock s "
												+ "left join s.annonces a "
											+ "WHERE s.utilisateur.idUtilisateur = :idUtilisateur "
												+ "and (s.produit.modeConservation.nomModeConservation = :frais "
												+ "OR (s.produit.modeConservation.nomModeConservation =:conserve "
												+ "and s.entame = 1) "
												+ "OR (s.produit.modeConservation.nomModeConservation = :epicerie_frais "
													+ "and s.entame = 1)) "
												+ "and s.dateJeter is null "
												+ "and s.dateManger is null "
												+ "and (s.annonces is empty or a.dateFinAnnonce is null) "
											+ "ORDER BY s.dlc"),
		
		@NamedQuery(name="Stock.Placard", query="SELECT s FROM Stock s "
													+ "left join s.annonces a "
												+ "WHERE s.utilisateur.idUtilisateur = :idUtilisateur "
													+ "and (s.produit.modeConservation.nomModeConservation =:epicerie "
														+ "OR s.produit.modeConservation.nomModeConservation =:epicerie_fine "
														+ "OR (s.produit.modeConservation.nomModeConservation =:conserve "
														+ "and s.entame = 0) "
														+ "OR (s.produit.modeConservation.nomModeConservation = :epicerie_frais "
														+ "and s.entame = 0)) "
													+ "and s.dateJeter is null "
													+ "and s.dateManger is null "
													+ "and (s.annonces is empty or a.dateFinAnnonce is null) "
												+ "ORDER BY s.dlc"),
		
})

public class Stock implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@GeneratedValue(strategy=GenerationType.IDENTITY) //IDENTITY convient le mieux
    //pour les bases de données récentes
	@Column(name="ID_STOCK")
	private int idStock;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_ACHAT")
	private Date dateAchat;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_CONSO_PREF")
	private Date dateConsoPref;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_JETER")
	private Date dateJeter;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_MANGER")
	private Date dateManger;

	@Temporal(TemporalType.DATE)
	private Date dlc;

	private byte entame;

	@Column(name="FRACTION_RESTANTE")
	private int fractionRestante;

	private int quantite;

	//bi-directional many-to-one association to Annonce
	@OneToMany(mappedBy="stock", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Annonce> annonces;

	//bi-directional many-to-one association to Mojeter
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ID_MOTIF_JETER")
	@JsonIgnore
	private Mojeter mojeter;

	//bi-directional many-to-one association to Produit
	@ManyToOne
	@JoinColumn(name="ID_PRODUIT")
	private Produit produit;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="ID_UTILISATEUR")
	private Utilisateur utilisateur;

	public Stock() {
	}

	public int getIdStock() {
		return this.idStock;
	}

	public void setIdStock(int idStock) {
		this.idStock = idStock;
	}

	public Date getDateAchat() {
		return this.dateAchat;
	}

	public void setDateAchat(Date dateAchat) {
		this.dateAchat = dateAchat;
	}

	public Date getDateConsoPref() {
		return this.dateConsoPref;
	}

	public void setDateConsoPref(Date dateConsoPref) {
		this.dateConsoPref = dateConsoPref;
	}

	public Date getDateJeter() {
		return this.dateJeter;
	}

	public void setDateJeter(Date dateJeter) {
		this.dateJeter = dateJeter;
	}

	public Date getDateManger() {
		return this.dateManger;
	}

	public void setDateManger(Date dateManger) {
		this.dateManger = dateManger;
	}

	public Date getDlc() {
		return this.dlc;
	}

	public void setDlc(Date dlc) {
		this.dlc = dlc;
	}

	public byte getEntame() {
		return this.entame;
	}

	public void setEntame(byte entame) {
		this.entame = entame;
	}

	public int getFractionRestante() {
		return this.fractionRestante;
	}

	public void setFractionRestante(int fractionRestante) {
		this.fractionRestante = fractionRestante;
	}

	public int getQuantite() {
		return this.quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public List<Annonce> getAnnonces() {
		return this.annonces;
	}

	public void setAnnonces(List<Annonce> annonces) {
		this.annonces = annonces;
	}

	public Annonce addAnnonce(Annonce annonce) {
		getAnnonces().add(annonce);
		annonce.setStock(this);

		return annonce;
	}

	public Annonce removeAnnonce(Annonce annonce) {
		getAnnonces().remove(annonce);
		annonce.setStock(null);

		return annonce;
	}

	public Mojeter getMojeter() {
		return this.mojeter;
	}

	public void setMojeter(Mojeter mojeter) {
		this.mojeter = mojeter;
	}

	public Produit getProduit() {
		return this.produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}
package fr.afcepf.ai103.data;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the annonce database table.
 * 
 */
@Entity
@NamedQueries(value= {@NamedQuery(name="Annonce.findAll", query="SELECT a FROM Annonce a"),
		@NamedQuery(name="Annonce.annonceParId", query="SELECT a FROM Annonce a  WHERE a.idAnnonce  = :idAnnonce"),
		@NamedQuery(name="Annonce.listeMesAnnoncesEnCours", query="SELECT a FROM Annonce a   WHERE a.stock.utilisateur.idUtilisateur = :idUtilisateur "
																								+ "and a.dateAnnulation is null "
																								+ "and a.dateFinAnnonce is null "

																								+"and  a.repannonces is empty"),

		@NamedQuery(name="Annonce.listeMesAnnoncesTerminees", query="SELECT a FROM Annonce a WHERE a.stock.utilisateur.idUtilisateur = :idUtilisateur "
																								+ "and(a.dateAnnulation is not null "
																								+ "or a.dateFinAnnonce is not null)"),
		@NamedQuery(name="Annonce.listeMesAnnoncesTermineesNonAnnulees", query="SELECT a FROM Annonce a WHERE a.stock.utilisateur.idUtilisateur = :idUtilisateur "
																								+ "and a.dateAnnulation is  null "
																								+ "and a.dateFinAnnonce is not null"),
		@NamedQuery(name="Annonce.listeMesAnnoncesTermineesCarAnnulees", query="SELECT a FROM Annonce a WHERE a.stock.utilisateur.idUtilisateur = :idUtilisateur "
																								+ "and a.dateAnnulation is not null "
																								+ "and a.dateFinAnnonce is null"),
		
		@NamedQuery(name="Annonce.listeMesAValidees", query="SELECT a FROM Annonce a  join a.repannonces r WHERE a.stock.utilisateur.idUtilisateur = :idUtilisateur "
																								+ "and a.dateAnnulation is null "
																								+ "and r.dateReponse is not null "
																								+ "and a.dateFinAnnonce is null "
																								+ "and r.dateAcceptationReponse is not null"),
		
//		@NamedQuery(name="Annonce.ListeAnnonceEnCoursSaufUtilisateur", query="SELECT a FROM Annonce a  join a.repannonces r WHERE a.stock.utilisateur.idUtilisateur <> :idUtilisateur "
//																								+ "and (r.dateAcceptationReponse is null and a.dateFinAnnonce is null)"),
		@NamedQuery(name="Annonce.ListeAnnonceEnCoursSaufUtilisateur", query="SELECT a FROM Annonce a  WHERE a.stock.utilisateur.idUtilisateur <> :idUtilisateur "
				+ "and (a.dateAnnulation is null and a.dateFinAnnonce is null)"),
		
		@NamedQuery(name="Annonce.ListeToutesLesAnnonces", query="SELECT a FROM Annonce a  join a.repannonces r WHERE a.dateAnnulation is null "
																								+ "and r.dateAcceptationReponse is null and a.dateFinAnnonce is null"),
		
		@NamedQuery(name="Annonce.listeMesEnvies", query= "SELECT a FROM Annonce a join a.repannonces r WHERE r.utilisateur.idUtilisateur =:idUtilisateur"
																										+ " and( r.dateAcceptationReponse is null and a.dateFinAnnonce is  null)"),
		
		@NamedQuery(name="Annonce.listeMesEnviesConfirmes", query="SELECT a FROM Annonce a join a.repannonces r WHERE r.utilisateur.idUtilisateur =:idUtilisateur"
																	+ " and( r.dateAcceptationReponse is not null and (a.dateFinAnnonce is  null and a.dateAnnulation is null))"),
		
	
		@NamedQuery(name="Annonce.listeMesEnviesTermines", query="SELECT a FROM Annonce a join a.repannonces r join r.evaluations e  WHERE r.utilisateur.idUtilisateur =:idUtilisateur"
																									+ " and r.dateRefus is null and ( r.dateAcceptationReponse is not null "
																									+ "and (a.dateFinAnnonce is not null and a.dateAnnulation is null and e.note is not null))"),
		
		@NamedQuery(name="Annonce.listeMesEnviesTerminesAEvaluees", query ="SELECT a FROM Annonce a join a.repannonces r   WHERE r.utilisateur.idUtilisateur =:idUtilisateur"
																								+ " and r.dateAcceptationReponse is not null and (r.dateRefus is null "
																								+ "and a.dateFinAnnonce is not null and a.dateAnnulation is null) and r.evaluations is empty")
		})

public class Annonce implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ANNONCE_IDANNONCE_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ANNONCE_IDANNONCE_GENERATOR")
	@Column(name="ID_ANNONCE")
	private int idAnnonce;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_ANNULATION")
	private Date dateAnnulation;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_FIN_ANNONCE")
	private Date dateFinAnnonce;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_PUBLICATION")
	private Date datePublication;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_RDV_1")
	private Date dateRdv1;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_RDV_2")
	private Date dateRdv2;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_RDV_3")
	private Date dateRdv3;

	private String titre;

	//bi-directional many-to-one association to Adresse
	@ManyToOne
	@JoinColumn(name="ID_ADRESSE")
	private Adresse adresse;

	//bi-directional many-to-one association to Annulation
	@ManyToOne
	@JoinColumn(name="ID_ANNULATION")
	private Annulation annulation;

	//bi-directional many-to-one association to Stock
	@ManyToOne
	@JoinColumn(name="ID_STOCK")
	private Stock stock;

	//bi-directional many-to-one association to Repannonce
	
	@OneToMany(mappedBy="annonce", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Repannonce> repannonces;

	public Annonce() {
	}

	public int getIdAnnonce() {
		return this.idAnnonce;
	}

	public void setIdAnnonce(int idAnnonce) {
		this.idAnnonce = idAnnonce;
	}

	public Date getDateAnnulation() {
		return this.dateAnnulation;
	}

	public void setDateAnnulation(Date dateAnnulation) {
		this.dateAnnulation = dateAnnulation;
	}

	public Date getDateFinAnnonce() {
		return this.dateFinAnnonce;
	}

	public void setDateFinAnnonce(Date dateFinAnnonce) {
		this.dateFinAnnonce = dateFinAnnonce;
	}

	public Date getDatePublication() {
		return this.datePublication;
	}

	public void setDatePublication(Date datePublication) {
		this.datePublication = datePublication;
	}

	public Date getDateRdv1() {
		return this.dateRdv1;
	}

	public void setDateRdv1(Date dateRdv1) {
		this.dateRdv1 = dateRdv1;
	}

	public Date getDateRdv2() {
		return this.dateRdv2;
	}

	public void setDateRdv2(Date dateRdv2) {
		this.dateRdv2 = dateRdv2;
	}

	public Date getDateRdv3() {
		return this.dateRdv3;
	}

	public void setDateRdv3(Date dateRdv3) {
		this.dateRdv3 = dateRdv3;
	}

	public String getTitre() {
		return this.titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Adresse getAdresse() {
		return this.adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Annulation getAnnulation() {
		return this.annulation;
	}

	public void setAnnulation(Annulation annulation) {
		this.annulation = annulation;
	}

	public Stock getStock() {
		return this.stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public List<Repannonce> getRepannonces() {
		return this.repannonces;
	}

	public void setRepannonces(List<Repannonce> repannonces) {
		this.repannonces = repannonces;
	}

	public Repannonce addRepannonce(Repannonce repannonce) {
		getRepannonces().add(repannonce);
		repannonce.setAnnonce(this);

		return repannonce;
	}

	public Repannonce removeRepannonce(Repannonce repannonce) {
		getRepannonces().remove(repannonce);
		repannonce.setAnnonce(null);

		return repannonce;
	}

}
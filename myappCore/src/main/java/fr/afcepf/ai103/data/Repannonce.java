package fr.afcepf.ai103.data;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the repannonce database table.
 * 
 */
@Entity
@NamedQueries
(value= {@NamedQuery(name="Repannonce.findAll", query="SELECT r FROM Repannonce r"),
		@NamedQuery(name="Repannonce.essai", query ="SELECT r FROM Repannonce r  WHERE r.utilisateur.idUtilisateur =:idUtilisateur"),
		@NamedQuery(name="Repannonce.parUtilisateur", query = "SELECT r FROM Repannonce r "
																+ "WHERE r.annonce.stock.utilisateur.idUtilisateur = :id "
																+ "ORDER BY r.dateRdv")})
		
public class Repannonce implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="REPANNONCE_IDREPONSE_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REPANNONCE_IDREPONSE_GENERATOR")
	@Column(name="ID_REPONSE")
	private int idReponse;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_ACCEPTATION_REPONSE")
	private Date dateAcceptationReponse;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_ANNULATION_REPONSE")
	private Date dateAnnulationReponse;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_RDV")
	private Date dateRdv;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_REFUS")
	private Date dateRefus;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_REPONSE")
	private Date dateReponse;

	//bi-directional many-to-one association to Evaluation
	
	@OneToMany(mappedBy="repannonce", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Evaluation> evaluations;

	//bi-directional many-to-one association to Annonce
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="ID_ANNONCE")
	private Annonce annonce;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="ID_UTILISATEUR")
	private Utilisateur utilisateur;

	public Repannonce() {
	}

	public int getIdReponse() {
		return this.idReponse;
	}

	public void setIdReponse(int idReponse) {
		this.idReponse = idReponse;
	}

	public Date getDateAcceptationReponse() {
		return this.dateAcceptationReponse;
	}

	public void setDateAcceptationReponse(Date dateAcceptationReponse) {
		this.dateAcceptationReponse = dateAcceptationReponse;
	}

	public Date getDateAnnulationReponse() {
		return this.dateAnnulationReponse;
	}

	public void setDateAnnulationReponse(Date dateAnnulationReponse) {
		this.dateAnnulationReponse = dateAnnulationReponse;
	}

	public Date getDateRdv() {
		return this.dateRdv;
	}

	public void setDateRdv(Date dateRdv) {
		this.dateRdv = dateRdv;
	}

	public Date getDateRefus() {
		return this.dateRefus;
	}

	public void setDateRefus(Date dateRefus) {
		this.dateRefus = dateRefus;
	}

	public Date getDateReponse() {
		return this.dateReponse;
	}

	public void setDateReponse(Date dateReponse) {
		this.dateReponse = dateReponse;
	}

	public List<Evaluation> getEvaluations() {
		return this.evaluations;
	}

	public void setEvaluations(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}

	public Evaluation addEvaluation(Evaluation evaluation) {
		getEvaluations().add(evaluation);
		evaluation.setRepannonce(this);

		return evaluation;
	}

	public Evaluation removeEvaluation(Evaluation evaluation) {
		getEvaluations().remove(evaluation);
		evaluation.setRepannonce(null);

		return evaluation;
	}

	public Annonce getAnnonce() {
		return this.annonce;
	}

	public void setAnnonce(Annonce annonce) {
		this.annonce = annonce;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setutilisateur() {
		this.utilisateur = utilisateur;
	}


}
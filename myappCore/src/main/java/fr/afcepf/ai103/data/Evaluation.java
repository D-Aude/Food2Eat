package fr.afcepf.ai103.data;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;


/**
 * The persistent class for the evaluation database table.
 * 
 */
@Entity
@NamedQueries({@NamedQuery(name="Evaluation.findAll", query="SELECT e FROM Evaluation e"),
@NamedQuery(name="Evaluations.avecNoteEtCommentaire", query="SELECT e FROM Evaluation e "
															+ "WHERE e.repannonce.idReponse = :idReponse "
															+ "AND e.note IS NOT NULL "
															+ "AND e.commentaire IS NOT NULL"),

@NamedQuery(name="Evaluations.annoncesAvecEvaluationetNote", query="SELECT e from Evaluation e join e.repannonce r "
															+ "WHERE e.repannonce.idReponse = :idReponse "
															+ "AND r.idReponse = r.idReponse "), //en cours 

@NamedQuery(name="Evaluations.avecNoteSansCommentaire", query="SELECT e FROM Evaluation e "
															+ "WHERE e.repannonce.idReponse = :idReponse "
															+ "AND e.note IS NOT NULL "
															+ "AND e.commentaire IS NULL "),

@NamedQuery(name="Evaluations.avecNoteEtCommentaireParUt", query="SELECT e FROM Evaluation e "
														+ "WHERE e.repannonce.utilisateur.idUtilisateur = :idUtilisateur "
														+ "AND e.note IS NOT NULL "
														+ "AND e.commentaire IS NOT NULL"),
})



public class Evaluation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_EVALUATION")
	private int idEvaluation;

	private String commentaire;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_EVALUATION")
	private Date dateEvaluation;

	private int note;

	//bi-directional many-to-one association to Repannonce
	@ManyToOne
	//@JsonIgnore
	@JoinColumn(name="ID_REPONSE")
	private Repannonce repannonce;

	public Evaluation() {
	}

	public int getIdEvaluation() {
		return this.idEvaluation;
	}

	public void setIdEvaluation(int idEvaluation) {
		this.idEvaluation = idEvaluation;
	}

	public String getCommentaire() {
		return this.commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Date getDateEvaluation() {
		return this.dateEvaluation;
	}

	public void setDateEvaluation(Date dateEvaluation) {
		this.dateEvaluation = dateEvaluation;
	}

	public int getNote() {
		return this.note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public Repannonce getRepannonce() {
		return this.repannonce;
	}

	public void setRepannonce(Repannonce repannonce) {
		this.repannonce = repannonce;
	}

}
package fr.afcepf.ai103.data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the evaluation database table.
 * 
 */
@Entity
@NamedQuery(name="Evaluation.findAll", query="SELECT e FROM Evaluation e")
public class Evaluation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EVALUATION_IDEVALUATION_GENERATOR", sequenceName="ORDER_SEQUENCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EVALUATION_IDEVALUATION_GENERATOR")
	@Column(name="ID_EVALUATION")
	private int idEvaluation;

	private String commentaire;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_EVALUATION")
	private Date dateEvaluation;

	private int note;

	//bi-directional many-to-one association to Repannonce
	@ManyToOne
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
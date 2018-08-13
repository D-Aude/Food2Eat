package fr.afcepf.ai103.data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the foodfriend database table.
 * 
 */
@Entity
@NamedQueries({	
	@NamedQuery(name="Foodfriend.findAll", query="SELECT f FROM Foodfriend f"),
	//@NamedQuery(name="Foodfriend.utilisateur1", query="SELECT f FROM Foodfriend f WHERE (f.utilisateur1.idUtilisateur =:utilisateur1) and (f.dateFinRelation is null) and (f.dateReponse is not null)"),
	@NamedQuery(name="Foodfriend.utilisateur1", query="SELECT f FROM Foodfriend f WHERE (f.utilisateur1.idUtilisateur =:utilisateur1 or f.utilisateur2.idUtilisateur =:utilisateur1) and (f.dateFinRelation is null) and (f.dateReponse is not null)"),
	@NamedQuery(name="Foodfriend.demandesrecues", query="SELECT f FROM Foodfriend f WHERE (f.utilisateur2.idUtilisateur =:utilisateur2) and (f.dateReponse is null)"),
	@NamedQuery(name="Foodfriend.demandesenvoyees", query="SELECT f FROM Foodfriend f WHERE (f.utilisateur1.idUtilisateur =:utilisateur1) and (f.dateReponse is null)")
})
public class Foodfriend implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_FOODFRIEND")
	private Integer idFoodfriend;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_DEMANDE")
	private Date dateDemande;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_FIN_RELATION")
	private Date dateFinRelation;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_REPONSE")
	private Date dateReponse;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="ID_UTILISATEUR")
	private Utilisateur utilisateur1;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="UTI_ID_UTILISATEUR")
	private Utilisateur utilisateur2;

	public Foodfriend() {
	}
	
	





	public Integer getIdFoodfriend() {
		return this.idFoodfriend;
	}

	public void setIdFoodfriend(Integer idFoodfriend) {
		this.idFoodfriend = idFoodfriend;
	}

	public Date getDateDemande() {
		return this.dateDemande;
	}

	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}

	public Date getDateFinRelation() {
		return this.dateFinRelation;
	}

	public void setDateFinRelation(Date dateFinRelation) {
		this.dateFinRelation = dateFinRelation;
	}

	public Date getDateReponse() {
		return this.dateReponse;
	}

	public void setDateReponse(Date dateReponse) {
		this.dateReponse = dateReponse;
	}

	public Utilisateur getUtilisateur1() {
		return this.utilisateur1;
	}

	public void setUtilisateur1(Utilisateur utilisateur1) {
		this.utilisateur1 = utilisateur1;
	}

	public Utilisateur getUtilisateur2() {
		return this.utilisateur2;
	}

	public void setUtilisateur2(Utilisateur utilisateur2) {
		this.utilisateur2 = utilisateur2;
	}

}
package fr.afcepf.ai103.data;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the utilisateur database table.
 * 
 */
@Entity

@NamedQueries({
	@NamedQuery(name="Utilisateur.findAll", query="SELECT u FROM Utilisateur u"),
	@NamedQuery(name="Utilisateur.Authentification", query="SELECT u FROM Utilisateur u "
															+ "WHERE u.pseudo = :pseudo "
															+ "and u.mdp = :mdp "
															+ "and u.dateDesinscription is null"
															),
	// Requête pour retrouver les utilisateurs qui ne sont pas foodfriend avec moi et à qui je peux envoyer une invitations
	@NamedQuery(name="Utilisateur.notFoodfriend", query="SELECT u FROM Utilisateur u "
			+ "where u.idUtilisateur <> :idUtilisateur "
			+ "and u.idUtilisateur not in "
			+ "(SELECT f.utilisateur1.idUtilisateur FROM Utilisateur u join u.foodfriend2 f WHERE u.idUtilisateur = :idUtilisateur) "
			+ "and u.idUtilisateur not in "
			+ "(SELECT f.utilisateur2.idUtilisateur FROM Utilisateur u join u.foodfriend1 f WHERE u.idUtilisateur = :idUtilisateur)")
	})

public class Utilisateur implements Serializable {
		public List<Foodfriend> getFoodfriend1() {
		return foodfriend1;
	}



		private static final long serialVersionUID = 1L;
		

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	@Column(name="ID_UTILISATEUR")
	private Integer idUtilisateur;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_DE_NAISSANCE")
	private Date dateDeNaissance;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_DESINSCRIPTION")
	private Date dateDesinscription;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_INSCRIPTION")
	private Date dateInscription;

	private String email;

	@Column(name="ID_MOTIF_DESINSCRIPTION")
	private Integer idMotifDesinscription;

	private String mdp;

	private String nom;

	private String prenom;

	private String pseudo;

	private byte sexe;

	//bi-directional many-to-one association to Useradresse
	@OneToMany(mappedBy="utilisateur", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Useradresse> useradresses;
	
	//bi-directional many-to-one association to Foodfriend
	@OneToMany(mappedBy="utilisateur1", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Foodfriend> foodfriend1;
	
	//bi-directional many-to-one association to Foodfriend
	@OneToMany(mappedBy="utilisateur2", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Foodfriend> foodfriend2;

	public Utilisateur() {
	}
	
	

	public Integer getIdUtilisateur() {
		return this.idUtilisateur;
	}

	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public Date getDateDeNaissance() {
		return this.dateDeNaissance;
	}

	public void setDateDeNaissance(Date dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	public Date getDateDesinscription() {
		return this.dateDesinscription;
	}

	public void setDateDesinscription(Date dateDesinscription) {
		this.dateDesinscription = dateDesinscription;
	}

	public Date getDateInscription() {
		return this.dateInscription;
	}

	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getIdMotifDesinscription() {
		return this.idMotifDesinscription;
	}

	public void setIdMotifDesinscription(Integer idMotifDesinscription) {
		this.idMotifDesinscription = idMotifDesinscription;
	}

	public String getMdp() {
		return this.mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPseudo() {
		return this.pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public byte getSexe() {
		return this.sexe;
	}

	public void setSexe(byte sexe) {
		this.sexe = sexe;
	}

	public List<Useradresse> getUseradresses() {
		return this.useradresses;
	}

	public void setUseradresses(List<Useradresse> useradresses) {
		this.useradresses = useradresses;
	}

	public Useradresse addUseradress(Useradresse useradress) {
		getUseradresses().add(useradress);
		useradress.setUtilisateur(this);

		return useradress;
	}

	public Useradresse removeUseradress(Useradresse useradress) {
		getUseradresses().remove(useradress);
		useradress.setUtilisateur(null);

		return useradress;
	}
	
	public void setFoodfriend1(List<Foodfriend> foodfriend1) {
		this.foodfriend1 = foodfriend1;
	}

	public List<Foodfriend> getFoodfriend2() {
		return foodfriend2;
	}

	public void setFoodfriend2(List<Foodfriend> foodfriend2) {
		this.foodfriend2 = foodfriend2;
	}

}
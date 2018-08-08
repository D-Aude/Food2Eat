package fr.afcepf.ai103.data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the modesinscription database table.
 * 
 */
@Entity
@NamedQuery(name="Modesinscription.findAll", query="SELECT m FROM Modesinscription m")
public class Modesinscription implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MODESINSCRIPTION_IDMOTIFDESINSCRIPTION_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MODESINSCRIPTION_IDMOTIFDESINSCRIPTION_GENERATOR")
	@Column(name="ID_MOTIF_DESINSCRIPTION")
	private int idMotifDesinscription;

	@Column(name="MOTIF_DESISCRIPTION")
	private String motifDesiscription;

	public Modesinscription() {
	}

	public int getIdMotifDesinscription() {
		return this.idMotifDesinscription;
	}

	public void setIdMotifDesinscription(int idMotifDesinscription) {
		this.idMotifDesinscription = idMotifDesinscription;
	}

	public String getMotifDesiscription() {
		return this.motifDesiscription;
	}

	public void setMotifDesiscription(String motifDesiscription) {
		this.motifDesiscription = motifDesiscription;
	}

}
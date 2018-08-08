package fr.afcepf.ai103.data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the mojeter database table.
 * 
 */
@Entity
@NamedQuery(name="Mojeter.findAll", query="SELECT m FROM Mojeter m")
public class Mojeter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MOJETER_IDMOTIFJETER_GENERATOR", sequenceName="ORDER_SEQUENCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MOJETER_IDMOTIFJETER_GENERATOR")
	@Column(name="ID_MOTIF_JETER")
	private int idMotifJeter;

	@Column(name="NOM_MOTIF_JETER")
	private String nomMotifJeter;

	//bi-directional many-to-one association to Stock
	@OneToMany(mappedBy="mojeter", fetch = FetchType.LAZY)
	private List<Stock> stocks;

	public Mojeter() {
	}

	public int getIdMotifJeter() {
		return this.idMotifJeter;
	}

	public void setIdMotifJeter(int idMotifJeter) {
		this.idMotifJeter = idMotifJeter;
	}

	public String getNomMotifJeter() {
		return this.nomMotifJeter;
	}

	public void setNomMotifJeter(String nomMotifJeter) {
		this.nomMotifJeter = nomMotifJeter;
	}

	public List<Stock> getStocks() {
		return this.stocks;
	}

	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}

	public Stock addStock(Stock stock) {
		getStocks().add(stock);
		stock.setMojeter(this);

		return stock;
	}

	public Stock removeStock(Stock stock) {
		getStocks().remove(stock);
		stock.setMojeter(null);

		return stock;
	}

}
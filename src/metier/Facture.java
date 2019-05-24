package metier;


import java.time.LocalDate;

public class Facture {
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_facture;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Facture other = (Facture) obj;
		if (id_facture != other.id_facture)
			return false;
		return true;
	}

	private int id_facture;
	private Client id_client;
	private LocalDate date_facture;

	public Facture(int id_facture, Client id_client, LocalDate date_facture) {
		super();
		this.id_facture = id_facture;
		this.id_client = id_client;
		this.date_facture = date_facture;
		
	}
	public Facture() {}

	public int getId_facture() {
		return id_facture;
	}

	public void setId_facture(int id_facture) {
		if (id_facture <= 0) {
			throw new IllegalArgumentException("L'id facture doit etre positif");
		}
		this.id_facture = id_facture;
	}

	public Client getId_client() {
		return id_client;
	}

	public void setId_client(Client id_client) {
		this.id_client = id_client;
	}

	public LocalDate getDate_facture() {
		return date_facture;
	}

	public void setDate_facture(LocalDate date_facture) {
		this.date_facture = date_facture;
	}

	@Override
	public String toString() {
		return "Facture n° " + id_facture + " de " + id_client + " fait le " + date_facture;
	}
}
package metier;

public class LigneFacture {

private Facture id_facture;
private int id_ligne;
private Produit id_produit;
private int quantite;

public LigneFacture(Facture id_facture, int id_ligne, Produit id_produit, int quantite) {
	super();
	this.id_facture = id_facture;
	this.id_ligne = id_ligne;
	this.id_produit = id_produit;
	this.quantite = quantite;
}

public LigneFacture() {}

	public Facture getId_facture() {
		return id_facture;
	}
	
	public void setId_facture(Facture id_facture) {
		this.id_facture = id_facture;
	}
	
	public int getId_ligne() {
		return id_ligne;
	}
	
	public void setId_ligne(int id_ligne) {
		this.id_ligne = id_ligne;
	}
	
	public Produit getId_produit() {
		return id_produit;
	}
	
	public void setId_produit(Produit id_produit) {
		this.id_produit = id_produit;
	}
	
	public int getQuantite() {
		return quantite;
	}
	
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

@Override
public String toString() {
	return "Ligne_facture [id_facture=" + id_facture + ", id_ligne=" + id_ligne + ", id_produit=" + id_produit
			+ ", quantite=" + quantite + "]";
}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_facture == null) ? 0 : id_facture.hashCode());
		result = prime * result + id_ligne;
		result = prime * result + ((id_produit == null) ? 0 : id_produit.hashCode());
		result = prime * result + quantite;
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
		LigneFacture other = (LigneFacture) obj;
		if (id_facture != other.id_facture)
			return false;
		if (id_ligne != other.id_ligne)
			return false;
		if (id_produit != other.id_produit)
			return false;
		if (quantite != other.quantite)
			return false;
		return true;
	}
}
package metier;

public class Produit{
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_produit;
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
		Produit other = (Produit) obj;
		if (id_produit != other.id_produit)
			return false;
		return true;
	}


	private int id_produit;
	private String libelle_produit;
	private	double prix_produit;
	private Tva id_tva;
	private String url;
	
	public Produit(int id_produit, String libelle_produit, double prix_produit, Tva id_tva, String url) {
		super();
		this.id_produit = id_produit;
		this.libelle_produit = libelle_produit;
		this.prix_produit = prix_produit;
		this.id_tva = id_tva;
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Produit() {}


	public int getId_produit() {
		return id_produit;
	}
	
	public void setId_produit(int id_produit) {
		if (id_produit <= 0) {
			throw new IllegalArgumentException("L'id produit ne doit pas être inferieur a 0");
		}
		this.id_produit = id_produit;
	}
	
	public String getLibelle_produit() {
		return libelle_produit;
	}
	
	public void setLibelle_produit(String libelle_produit) {
		if (libelle_produit == null || libelle_produit.trim().length() == 0) {
			throw new IllegalArgumentException("Le libellé du produit ne doit pas etre vide");
		}

		this.libelle_produit = libelle_produit;
	}
	
	public double getPrix_produit() {
		return prix_produit;
	}
	
	public void setPrix_produit(double d) {
		if (d < 0) {
			throw new IllegalArgumentException("Le prix ne peux pas être inférieur à 0");
		}
		this.prix_produit = d;
	}
	
	public Tva getId_tva() {
		return id_tva;
	}
	public void setId_tva(Tva id_tva) {
		this.id_tva = id_tva;
	}
	
	
	@Override
	public String toString() {
		return libelle_produit + " (" + prix_produit + "€)";
	}
	
	

}
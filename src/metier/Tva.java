package metier;

public class Tva {
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_tva;
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
		Tva other = (Tva) obj;
		if (id_tva != other.id_tva)
			return false;
		return true;
	}


	private int id_tva;
	private String libelle_tva;
	private double taux_tva;
	
	public Tva(int id_tva, String libelle_tva, double taux_tva) {
		super();
		this.id_tva = id_tva;
		this.libelle_tva = libelle_tva;
		this.taux_tva = taux_tva;
	}
	
	public Tva() {}

	public int getId_tva() {
		return id_tva;
	}


	public void setId_tva(int id_tva) {
		if (id_tva <= 0) {
			throw new IllegalArgumentException("L'id de la TVA doit etre positif à 0");
		}
		this.id_tva = id_tva;
	}


	public String getLibelle_tva() {
		return libelle_tva;
	}


	public void setLibelle_tva(String lib_tva) {
		if (lib_tva == null || lib_tva.trim().length() == 0) {
			throw new IllegalArgumentException("Le libellé de la tva ne peut être vide");
		}
		this.libelle_tva = lib_tva;
	}


	public double getTaux_tva() {
		return taux_tva;
	}


	public void setTaux_tva(double taux_tva) {
		if (taux_tva <= 0) {
			throw new IllegalArgumentException("Le taux de la TVA ne peut pas être negatif !");
		}
		this.taux_tva = taux_tva;
	}


	@Override
	public String toString() {
		return libelle_tva + " (" + taux_tva + " %)";
	}
	
	
}
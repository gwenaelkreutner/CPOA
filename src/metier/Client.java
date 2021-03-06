package metier;

public class Client{
	
	
	private int id_client;
	private String nom;
	private String prenom;
	private String no_rue;
	private String voie;
	private String code_postal;
	private String ville;
	private String pays;
	private int ca;
	
	public Client(int id_client, String nom, String prenom, String no_rue, String voie, String code_postal,String ville, String pays, int ca) {
		super();
		this.id_client = id_client;
		this.nom = nom;
		this.prenom = prenom;
		this.no_rue = no_rue;
		this.voie = voie;
		this.code_postal = code_postal;
		this.ville = ville;
		this.pays = pays;
		this.ca = ca;
	}

	public int getCa() {
		return ca;
	}

	public void setCa(int ca) {
		this.ca = ca;
	}

	public Client() {}
	
	public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}

	public String getNom() {
		return nom;
	}

	public void setNom_client(String nom_client) {
		this.nom = nom_client;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom_client(String prenom_client) {
		this.prenom = prenom_client;
	}

	public String getNo_rue() {
		return no_rue;
	}

	public void setNo_rue(String no_rue) {
		this.no_rue = no_rue;
	}

	public String getVoie() {
		return voie;
	}

	public void setVoie(String voie) {
		this.voie = voie;
	}

	public String getCode_postal() {
		return code_postal;
	}

	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	@Override
	public String toString() {
		return nom + " " + prenom;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_client;
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
		Client other = (Client) obj;
		if (id_client != other.id_client)
			return false;
		return true;
	}
	
	
}
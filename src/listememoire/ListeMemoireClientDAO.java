package listememoire;


import java.util.ArrayList;
import java.util.List;

import dao.ClientDAO;
import dao.TvaDAO;
import metier.Client;
import metier.Tva;

public class ListeMemoireClientDAO  implements ClientDAO {

	private static ListeMemoireClientDAO instance;

	private ArrayList<Client> donnees;

	public static ListeMemoireClientDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoireClientDAO();
		}

		return instance;
	}
	
	

	private ListeMemoireClientDAO() {

		this.donnees = new ArrayList<Client>();

		this.donnees.add(new Client(1, "Gwen", "HIHI", "test", "test", "test", "test", "test",65));
		this.donnees.add(new Client(2, "YOlo", "Sw@G", "test", "test", "test", "test", "test",69));
	}

	@Override
	public void create(Client objet) {

		// gestion de l'auto-incrément
		if (this.donnees.size() == 0) {
			objet.setId_client(0);
		} else {
			int id = this.donnees.get(this.donnees.size() - 1).getId_client() + 1;
			objet.setId_client(id);
		}
		
		// ajout du nouvel objet à la liste
		this.donnees.add(objet);
	}

	@Override
	public void update(Client objet) {

		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");
		} else {
			this.donnees.set(idx, objet);
		}
	}

	@Override
	public void delete(Client objet) {

		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
		} else {
			this.donnees.remove(idx);
		}
	}

	@Override
	public Client getById(int id) {

		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(new Client(id, "test", "prenom", "test", "test", "test", "test", "test", 56));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucun objet ne possède cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}

	@Override
	public ArrayList<Client> Afficher() {
		return donnees;
		// TODO Auto-generated method stub
		
	}



}

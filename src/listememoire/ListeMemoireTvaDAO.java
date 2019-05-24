package listememoire;


import java.util.ArrayList;
import java.util.List;

import dao.TvaDAO;
import metier.Tva;

public class ListeMemoireTvaDAO  implements TvaDAO {

	private static ListeMemoireTvaDAO instance;

	private ArrayList<Tva> donnees;

	public static ListeMemoireTvaDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoireTvaDAO();
		}

		return instance;
	}

	private ListeMemoireTvaDAO() {

		this.donnees = new ArrayList<Tva>();

		this.donnees.add(new Tva(1, "Normal", 20));
		this.donnees.add(new Tva(2, "Réduit", 10));
	}

	@Override
	public void create(Tva objet) {

		// gestion de l'auto-incrément
		if (this.donnees.size() == 0) {
			objet.setId_tva(0);
		} else {
			int id = this.donnees.get(this.donnees.size() - 1).getId_tva() + 1;
			objet.setId_tva(id);
		}
		
		// ajout du nouvel objet à la liste
		this.donnees.add(objet);
	}

	@Override
	public void update(Tva objet) {

		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");
		} else {
			this.donnees.set(idx, objet);
		}
	}

	@Override
	public void delete(Tva objet) {

		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
		} else {
			this.donnees.remove(idx);
		}
	}

	@Override
	public Tva getById(int id) {

		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(new Tva(id, "test", 0));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucun objet ne possède cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}

	@Override
	public ArrayList<Tva> Afficher() {
		return donnees;
		// TODO Auto-generated method stub
		
	}



}

package listememoire;

import java.util.ArrayList;

import dao.LigneFactureDAO;
import metier.LigneFacture;
import metier.Produit;

public class ListeMemoireLigneFactureDAO implements LigneFactureDAO {

	private static ListeMemoireLigneFactureDAO instance;
	private ArrayList<LigneFacture> donnees;

	protected static ListeMemoireFactureDAO instanceFacture = ListeMemoireFactureDAO.getInstance();

	public static ListeMemoireLigneFactureDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoireLigneFactureDAO();
		}

		return instance;
	}

	private ListeMemoireLigneFactureDAO() {

		this.donnees = new ArrayList<LigneFacture>();

	}

	@Override
	public void create(LigneFacture objet) {

		
		// gestion de l'auto-incrément
		if (this.donnees.size() == 0) {
			objet.setId_ligne(0);
		} else {
			int id = this.donnees.get(this.donnees.size() - 1).getId_ligne() + 1;
			objet.setId_ligne(id);
		}
		
		// ajout du nouvel objet à la liste
		this.donnees.add(objet);
	}

	@Override
	public void update(LigneFacture objet) {

		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");
		} else {
			this.donnees.set(idx, objet);
		}
	}

	@Override
	public void delete(LigneFacture objet) {

		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
		} else {
			this.donnees.remove(idx);
		}
	}

	@Override
	public ArrayList<LigneFacture> Afficher() {

		return this.donnees;

	}

	@Override
	public LigneFacture getById(int id, int ligne) {

		
		LigneFacture lf = new LigneFacture(instanceFacture.getById(id), ligne, new Produit(), 0);
		int idx = this.donnees.indexOf(lf);
		if (idx == -1) {
		
			return null;
		} else {
			return this.donnees.get(idx);
		}
	}

	@Override
	public LigneFacture getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}

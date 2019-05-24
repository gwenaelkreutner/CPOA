package listememoire;


import java.util.ArrayList;
import java.util.List;

import dao.ProduitDAO;
import dao.TvaDAO;
import metier.Client;
import metier.Produit;
import metier.Tva;

public class ListeMemoireProduitDAO  implements ProduitDAO {

	private static ListeMemoireProduitDAO instance;

	private ArrayList<Produit> donnees;

	public static ListeMemoireProduitDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoireProduitDAO();
		}

		return instance;
	}
	
	

	private ListeMemoireProduitDAO() {

		this.donnees = new ArrayList<Produit>();

		this.donnees.add(new Produit(1,"tabouret",11.5, new Tva(1,"hey2",0.15), null));
		this.donnees.add(new Produit(2, "vase",12.5,new Tva(1,"hey",0.15), null));
	}

	@Override
	public void create(Produit objet) {

		// gestion de l'auto-incrÃ©ment
		if (this.donnees.size() == 0) {
			objet.setId_produit(0);
		} else {
			int id = this.donnees.get(this.donnees.size() - 1).getId_produit() + 1;
			objet.setId_produit(id);
		}
		
		// ajout du nouvel objet Ã  la liste
		this.donnees.add(objet);
	}

	@Override
	public void update(Produit objet) {

		// Ne fonctionne que si l'objet mÃ©tier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");
		} else {
			this.donnees.set(idx, objet);
		}
	}

	@Override
	public void delete(Produit objet) {

		// Ne fonctionne que si l'objet mÃ©tier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
		} else {
			this.donnees.remove(idx);
		}
	}

	@Override
	public Produit getById(int id) {

		// Ne fonctionne que si l'objet mÃ©tier est bien fait...
		int idx = this.donnees.indexOf(new Produit(id, "yoo",1.5,new Tva(1,"hello",0.15), null));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucun objet ne possede cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}

	@Override
	public ArrayList<Produit> Afficher() {
		return donnees;
		// TODO Auto-generated method stub
		
	}



}

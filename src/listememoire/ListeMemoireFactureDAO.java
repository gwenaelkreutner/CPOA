package listememoire;
import java.time.LocalDate;
import java.util.ArrayList;

import metier.Client;
import metier.Facture;
import java.util.List;

import dao.FactureDAO;

public class ListeMemoireFactureDAO implements FactureDAO {
	
		private static ListeMemoireFactureDAO instance;
		private ArrayList<Facture> donnees;

		public static ListeMemoireFactureDAO getInstance() {

			if (instance == null) {
				instance = new ListeMemoireFactureDAO();
			}

			return instance;
		}

		private ListeMemoireFactureDAO() {

			this.donnees = new ArrayList<Facture>();

			this.donnees.add(new Facture(1, new Client(2,"rfzfgrz","rgrg", "test", "test", "test", "test", "test",55),LocalDate.parse("12/06/1999", formatage) ));
			this.donnees.add(new Facture(2, new Client(2,"rfzfgrz","rgrg", "test", "test", "test", "test", "test",55), LocalDate.parse("11/06/1999", formatage)));
		}

		@Override
		public void create(Facture objet) {

			// gestion de l'auto-incrément
			if (this.donnees.size() == 0) {
				objet.setId_facture(0);
			} else {
				int id = this.donnees.get(this.donnees.size() - 1).getId_facture() + 1;
				objet.setId_facture(id);
			}
			
			// ajout du nouvel objet à la liste
			this.donnees.add(objet);
		}

		@Override
		public void update(Facture objet) {

			// Ne fonctionne que si l'objet métier est bien fait...
			int idx = this.donnees.indexOf(objet);
			if (idx == -1) {
				throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");
			} else {
				this.donnees.set(idx, objet);
			}
		}

		@Override
		public void delete(Facture objet) {

			// Ne fonctionne que si l'objet métier est bien fait...
			int idx = this.donnees.indexOf(objet);
			if (idx == -1) {
				throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
			} else {
				this.donnees.remove(idx);
			}
		}

		@Override
		public Facture getById(int id) {

			// Ne fonctionne que si l'objet métier est bien fait...
			int idx = this.donnees.indexOf(new Facture(id, new Client(2,"jean","jose", "test", "test", "test", "test", "test",3), LocalDate.parse("13/06/1999", formatage)));
			if (idx == -1) {
				throw new IllegalArgumentException("Aucun objet ne possède cet identifiant");
			} else {
				return this.donnees.get(idx);
			}
		}

		@Override
		public ArrayList<Facture> Afficher() {
			return donnees;
			// TODO Auto-generated method stub
			
		}



	}



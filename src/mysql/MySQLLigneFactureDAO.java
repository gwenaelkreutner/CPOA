package mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import bdd.Connexion;
import metier.LigneFacture;
import dao.LigneFactureDAO;

public class MySQLLigneFactureDAO implements LigneFactureDAO {
	
	private Connection connexion;
	private static MySQLLigneFactureDAO instance;
	private static MySQLFactureDAO instanceFacture;
	private static MySQLProduitDAO instanceProduit;
	
 	private MySQLLigneFactureDAO()
	{
		connexion = Connexion.creeConnexion().getMaConnexion();
		instanceFacture = MySQLFactureDAO.getInstance();
		instanceProduit = MySQLProduitDAO.getInstance();

	}

	
	public static MySQLLigneFactureDAO getInstance() {

		if (instance == null) {
			instance = new MySQLLigneFactureDAO();
		}
		return instance;
	}
	
	public int autoincremente() {
        int id= 1;

            try {
                PreparedStatement requete = connexion.prepareStatement("SELECT MAX(id_ligne) FROM ligne_facture");
                ResultSet res= requete.executeQuery();
                while (res.next()) {
                    id = res.getInt(1) + 1;

                }
            }    catch (SQLException sqle) {
                System.out.println("Pb dans l'auto incrémentation " + sqle.getMessage());
                }
            return id;
        }

	public void create(LigneFacture objet) {
		int id_ligne = autoincremente();
		
		try {
			PreparedStatement requete = connexion.prepareStatement("INSERT INTO ligne_facture (id_facture, id_ligne, id_produit, quantite) VALUES (?,?,?, ?)", 
					Statement.RETURN_GENERATED_KEYS);
			requete.setInt(1, objet.getId_facture().getId_facture());
			requete.setInt(2, id_ligne);
			requete.setInt(3, objet.getId_produit().getId_produit());
			requete.setInt(4, objet.getQuantite());
			requete.executeUpdate();
			ResultSet res = requete.getGeneratedKeys();
			if (res.next()) {
			objet.setId_ligne(res.getInt(1));
			
			}
			requete.close();
	}
	
	catch (SQLException sqle) {
		System.out.println("Pb dans select " + sqle.getMessage());
		}
}
	
    @Override
	public void update(LigneFacture p) {
		try {
			
			PreparedStatement requete = connexion.prepareStatement("UPDATE ligne_facture SET id_facture = ?, id_produit = ?, quantite = ? WHERE id_ligne = ? ") ;	
			requete.setInt(1, p.getId_facture().getId_facture());
			requete.setInt(4, p.getId_ligne());
			requete.setInt(2, p.getId_produit().getId_produit());
			requete.setInt(3, p.getQuantite());

			System.out.println(requete.executeUpdate());
			requete.executeUpdate();

		}
		
		catch (SQLException sqle) {
			System.out.println("Pb dans select " + sqle.getMessage());
			}
	}


	@Override 
	public void delete(LigneFacture objet) {
		try {
			PreparedStatement requete = connexion.prepareStatement("DELETE FROM ligne_facture WHERE id_ligne=?",Statement.RETURN_GENERATED_KEYS);
			requete.setInt(1, objet.getId_ligne());
			requete.executeUpdate();

		} catch (SQLException se) {
			System.err.println("Pb SQL :" + se.getMessage());
		}

	}
     
    @Override
	public LigneFacture getById(int id) {
		
	    try {
	    PreparedStatement requete = connexion.prepareStatement("SELECT * FROM ligne_facture WHERE id_ligne=?");
	    requete.setInt(1, id);
	    ResultSet res = requete.executeQuery();
	    	if (res.next()) {
			LigneFacture lf = new LigneFacture();
				instanceFacture.getById(res.getInt(1));
			    lf.setId_ligne(res.getInt(2));
			    instanceProduit.getById(res.getInt(3));
			    lf.setId_ligne(res.getInt(4));
			    return lf;
	    }
	    }catch(SQLException sqle) {

	        System.out.println("Pb select " + sqle.getMessage());
	    }

	    return null;
	}

    @Override
	public ArrayList<LigneFacture> Afficher() {
		ArrayList<LigneFacture> liste = new ArrayList<LigneFacture>();

		try {
			PreparedStatement requete = connexion.prepareStatement("SELECT * FROM ligne_facture");
			ResultSet res = requete.executeQuery();

			while (res.next()) {
				LigneFacture prodgeneree = new LigneFacture (instanceFacture.getById(res.getInt("id_facture")), res.getInt("id_ligne"), instanceProduit.getById(res.getInt("id_produit")), res.getInt("quantite"));
				liste.add(prodgeneree);
				
			}
			if (requete != null) {
				requete.close();
			}
			res.close();

		} catch (SQLException se) {
			System.out.println("Pb SQL " + se.getMessage());
		}
		return liste;
	}


	@Override
	public LigneFacture getById(int id, int ligne) {
		/*try {
		    PreparedStatement requete = connexion.prepareStatement("SELECT * FROM ligne_facture WHERE id_facture=?,id_ligne=?");
		    requete.setInt(1, id);
		    requete.setInt(2, ligne);
		    ResultSet res = requete.executeQuery();
		    	if (res.next()) {
				LigneFacture lf = new LigneFacture();
					instanceFacture.getById(res.getInt(1));
				    lf.setId_ligne(res.getInt(2));
				    instanceProduit.getById(res.getInt(3));
				    lf.setId_ligne(res.getInt(4));
				    return lf;
		    }
		    }catch(SQLException sqle) {

		        System.out.println("Pb select " + sqle.getMessage());
		    }*/
		return null;
	}
    
    
}
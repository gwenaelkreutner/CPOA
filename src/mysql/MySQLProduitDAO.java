package mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.mysql.jdbc.Statement;

import bdd.Connexion;
import metier.Client;
import metier.Produit;
import metier.Tva;
import dao.ProduitDAO;

public class MySQLProduitDAO implements ProduitDAO {
	
	private Connection connexion;
	private static MySQLProduitDAO instance;
	private static MySQLTvaDAO instanceTva;
	
 	private MySQLProduitDAO()
	{
		connexion = Connexion.creeConnexion().getMaConnexion();
		instanceTva = MySQLTvaDAO.getInstance();
	}

	
	public static MySQLProduitDAO getInstance() {

		if (instance == null) {
			instance = new MySQLProduitDAO();
		}
		return instance;
	}

	public void create(Produit objet) {
		try {
			PreparedStatement requete = connexion.prepareStatement("INSERT INTO produit (id_produit, libelle_produit, prix_produit, id_tva, url) VALUES (?,?,?, ?, ?)", 
					Statement.RETURN_GENERATED_KEYS);
			requete.setInt(1, objet.getId_produit());
			requete.setString(2, objet.getLibelle_produit());
			requete.setDouble(3, objet.getPrix_produit());
			requete.setInt(4, objet.getId_tva().getId_tva());
			requete.setString(5, objet.getUrl());
			requete.executeUpdate();
			ResultSet res = requete.getGeneratedKeys();
			if (res.next()) {
			objet.setId_produit(res.getInt(1));
			
			}
			requete.close();
	}
	
	catch (SQLException sqle) {
		System.out.println("Pb dans select " + sqle.getMessage());
		}
}
	
    @Override
	public void update(Produit p) {
		try {
			
			PreparedStatement requete = connexion.prepareStatement("UPDATE produit SET libelle_produit = ?, prix_produit= ?, id_tva =?, url=? WHERE id_produit = ? ") ;	
			requete.setInt(5, p.getId_produit());
			requete.setString(1, p.getLibelle_produit());
			requete.setDouble(2, p.getPrix_produit());
			requete.setInt(3, p.getId_tva().getId_tva());
			requete.setString(4, p.getUrl());


			System.out.println(requete.executeUpdate());
			requete.executeUpdate();

		}
		
		catch (SQLException sqle) {
			System.out.println("Pb dans select " + sqle.getMessage());
			}
	}


	@Override 
	public void delete(Produit objet) {
		try {
			PreparedStatement requete = connexion.prepareStatement("DELETE FROM produit WHERE id_produit=?",Statement.RETURN_GENERATED_KEYS);
			requete.setInt(1, objet.getId_produit());
			requete.executeUpdate();

		} catch (SQLException se) {
			System.err.println("Pb SQL :" + se.getMessage());
		}

	}
     
    @Override
	public Produit getById(int id) {
		
	    try {
	    PreparedStatement requete = connexion.prepareStatement("SELECT * FROM produit WHERE id_produit=?");
	    requete.setInt(1, id);
	    ResultSet res = requete.executeQuery();
	 if (res.next()) {
		 Produit p = new Produit();
	      p.setId_produit(res.getInt(1));
	      p.setLibelle_produit(res.getString(2));
	      p.setPrix_produit(res.getDouble(3));
	      instanceTva.getById(res.getInt(4));
	      p.setUrl(res.getString(5));

	      return p;
	    }
	    }catch(SQLException sqle) {

	        System.out.println("Pb select " + sqle.getMessage());
	    }

	    return null;
	}

    @Override
	public ArrayList<Produit> Afficher() {
		ArrayList<Produit> liste = new ArrayList<Produit>();

		try {
			PreparedStatement requete = connexion.prepareStatement("SELECT * FROM produit");
			ResultSet res = requete.executeQuery();

			while (res.next()) {

				Produit prodgeneree = new Produit (res.getInt("id_produit"), res.getString("libelle_produit"), res.getDouble("prix_produit"), instanceTva.getById(res.getInt("id_tva")), res.getString("url"));
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
    
    
}
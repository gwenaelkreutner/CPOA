package mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import bdd.Connexion;
import metier.Client;
import metier.Facture;
import dao.FactureDAO;

public class MySQLFactureDAO implements FactureDAO {
	
	private Connection connexion;
	private static MySQLClientDAO instanceClient ;

	private MySQLFactureDAO()
	{
		connexion = Connexion.creeConnexion().getMaConnexion();
		instanceClient = MySQLClientDAO.getInstance();
	}
	
	private static MySQLFactureDAO instance;

	public static MySQLFactureDAO getInstance() {

		if (instance == null) {
			instance = new MySQLFactureDAO();
		}
		return instance;
	}

	@Override 
	public void create(Facture objet) {

		try {
			PreparedStatement requete = connexion.prepareStatement(
					"INSERT INTO facture (id_facture, id_client, date_facture) VALUES (?,?, ?)", Statement.RETURN_GENERATED_KEYS);
			
            requete.setDate(3, Date.valueOf(objet.getDate_facture()));																	
            requete.setInt(2, objet.getId_client().getId_client());
			requete.setInt(1, objet.getId_facture()); 
			requete.executeUpdate();

			ResultSet res = requete.getGeneratedKeys();
			if (res.next()) {
				int cle = res.getInt(1);
                objet.setId_facture(cle);
			}
		} catch (SQLException se) {
			System.err.println("Pb SQL :" + se.getMessage());
		}
	}

	@Override 
	public void update(Facture objet) {
		try {
			PreparedStatement requete = connexion.prepareStatement(
					"UPDATE facture SET id_client=? , date_facture=? WHERE id_facture=?",
					Statement.RETURN_GENERATED_KEYS);
			requete.setDate(2, Date.valueOf(objet.getDate_facture()));
			requete.setInt(1, objet.getId_client().getId_client());
			requete.setInt(3, objet.getId_facture());
			requete.executeUpdate();

			if (requete != null) {
				requete.close();
			}
		} catch (SQLException se) {
			System.err.println("Pb SQL :" + se.getMessage());
		}
	}

	@Override 
	public void delete(Facture objet) {
		try {
			PreparedStatement requete = connexion.prepareStatement("DELETE FROM facture WHERE id_facture=?",
					Statement.RETURN_GENERATED_KEYS);
			requete.setInt(1, objet.getId_facture());
			requete.executeUpdate();

		} catch (SQLException se) {
			System.err.println("Pb SQL :" + se.getMessage());
		}
	}

	@Override
	public Facture getById(int id_facture) {
		Facture fact = new Facture();
		try {
			PreparedStatement requete = connexion.prepareStatement("SELECT * FROM facture WHERE id_facture=?");
			requete.setInt(1, id_facture);
			ResultSet res = requete.executeQuery();

			while (res.next()) {
			      fact.setId_facture(res.getInt(1));
			      fact.setId_client(instanceClient.getById(res.getInt(2)));
			      fact.setDate_facture( res.getDate(3).toLocalDate());
			    }
			    }catch(SQLException sqle) {

			        System.out.println("Pb select " + sqle.getMessage());
			    }

			    return fact;
	}

	@Override
	public ArrayList<Facture> Afficher() {
		ArrayList<Facture> liste = new ArrayList<Facture>();

		try {
			PreparedStatement requete = connexion.prepareStatement("SELECT * FROM facture");
			ResultSet res = requete.executeQuery();

			while (res.next()) {

				Facture facturegeneree = new Facture(res.getInt("id_facture"),instanceClient.getById(res.getInt("id_client")),  res.getDate("date_facture").toLocalDate());
				liste.add(facturegeneree);

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
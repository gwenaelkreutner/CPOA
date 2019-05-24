package mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import bdd.Connexion;
import metier.Client;
import dao.ClientDAO;

public class MySQLClientDAO implements ClientDAO {
	
	private Connection connexion;

	private MySQLClientDAO()
	{
		connexion = Connexion.creeConnexion().getMaConnexion();
	}
	
	private static MySQLClientDAO instance;

	public static MySQLClientDAO getInstance() {

		if (instance == null) {
			instance = new MySQLClientDAO();
		}
		return instance;
	}

	@Override 
	public void create(Client objet) {
		try {
			PreparedStatement requete = connexion.prepareStatement("INSERT INTO client (id_client, nom, prenom, no_rue, voie, code_postal, ville, pays) VALUES (?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			requete.setInt(1, objet.getId_client());
			requete.setString(2, objet.getNom());
			requete.setString(3, objet.getPrenom());
			requete.setString(4, objet.getNo_rue());
			requete.setString(5, objet.getVoie());
			requete.setString(6, objet.getCode_postal());
			requete.setString(7, objet.getVille());
			requete.setString(8, objet.getPays());

			requete.executeUpdate();

			ResultSet res = requete.getGeneratedKeys();
			if (requete != null) {
				requete.close();
			}
		} catch (SQLException se) {
			System.err.println("Pb SQL :" + se.getMessage());
		}

	}

	@Override 
	public void update(Client objet) {
		try {
			PreparedStatement requete = connexion.prepareStatement("UPDATE client SET nom=?, prenom=?, no_rue=?, voie=?, code_postal=?, ville=?, pays=? WHERE id_client=?");
			requete.setInt(8, objet.getId_client());
			requete.setString(1, objet.getNom());
			requete.setString(2, objet.getPrenom());
			requete.setString(3, objet.getNo_rue());
			requete.setString(4, objet.getVoie());
			requete.setString(5, objet.getCode_postal());
			requete.setString(6, objet.getVille());
			requete.setString(7, objet.getPays());
			requete.executeUpdate();

			if (requete != null) {
				requete.close();
			}
		} catch (SQLException se) {
			System.err.println("Pb SQL :" + se.getMessage());
		}

	}

	@Override 
	public void delete(Client objet) {
		try {
			PreparedStatement requete = connexion.prepareStatement("DELETE FROM client WHERE id_client=?",Statement.RETURN_GENERATED_KEYS);
			requete.setInt(1, objet.getId_client());
			requete.executeUpdate();

		} catch (SQLException se) {
			System.err.println("Pb SQL :" + se.getMessage());
		}

	}

	@Override 
	public Client getById(int id) {
		
	    Client c = new Client();
	    try {
	    	PreparedStatement requete = connexion.prepareStatement("SELECT * FROM client WHERE id_client=?");
	    requete.setInt(1, id);
	    ResultSet res = requete.executeQuery();
	    while (res.next()) {

	      c.setId_client(res.getInt(1));
	      c.setNom_client(res.getString(2));
	      c.setPrenom_client(res.getString(3));
	    }
	    }catch(SQLException sqle) {

	        System.out.println("Pb select " + sqle.getMessage());
	    }

	    return c;
	}

	@Override
	public ArrayList<Client> Afficher() {
		ArrayList<Client> liste = new ArrayList<Client>();

		try {
			PreparedStatement requete = connexion.prepareStatement("SELECT * FROM client");
			ResultSet res = requete.executeQuery();

			while (res.next()) {

				Client clientgeneree = new Client(res.getInt("id_client"), res.getString("nom"), res.getString("prenom"), res.getString("no_rue"), res.getString("voie"), res.getString("code_postal"), res.getString("ville"), res.getString("pays"), res.getInt("ca_cumule"));
				liste.add(clientgeneree);

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
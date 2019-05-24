package mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import bdd.Connexion;
import metier.Tva;
import dao.TvaDAO;

public class MySQLTvaDAO implements TvaDAO {
	
	private Connection connexion;

 	private MySQLTvaDAO()
	{
		connexion = Connexion.creeConnexion().getMaConnexion();
	}
	
	private static MySQLTvaDAO instance;

	public static MySQLTvaDAO getInstance() {

		if (instance == null) {
			instance = new MySQLTvaDAO();
		}
		return instance;
	}

	
	public void create(Tva objet) {
		try {
			PreparedStatement requete = connexion.prepareStatement("INSERT INTO tva (id_tva, libelle_tva, taux_tva) VALUES (?,?,?)", 
			Statement.RETURN_GENERATED_KEYS);
			requete.setInt(1, objet.getId_tva());
			requete.setString(2, objet.getLibelle_tva());
			requete.setDouble(3, objet.getTaux_tva());
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
	public Tva getById(int id) {

        Tva t = new Tva();
        try {
        	PreparedStatement requete = connexion.prepareStatement("SELECT * FROM tva WHERE id_tva=?");
        requete.setInt(1, id);
        ResultSet res = requete.executeQuery();
        while (res.next()) {

           t.setId_tva(res.getInt(1));
           t.setLibelle_tva(res.getString(2));
           t.setTaux_tva(res.getDouble(3));
        }
        }catch(SQLException sqle) {

            System.out.println("Pb select " + sqle.getMessage());
        }

        return t;
    }
	
	
	public void update(Tva objet) {
		try {
			PreparedStatement requete = connexion.prepareStatement(
					"UPDATE tva SET libelle_tva=?, taux_tva=? WHERE id_tva=?",
					Statement.RETURN_GENERATED_KEYS);
			requete.setInt(3, objet.getId_tva());
			requete.setString(1, objet.getLibelle_tva());
			requete.setDouble(2, objet.getTaux_tva());
			requete.executeUpdate();

			if (requete != null) {
				requete.close();
			}
		} catch (SQLException se) {
			System.err.println("Pb SQL :" + se.getMessage());
		}
	}

	
	public void delete(Tva objet) {
		try {
			PreparedStatement requete = connexion.prepareStatement("DELETE FROM tva WHERE id_tva=?",Statement.RETURN_GENERATED_KEYS);
			requete.setInt(1, objet.getId_tva());
			requete.executeUpdate();

		} catch (SQLException se) {
			System.err.println("Pb SQL :" + se.getMessage());
		}
	}


	@Override
	public ArrayList<Tva> Afficher() {
		ArrayList<Tva> liste = new ArrayList<Tva>();

		try {
			PreparedStatement requete = connexion.prepareStatement("SELECT * FROM tva");
			ResultSet res = requete.executeQuery();

			while (res.next()) {

				Tva tvageneree = new Tva(res.getInt("id_tva"), res.getString("libelle_tva"), res.getDouble("taux_tva"));
				liste.add(tvageneree);

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
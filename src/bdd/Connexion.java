package bdd;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connexion {

	//private static final String url = "jdbc:mysql://infodb.iutmetz.univ-lorraine.fr:3306/kreutner1u_java?serverTimezone=UTC";
	private static Connexion instance = new Connexion();
	private Connection maConnexion;
	
	private Connexion() {
		try {	
			Properties accesBdd1 = new Properties();
			InputStream source = Connexion.class.getResourceAsStream("/config/bdd.properties");
			accesBdd1.loadFromXML(source);
			maConnexion = DriverManager.getConnection(accesBdd1.getProperty("url"), accesBdd1.getProperty("login"), accesBdd1.getProperty("pass"));
		} catch (SQLException | IOException e) {
			System.out.println("Erreur connexion" + e.getMessage());
		}
	}
	
	public static Connexion creeConnexion() {

		return instance;
	}

	public Connection getMaConnexion() {
		return maConnexion;
	}
}
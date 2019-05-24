package dao;

import listememoire.ListeMemoireDAOFactory;
import mysql.MySQLDAOFactory;

public abstract class DAOFactory {
	
	private static DAOFactory daoF = null;

	public enum Persistance
	{
		MYSQL,
		LISTE_MEMOIRE,
	}
	
	public static DAOFactory getDAOFactory(Persistance cible) {
		
		switch (cible) {
			case MYSQL:  daoF = new MySQLDAOFactory(); break;
			case LISTE_MEMOIRE: daoF = new ListeMemoireDAOFactory(); break;
			default:
		}
		
		return daoF;
	}

	public abstract ClientDAO getClientDAO();
	public abstract TvaDAO getTvaDAO();
	public abstract FactureDAO getFactureDAO();
	public abstract ProduitDAO getProduitDAO();
	public abstract LigneFactureDAO getLigneFactureDAO();
	
	
}

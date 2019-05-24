package dao;

import java.time.format.DateTimeFormatter;

import metier.Facture;

public interface FactureDAO extends DAO<Facture>{
	
	public static final DateTimeFormatter formatage = DateTimeFormatter.ofPattern("dd/MM/yyyy");

}

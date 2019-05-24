package testunitairesql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import dao.FactureDAO;
import metier.Client;
import metier.Facture;
import mysql.MySQLFactureDAO;


public class MySQLFactureDAOTest implements FactureDAO {
	
	@Test
	public void testCreate() {
		Client t = new Client(1,"test","test", "test", "test", "test", "test", "test",0);
		Facture fact = new Facture(1,t,LocalDate.parse("12/06/1999", formatage ));
		MySQLFactureDAO.getInstance().create(fact);
		Facture f = MySQLFactureDAO.getInstance().getById(fact.getId_facture());
		assertNotEquals(fact.getId_client(),f.getId_client());
		assertNotEquals(fact.getDate_facture(),f.getDate_facture());
		assertEquals(fact.getId_facture(),f.getId_facture());
	}

@Test
public void testDelete() {
	Client t = new Client(1,"test","test", "test", "test", "test", "test", "test",0);
	Facture fact = new Facture(1,t,LocalDate.parse("12/06/1999", formatage ));
	MySQLFactureDAO.getInstance().create(fact);
	MySQLFactureDAO.getInstance().delete(fact);
	Facture f = MySQLFactureDAO.getInstance().getById(fact.getId_facture());	
	assertNotEquals(fact.getId_facture(),f.getId_facture());


}

@Test
public void testUpdate() {
	Client t = new Client(1,"test","test", "test", "test", "test", "test", "test",0);
	Facture fact = new Facture(1,t,LocalDate.parse("12/06/1999", formatage ));
	MySQLFactureDAO.getInstance().create(fact);
	MySQLFactureDAO.getInstance().delete(fact);
    Facture f = MySQLFactureDAO.getInstance().getById(1);
	assertNotEquals(fact.getId_client(),f.getId_client());
	assertNotEquals(fact.getDate_facture(),f.getDate_facture());
}

@Test
public void testAfficher() {
	MySQLFactureDAO.getInstance().Afficher();
}

@Test
public void testGetById(){
	Client t = new Client(1,"test","test", "test", "test", "test", "test", "test",0);
	Facture fact = new Facture(1,t,LocalDate.parse("12/06/1999", formatage ));
	MySQLFactureDAO.getInstance().create(fact);
	Facture f = MySQLFactureDAO.getInstance().getById(fact.getId_facture());
	assertEquals(fact.getId_facture(),f.getId_facture());
	
}

@Override
public Facture getById(int id) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void create(Facture objet) {
	// TODO Auto-generated method stub
	
}

@Override
public void update(Facture objet) {
	// TODO Auto-generated method stub
	
}

@Override
public void delete(Facture objet) {
	// TODO Auto-generated method stub
	
}

@Override
public ArrayList<Facture> Afficher() {
	// TODO Auto-generated method stub
	return null;
}
}
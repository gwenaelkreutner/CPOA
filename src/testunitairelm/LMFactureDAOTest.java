package testunitairelm;

import static org.junit.Assert.*;
import java.time.LocalDate;

import metier.Client;
import metier.Facture;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import dao.FactureDAO;
import listememoire.ListeMemoireFactureDAO;


public class LMFactureDAOTest implements FactureDAO {

	@Test
	public void testCreate() {
		Client t = new Client(1,"test","test", null, null, null, null, null,0);
		Facture fact = new Facture(1,t,LocalDate.parse("12/06/1999", formatage ));
		ListeMemoireFactureDAO.getInstance().create(fact);
		Facture f = new Facture(1,t,LocalDate.parse("12/06/2018", formatage ));
		ListeMemoireFactureDAO.getInstance().create(f);
		assertEquals(fact.getId_client(),f.getId_client());
		assertNotEquals(fact.getDate_facture(),f.getDate_facture());
	}
	
	@Test
	public void testDelete() {
		Client t = new Client(1,"test","test", null, null, null, null, null,0);
		Facture fact = new Facture(1,t,LocalDate.parse("12/06/1999", formatage ));
		ListeMemoireFactureDAO.getInstance().create(fact);
		ListeMemoireFactureDAO.getInstance().delete(fact);
		Facture f = new Facture(1,t,LocalDate.parse("12/06/1999", formatage ));
		ListeMemoireFactureDAO.getInstance().create(fact); 
		assertEquals(fact.getId_client(),f.getId_client());
		assertEquals(fact.getDate_facture(),f.getDate_facture());


	}
	
	@Test
	public void testUpdate() {
		Client t = new Client(1,"test","test", null, null, null, null, null,0);
		Facture fact = new Facture(1,t,LocalDate.parse("12/06/1999", formatage ));
		ListeMemoireFactureDAO.getInstance().create(fact);
		ListeMemoireFactureDAO.getInstance().update(fact);
		Facture f = new Facture(3,t,LocalDate.parse("04/03/1854", formatage ));
		ListeMemoireFactureDAO.getInstance().create(f);
		ListeMemoireFactureDAO.getInstance().update(f);
	   // Facture factnew = ListeMemoireFactureDAO.getInstance().getById(3);
		assertEquals(fact.getId_client(),f.getId_client());
		assertNotEquals(fact.getDate_facture(),f.getDate_facture());
	}
	
	@Test
	public void testAfficher() {
		ListeMemoireFactureDAO.getInstance().Afficher();
	}
	
	@Test
	public void testGetById(){
		Client t = new Client(1,"test","test", null, null, null, null, null,0);
		Facture fact = new Facture(1,t,LocalDate.parse("12/06/1999", formatage ));
		Facture f = new Facture(1,t,LocalDate.parse("12/06/1999", formatage ));
		ListeMemoireFactureDAO.getInstance().create(fact);
		ListeMemoireFactureDAO.getInstance().create(f);		
		//Facture f = ListeMemoireFactureDAO.getInstance().getById(fact.getId_facture());
		assertNotEquals(fact.getId_facture(),f.getId_facture());
		
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
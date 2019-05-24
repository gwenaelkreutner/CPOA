package testunitairelm;

import static org.junit.Assert.*;

import org.junit.Test;

import listememoire.ListeMemoireTvaDAO;
import mysql.MySQLTvaDAO;
import metier.Tva;

public class LMTvaDAOTest {

	@Test
	public void testCreate() {
		Tva t = new Tva(1,"test",2);
		ListeMemoireTvaDAO.getInstance().create(t);
		Tva tv = new Tva(1,"test",2);
		ListeMemoireTvaDAO.getInstance().create(tv);
		assertEquals(t.getLibelle_tva(),tv.getLibelle_tva());
		assertEquals(t.getTaux_tva(),tv.getTaux_tva(),2);
	}
	
	@Test
	public void testDelete() {
		Tva t = new Tva(3,"yoyo", 5);
		ListeMemoireTvaDAO.getInstance().create(t);
		ListeMemoireTvaDAO.getInstance().delete(t);
		Tva tv = new Tva(3,"yoyo",5);
		ListeMemoireTvaDAO.getInstance().create(tv);
		//Tva tv = ListeMemoireTvaDAO.getInstance().getById(t.getId_tva());	
		assertEquals(t.getId_tva(),tv.getId_tva());
		// notequals

	}
	
	@Test
	public void testUpdate() {
	    Tva t = new Tva(4,"id", 5);
	    ListeMemoireTvaDAO.getInstance().create(t);
	    ListeMemoireTvaDAO.getInstance().update(t);
		Tva tv = new Tva(3,"yoyo",5);
		ListeMemoireTvaDAO.getInstance().create(tv);
	    //Tva tv = ListeMemoireTvaDAO.getInstance().getById(4);
	    assertEquals(t.getTaux_tva(), tv.getTaux_tva(), 5);
	    assertNotEquals(t.getLibelle_tva(), tv.getLibelle_tva());
	}
	
	@Test
	public void testAfficher() {
		ListeMemoireTvaDAO.getInstance().Afficher();
	}
	
	@Test
	public void testGetById(){
		Tva t = new Tva(1, "test", 2);
		ListeMemoireTvaDAO.getInstance().create(t);
		Tva tv = MySQLTvaDAO.getInstance().getById(t.getId_tva());
		assertEquals(t.getId_tva(),tv.getId_tva());
		
	}

}
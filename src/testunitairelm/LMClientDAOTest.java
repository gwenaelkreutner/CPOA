package testunitairelm;

import static org.junit.Assert.*;

import org.junit.Test;

import listememoire.ListeMemoireClientDAO;
import mysql.MySQLClientDAO;
import metier.Client;

public class LMClientDAOTest {

	@Test
	public void testCreate() {
		Client t = new Client(1,"test","test", "test", "test", "test", "test", "test", 0);
		ListeMemoireClientDAO.getInstance().create(t);
		Client tv = new Client(1,"test","test", "test", "test", "test", "test", "test", 0);
		ListeMemoireClientDAO.getInstance().create(tv);
		assertEquals(t.getPrenom(),tv.getPrenom());
		assertNotEquals(t.getNom(),tv.getNom(),2);
	}
	
	@Test
	public void testDelete() {
		Client t = new Client(3,"yoyo", "test", "test", "test", "test", "test", "test",0);
		ListeMemoireClientDAO.getInstance().create(t);
		ListeMemoireClientDAO.getInstance().delete(t);
		Client tv = new Client(3,"yoyo","test", "test", "test", "test", "test", "test",0);
		ListeMemoireClientDAO.getInstance().create(tv);
		//Client tv = ListeMemoireClientDAO.getInstance().getById(t.getId_Client());	
		assertEquals(t.getId_client(),tv.getId_client());
		// notequals

	}
	
	@Test
	public void testUpdate() {
	    Client t = new Client(4,"id", "test", "test", "test", "test", "test", "test",0);
	    ListeMemoireClientDAO.getInstance().create(t);
	    ListeMemoireClientDAO.getInstance().update(t);
		Client tv = new Client(3,"yoyo","test", "test", "test", "test", "test", "test",0);
		ListeMemoireClientDAO.getInstance().create(tv);
	    //Client tv = ListeMemoireClientDAO.getInstance().getById(4);
	    assertNotEquals(t.getPrenom(), tv.getPrenom(), 2);
	    assertNotEquals(t.getNom(), tv.getNom());
	}
	
	@Test
	public void testAfficher() {
		ListeMemoireClientDAO.getInstance().Afficher();
	}
	
	@Test
	public void testGetById(){
		Client t = new Client(1, "test", "test", "test", "test", "test", "test", "test",0);
		ListeMemoireClientDAO.getInstance().create(t);
		Client tv = MySQLClientDAO.getInstance().getById(t.getId_client());
		assertNotEquals(t.getId_client(),tv.getId_client());
		
	}
}
package testunitairesql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import metier.Client;
import mysql.MySQLClientDAO;


	
	public class MySQLClientDAOTest {

	@Test
	public void testCreate() {
 		Client c = new Client(5,"theo","test", "test", "test", "test", "test", "test",0);
		MySQLClientDAO.getInstance().create(c);
		Client cl = MySQLClientDAO.getInstance().getById(1);
 		assertNotEquals(c.getId_client(),cl.getId_client());
		assertNotEquals(c.getNom(),cl.getNom());
		assertNotEquals(c.getPrenom(),cl.getPrenom());
		
	}

	@Test
	public void testDelete() {
		Client c = new Client(6,"gwen","test", "test", "test", "test", "test", "test",0);
		 MySQLClientDAO.getInstance().create(c);
	     MySQLClientDAO.getInstance().delete(c);
		Client cl = MySQLClientDAO.getInstance().getById(c.getId_client());		
		assertNotEquals(c.getId_client(),cl.getId_client());


	}

	@Test
	public void testUpdate() {
 		Client c = new Client(5,"theo","test", "test", "test", "test", "test", "test",0);
 	    MySQLClientDAO.getInstance().create(c);
 	    MySQLClientDAO.getInstance().update(c);
 	    Client cl = MySQLClientDAO.getInstance().getById(4);
	    assertNotEquals(c.getNom(), cl.getNom());
	    assertNotEquals(c.getPrenom(), cl.getPrenom());
	    assertNotEquals(c.getId_client(),cl.getId_client());
	}

	@Test
	public void testAfficher() {
		MySQLClientDAO.getInstance().Afficher();
	}

	@Test
	public void testGetById(){
 		Client c = new Client(5,"theo","test", "test", "test", "test", "test", "test",0);
		MySQLClientDAO.getInstance().create(c);
		Client cl = MySQLClientDAO.getInstance().getById(c.getId_client());
	    assertEquals(c.getNom(), cl.getNom());
	    assertEquals(c.getPrenom(), cl.getPrenom());
	    assertEquals(c.getId_client(),cl.getId_client());
		
	}
	}



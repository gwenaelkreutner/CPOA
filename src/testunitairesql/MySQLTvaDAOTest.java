package testunitairesql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import metier.Tva;
import mysql.MySQLTvaDAO;

public class MySQLTvaDAOTest {
	
	@Test
	public void testCreate() {
		Tva t = new Tva(1,"libelle",4);
		MySQLTvaDAO.getInstance().create(t);
		Tva tv = MySQLTvaDAO.getInstance().getById(t.getId_tva());
		assertEquals(t.getTaux_tva(),tv.getTaux_tva(),1);
		assertEquals(t.getId_tva(),tv.getId_tva(),1);
	}

@Test
public void testDelete() {
	Tva t = new Tva(3,"yoyo", 5);
	 MySQLTvaDAO.getInstance().create(t);
     MySQLTvaDAO.getInstance().delete(t);
	Tva tv = MySQLTvaDAO.getInstance().getById(t.getId_tva());	
	assertNotEquals(t.getId_tva(),tv.getId_tva());


}

@Test
public void testUpdate() {
    Tva t = new Tva(4,"id", 4);
    MySQLTvaDAO.getInstance().create(t);
    MySQLTvaDAO.getInstance().update(t);
    Tva tv = MySQLTvaDAO.getInstance().getById(4);
    assertEquals(t.getTaux_tva(), tv.getTaux_tva(), 2);
    assertEquals(t.getLibelle_tva(), tv.getLibelle_tva());
}

@Test
public void testAfficher() {
	MySQLTvaDAO.getInstance().Afficher();
}

@Test
public void testGetById(){
	Tva t = new Tva(1, "test", 2);
	MySQLTvaDAO.getInstance().create(t);
	Tva tv = MySQLTvaDAO.getInstance().getById(t.getId_tva());
	assertEquals(t.getId_tva(),tv.getId_tva());
	
}
}
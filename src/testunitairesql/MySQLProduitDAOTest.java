package testunitairesql;

import static org.junit.Assert.*;

import org.junit.Test;

import mysql.MySQLProduitDAO;
import metier.Produit;
import metier.Tva;

public class MySQLProduitDAOTest {

	@Test
	public void testCreate() {
		Tva tv = new Tva(1,"test", 2);
		Produit produit = new Produit(2,"bureau",7,tv, null);
		MySQLProduitDAO.getInstance().create(produit);
 		Produit p = MySQLProduitDAO.getInstance().getById(1);
 		assertNotEquals(produit.getId_produit(),p.getId_produit());
 		assertNotEquals(produit.getLibelle_produit(),p.getLibelle_produit());
		
		
	}

	@Test
	public void testDelete() {
		Tva tv = new Tva(1,"test", 2);
		Produit p  = new Produit(1,"chassis",8,tv, null);
		MySQLProduitDAO.getInstance().create(p);
	    MySQLProduitDAO.getInstance().delete(p);
		Produit produit = MySQLProduitDAO.getInstance().getById(p.getId_produit());
		assertNull(produit);


	}
	
	@Test
	public void testUpdate() {
		Tva tva = new Tva(1,"test", 2);
		Produit p  = new Produit(1,"chassis",8,tva, null);
		MySQLProduitDAO.getInstance().create(p);
	    MySQLProduitDAO.getInstance().update(p);
	    Produit prodnew = MySQLProduitDAO.getInstance().getById(1);
	    assertEquals(p.getLibelle_produit(), prodnew.getLibelle_produit());
	    assertEquals(p.getPrix_produit(), prodnew.getPrix_produit(), 8);
	}

	
	@Test
	public void testAfficher() {
		MySQLProduitDAO.getInstance().Afficher();
	}

	@Test
	public void testGetById(){
		Tva tva = new Tva(1,"test", 2);
		Produit p  = new Produit(1,"chassis",8,tva, null);
	    Produit prodnew = MySQLProduitDAO.getInstance().getById(1);
		assertEquals(p.getId_produit(),prodnew.getId_produit());
		
	}
}
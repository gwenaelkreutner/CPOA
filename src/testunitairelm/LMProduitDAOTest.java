package testunitairelm;

import static org.junit.Assert.*;

import org.junit.Test;

import listememoire.ListeMemoireProduitDAO;
import listememoire.ListeMemoireTvaDAO;
import metier.Produit;
import metier.Tva;


public class LMProduitDAOTest {
	
	@Test
		public void testCreate() {
			Tva t = new Tva(1,"test", 2);
			ListeMemoireTvaDAO.getInstance().create(t);
			Produit produit = new Produit(2,"bureau",7,t, null);
			Produit p = new Produit(2,"bureau",7,t, null);
			ListeMemoireProduitDAO.getInstance().create(produit);
			//Produit produit =ListeMemoireProduitDAO.getInstance().getById(produit.getId_tva());
			assertEquals(produit.getLibelle_produit(),p.getLibelle_produit());
		}
		
		


	@Test
	public void testDelete() {
		Tva t = new Tva(3,"yoyo", 5);
		Produit p = new Produit(2,"chaise", 7,t, null);
		ListeMemoireProduitDAO.getInstance().create(p);
		ListeMemoireProduitDAO.getInstance().delete(p);
		Produit prod = new Produit(2,"chaise", 7,t, null);
		ListeMemoireProduitDAO.getInstance().create(prod);
		//Tva tv = ListeMemoireTvaDAO.getInstance().getById(t.getId_tva());	
		assertEquals(p.getId_produit(),prod.getId_produit());
		// notequals

	}
	
	@Test
	public void testUpdate() {
		Tva tva = new Tva(1,"test", 2);
		Produit p  = new Produit(1,"chassis",8,tva, null);
		ListeMemoireProduitDAO.getInstance().create(p);
		ListeMemoireProduitDAO.getInstance().update(p);
		Produit prod  = new Produit(1,"chassis",8,tva, null);
		ListeMemoireProduitDAO.getInstance().create(prod);
		ListeMemoireProduitDAO.getInstance().update(prod);
	    //Produit prodnew = MySQLProduitDAO.getInstance().getById(1);
	    assertEquals(p.getLibelle_produit(), prod.getLibelle_produit());
	    assertEquals(p.getPrix_produit(), prod.getPrix_produit(), 8);
	}

	
	@Test
	public void testAfficher() {
		ListeMemoireProduitDAO.getInstance().Afficher();
	}

	@Test
	public void testGetById(){
		Tva tva = new Tva(1,"test", 2);
		Produit p  = new Produit(1,"chassis",8,tva, null);
		Produit prod  = new Produit(2,"chassis",8,tva, null);
		ListeMemoireProduitDAO.getInstance().create(prod);
		ListeMemoireProduitDAO.getInstance().create(p);
		assertNotEquals(p.getId_produit(),prod.getId_produit());
		
	}
	}
	




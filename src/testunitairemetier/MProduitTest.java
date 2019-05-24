package testunitairemetier;

import static org.junit.Assert.*;
import metier.Produit;
import metier.Tva;

import org.junit.Test;

public class MProduitTest {

    @Test
    public void testSimple() {
        try{
            Produit p = new Produit(1,"libelle_produit", 5.0, new Tva(1,"hey",0.15), null);
        }
        catch(IllegalArgumentException e){
            fail("Devrait renvoyer une erreur si problème de constructeur");};
        }
    
    // id_produit
    
    @Test
    public void testGetId_produit(){
        Produit p = new Produit(1,"libelle_produit", 5.0, new Tva(1,"hey",0.15), null);
        assertEquals(1,p.getId_produit());
    }
    
    
    @Test
    public void testSetId_produitFAIL(){
        try{
            Produit p = new Produit(-2,"libelle_produit", 5.0, new Tva(1,"hey",0.15), null);
            
        } catch(IllegalArgumentException e){
        	
        	fail("Renvoie une erreur car l'id est inferieur a 1");
        }
    }
    
    @Test
    public void testSetId_produitOk(){
        try{
            Produit p = new Produit(1,"libelle_produit", 5.0, new Tva(1,"hey",0.15), null);
            
        } catch(IllegalArgumentException e){
            fail("Renvoie pas d'erreur car id_produit > 0");}
    }
    
    
    // libelle_produit
    
    @Test
    public void testGetLibelle_produit(){
        Produit p = new Produit(1,"libelle_produit", 5.0, new Tva(1,"hey",0.15), null);
        assertEquals("libelle_produit",p.getLibelle_produit());
        
    }
    
    @Test
    public void testSetLibelle_produitFAIL(){
        try{
            Produit p = new Produit(1,"", 5.0, new Tva(1,"hey",0.15), null);
            
        } catch(IllegalArgumentException e){
        	fail("Erreur,le libelle est vide ");
        }
    }
    
    @Test
    public void testSetLibelle_produitOk(){
        try{
            Produit p = new Produit(1,"libelle_produit", 5.0, new Tva(1,"hey",0.15), null);
            
        } catch(IllegalArgumentException e){
            fail("Envoie pas d'erreur car le libelle est non vide");}
    }
    
    // prix_produit
    
        @Test
        public void testGetPrix_produit(){
            Produit p = new Produit(1,"libelle_produit", 5.0, new Tva(1,"hey",0.15), null);
            assertEquals(5.0,p.getPrix_produit(),0.0);
        }
        
        @Test
        public void testSetPrix_produitFAIL(){
            try{
                Produit p = new Produit(1,"libelle_produit", -12.0, new Tva(1,"hey",0.15), null);
               
            } catch(IllegalArgumentException e){
            	fail("Renvoie une erreur car le prix est negatif");
            }
        }
        
        @Test
        public void testSetPrix_produitOk(){
            try{
                Produit p = new Produit(1,"libelle_produit", 5.0, new Tva(1,"hey",0.15), null);
                
                
            } catch(IllegalArgumentException e){
                fail("Pas d'erreur car prix est bien positif");}
        }
        
       //////Pour tva, reprendre méthode de TvaTest//////
    	
}
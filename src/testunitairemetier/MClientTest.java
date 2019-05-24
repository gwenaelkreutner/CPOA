package testunitairemetier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import metier.Client;

public class MClientTest {

    
    @Test
    public void testSimple() {
        try{
            Client c = new Client(1,"nom","prenom", null, null, null, null, null, 0);
        }
        catch(IllegalArgumentException e){
            fail("Devrait renvoyer une erreur si problème de constructeur");};
        }
    
    // id_client
    
    @Test
    public void testGetId_client(){
        Client c = new Client(1,"nom","prenom", null, null, null, null, null, 0);
        assertEquals(1,c.getId_client());
    }
    
    @Test
    public void testSetId_clientFAIL(){
        try{
            Client c = new Client(-12,"nom","prenom", null, null, null, null, null, 0);
            
        } catch(IllegalArgumentException e){
        	fail("Devrait renvoyer une erreur car id_client doit être supérieur à 0");
        }
    }
    
    @Test
    public void testSetId_clientOk(){
        try{
            Client c = new Client(10,"nom","prenom", null, null, null, null, null, 0);
            
        } catch(IllegalArgumentException e){
            fail("Renvoie pas d'érreur car id_client > 0");}
    }
        

    
    // nom_client
    
    @Test
    public void testGetNom_client(){
        Client c = new Client(4,"helf","theo", null, null, null, null, null, 0);
        assertEquals("helf",c.getNom());
    }
    
    @Test
    public void testSetNom_clientFAIL(){
        try{
            Client c = new Client(4,null,"prenom", null, null, null, null, null, 0);
            
        } catch(IllegalArgumentException e){
        	fail("Devrait renvoyer une erreur car le nom du client est vide");
        }
    }
    
    @Test
    public void testSetNom_clientOk(){
        try{
            Client c = new Client(4,"nom","prenom", null, null, null, null, null, 0);
            
        } catch(IllegalArgumentException e){
            fail("Renvoie pas d'erreur");}
    }
    
    // prenom
    
    @Test
    public void testGetPrenom_client(){
        Client c = new Client(4,"kreutner","gwenael", null, null, null, null, null, 0);
        assertEquals("gwenael",c.getPrenom());
    }
    
    @Test
    public void testSetPrenom_clientFAIL(){
        try{
            Client c = new Client(4,"nom",null, null, null, null, null, null, 0);
            
        } catch(IllegalArgumentException e){
        	fail("Devrait renvoyer une erreur car le prenom est vide");
        }
    }
    
    @Test
    public void testSetPrenom_clientOk(){
        try{
            Client c = new Client(4,"Nom_Client","Prenom_Client", null, null, null, null, null, 0);
            
        } catch(IllegalArgumentException e){
            fail("Renvoie aucune erreur");}
    }

}
package main;

import java.time.LocalDate;
import java.util.Scanner;

import dao.DAOFactory;
import dao.DAOFactory.Persistance;
import dao.TvaDAO;
import dao.ClientDAO;
import dao.ProduitDAO;
import dao.FactureDAO;
import metier.Tva;
import metier.Client;
import metier.Produit;
import metier.Facture;
	
	public class MainSQL {
		
		private static Scanner sc = new Scanner (System.in); 	
		private static boolean finProg = false;
	
		public static void main(String[] args) {
			// TODO Auto-generated method stub
	DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
	TvaDAO Tva = dao.getTvaDAO();
	ClientDAO Client = dao.getClientDAO();
	ProduitDAO Produit = dao.getProduitDAO();
	FactureDAO Facture = dao.getFactureDAO();

	
	while (!finProg)
	{
		
	System.out.println("TAPEZ :"+"\n"+ "1 : Pour la table tva"+"\n"+ "2 : Pour la table client" + "\n"+ "3 : Pour la table produit" + "\n"+ "4 : Pour la table facture" +"\n"+ "5 : Quitter le programme" );
	String str = sc.nextLine();	
	
	
	
	switch (str)
	{
	
	case "1" : System.out.println("vous avez choisi la table tva"+"\n"+"voici la table:");
	Tva.Afficher();
	for (Tva tab : Tva.Afficher())
		System.out.println(tab);
	
	
	System.out.println("vous pouvez ajouter, supprimer ou modifier cette table"+"\n"
			+ "1 : pour ajouter"+"\n"
			+ "2 : pour supprimer"+"\n"
			+ "3 : pour modifier");
	System.out.println("Pour revenir en arriere tapez un autre chiffre");
	
		switch(sc.nextLine())
		{
		case "1": 	System.out.println("Vous allez ajouter une nouvelle tva:");
					Tva t = new Tva();
					System.out.println("Saisir l'id de la TVA");
					t.setId_tva(sc.nextInt());
					System.out.println("Saisir le libelle de la TVA");
					t.setLibelle_tva(sc.next());
					System.out.println("Saisir le taux de TVA");
					t.setTaux_tva(sc.nextDouble());
		
					Tva.create(t);
					
					
					System.out.println("Voici la nouvelle table:");
					//Tva.Afficher();
					for (Tva tab : Tva.Afficher())
						System.out.println(tab);
					break;
					
					
					
		case"2":	System.out.println("SUPPRESSION D'UNE TVA : -->");
					Tva t2 = new Tva();
					System.out.println("Saisir l'id de la TVA a supprimer");
					t2.setId_tva(sc.nextInt());
					Tva.delete(t2);
			
					System.out.println("Voici la nouvelle table:");
					Tva.Afficher();
					for (Tva tab : Tva.Afficher())
						System.out.println(tab);
					break;
					
		case "3": 	System.out.println("MODIFICATION D'UNE TVA : -->");
					Tva t3 = new Tva();
			        System.out.println("Saisir l'id de la TVA Ã  modifier");
					t3.setId_tva(sc.nextInt());
					System.out.println("Saisir le nouveau libelle de la TVA");
					t3.setLibelle_tva(sc.next());
					System.out.println("Saisir le nouveau taux de la TVA");
					t3.setTaux_tva(sc.nextDouble());
			
					Tva.update(t3);
			
					System.out.println("Voici la nouvelle table:");
					Tva.Afficher();
					for (Tva tab : Tva.Afficher())
						System.out.println(tab);
					break;
					
		}
	break;
	}
	switch (str)
	{
	case "2" : System.out.println("vous avez choisi la table client"+"\n"+"voici la table:");
	Client.Afficher();
	for (Client tab : Client.Afficher())
		System.out.println(tab);
	
	
	System.out.println("vous pouvez ajouter, supprimer ou modifier cette table"+"\n"
			+ "1 : AJOUTER"+"\n"
			+ "2 : SUPPRIMER"+"\n"
			+ "3 : MODIFIER");
	System.out.println("Pour revenir en arriere tapez un autre chiffre");
	
		switch(sc.nextLine())
		{
		case "1": 	System.out.println("CREATION D'UN CLIENT : -->");
					Client cl = new Client();
					System.out.println("Saisir l'id de la Client");
					cl.setId_client(sc.nextInt());
					System.out.println("Saisir le nom du Client");
					cl.setNom_client(sc.next());
					System.out.println("Saisir le prenom client");
					cl.setPrenom_client(sc.next());
		
					Client.create(cl);
					
					
					System.out.println("Voici la nouvelle table:");
					Client.Afficher();
					for (Client tab : Client.Afficher())
						System.out.println(tab);
					break;
					
					
		case"2":	System.out.println("SUPPRESSION D'UN CLIENT : -->");
					Client cl2 = new Client();
					System.out.println("Saisir l'id du client a supprimer");
					cl2.setId_client(sc.nextInt());
					Client.delete(cl2);
		
					System.out.println("Voici la nouvelle table:");
					Client.Afficher();
					for (Client tab : Client.Afficher())
						System.out.println(tab);
					break;
					
		case "3": 	System.out.println("MODIFICATION D'UN CLIENT : -->");
					Client cl3 = new Client();
		            System.out.println("Saisir l'id du client Ã  modifier");
					cl3.setId_client(sc.nextInt());
					System.out.println("Saisir le nouveau nom du Client");
					cl3.setNom_client(sc.next());
					System.out.println("Saisir le nouveau prenom client");
					cl3.setPrenom_client(sc.next());
		
					Client.update(cl3);
		
					System.out.println("Voici la nouvelle table:");
					Client.Afficher();
					for (Client tab : Client.Afficher())
						System.out.println(tab);
					break;
		}
	break;
	}
	switch (str)
	{
	case "3" : System.out.println("vous avez choisi la table produit"+"\n"+"voici la table:");
	Produit.Afficher();
	for (Produit tab : Produit.Afficher())
		System.out.println(tab);
	
	
	System.out.println("vous pouvez ajouter, supprimer ou modifier cette table"+"\n"
			+ "1 : pour ajouter"+"\n"
			+ "2 : pour supprimer"+"\n"
			+ "3 : pour modifier");
	System.out.println("Pour revenir en arriere tapez un autre chiffre");
	
		switch(sc.nextLine())
		{
		case "1": 	System.out.println("Vous allez ajouter un nouveau produit:");
					Produit prod = new Produit();
					Tva t = new Tva();
					System.out.println("Saisir l'id du produit");
					prod.setId_produit(sc.nextInt());
					System.out.println("Saisir le libelle du produit");
					prod.setLibelle_produit(sc.next());
					System.out.println("Saisir le prix du produit");
					prod.setPrix_produit(sc.nextDouble());
					System.out.println("Saisir l'id tva:");
					t.setId_tva(sc.nextInt());
					prod.setId_tva(t);
					
					Produit.create(prod);
					
					
					System.out.println("Voici la nouvelle table:");
					Produit.Afficher();
					for (Produit tab : Produit.Afficher())
						System.out.println(tab);
					break;
					
					
					
					case"2":	System.out.println("SUPPRESSION D'UN produit : -->");
					Produit prod2 = new Produit();
					System.out.println("Saisir l'id du produit a supprimer");
					prod2.setId_produit(sc.nextInt());
					Produit.delete(prod2);
					
					System.out.println("Voici la nouvelle table:");
					Produit.Afficher();
					for (Produit tab : Produit.Afficher())
						System.out.println(tab);
					break;
								
					case "3": 	System.out.println("MODIFICATION D'UN produit : -->");
					Produit prod3 = new Produit();
					Tva t2 = new Tva();
			        System.out.println("Saisir l'id du produit a modifier");
			        prod3.setId_produit(sc.nextInt());
					System.out.println("Saisir le nouveau libelle du produit");
					prod3.setLibelle_produit(sc.next());
					System.out.println("Saisir le nouveau prix du produit");
					prod3.setPrix_produit(sc.nextDouble());
					System.out.println("Saisir la nouvelle id de tva");
					t2.setId_tva(sc.nextInt());
					prod3.setId_tva(t2);
					
					Produit.update(prod3);
			
					System.out.println("Voici la nouvelle table:");
					Produit.Afficher();
					for (Produit tab : Produit.Afficher())
						System.out.println(tab);
					break;
					}
	break;
	}
	switch (str)
	{
	case "4" : System.out.println("vous avez choisi la table facture"+"\n"+"voici la table:");
	Facture.Afficher();
	for (Facture tab : Facture.Afficher())
		System.out.println(tab);
	
	
	System.out.println("vous pouvez ajouter, supprimer ou modifier cette table"+"\n"
			+ "1 : pour ajouter"+"\n"
			+ "2 : pour supprimer"+"\n"
			+ "3 : pour modifier");
	System.out.println("Pour revenir en arriere tapez un autre chiffre");
	
		switch(sc.nextLine())
		{
		case "1": 	System.out.println("Vous allez ajouter une nouvelle facture:");
					Facture fact = new Facture();
					Client cl = new Client();
					System.out.println("Saisir l'id de la facture");
					fact.setId_facture(sc.nextInt());
					System.out.println("Saisir l'id du Client");
					cl.setId_client(sc.nextInt());
					fact.setId_client(cl);
					System.out.println("Saisir la date de la facture sous forme (dd/MM/yyyy)");
					fact.setDate_facture(LocalDate.parse(sc.next(), FactureDAO.formatage));
		
					Facture.create(fact);
					
					
					System.out.println("Voici la nouvelle table:");
					Facture.Afficher();
					for (Facture tab : Facture.Afficher())
						System.out.println(tab);
					break;
					
					
					
		case"2":	System.out.println("SUPPRESSION D'UNE facture : -->");
					Facture fact2 = new Facture();
					System.out.println("Saisir l'id du produit a supprimer");
					
					fact2.setId_facture(sc.nextInt());
					Facture.delete(fact2);

					System.out.println("Voici la nouvelle table:");
					Facture.Afficher();
					for (Facture tab : Facture.Afficher())
						System.out.println(tab);
					break;
					
		case "3": 	System.out.println("MODIFICATION D'UN CLIENT : -->");
					Facture fact3 = new Facture();
					Client cl3 = new Client();
			        System.out.println("Saisir l'id du client Ã  modifier");
					fact3.setId_facture(sc.nextInt());
					System.out.println("Saisir le nouveau nom du Client");
					cl3.setId_client(sc.nextInt());
					fact3.setId_client(cl3);
					System.out.println("Saisir la nouvelle date de la facture sous forme (dd/MM/yyyy)");
					fact3.setDate_facture(LocalDate.parse(sc.next(), FactureDAO.formatage));
			
					Facture.update(fact3);
			
					System.out.println("Voici la nouvelle table:");
					Facture.Afficher();
					for (Facture tab : Facture.Afficher())
						System.out.println(tab);
					break;
		}
	break;
	}
	switch (str)
	{
	case "5": finProg = true; 
	System.out.println("Fin du programme !");
	break;
	}
	
	}
	}
	}

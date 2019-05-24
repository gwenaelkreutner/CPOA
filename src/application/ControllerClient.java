package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import dao.DAOFactory;
import dao.DAOFactory.Persistance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import metier.Client;
import metier.Produit;

public class ControllerClient {
	
	private DAOFactory dao = ControllerMenu.getDao();

	@FXML
	private TableView<Client> tblClient;
	
    @FXML
    private Pane Pane;

    @FXML
    private TextField text_nom;

    @FXML
    private TextField text_prenom;
    
    @FXML
    private TextField text_rue;
    
    @FXML
    private TextField text_voie;
    
    @FXML
    private TextField text_cp;
    
    @FXML
    private TextField text_ville;
    
    @FXML
    private TextField text_pays;

    @FXML
    private Button btn_suppr;
    
    @FXML
    private Button btn_tva;
    
    @FXML
    private Button btn_client;
    
    @FXML
    private Button btn_prod;
    
    @FXML
    private Button btn_fact;
    
    @FXML
    private Button btn_lf;
    
    @FXML
    private Button btn_modif;
    
    @FXML
    private Button btn_creer;
    
    @FXML
    private Button btn_retour;
    
    @FXML
    private TableColumn<Client, String> colNom = new TableColumn<>("Nom");
    
    @FXML
    private TableColumn<Client, String> colPrenom = new TableColumn<>("Prenom");
    
    @FXML
    private TableColumn<Client, String> colRue = new TableColumn<>("Rue");
    
    @FXML
    private TableColumn<Client, String> colVoie = new TableColumn<>("Voie");
    
    @FXML
    private TableColumn<Client, String> colCp = new TableColumn<>("Code postal");
    
    @FXML
    private TableColumn<Client, String> colVille = new TableColumn<>("Ville");
    
    @FXML
    private TableColumn<Client, String> colPays = new TableColumn<>("Pays");
    
    @FXML
    private TableColumn<Client, String> colCa = new TableColumn<>("Ca");
    
    
public void initialize() 
{
	ObservableList<Client> data = FXCollections.observableArrayList();
	ArrayList<Client> list = dao.getClientDAO().Afficher();
	Iterator<Client> it = list.iterator();
	while(it.hasNext()) {
		data.add(it.next());
	}
	colNom.setCellValueFactory(new PropertyValueFactory<Client, String>("nom"));	
	colPrenom.setCellValueFactory(new PropertyValueFactory<Client, String>("prenom"));
	colRue.setCellValueFactory(new PropertyValueFactory<Client, String>("no_rue"));	
	colVoie.setCellValueFactory(new PropertyValueFactory<Client, String>("voie"));	
	colCp.setCellValueFactory(new PropertyValueFactory<Client, String>("code_postal"));	
	colVille.setCellValueFactory(new PropertyValueFactory<Client, String>("ville"));	
	colPays.setCellValueFactory(new PropertyValueFactory<Client, String>("pays"));	
	colCa.setCellValueFactory(new PropertyValueFactory<Client, String>("ca"));

	this.tblClient.setItems(data);
	//this.tblProduits.getColumns().setAll(colLibelle, colTarif, colClient);
	btn_suppr.setDisable(true);
	btn_modif.setDisable(true);
	btn_creer.setDisable(true);
	btn_client.setDisable(true);
	tblClient.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->majselect());
	text_nom.textProperty().addListener((observable,oldValue,newValue)->btnok());
	text_prenom.textProperty().addListener((observable,oldValue,newValue)->btnok());
	text_rue.textProperty().addListener((observable,oldValue,newValue)->btnok());
	text_voie.textProperty().addListener((observable,oldValue,newValue)->btnok());
	text_cp.textProperty().addListener((observable,oldValue,newValue)->btnok());
	text_ville.textProperty().addListener((observable,oldValue,newValue)->btnok());
	text_pays.textProperty().addListener((observable,oldValue,newValue)->btnok());
	
	
}
	public void majselect() {
		
		btn_suppr.setDisable(false);
		/*System.out.println(text_nom.toString());
		System.out.println(tblProduits.toString());
		System.out.println(tblProduits.getSelectionModel().toString());
		System.out.println(tblProduits.getSelectionModel().selectedItemProperty().toString());
		System.out.println(tblProduits.getSelectionModel().selectedItemProperty().get().toString());
		System.out.println(tblProduits.getSelectionModel().selectedItemProperty().get().getLibelle_produit().toString());*/
		try {
		text_nom.setText(tblClient.getSelectionModel().selectedItemProperty().get().getNom());
		text_prenom.setText(tblClient.getSelectionModel().selectedItemProperty().get().getPrenom());
		text_rue.setText(tblClient.getSelectionModel().selectedItemProperty().get().getNo_rue());
		text_voie.setText(tblClient.getSelectionModel().selectedItemProperty().get().getVoie());
		text_cp.setText(tblClient.getSelectionModel().selectedItemProperty().get().getCode_postal());
		text_ville.setText(tblClient.getSelectionModel().selectedItemProperty().get().getVille());
		text_pays.setText(tblClient.getSelectionModel().selectedItemProperty().get().getPays());
		btn_modif.setVisible(true);
		btn_suppr.setVisible(true);
		btn_creer.setVisible(false);
		}
		catch(java.lang.NullPointerException e) {
			text_nom.setText("");
			text_prenom.setText("");
			text_rue.setText("");
			text_voie.setText("");
			text_cp.setText("");
			text_ville.setText("");
			text_pays.setText("");
		}
		
	}
	public void btnok() {
		boolean saisie = text_nom.getText().isEmpty() || text_prenom.getText().isEmpty() || text_rue.getText().isEmpty() || text_voie.getText().isEmpty() || text_cp.getText().isEmpty() || text_ville.getText().isEmpty() || text_pays.getText().isEmpty();
		btn_creer.setDisable(saisie);
		btn_modif.setDisable(saisie);
	}
	
    public void btnajouter()
    {	
    		Client c = new Client();
    	    c.setNom_client(text_nom.getText());
    	    c.setPrenom_client(text_prenom.getText());
    	    c.setNo_rue(text_rue.getText());
    	    c.setVoie(text_voie.getText());
    	    c.setCode_postal(text_cp.getText());
    	    c.setVille(text_ville.getText());
    	    c.setPays(text_pays.getText());
    		dao.getClientDAO().create(c);
    		ObservableList<Client> data = FXCollections.observableArrayList();
    		ArrayList<Client> list = dao.getClientDAO().Afficher();
    		Iterator<Client> it = list.iterator();
    		while(it.hasNext()) {
    			data.add(it.next());
    		}
    		//this.tblProduits.setItems(null);
    	   this.tblClient.setItems(data);
    	   	text_nom.setText("");
			text_prenom.setText("");
			text_rue.setText("");
			text_voie.setText("");
			text_cp.setText("");
			text_ville.setText("");
			text_pays.setText("");
    }
    
    public void btnsuppr(){
    	
    	Client c = new Client();
    	c.setId_client(tblClient.getSelectionModel().getSelectedItem().getId_client());
    	dao.getClientDAO().delete(c);
    	ObservableList<Client> data = FXCollections.observableArrayList();
		ArrayList<Client> list = dao.getClientDAO().Afficher();
		Iterator<Client> it = list.iterator();
		while(it.hasNext()) {
			data.add(it.next());
		}
	   this.tblClient.setItems(data);
	   btn_creer.setVisible(true);
	   btn_modif.setVisible(false);
	   btn_suppr.setVisible(false);
    	 	   	
    }
    
    public void btnretour(ActionEvent event) throws IOException{
    	Parent tableViewParent = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //On prend les informations de la scene
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
    public void btnmodif() {
    	Client c = new Client();
    	c.setId_client(tblClient.getSelectionModel().getSelectedItem().getId_client());
	    c.setNom_client(text_nom.getText());
	    c.setPrenom_client(text_prenom.getText());
	    c.setNo_rue(text_rue.getText());
	    c.setVoie(text_voie.getText());
	    c.setCode_postal(text_cp.getText());
	    c.setVille(text_ville.getText());
	    c.setPays(text_pays.getText());
	    dao.getClientDAO().update(c);
	    ObservableList<Client> data = FXCollections.observableArrayList();
		ArrayList<Client> list = dao.getClientDAO().Afficher();
		Iterator<Client> it = list.iterator();
		while(it.hasNext()) {
			data.add(it.next());
		}
		//this.tblProduits.setItems(null);
	    this.tblClient.setItems(data);
	    tblClient.getSelectionModel().clearSelection();
		tblClient.refresh();
		btn_creer.setVisible(true);
		btn_modif.setVisible(false);
		btn_suppr.setVisible(false);
    }
    
    public void btntva(ActionEvent event) throws IOException{
    	Parent tableViewParent = FXMLLoader.load(getClass().getResource("/fxml/tva.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //On prend les informations de la scene
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
    public void btnclient(ActionEvent event) throws IOException{
    
    }
    public void btnprod(ActionEvent event) throws IOException{
    	Parent tableViewParent = FXMLLoader.load(getClass().getResource("/fxml/produit.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //On prend les informations de la scene
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    public void btnfact(ActionEvent event) throws IOException{
    	Parent tableViewParent = FXMLLoader.load(getClass().getResource("/fxml/facture.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //On prend les informations de la scene
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    public void btnlf(ActionEvent event) throws IOException{
    	Parent tableViewParent = FXMLLoader.load(getClass().getResource("/fxml/lignefact.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //On prend les informations de la scene
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
}

package application;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import dao.DAOFactory;
import dao.FactureDAO;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import metier.Facture;
import metier.Client;

public class ControllerFacture {
	
	private DAOFactory dao = ControllerMenu.getDao();

	@FXML
	private TableView<Facture> tblFacture;
	
    @FXML
    private Pane Pane;

    @FXML
    private ComboBox<Client> combobox_client;
    
    @FXML
    private DatePicker text_date;

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
    private TableColumn<Facture, String> colClient = new TableColumn<>("Client");
    
    @FXML
    private TableColumn<Facture, String> colDate = new TableColumn<>("Date");

    
public void initialize() 
{
	combobox_client.setItems(FXCollections.observableList(dao.getClientDAO().Afficher()));
	ObservableList<Facture> data = FXCollections.observableArrayList();
	ArrayList<Facture> list = dao.getFactureDAO().Afficher();
	Iterator<Facture> it = list.iterator();
	while(it.hasNext()) {
		data.add(it.next());
	}
	colClient.setCellValueFactory(new PropertyValueFactory<Facture, String>("id_client"));	
    colDate.setCellValueFactory(new PropertyValueFactory<Facture, String>("date_facture"));
	this.tblFacture.setItems(data);
	//this.tblProduits.getColumns().setAll(colLibelle, colTarif, colTva);
	btn_suppr.setDisable(true);
	btn_modif.setDisable(true);
	btn_creer.setDisable(true);
	btn_fact.setDisable(true);
	tblFacture.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->majselect());
	combobox_client.valueProperty().addListener((observable,oldValue,newValue)->btnok());
	text_date.valueProperty().addListener((observable,oldValue,newValue)->btnok());

	
	
}
	public void majselect() {
		
		btn_suppr.setDisable(false);
		/*System.out.println(text_libelle.toString());
		System.out.println(tblProduits.toString());
		System.out.println(tblProduits.getSelectionModel().toString());
		System.out.println(tblProduits.getSelectionModel().selectedItemProperty().toString());
		System.out.println(tblProduits.getSelectionModel().selectedItemProperty().get().toString());
		System.out.println(tblProduits.getSelectionModel().selectedItemProperty().get().getLibelle_produit().toString());*/
		try {
		combobox_client.setValue(tblFacture.getSelectionModel().selectedItemProperty().get().getId_client());
		text_date.setValue(tblFacture.getSelectionModel().selectedItemProperty().get().getDate_facture());
		btn_modif.setVisible(true);
		btn_suppr.setVisible(true);
		btn_creer.setVisible(false);
		}
		catch(java.lang.NullPointerException e) {
			combobox_client.setValue(null);
			text_date.setValue(null);
		}
		
	}
	public void btnok() {
		boolean saisie = text_date.getValue() == null || combobox_client.getValue() == null;
		btn_creer.setDisable(saisie);
		btn_modif.setDisable(saisie);
	}
    public void btnajouter()
    {	
		Facture f = new Facture();
		f.setId_client(combobox_client.getValue());
	    f.setDate_facture(text_date.getValue());
		dao.getFactureDAO().create(f);
		ObservableList<Facture> data = FXCollections.observableArrayList();
		ArrayList<Facture> list = dao.getFactureDAO().Afficher();
		Iterator<Facture> it = list.iterator();
		while(it.hasNext()) {
			data.add(it.next());
		}
		//this.tblProduits.setItems(null);
	   this.tblFacture.setItems(data);
	   combobox_client.setValue(null);
	   text_date.setValue(null);
    }
    
    public void btnsuppr(){
    	
    	Facture f = new Facture();
    	f.setId_facture(tblFacture.getSelectionModel().getSelectedItem().getId_facture());
    	dao.getFactureDAO().delete(f);
    	ObservableList<Facture> data = FXCollections.observableArrayList();
		ArrayList<Facture> list = dao.getFactureDAO().Afficher();
		Iterator<Facture> it = list.iterator();
		while(it.hasNext()) {
			data.add(it.next());
		}
		this.tblFacture.setItems(data);
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
    	Facture f = new Facture();
    	f.setId_facture(tblFacture.getSelectionModel().getSelectedItem().getId_facture());
	    f.setId_client(combobox_client.getValue());
	    f.setDate_facture(text_date.getValue());
	    dao.getFactureDAO().update(f);
	    ObservableList<Facture> data = FXCollections.observableArrayList();
		ArrayList<Facture> list = dao.getFactureDAO().Afficher();
		Iterator<Facture> it = list.iterator();
		while(it.hasNext()) {
			data.add(it.next());
		}
		//this.tblProduits.setItems(null);
	   this.tblFacture.setItems(data);
	   tblFacture.getSelectionModel().clearSelection();
	   tblFacture.refresh();
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
    	Parent tableViewParent = FXMLLoader.load(getClass().getResource("/fxml/client.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //On prend les informations de la scene
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
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

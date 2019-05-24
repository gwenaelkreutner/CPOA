package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.table.DefaultTableModel;

import dao.DAOFactory;
import dao.DAOFactory.Persistance;
import dao.LigneFactureDAO;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import metier.Facture;
import metier.LigneFacture;
import metier.Produit;
import metier.Client;
import metier.Tva;

public class ControllerLigneFacture {
	
	private DAOFactory dao = ControllerMenu.getDao();

	@FXML private TableView<LigneFacture> tblLignefact;
	
    @FXML private Pane Pane;

    @FXML private TextField text_qt;

    @FXML private ComboBox<Facture> combobox_fact;
    
    @FXML private ComboBox<Produit> combobox_prod;

    @FXML private Button btn_suppr;
    
    @FXML private Button btn_tva;
    
    @FXML private Button btn_client;
    
    @FXML private Button btn_prod;
    
    @FXML private Button btn_fact;
    
    @FXML private Button btn_lf;
    
    @FXML private Button btn_modif;
    
    @FXML private Button btn_creer;
    
    @FXML private Button btn_retour;
    
    @FXML private Label prixtot;
    
    @FXML private Label idfacture;
    
    @FXML private Label textclient;
    
    @FXML private TableColumn<LigneFacture, String> colFact = new TableColumn<>("Facture");
    
    @FXML private TableColumn<LigneFacture, String> colProd = new TableColumn<>("Produit");
     
    @FXML private TableColumn<LigneFacture, String> colQt = new TableColumn<>("Quantité");
    
    @FXML private TableColumn<LigneFacture, String> colPrixtot = new TableColumn<>("Prix total");
    
    public void total(){
    	double total = tblLignefact.getItems().stream().mapToDouble(lf ->  lf.getId_produit().getPrix_produit() * lf.getQuantite()).sum();
		prixtot.setText(Double.toString(total));
    }
    
    private List<LigneFacture> addfact(Facture facture) {
        return dao.getLigneFactureDAO().Afficher().stream().filter(lf -> lf.getId_facture().equals(facture)).collect(Collectors.toList());
    }
    
    public void initialize() 
    {
    	combobox_fact.setItems(FXCollections.observableList(dao.getFactureDAO().Afficher()));
    	combobox_prod.setItems(FXCollections.observableList(dao.getProduitDAO().Afficher()));
    	//ObservableList<LigneFacture> data = FXCollections.observableArrayList(dao.getLigneFactureDAO().Afficher());
    	colFact.setCellValueFactory(new PropertyValueFactory<LigneFacture, String>("id_facture"));	
        colProd.setCellValueFactory(new PropertyValueFactory<LigneFacture, String>("id_produit"));
    	colQt.setCellValueFactory(new PropertyValueFactory<LigneFacture, String>("quantite"));
    	colPrixtot.setCellValueFactory(lf -> new SimpleStringProperty(String.format("%.2f €", lf.getValue().getId_produit().getPrix_produit() * lf.getValue().getQuantite())));
    	//this.tblLignefact.setItems(data);
    	//total();
    	//this.tblProduits.getColumns().setAll(colLibelle, colTarif, colTva);
    	btn_creer.setDisable(true);
    	btn_lf.setDisable(true);
    	tblLignefact.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->majselect());
    	combobox_fact.valueProperty().addListener((observable,oldValue,newValue)->ajoutfact(observable, oldValue, newValue));
    	//combobox_fact.valueProperty().addListener((observable,oldValue,newValue)->btnok());
    	combobox_prod.valueProperty().addListener((observable,oldValue,newValue)->btnok());
    	text_qt.textProperty().addListener((observable,oldValue,newValue)->btnok());

    	
    	
    }
		    public void ajoutfact(ObservableValue<? extends Facture> observable,Facture oldValue,Facture newValue)
		    {
		        ObservableList<LigneFacture> data = FXCollections.observableList(addfact(newValue));
		        this.tblLignefact.setItems(data);
		        total();
		        //System.out.println(newValue);
		        idfacture.setText("Facture n° "+Integer.toString(newValue.getId_facture()));
		        textclient.setText(newValue.getId_client().getNom()+" "+newValue.getId_client().getPrenom()+" ("+newValue.getDate_facture()+")");
		}
    
    	public void majselect() {
    		/*System.out.println(text_libelle.toString());
    		System.out.println(tblProduits.toString());
    		System.out.println(tblProduits.getSelectionModel().toString());
    		System.out.println(tblProduits.getSelectionModel().selectedItemProperty().toString());
    		System.out.println(tblProduits.getSelectionModel().selectedItemProperty().get().toString());
    		System.out.println(tblProduits.getSelectionModel().selectedItemProperty().get().getLibelle_produit().toString());*/
    		try {
    		//combobox_fact.setValue(tblLignefact.getSelectionModel().selectedItemProperty().get().getId_facture());
    		combobox_prod.setValue(tblLignefact.getSelectionModel().selectedItemProperty().get().getId_produit());
    		text_qt.setText(Integer.toString(tblLignefact.getSelectionModel().selectedItemProperty().get().getQuantite()));
    		btn_modif.setVisible(true);
    		btn_suppr.setVisible(true);
    		btn_creer.setVisible(false);
    		}
    		catch(java.lang.NullPointerException e) {
    			//combobox_fact.setValue(null);
    			combobox_prod.setValue(null);
    			text_qt.setText("");
    		}
    		
    	}
    	public void btnok() {
    		boolean saisie = text_qt.getText().isEmpty() || combobox_prod.getValue() == null;
    		btn_creer.setDisable(saisie);
    		btn_modif.setDisable(saisie);
    	}
        public void btnajouter()
        {
        	try {
        		
        		LigneFacture lfa = new LigneFacture();
        		lfa.setId_facture(combobox_fact.getValue());
        		lfa.setId_produit(combobox_prod.getValue());
        		lfa.setQuantite(Integer.parseInt(text_qt.getText()));
        		dao.getLigneFactureDAO().create(lfa);
        		/*
        		ObservableList<LigneFacture> data = FXCollections.observableArrayList();
    			ArrayList<LigneFacture> list = dao.getLigneFactureDAO().Afficher();
    			Iterator<LigneFacture> it = list.iterator();
    			while(it.hasNext()) {
    				data.add(it.next());
    			}
    			this.tblLignefact.setItems(data);
    			*/
        		this.tblLignefact.getItems().add(lfa);
    			total();
    			//combobox_fact.setValue(null);
    			combobox_prod.setValue(null);
    			text_qt.setText("");
    		}
    		catch(java.lang.NumberFormatException e) {
    			String erreur = "Veuillez saisir une quantite avec un nombre de type int";
    	    	Alert alert=new Alert(Alert.AlertType.ERROR);
    	    	alert.setTitle("Erreur lors de la saisie");
    	    	alert.setHeaderText("La quantite n'est pas un nombre");
    	    	alert.setContentText(erreur);
    	    	alert.showAndWait();
    		}
        }
        
        public void btnsuppr(){
        	
        	LigneFacture lfa = new LigneFacture();
        	lfa.setId_ligne(tblLignefact.getSelectionModel().getSelectedItem().getId_ligne());
        	dao.getLigneFactureDAO().delete(lfa);
        	/*
        	ObservableList<LigneFacture> data = FXCollections.observableArrayList();
    		ArrayList<LigneFacture> list = dao.getLigneFactureDAO().Afficher();
    		Iterator<LigneFacture> it = list.iterator();
    		while(it.hasNext()) {
    			data.add(it.next());
    		}
    		this.tblLignefact.setItems(data);
    		*/
        	//ajoutfact(null, null, lfa.getId_facture());
        	tblLignefact.getItems().removeAll(tblLignefact.getSelectionModel().getSelectedItem());
        	tblLignefact.getSelectionModel().clearSelection();
    		total();
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
        	try {
    	    	LigneFacture lf = new LigneFacture();
    	    	lf.setId_facture(combobox_fact.getValue());
    	    	lf.setId_ligne(tblLignefact.getSelectionModel().getSelectedItem().getId_ligne());
    			lf.setId_produit(combobox_prod.getValue());
    			lf.setQuantite(Integer.parseInt(text_qt.getText()));
    		    dao.getLigneFactureDAO().update(lf);
    		    /*
    		    ObservableList<LigneFacture> data = FXCollections.observableArrayList();
    			ArrayList<LigneFacture> list = dao.getLigneFactureDAO().Afficher();
    			Iterator<LigneFacture> it = list.iterator();
    			while(it.hasNext()) {
    				data.add(it.next());
    			}
    			//this.tblProduits.setItems(null);
    			this.tblLignefact.setItems(data);
    			*/
    		    tblLignefact.getItems().removeAll(tblLignefact.getSelectionModel().getSelectedItem());
    		    this.tblLignefact.getItems().add(lf);
    			tblLignefact.getSelectionModel().clearSelection();
    			total();
    			//tblLignefact.refresh();
    			btn_creer.setVisible(true);
    			btn_modif.setVisible(false);
    			btn_suppr.setVisible(false);
        	}
        	catch(java.lang.NumberFormatException e) {
    			String erreur = "Veuillez saisir une quantite avec un nombre de type int";
    	    	Alert alert=new Alert(Alert.AlertType.ERROR);
    	    	alert.setTitle("Erreur lors de la saisie");
    	    	alert.setHeaderText("La quantite n'est pas un nombre");
    	    	alert.setContentText(erreur);
    	    	alert.showAndWait();
    		}
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
        	Parent tableViewParent = FXMLLoader.load(getClass().getResource("/fxml/facture.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            
            //On prend les informations de la scene
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            window.setScene(tableViewScene);
            window.show();
        }
        public void btnlf(ActionEvent event) throws IOException{
       
        }
    
}

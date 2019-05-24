package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import dao.DAOFactory;
import dao.DAOFactory.Persistance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import metier.LigneFacture;
import metier.Produit;
import metier.Tva;
import application.ControllerMenu;

public class ControllerProduit {

	private DAOFactory dao = ControllerMenu.getDao();
	
	@FXML
	private TableView<Produit> tblProduits;
	
    @FXML
    private Pane Pane;

    @FXML
    private TextField text_libelle;

    @FXML
    private TextField text_tarif;
    
    @FXML private TextField txt_url;

    @FXML
    private ComboBox<Tva> combobox_tva;
    
    @FXML
    private ImageView imageview;

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
    private TableColumn<Produit, String> colLibelle = new TableColumn<>("Nom du produit");
    
    @FXML
    private TableColumn<Produit, String> colTarif = new TableColumn<>("Tarif");
    
    @FXML
    private TableColumn<Produit, String> colTva = new TableColumn<>("Tva");
    
    
public void initialize() 
{
	combobox_tva.setItems(FXCollections.observableList(dao.getTvaDAO().Afficher()));
	ObservableList<Produit> data = FXCollections.observableArrayList(dao.getProduitDAO().Afficher());
	colLibelle.setCellValueFactory(new PropertyValueFactory<Produit, String>("libelle_produit"));	
    colTva.setCellValueFactory(new PropertyValueFactory<Produit, String>("id_tva"));
	colTarif.setCellValueFactory(new PropertyValueFactory<Produit, String>("prix_produit"));
	this.tblProduits.setItems(data);
	//this.tblProduits.getColumns().setAll(colLibelle, colTarif, colTva);
	btn_suppr.setDisable(true);
	btn_modif.setDisable(true);
	btn_creer.setDisable(true);
	btn_prod.setDisable(true);
	tblProduits.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->majselect());
	text_libelle.textProperty().addListener((observable,oldValue,newValue)->btnok());
	text_tarif.textProperty().addListener((observable,oldValue,newValue)->btnok());
	combobox_tva.valueProperty().addListener((observable,oldValue,newValue)->btnok());
	imageview.setImage(new Image("http://www.xn--achat-group-lbb.com/wp-content/uploads/2018/08/produit.jpg%22", true));
	
	
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
		text_libelle.setText(tblProduits.getSelectionModel().selectedItemProperty().get().getLibelle_produit());
		text_tarif.setText(Double.toString(tblProduits.getSelectionModel().selectedItemProperty().get().getPrix_produit()));
		combobox_tva.setValue(tblProduits.getSelectionModel().selectedItemProperty().get().getId_tva());
		txt_url.setText(tblProduits.getSelectionModel().selectedItemProperty().get().getUrl());
			try {
			imageview.setImage(new Image(tblProduits.getSelectionModel().selectedItemProperty().get().getUrl(), true));
			}
			catch(Exception e) {
				imageview.setImage(null);
			}
		btn_modif.setVisible(true);
		btn_suppr.setVisible(true);
		btn_creer.setVisible(false);
		}
		catch(java.lang.NullPointerException e) {
			text_libelle.setText("");
			text_tarif.setText("");
			combobox_tva.setValue(null);
		}
		
	}
	public void btnok() {
		boolean saisie = text_libelle.getText().isEmpty() || text_tarif.getText().isEmpty() || combobox_tva.getValue() == null;
		btn_creer.setDisable(saisie);
		btn_modif.setDisable(saisie);
	}
    public void btnajouter()
    {
    	try {
    		
    		Produit p = new Produit();
    	    p.setLibelle_produit(text_libelle.getText());
    	    p.setPrix_produit(Double.parseDouble(text_tarif.getText()));
    	    p.setId_tva(combobox_tva.getValue());
    	    p.setUrl(txt_url.getText());
    	    
    	    //Doublon
    	    ArrayList<Produit> doublon = dao.getProduitDAO().Afficher();
    	    Produit p2 = new Produit();
    	    Iterator<Produit> it2 = doublon.iterator();
			while(it2.hasNext()) {
					p2 = it2.next();
					if (p.getLibelle_produit().equals(p2.getLibelle_produit()))
					{
				    	Alert alert=new Alert(Alert.AlertType.ERROR);
				    	alert.setTitle("Erreur lors de la saisie");
				    	alert.setHeaderText("Le libelle existe déjà");
				    	alert.setContentText("Veuillez saisir un produit qui n'existe pas");
				    	alert.showAndWait();
				    	text_libelle.setText("");
						return;
					}
				}
    	    
    		dao.getProduitDAO().create(p);
    		ObservableList<Produit> data = FXCollections.observableArrayList();
			ArrayList<Produit> list = dao.getProduitDAO().Afficher();
			Iterator<Produit> it = list.iterator();
			while(it.hasNext()) {
				data.add(it.next());
				}
			//this.tblProduits.setItems(null);
			this.tblProduits.setItems(data);
    		text_libelle.setText("");
			text_tarif.setText("");
			combobox_tva.setValue(null);
		}
		catch(java.lang.NumberFormatException e) {
			String erreur = "Veuillez saisir un tarif de type double";
	    	Alert alert=new Alert(Alert.AlertType.ERROR);
	    	alert.setTitle("Erreur lors de la saisie");
	    	alert.setHeaderText("Le tarif n'est pas un nombre");
	    	alert.setContentText(erreur);
	    	alert.showAndWait();
		}
    }
    
    public void btnsuppr(){
    	
    	Produit p = new Produit();
    	p.setId_produit(tblProduits.getSelectionModel().getSelectedItem().getId_produit());
    	
    	//ne pas supprimer une tva s'il existe dans un produit
    	ArrayList<LigneFacture> supp = dao.getLigneFactureDAO().Afficher();
    	LigneFacture lf = new LigneFacture();
	    Iterator<LigneFacture> it2 = supp.iterator();
		while(it2.hasNext()) {
				lf = it2.next();
				if ((p.getId_produit())==(lf.getId_produit().getId_produit()))
				{
			    	Alert alert=new Alert(Alert.AlertType.ERROR);
			    	alert.setTitle("Erreur lors de la suppression");
			    	alert.setHeaderText("Le produit est utilisé pour la facture n° " + lf.getId_facture().getId_facture() + " de " + lf.getId_facture().getId_client().getNom() + " " +lf.getId_facture().getId_client().getPrenom());
			    	alert.setContentText("Veuillez essayer ulterieurement");
			    	alert.showAndWait();
			    	text_libelle.setText("");
					return;
				}
			}
    	
    	dao.getProduitDAO().delete(p);
    	ObservableList<Produit> data = FXCollections.observableArrayList();
		ArrayList<Produit> list = dao.getProduitDAO().Afficher();
		Iterator<Produit> it = list.iterator();
		while(it.hasNext()) {
			data.add(it.next());
		}
	   this.tblProduits.setItems(data);
	   btn_creer.setVisible(true);
	   btn_modif.setVisible(false);
	   btn_suppr.setVisible(false);
	 	   	
    }
    
    public void btnmodif() {
    	try {
	    	Produit p = new Produit();
	    	p.setId_produit(tblProduits.getSelectionModel().getSelectedItem().getId_produit());
		    p.setLibelle_produit(text_libelle.getText());
		    p.setPrix_produit(Double.parseDouble(text_tarif.getText()));
		    p.setId_tva(combobox_tva.getValue());
		    p.setUrl(txt_url.getText());
		    dao.getProduitDAO().update(p);
			ObservableList<Produit> data = FXCollections.observableArrayList(dao.getProduitDAO().Afficher());
			this.tblProduits.setItems(data);
			tblProduits.getSelectionModel().clearSelection();
			tblProduits.refresh();
			btn_creer.setVisible(true);
			btn_modif.setVisible(false);
			btn_suppr.setVisible(false);
    	}
		catch(java.lang.NumberFormatException e) {
			String erreur = "Veuillez saisir un tarif de type double";
	    	Alert alert=new Alert(Alert.AlertType.ERROR);
	    	alert.setTitle("Erreur lors de la saisie");
	    	alert.setHeaderText("Le tarif n'est pas un nombre");
	    	alert.setContentText(erreur);
	    	alert.showAndWait();
		}
			
    }
    public void btnretour(ActionEvent event) throws IOException{
    	Parent tableViewParent = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //On prend les informations de la scene
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
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

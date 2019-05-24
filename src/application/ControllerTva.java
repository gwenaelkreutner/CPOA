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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import metier.Produit;
import metier.Tva;

public class ControllerTva {
	
	private DAOFactory dao = ControllerMenu.getDao();

	@FXML
	private TableView<Tva> tblTva;
	
    @FXML
    private Pane Pane;

    @FXML
    private TextField text_libelle;

    @FXML
    private TextField text_taux;

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
    private TableColumn<Tva, String> colLibelle = new TableColumn<>("Libellé");
    
    @FXML
    private TableColumn<Tva, String> colTaux = new TableColumn<>("Taux");
    
    
public void initialize() 
{
	ObservableList<Tva> data = FXCollections.observableArrayList(dao.getTvaDAO().Afficher());
	colLibelle.setCellValueFactory(new PropertyValueFactory<Tva, String>("libelle_tva"));	
	colTaux.setCellValueFactory(new PropertyValueFactory<Tva, String>("taux_tva"));
	this.tblTva.setItems(data);
	//this.tblProduits.getColumns().setAll(colLibelle, colTarif, colTva);
	btn_suppr.setDisable(true);
	btn_modif.setDisable(true);
	btn_creer.setDisable(true);
	btn_tva.setDisable(true);
	tblTva.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->majselect());
	text_libelle.textProperty().addListener((observable,oldValue,newValue)->btnok());
	text_taux.textProperty().addListener((observable,oldValue,newValue)->btnok());
	
	
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
		text_libelle.setText(tblTva.getSelectionModel().selectedItemProperty().get().getLibelle_tva());
		text_taux.setText(Double.toString(tblTva.getSelectionModel().selectedItemProperty().get().getTaux_tva()));
		btn_modif.setVisible(true);
		btn_suppr.setVisible(true);
		btn_creer.setVisible(false);
		}
		catch(java.lang.NullPointerException e) {
			text_libelle.setText("");
			text_taux.setText("");
		}
		
	}
	public void btnok() {
		boolean saisie = text_libelle.getText().isEmpty() || text_taux.getText().isEmpty();
		btn_creer.setDisable(saisie);
		btn_modif.setDisable(saisie);
	}
	
    public void btnajouter()
    {
    	try {
    		
    		Tva t = new Tva();
    	    t.setLibelle_tva(text_libelle.getText());
    	    t.setTaux_tva(Double.parseDouble(text_taux.getText()));
    		dao.getTvaDAO().create(t);
    		ObservableList<Tva> data = FXCollections.observableArrayList();
    		ArrayList<Tva> list = dao.getTvaDAO().Afficher();
    		Iterator<Tva> it = list.iterator();
    		while(it.hasNext()) {
    			data.add(it.next());
    		}
    		//this.tblProduits.setItems(null);
    		this.tblTva.setItems(data);
    		text_libelle.setText("");
			text_taux.setText("");
		}
		catch(java.lang.NumberFormatException e) {
			String erreur = "Veuillez saisir un taux de type double";
	    	Alert alert=new Alert(Alert.AlertType.ERROR);
	    	alert.setTitle("Erreur lors de la saisie");
	    	alert.setHeaderText("Le tarif n'est pas un nombre");
	    	alert.setContentText(erreur);
	    	alert.showAndWait();
		}
    }
    
    public void btnsuppr(){
    	Tva t = new Tva();
    	t.setId_tva(tblTva.getSelectionModel().getSelectedItem().getId_tva());
    	
    	//ne pas supprimer une tva s'il existe dans un produit
    	ArrayList<Produit> supp = dao.getProduitDAO().Afficher();
	    Produit p2 = new Produit();
	    Iterator<Produit> it2 = supp.iterator();
		while(it2.hasNext()) {
				p2 = it2.next();
				if ((t.getId_tva())==(p2.getId_tva().getId_tva()))
				{
			    	Alert alert=new Alert(Alert.AlertType.ERROR);
			    	alert.setTitle("Erreur lors de la suppression");
			    	alert.setHeaderText("Le tva est utilisée pour un produit");
			    	alert.setContentText("Veuillez essayer ulterieurement");
			    	alert.showAndWait();
			    	text_libelle.setText("");
					return;
				}
			}
    	
    	//suppression de la tva selectionné
    	dao.getTvaDAO().delete(t);
    	ObservableList<Tva> data = FXCollections.observableArrayList(dao.getTvaDAO().Afficher());
	    this.tblTva.setItems(data); 
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
    	Tva t = new Tva();
    	t.setId_tva(tblTva.getSelectionModel().getSelectedItem().getId_tva());
	    t.setLibelle_tva(text_libelle.getText());
	    t.setTaux_tva(Double.parseDouble(text_taux.getText()));
	    dao.getTvaDAO().update(t);
		ObservableList<Tva> data = FXCollections.observableArrayList(dao.getTvaDAO().Afficher());
		//this.tblTva.setItems(null);
	   this.tblTva.setItems(data);
	   tblTva.getSelectionModel().clearSelection();
	   tblTva.refresh();
	   btn_creer.setVisible(true);
	   btn_modif.setVisible(false);
	   btn_suppr.setVisible(false);
    	}
    	catch(java.lang.NumberFormatException e){
    		String erreur = "Veuillez saisir un taux de type double";
	    	Alert alert=new Alert(Alert.AlertType.ERROR);
	    	alert.setTitle("Erreur lors de la saisie");
	    	alert.setHeaderText("Le tarif n'est pas un nombre");
	    	alert.setContentText(erreur);
	    	alert.showAndWait();
    	}
    }
    public void btntva(ActionEvent event) throws IOException{
    	
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
    	Parent tableViewParent = FXMLLoader.load(getClass().getResource("/fxml/lignefact.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //On prend les informations de la scene
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
}

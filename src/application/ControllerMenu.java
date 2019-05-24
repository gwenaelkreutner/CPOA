package application;

import java.io.IOException;

import dao.DAOFactory;
import dao.DAOFactory.Persistance;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControllerMenu {

    @FXML
    private Button btn_lm;
    
    @FXML
    private Button btn_sql;
    
    private static DAOFactory dao;
	    
    public void btnlm(ActionEvent event) throws IOException{
    	dao = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
    	Parent tableViewParent = FXMLLoader.load(getClass().getResource("/fxml/produit.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
    
    public void btnsql(ActionEvent event) throws IOException{
    	dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
    	Parent tableViewParent = FXMLLoader.load(getClass().getResource("/fxml/produit.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
    
    public static DAOFactory getDao() {
    	return dao;
    }
}
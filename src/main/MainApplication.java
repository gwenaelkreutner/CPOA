package main;
	
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
//import javafx.scene.layout.VBox;


public class MainApplication extends Application {
	@Override
	public void start(Stage Stage) {
	try {
	URL fxmlURL=getClass().getResource("/fxml/menu.fxml");
	FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
	Node root = fxmlLoader.load();
	Scene scene = new Scene((Pane) root, 600, 400);
	//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	Stage.setScene(scene);
	Stage.setTitle("Gestion des produits");
	Stage.show();
	} catch (Exception e) {
	e.printStackTrace();
	}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
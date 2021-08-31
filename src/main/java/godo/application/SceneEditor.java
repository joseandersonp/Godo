package godo.application;

import java.net.URL;

import godo.controller.Dialog;
import godo.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SceneEditor extends Application {

	public void start(Stage stage) {

		try {
			
			URL url = getClass().getResource("/godo/fxml/main.fxml");
			FXMLLoader loader = new FXMLLoader(url);
			VBox vboxMain = (VBox) loader.load();
			MainController mainController = loader.getController();
			
			url = getClass().getResource("/godo/fxml/enemy.fxml");
			loader = new FXMLLoader(url);
			mainController.getTabEnemy().setContent((VBox) loader.load());			
			mainController.setEnemyController(loader.getController());
			
			url = getClass().getResource("/godo/fxml/attack.fxml");
			loader = new FXMLLoader(url);
			mainController.getTabAttack().setContent((AnchorPane) loader.load());
			mainController.setAttackController(loader.getController());
						
			Scene sc = new Scene(vboxMain, 1024, 600);
			stage.setTitle("Godo-Scene Editor");
			stage.setScene(sc);
			stage.show();

		} catch (Exception e) {
			Dialog.showExceptionDialog(e.getMessage(), e);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
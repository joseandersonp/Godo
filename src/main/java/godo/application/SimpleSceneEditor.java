package godo.application;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SimpleSceneEditor extends Application {

	public void start(Stage stage) {

		try {
			
			URL url = getClass().getResource("/godo/fxml/scene-editor.fxml");
			VBox aiScriptView;
			aiScriptView = (VBox) FXMLLoader.load(url);
			Scene sc = new Scene(aiScriptView, 1024, 600);
			stage.setTitle("Godo-Scene Editor");
			stage.setScene(sc);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
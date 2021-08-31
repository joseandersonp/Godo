package godo.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Dialog {

	public static void showExceptionDialog(String message, Exception ex) {

		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Message");
		alert.setHeaderText(message);
		alert.setContentText(ex.getMessage());

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		String exceptionText = sw.toString();

		Label label = new Label("Exception stacktrace:");

		TextArea textArea = new TextArea(exceptionText);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		VBox.setVgrow(textArea, Priority.ALWAYS);

		VBox expContent = new VBox();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.getChildren().addAll(label, textArea);

		alert.getDialogPane().setExpandableContent(expContent);

		alert.showAndWait();

	}

	public static void showAboutDialog() {
		try {
			URL url = Dialog.class.getResource("/godo/fxml/about.fxml");
			HBox hbox = (HBox) FXMLLoader.load(url);
			Stage stage = new Stage();
			stage.setScene(new javafx.scene.Scene(hbox, 400, 220));
			stage.setTitle("About");
			stage.resizableProperty().setValue(Boolean.FALSE);
			stage.initStyle(StageStyle.UTILITY);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();
		} catch (Exception e) {
		}
	}	
}

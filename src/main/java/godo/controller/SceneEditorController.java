package godo.controller;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.List;

import godo.model.SceneEditorModel;
import godo.scene.AIScript;
import godo.scene.Opcode;
import godo.scene.Scene;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SceneEditorController {

	private SceneEditorModel model = SceneEditorModel.getInstance();
	private Scene selectedScene;
	private AIScript selectedEnemyAI;

	@FXML
	private VBox vboxMain;

	@FXML
	private SplitPane splitpMain;

	@FXML
	private MenuItem menuiOpen;

	@FXML
	private MenuItem menuiSave;

	@FXML
	private MenuItem menuiSaveFileAs;
	
	@FXML
	private MenuItem menuiImportAllScenes;
	
	@FXML
	private MenuItem menuiExportAllScenes;

	@FXML
	private Menu menuAbout;

	@FXML
	private Button btnSearch;

	@FXML
	private TableView<Scene> tablevScene;

	@FXML
	private TableColumn<Scene, Integer> colSceneID;

	@FXML
	private TableColumn<Scene, String> colSceneEnemy1;

	@FXML
	private TableColumn<Scene, String> colSceneEnemy2;

	@FXML
	private TableColumn<Scene, String> colSceneEnemy3;

	@FXML
	private Button btnNextEnemy;

	@FXML
	private Button btnBackEnemy;

	@FXML
	private ChoiceBox<String> cboxEnemy;

	@FXML
	private TextField txtSearchEnemy;

	@FXML
	private TextField txtEnemyName;

	@FXML
	private ListView<String> listvCodeSection;

	@FXML
	private TextArea txtAreaCode;

	@FXML
	private Button btnSaveCode;

	@FXML
	public void initialize() {

		// tablevScene Cols
		colSceneID.setCellValueFactory(new PropertyValueFactory<Scene, Integer>("id"));
		colSceneEnemy1.setCellValueFactory(new PropertyValueFactory<Scene, String>("descStringEnemyName1"));
		colSceneEnemy2.setCellValueFactory(new PropertyValueFactory<Scene, String>("descStringEnemyName2"));
		colSceneEnemy3.setCellValueFactory(new PropertyValueFactory<Scene, String>("descStringEnemyName3"));

		// Listeners
		listvCodeSection.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if (newValue.intValue() != -1)
					openCodeSectionEnemyAI(newValue.intValue());
			}
		});

		tablevScene.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if (tablevScene.getSelectionModel().getSelectedItem() != null)
					selectScene();
			}

		});

		cboxEnemy.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if (newValue.intValue() != -1) {

					selectEnemyAI(newValue.intValue());

					btnNextEnemy.setDisable(newValue.intValue() == 2);
					btnBackEnemy.setDisable(newValue.intValue() == 0);
				}
			}
		});

		// init lists
		listvCodeSection.getItems().addAll(model.listCodeSectionNames());

		cboxEnemy.getItems().add("1");
		cboxEnemy.getItems().add("2");
		cboxEnemy.getItems().add("3");


	}

	public File chooseOpenFile() {

		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File("./"));
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Scene Files", "*.BIN"));
		return fileChooser.showOpenDialog(vboxMain.getScene().getWindow());

	}

	public File chooseSaveFile() {

		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialFileName(model.getSceneFile().getName());
		fileChooser.setInitialDirectory(model.getSceneFile().getParentFile());
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Scene Files", "*.BIN"));
		return fileChooser.showSaveDialog(vboxMain.getScene().getWindow());

	}

	@FXML
	public void openFile(ActionEvent event) {

		File file = chooseOpenFile();
		if (file == null)
			return;

		try {

			model.openSceneFile(file);
			List<Scene> scenes = model.getScenes();
			ObservableList<Scene> olScenes = FXCollections.observableArrayList(scenes);

			tablevScene.setItems(olScenes);
			tablevScene.getSelectionModel().selectFirst();

			splitpMain.setDisable(false);
			menuiSave.setDisable(false);
			menuiSaveFileAs.setDisable(false);

		} catch (Exception e) {
			showExceptionDialog("An error occurred while opening the scene file!", e);
		}

	}

	@FXML
	public void saveFile(ActionEvent event) {
		try {
			model.saveSceneFile(null);
		} catch (Exception e) {
			showExceptionDialog("An error occurred while saving the scene file!", e);
		}
	}

	@FXML
	public void saveFileAs(ActionEvent event) {

		File file = chooseSaveFile();
		if (file == null)
			return;

		try {
			model.saveSceneFile(file);

		} catch (Exception e) {
			showExceptionDialog("An error occurred while saving the scene file!", e);
		}
	}
	
	@FXML
	public void importAllScenes(ActionEvent event) {

	}
	
	@FXML
	public void exportAllScenes(ActionEvent event) {

	}

	@FXML
	public void updateKernelFile(ActionEvent event) {

	}
	
	@FXML
    void showAboutWindow(ActionEvent event) {
		
		try {
			
			URL url = getClass().getResource("/godo/fxml/about.fxml");
			HBox hbox = (HBox) FXMLLoader.load(url);
			Stage stage = new Stage();
			stage.setScene(new javafx.scene.Scene(hbox, 400,220));
			stage.setTitle("About");
			stage.resizableProperty().setValue(Boolean.FALSE);
			stage.initStyle(StageStyle.UTILITY);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();
			
		}catch(Exception e){}
 
	}

	@FXML
	public void searchEnemy(ActionEvent event) {

		String text = txtSearchEnemy.getText().toLowerCase();
		int sidx = tablevScene.getSelectionModel().getSelectedIndex();
		for (int i = 0; i < 256; i++) {

			if (++sidx > 255)
				sidx = 0;

			Scene scene = tablevScene.getItems().get(sidx);
			if (scene.getEnemyName1().toLowerCase().contains(text) || scene.getEnemyName2().toLowerCase().contains(text)
					|| scene.getEnemyName3().toLowerCase().contains(text)) {
				tablevScene.getSelectionModel().select(scene);
				tablevScene.scrollTo(scene);
				break;
			}

			if (sidx > 255) {
				sidx = 0;
			}

		}

	}

	@FXML
	public void selectBackEnemy(ActionEvent event) {
		int index = cboxEnemy.getSelectionModel().getSelectedIndex();
		cboxEnemy.getSelectionModel().select(index - 1);
	}

	@FXML
	public void selectNextEnemy(ActionEvent event) {

		int index = cboxEnemy.getSelectionModel().getSelectedIndex();
		cboxEnemy.getSelectionModel().select(index + 1);

	}

	public void openCodeSectionEnemyAI(int idx) {

		try {

			if (selectedEnemyAI == null) {
				txtAreaCode.setText("");
				return;
			}

			List<List<Opcode>> sections = selectedEnemyAI.getSections();
			List<Opcode> script = sections.get(idx);

			if (script == null) {
				txtAreaCode.setText("");
				return;
			}

			String stringCode = AIScript.format(script);
			txtAreaCode.setText(stringCode);

		} catch (Exception e) {
			showExceptionDialog("An error occurred while opening the section code.", e);
		}

	}

	@FXML
	public void applyChanges(ActionEvent event) {

		try {

			List<List<Opcode>> sections = selectedEnemyAI.getSections();

			String text = txtAreaCode.getText();
			List<Opcode> opcodes = AIScript.parse(text);
			int indexsCode = listvCodeSection.getSelectionModel().getSelectedIndex();
			int indexEnemy = cboxEnemy.getSelectionModel().getSelectedIndex();

			sections.set(indexsCode, opcodes);

			String enemyName = txtEnemyName.getText();
			if (enemyName.length() > 31)
				enemyName = enemyName.substring(0, 31);

			selectedScene.getEnemys()[indexEnemy].setName(enemyName);

			openCodeSectionEnemyAI(indexsCode);

			tablevScene.refresh();

		} catch (Exception e) {
			showExceptionDialog("An error occurred while saving changes!", e);
		}

	}

	public void selectEnemyAI(int idxEnemy) {

		txtEnemyName.setText(selectedScene.getEnemys()[idxEnemy].getName());
		selectedEnemyAI = selectedScene.getEnemyAI()[idxEnemy];
		listvCodeSection.getSelectionModel().clearSelection();
		listvCodeSection.getSelectionModel().select(0);

	}

	public void selectScene() {
		selectedScene = tablevScene.getSelectionModel().getSelectedItem();
		cboxEnemy.getSelectionModel().clearSelection();
		cboxEnemy.getSelectionModel().select(0);
	}

	@FXML
	public void exitApplication(ActionEvent event) {
		Platform.exit();
	}

	public void showExceptionDialog(String message, Exception ex) {

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

}

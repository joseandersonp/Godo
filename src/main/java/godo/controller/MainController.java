package godo.controller;

import java.io.File;
import java.util.List;

import godo.model.MainModel;
import godo.scene.Attack;
import godo.scene.Scene;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class MainController {

	private MainModel model = MainModel.getInstance();
	private EnemyController enemyController;
	private AttackController attackController;

	private Scene selectedScene;

	@FXML
	private TabPane tabpMain;

	@FXML
	private Tab tabEnemy;

	@FXML
	private Tab tabAttack;

	@FXML
	private VBox vboxMain;

	@FXML
	private MenuItem menuiOpen;

	@FXML
	private MenuItem menuiSave;

	@FXML
	private MenuItem menuiSaveFileAs;
	
	@FXML
	private MenuItem menuImport;
	
	@FXML
	private MenuItem menuExport;
	
	@FXML
	private MenuItem menuiImportAllScenes;
	
	@FXML
	private MenuItem menuiExportAllScenes;

	@FXML
	private MenuItem menuiUpdadeKernel;

	@FXML
	private MenuItem menuiExit;

	@FXML
	private SplitPane splitpMain;

	@FXML
	private TextField txtSearchEnemy;

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
	public void initialize() {

		// tablevScene Cols
		colSceneID.setCellValueFactory(new PropertyValueFactory<Scene, Integer>("id"));
		colSceneEnemy1.setCellValueFactory(new PropertyValueFactory<Scene, String>("descStringEnemyName1"));
		colSceneEnemy2.setCellValueFactory(new PropertyValueFactory<Scene, String>("descStringEnemyName2"));
		colSceneEnemy3.setCellValueFactory(new PropertyValueFactory<Scene, String>("descStringEnemyName3"));

		tablevScene.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
			if (tablevScene.getSelectionModel().getSelectedItem() != null)
				selectScene();
		});
		

	}

	@FXML
	public void openFile(ActionEvent event) {

		File file = chooseOpenFile();
		if (file == null)
			return;
		
		Stage st = (Stage)vboxMain.getScene().getWindow();
		st.setTitle("GODO - " + file.getPath());
		
		try {

			model.openSceneFile(file);
			List<Scene> scenes = model.getScenes();
			ObservableList<Scene> olScenes = FXCollections.observableArrayList(scenes);

			tablevScene.setItems(olScenes);
			tablevScene.getSelectionModel().selectFirst();

			splitpMain.setDisable(false);
			menuiSave.setDisable(false);
			menuiSaveFileAs.setDisable(false);
			menuExport.setDisable(false);
			menuImport.setDisable(false);

		} catch (Exception e) {
			Dialog.showExceptionDialog("An error occurred while opening the scene file!", e);
		}
	}

	@FXML
	public void saveFile(ActionEvent event) {
		try {
			model.saveSceneFile(null);
		} catch (Exception e) {
			Dialog.showExceptionDialog("An error occurred while saving the scene file!", e);
		}
	}

	@FXML
	public void saveFileAs(ActionEvent event) {

		File file = chooseSaveFile();
		if (file == null)
			return;

		try {
			model.saveSceneFile(file);
			Stage st = (Stage)vboxMain.getScene().getWindow();
			st.setTitle("GODO - " + file.getPath());
		} catch (Exception e) {
			Dialog.showExceptionDialog("An error occurred while saving the scene file!", e);
		}
	}

	public File chooseExportAllScenes() {
		
		DirectoryChooser dirChooser = new DirectoryChooser();
		dirChooser.setInitialDirectory(new File("./"));
		dirChooser.setTitle("Export All Binary Scenes");
		return dirChooser.showDialog(vboxMain.getScene().getWindow());

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

	public void selectScene() {
		
		selectedScene = tablevScene.getSelectionModel().getSelectedItem();
		
		ObservableList<Attack> attacks = FXCollections.observableArrayList(selectedScene.getAttack());
		ListView<Attack> listvAttack = attackController.getListvAttack();
		listvAttack.setItems(attacks);
		listvAttack.getSelectionModel().clearSelection();
		listvAttack.getSelectionModel().selectFirst();
		
		enemyController.setScene(selectedScene);
		enemyController.getCboxEnemy().getSelectionModel().clearSelection();
		enemyController.getCboxEnemy().getSelectionModel().select(0);		
	
	}

	@FXML
	void exitApplication(ActionEvent event) {
		Platform.exit();
	}

	public void searchEnemy(ActionEvent event) {

		String text = txtSearchEnemy.getText().toLowerCase();
		int sidx = tablevScene.getSelectionModel().getSelectedIndex();
		for (int i = 0; i < 256; i++) {

			if (++sidx > 255) sidx = 0;

			Scene scene = tablevScene.getItems().get(sidx);
			if ((scene.getId() + "").contains(text) ||
					scene.getEnemyName1().toLowerCase().contains(text) || 
					scene.getEnemyName2().toLowerCase().contains(text) || 
					scene.getEnemyName3().toLowerCase().contains(text)) {
				tablevScene.getSelectionModel().select(scene);
				tablevScene.scrollTo(scene);
				break;
			}
			
		}

	}

	@FXML
	void showAboutWindow(ActionEvent event) {
		Dialog.showAboutDialog();
	}
	
	@FXML
	public void importAllScenes(ActionEvent event) {

	}
	
	@FXML
	public void exportAllScenes(ActionEvent event) {
		
		File sceneDir;
		if ((sceneDir = chooseExportAllScenes()) != null) {		
		
		try {
			model.saveAllScenes(sceneDir);
		} catch (Exception e) {
			Dialog.showExceptionDialog("An error occurred while saving the scene files!", e);
		}
		}
	}

	@FXML
	void updateKernelFile(ActionEvent event) {

	}

	public Tab getTabEnemy() {
		return tabEnemy;
	}

	public Tab getTabAttack() {
		return tabAttack;
	}

	public void setEnemyController(EnemyController enemyController) {
		this.enemyController = enemyController;
		this.enemyController.setMainController(this);
	}

	public void setAttackController(AttackController attackController) {
		this.attackController = attackController;
	}

	public TableView<Scene> getTablevScene() {
		return tablevScene;
	}

}

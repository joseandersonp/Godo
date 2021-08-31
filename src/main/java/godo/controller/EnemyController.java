package godo.controller;

import java.util.Arrays;
import java.util.List;

import godo.model.MainModel;
import godo.scene.AIScript;
import godo.scene.Attack;
import godo.scene.ElementRate;
import godo.scene.ElementType;
import godo.scene.Enemy;
import godo.scene.Item;
import godo.scene.Opcode;
import godo.scene.Scene;
import javafx.beans.binding.Bindings;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.converter.IntegerStringConverter;

public class EnemyController {

	private MainModel model = MainModel.getInstance();
	private MainController mainController;

	private Scene scene;
	private Enemy selectedEnemy;
	private AIScript selectedEnemyAI;

	@FXML
	private TextField txtEnemyName;

	@FXML
	private Button btnBackEnemy;

	@FXML
	private ChoiceBox<String> cboxEnemy;

	@FXML
	private Button btnNextEnemy;

	@FXML
	private ListView<String> listvCodeSection;

	@FXML
	private TextArea txtAreaCode;

	@FXML
	private Spinner<Integer> spnID;
	
	@FXML
	private Spinner<Integer> spnLevel;

	@FXML
	private Spinner<Integer> spnMP;

	@FXML
	private Spinner<Integer> spnHP;

	@FXML
	private Spinner<Integer> spnSpeed;

	@FXML
	private Spinner<Integer> spnLuck;

	@FXML
	private Spinner<Integer> spnEvade;

	@FXML
	private Spinner<Integer> spnStrength;

	@FXML
	private Spinner<Integer> spnDefense;

	@FXML
	private Spinner<Integer> spnMDefense;

	@FXML
	private Spinner<Integer> spnBDamage;

	@FXML
	private Spinner<Integer> spnMagic;

	@FXML
	private Spinner<Integer> spnGil;

	@FXML
	private Spinner<Integer> spnEXP;

	@FXML
	private Spinner<Integer> spnAP;

	@FXML
	private CheckBox ckbConfu;

	@FXML
	private CheckBox ckbDeathForce;

	@FXML
	private CheckBox ckbShield;

	@FXML
	private CheckBox ckbFrog;

	@FXML
	private CheckBox ckbDualDrain;

	@FXML
	private CheckBox ckbDual;

	@FXML
	private CheckBox ckbStop;

	@FXML
	private CheckBox ckbPoison;

	@FXML
	private CheckBox ckbPetrify;

	@FXML
	private CheckBox ckbSlowNumb;

	@FXML
	private CheckBox ckbResist;

	@FXML
	private CheckBox ckbSadness;

	@FXML
	private CheckBox ckbFury;

	@FXML
	private CheckBox ckbDSentence;

	@FXML
	private CheckBox ckbSmall;

	@FXML
	private CheckBox ckbNearDeath;

	@FXML
	private CheckBox ckbBerserk;

	@FXML
	private CheckBox ckbReflect;

	@FXML
	private CheckBox ckbSlow;

	@FXML
	private CheckBox ckbHaste;

	@FXML
	private CheckBox ckbRegen;

	@FXML
	private CheckBox ckbSleep;

	@FXML
	private CheckBox ckbManipulate;

	@FXML
	private CheckBox ckbBarrier;

	@FXML
	private CheckBox ckbSilence;

	@FXML
	private CheckBox ckbDeath;

	@FXML
	private CheckBox ckbMBarrier;

	@FXML
	private CheckBox ckbPeerless;

	@FXML
	private CheckBox ckbParalysis;

	@FXML
	private CheckBox ckbDarkness;

	@FXML
	private CheckBox ckbLuckyGirl;

	@FXML
	private CheckBox ckbImprisioned;

	@FXML
	private ComboBox<Item> cboxMorphItem;

	@FXML
	private ComboBox<Item> cboxItemDrop1;

	@FXML
	private ComboBox<Item> cboxItemDrop2;

	@FXML
	private ComboBox<Item> cboxItemDrop3;

	@FXML
	private ComboBox<Item> cboxItemDrop4;

	@FXML
	private Spinner<Integer> spnItemRate1;

	@FXML
	private Spinner<Integer> spnItemRate2;

	@FXML
	private Spinner<Integer> spnItemRate3;

	@FXML
	private Spinner<Integer> spnItemRate4;

	@FXML
	private CheckBox ckbSteal1;

	@FXML
	private CheckBox ckbSteal2;

	@FXML
	private CheckBox ckbSteal3;

	@FXML
	private CheckBox ckbSteal4;

	@FXML
	private ComboBox<ElementRate> cboxElementRate1;

	@FXML
	private ComboBox<ElementRate> cboxElementRate2;

	@FXML
	private ComboBox<ElementRate> cboxElementRate3;

	@FXML
	private ComboBox<ElementRate> cboxElementRate4;

	@FXML
	private ComboBox<ElementRate> cboxElementRate5;

	@FXML
	private ComboBox<ElementRate> cboxElementRate6;

	@FXML
	private ComboBox<ElementRate> cboxElementRate7;

	@FXML
	private ComboBox<ElementRate> cboxElementRate8;

	@FXML
	private ComboBox<ElementType> cboxElementType1;

	@FXML
	private ComboBox<ElementType> cboxElementType2;

	@FXML
	private ComboBox<ElementType> cboxElementType4;

	@FXML
	private ComboBox<ElementType> cboxElementType3;

	@FXML
	private ComboBox<ElementType> cboxElementType8;

	@FXML
	private ComboBox<ElementType> cboxElementType5;

	@FXML
	private ComboBox<ElementType> cboxElementType6;

	@FXML
	private ComboBox<ElementType> cboxElementType7;


	@FXML
	private Spinner<Integer> spnCam1;

	@FXML
	private Spinner<Integer> spnCam2;

	@FXML
	private Spinner<Integer> spnCam3;

	@FXML
	private Spinner<Integer> spnCam4;

	@FXML
	private Spinner<Integer> spnCam5;

	@FXML
	private Spinner<Integer> spnCam6;

	@FXML
	private Spinner<Integer> spnCam7;

	@FXML
	private Spinner<Integer> spnCam8;

	@FXML
	private Spinner<Integer> spnCam9;

	@FXML
	private Spinner<Integer> spnCam10;

	@FXML
	private Spinner<Integer> spnCam11;

	@FXML
	private Spinner<Integer> spnCam12;

	@FXML
	private Spinner<Integer> spnCam13;

	@FXML
	private Spinner<Integer> spnCam14;

	@FXML
	private Spinner<Integer> spnCam15;

	@FXML
	private Spinner<Integer> spnCam16;

	@FXML
	private Spinner<Integer> spnAnim1;

	@FXML
	private Spinner<Integer> spnAnim2;

	@FXML
	private Spinner<Integer> spnAnim3;

	@FXML
	private Spinner<Integer> spnAnim4;

	@FXML
	private Spinner<Integer> spnAnim5;

	@FXML
	private Spinner<Integer> spnAnim6;

	@FXML
	private Spinner<Integer> spnAnim7;

	@FXML
	private Spinner<Integer> spnAnim8;

	@FXML
	private Spinner<Integer> spnAnim9;

	@FXML
	private Spinner<Integer> spnAnim10;

	@FXML
	private Spinner<Integer> spnAnim11;

	@FXML
	private Spinner<Integer> spnAnim12;

	@FXML
	private Spinner<Integer> spnAnim13;

	@FXML
	private Spinner<Integer> spnAnim14;

	@FXML
	private Spinner<Integer> spnAnim15;

	@FXML
	private Spinner<Integer> spnAnim16;
	
	@FXML
    private ComboBox<Attack> cboxAttack1;
	
	@FXML
    private ComboBox<Attack> cboxAttack2;
	
	@FXML
    private ComboBox<Attack> cboxAttack3;
	

	@FXML
    private ComboBox<Attack> cboxAttack4;
	@FXML
    private ComboBox<Attack> cboxAttack5;
	@FXML
    private ComboBox<Attack> cboxAttack6;
	@FXML
    private ComboBox<Attack> cboxAttack7;
	@FXML
    private ComboBox<Attack> cboxAttack8;
	@FXML
    private ComboBox<Attack> cboxAttack9;
	@FXML
    private ComboBox<Attack> cboxAttack10;
	@FXML
    private ComboBox<Attack> cboxAttack11;
	@FXML
    private ComboBox<Attack> cboxAttack12;
	@FXML
    private ComboBox<Attack> cboxAttack13;
	@FXML
    private ComboBox<Attack> cboxAttack14;
	@FXML
    private ComboBox<Attack> cboxAttack15;
	@FXML
    private ComboBox<Attack> cboxAttack16;
	
	@FXML	
	private ComboBox<Attack> cboxManipAttk1;
	@FXML	
	private ComboBox<Attack> cboxManipAttk2;
	@FXML	
	private ComboBox<Attack> cboxManipAttk3;
	
	private List<CheckBox> ckbStatuses;

	private List<Spinner<Integer>> spnAnimations;
	private List<Spinner<Integer>> spnCameras;
	private List<ComboBox<ElementRate>> cboxElementRates;
	private List<ComboBox<ElementType>> cboxElementTypes;
	private List<ComboBox<Attack>> cboxAttacks;
	

	@FXML
	private void initialize() {
		
		ckbStatuses = Arrays.asList(
				ckbDeath,     ckbNearDeath, ckbSleep,    ckbPoison,    ckbSadness,    ckbFury,
				ckbConfu,     ckbSilence,   ckbHaste,	 ckbSlow,      ckbStop,       ckbFrog,
				ckbSmall,     ckbSlowNumb,  ckbPetrify,  ckbRegen,     ckbBarrier,    ckbMBarrier,
				ckbReflect,   ckbDual,      ckbShield,   ckbDSentence, ckbManipulate, ckbBerserk,
				ckbPeerless,  ckbParalysis, ckbDarkness, ckbDualDrain, ckbDeathForce, ckbResist, 
				ckbLuckyGirl, ckbImprisioned);

		cboxElementRates = Arrays.asList(		
				cboxElementRate1, cboxElementRate2, cboxElementRate3, cboxElementRate4,
				cboxElementRate5, cboxElementRate6, cboxElementRate7, cboxElementRate8);

		cboxElementTypes = Arrays.asList(				
				cboxElementType1, cboxElementType2, cboxElementType3, cboxElementType4,
				cboxElementType5, cboxElementType6, cboxElementType7, cboxElementType8);

		spnCameras = Arrays.asList(
				spnCam1, spnCam2,  spnCam3,  spnCam4,  spnCam5,  spnCam6,  spnCam7,  spnCam8,
				spnCam9, spnCam10, spnCam11, spnCam12, spnCam13, spnCam14, spnCam15, spnCam16);

		spnAnimations = Arrays.asList(
				spnAnim1, spnAnim2,  spnAnim3,  spnAnim4,  spnAnim5,  spnAnim6,  spnAnim7,  spnAnim8, 
				spnAnim9, spnAnim10, spnAnim11, spnAnim12, spnAnim13, spnAnim14, spnAnim15, spnAnim16);
		
		cboxAttacks = Arrays.asList(
				cboxAttack1, cboxAttack2,  cboxAttack3,  cboxAttack4,  cboxAttack5,  cboxAttack6,  cboxAttack7,  cboxAttack8, 
				cboxAttack9, cboxAttack10, cboxAttack11, cboxAttack12, cboxAttack13, cboxAttack14, cboxAttack15, cboxAttack16);

		// Listeners		
		txtEnemyName.textProperty().addListener((obs, newv, oldv) ->{
			mainController.getTablevScene().refresh();
		});

		listvCodeSection.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.intValue() != -1)
				openCodeSectionEnemyAI(newValue.intValue());

		});

		cboxEnemy.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.intValue() != -1) {
				selectEnemy(newValue.intValue());
				btnNextEnemy.setDisable(newValue.intValue() == 2);
				btnBackEnemy.setDisable(newValue.intValue() == 0);
			}
		});

		ChangeListener<? super String> numberChangeListener = (observable, oldValue, newValue) -> {
			newValue = newValue.replaceAll("[^0-9]", "");
			((StringProperty) observable).setValue(newValue);
		};

		spnID.getEditor().textProperty().addListener(numberChangeListener);
		spnLevel.getEditor().textProperty().addListener(numberChangeListener);
		spnHP.getEditor().textProperty().addListener(numberChangeListener);
		spnMP.getEditor().textProperty().addListener(numberChangeListener);

		spnSpeed.getEditor().textProperty().addListener(numberChangeListener);
		spnLuck.getEditor().textProperty().addListener(numberChangeListener);
		spnEvade.getEditor().textProperty().addListener(numberChangeListener);
		spnStrength.getEditor().textProperty().addListener(numberChangeListener);
		spnDefense.getEditor().textProperty().addListener(numberChangeListener);
		spnMagic.getEditor().textProperty().addListener(numberChangeListener);
		spnMDefense.getEditor().textProperty().addListener(numberChangeListener);
		spnBDamage.getEditor().textProperty().addListener(numberChangeListener);

		spnAP.getEditor().textProperty().addListener(numberChangeListener);
		spnEXP.getEditor().textProperty().addListener(numberChangeListener);
		spnGil.getEditor().textProperty().addListener(numberChangeListener);

		spnItemRate1.getEditor().textProperty().addListener(numberChangeListener);
		spnItemRate2.getEditor().textProperty().addListener(numberChangeListener);
		spnItemRate3.getEditor().textProperty().addListener(numberChangeListener);
		spnItemRate4.getEditor().textProperty().addListener(numberChangeListener);

		cboxItemDrop1.getSelectionModel().selectedItemProperty().addListener((observable,  oldValue,  newValue) -> {
			spnItemRate1.setDisable(newValue.equals(Item.NOTHING));
			ckbSteal1.setDisable(newValue.equals(Item.NOTHING));
		});

		cboxItemDrop2.getSelectionModel().selectedItemProperty().addListener((observable,  oldValue,  newValue) -> {
			spnItemRate2.setDisable(newValue.equals(Item.NOTHING));	
			ckbSteal2.setDisable(newValue.equals(Item.NOTHING));
		});	

		cboxItemDrop3.getSelectionModel().selectedItemProperty().addListener((observable,  oldValue,  newValue) -> {
			spnItemRate3.setDisable(newValue.equals(Item.NOTHING));	
			ckbSteal3.setDisable(newValue.equals(Item.NOTHING));
		});	

		cboxItemDrop4.getSelectionModel().selectedItemProperty().addListener((observable,  oldValue,  newValue) -> {
			spnItemRate4.setDisable(newValue.equals(Item.NOTHING));	
			ckbSteal4.setDisable(newValue.equals(Item.NOTHING));
		});

		cboxElementType1.getSelectionModel().selectedItemProperty().addListener((observable,  oldValue,  newValue) -> {
			cboxElementRate1.setDisable(newValue.equals(ElementType.NOTHING));
		});
		cboxElementType2.getSelectionModel().selectedItemProperty().addListener((observable,  oldValue,  newValue) -> {
			cboxElementRate2.setDisable(newValue.equals(ElementType.NOTHING));
		});
		cboxElementType3.getSelectionModel().selectedItemProperty().addListener((observable,  oldValue,  newValue) -> {
			cboxElementRate3.setDisable(newValue.equals(ElementType.NOTHING));
		});
		cboxElementType4.getSelectionModel().selectedItemProperty().addListener((observable,  oldValue,  newValue) -> {
			cboxElementRate4.setDisable(newValue.equals(ElementType.NOTHING));
		});		
		cboxElementType5.getSelectionModel().selectedItemProperty().addListener((observable,  oldValue,  newValue) -> {
			cboxElementRate5.setDisable(newValue.equals(ElementType.NOTHING));
		});		
		cboxElementType6.getSelectionModel().selectedItemProperty().addListener((observable,  oldValue,  newValue) -> {
			cboxElementRate6.setDisable(newValue.equals(ElementType.NOTHING));
		});		
		cboxElementType7.getSelectionModel().selectedItemProperty().addListener((observable,  oldValue,  newValue) -> {
			cboxElementRate7.setDisable(newValue.equals(ElementType.NOTHING));
		});
		cboxElementType8.getSelectionModel().selectedItemProperty().addListener((observable,  oldValue,  newValue) -> {
			cboxElementRate8.setDisable(newValue.equals(ElementType.NOTHING));
		});
		

		// init lists
		listvCodeSection.getItems().addAll(model.listCodeSectionNames());

		cboxEnemy.getItems().add("1");
		cboxEnemy.getItems().add("2");
		cboxEnemy.getItems().add("3");

		// init spinners
		spnID.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 65535, 0));
		spnLevel.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 255, 0));
		spnHP.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999999999, 0));
		spnMP.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 65535, 0));

		spnSpeed.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 255, 0));
		spnLuck.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 255, 0));
		spnEvade.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 255, 0));
		spnStrength.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 255, 0));
		spnDefense.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 255, 0));
		spnMagic.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 255, 0));
		spnMDefense.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 255, 0));
		spnBDamage.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 255, 0));

		spnGil.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999999999, 0));
		spnEXP.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999999999, 0));
		spnAP.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999999999, 0));

		spnItemRate1.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 255, 0));
		spnItemRate2.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 255, 0));
		spnItemRate3.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 255, 0));
		spnItemRate4.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 255, 0));

		cboxElementType1.setItems(FXCollections.observableArrayList(model.listElementType()));
		cboxElementType2.setItems(FXCollections.observableArrayList(model.listElementType()));
		cboxElementType3.setItems(FXCollections.observableArrayList(model.listElementType()));
		cboxElementType4.setItems(FXCollections.observableArrayList(model.listElementType()));
		cboxElementType5.setItems(FXCollections.observableArrayList(model.listElementType()));
		cboxElementType6.setItems(FXCollections.observableArrayList(model.listElementType()));
		cboxElementType7.setItems(FXCollections.observableArrayList(model.listElementType()));
		cboxElementType8.setItems(FXCollections.observableArrayList(model.listElementType()));

		cboxElementRate1.setItems(FXCollections.observableArrayList(model.listElementRate()));
		cboxElementRate2.setItems(FXCollections.observableArrayList(model.listElementRate()));
		cboxElementRate3.setItems(FXCollections.observableArrayList(model.listElementRate()));
		cboxElementRate4.setItems(FXCollections.observableArrayList(model.listElementRate()));
		cboxElementRate5.setItems(FXCollections.observableArrayList(model.listElementRate()));
		cboxElementRate6.setItems(FXCollections.observableArrayList(model.listElementRate()));
		cboxElementRate7.setItems(FXCollections.observableArrayList(model.listElementRate()));
		cboxElementRate8.setItems(FXCollections.observableArrayList(model.listElementRate()));

		cboxItemDrop1.setItems(FXCollections.observableArrayList(model.listItem()));
		cboxItemDrop2.setItems(FXCollections.observableArrayList(model.listItem()));
		cboxItemDrop3.setItems(FXCollections.observableArrayList(model.listItem()));
		cboxItemDrop4.setItems(FXCollections.observableArrayList(model.listItem()));

		cboxMorphItem.setItems(FXCollections.observableArrayList(model.listItem()));
		
		
		for (Spinner<Integer>  spnAnim: spnAnimations) {
			spnAnim.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 255, 0));	
			spnAnim.getEditor().textProperty().addListener(numberChangeListener);
		}
		
		for (Spinner<Integer>  spnCam: spnCameras) {
			spnCam.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 255, 0));	
			spnCam.getEditor().textProperty().addListener(numberChangeListener);
		}
		
		
		
	}

	private void selectEnemy(int idx) {

		closeEnemy();

		selectedEnemy = scene.getEnemys()[idx];
		selectedEnemyAI = scene.getEnemyAI()[idx];

		listvCodeSection.getSelectionModel().clearSelection();
		listvCodeSection.getSelectionModel().select(0);

		openEnemy();

	}

	private void openEnemy() {

		Bindings.bindBidirectional(txtEnemyName.textProperty(), selectedEnemy.nameProperty());	
		
		Bindings.bindBidirectional(spnID.getEditor().textProperty(), selectedEnemy.idProperty(), new IntegerStringConverter());
	
		Bindings.bindBidirectional(spnLevel.getEditor().textProperty(), selectedEnemy.levelProperty(), new IntegerStringConverter());
		Bindings.bindBidirectional(spnHP.getEditor().textProperty(), selectedEnemy.hpProperty(), new IntegerStringConverter());
		Bindings.bindBidirectional(spnMP.getEditor().textProperty(), selectedEnemy.mpProperty(), new IntegerStringConverter());

		Bindings.bindBidirectional(spnSpeed.getEditor().textProperty(), selectedEnemy.speedProperty(), new IntegerStringConverter());
		Bindings.bindBidirectional(spnLuck.getEditor().textProperty(), selectedEnemy.luckProperty(), new IntegerStringConverter());
		Bindings.bindBidirectional(spnEvade.getEditor().textProperty(), selectedEnemy.evadeProperty(), new IntegerStringConverter());
		Bindings.bindBidirectional(spnStrength.getEditor().textProperty(), selectedEnemy.strengthProperty(), new IntegerStringConverter());

		Bindings.bindBidirectional(spnDefense.getEditor().textProperty(), selectedEnemy.defenseProperty(), new IntegerStringConverter());
		Bindings.bindBidirectional(spnMagic.getEditor().textProperty(), selectedEnemy.magicProperty(), new IntegerStringConverter());
		Bindings.bindBidirectional(spnMDefense.getEditor().textProperty(), selectedEnemy.magicDefenceProperty(), new IntegerStringConverter());
		Bindings.bindBidirectional(spnBDamage.getEditor().textProperty(), selectedEnemy.backDamageMultiplierProperty(), new IntegerStringConverter());

		Bindings.bindBidirectional(spnEXP.getEditor().textProperty(), selectedEnemy.expReceivedProperty(), new IntegerStringConverter());
		Bindings.bindBidirectional(spnAP.getEditor().textProperty(), selectedEnemy.apReceivedProperty(), new IntegerStringConverter());
		Bindings.bindBidirectional(spnGil.getEditor().textProperty(), selectedEnemy.gilReceivedProperty(), new IntegerStringConverter());

		for (int i = 0; i < ckbStatuses.size(); i++) {
			Bindings.bindBidirectional(ckbStatuses.get(i).selectedProperty(), selectedEnemy.getStatuses().get(i));
		}	

		for (int i = 0; i < cboxElementRates.size(); i++) {
			Bindings.bindBidirectional(cboxElementRates.get(i).valueProperty(), selectedEnemy.getElementRates().get(i));
		}

		for (int i = 0; i < cboxElementTypes.size(); i++) {
			Bindings.bindBidirectional(cboxElementTypes.get(i).valueProperty(), selectedEnemy.getElementTypes().get(i));
		}

		Bindings.bindBidirectional(cboxMorphItem.valueProperty(), selectedEnemy.morphItemProperty());

		Bindings.bindBidirectional(cboxItemDrop1.valueProperty(), selectedEnemy.getItemDropSteals().get(0));
		Bindings.bindBidirectional(cboxItemDrop2.valueProperty(), selectedEnemy.getItemDropSteals().get(1));
		Bindings.bindBidirectional(cboxItemDrop3.valueProperty(), selectedEnemy.getItemDropSteals().get(2));
		Bindings.bindBidirectional(cboxItemDrop4.valueProperty(), selectedEnemy.getItemDropSteals().get(3));

		Bindings.bindBidirectional(spnItemRate1.getEditor().textProperty(), selectedEnemy.getDropStealRates().get(0), new IntegerStringConverter());
		Bindings.bindBidirectional(spnItemRate2.getEditor().textProperty(), selectedEnemy.getDropStealRates().get(1), new IntegerStringConverter());
		Bindings.bindBidirectional(spnItemRate3.getEditor().textProperty(), selectedEnemy.getDropStealRates().get(2), new IntegerStringConverter());
		Bindings.bindBidirectional(spnItemRate4.getEditor().textProperty(), selectedEnemy.getDropStealRates().get(3), new IntegerStringConverter());

		Bindings.bindBidirectional(ckbSteal1.selectedProperty(), selectedEnemy.getDropSteals().get(0));
		Bindings.bindBidirectional(ckbSteal2.selectedProperty(), selectedEnemy.getDropSteals().get(1));
		Bindings.bindBidirectional(ckbSteal3.selectedProperty(), selectedEnemy.getDropSteals().get(2));
		Bindings.bindBidirectional(ckbSteal4.selectedProperty(), selectedEnemy.getDropSteals().get(3));

		for (int i = 0; i < cboxAttacks.size(); i++) {
			Bindings.bindBidirectional(spnAnimations.get(i).getEditor().textProperty(), selectedEnemy.getActionAnimationIndex().get(i), new IntegerStringConverter());
			Bindings.bindBidirectional(spnCameras.get(i).getEditor().textProperty(), selectedEnemy.getEnemyAttackCMId().get(i), new IntegerStringConverter());
			Bindings.bindBidirectional(cboxAttacks.get(i).valueProperty(), selectedEnemy.getAttacks().get(i));			
		}
		
		Bindings.bindBidirectional(cboxManipAttk1.valueProperty(), selectedEnemy.getManipAttacks().get(0));		
		Bindings.bindBidirectional(cboxManipAttk2.valueProperty(), selectedEnemy.getManipAttacks().get(1));		
		Bindings.bindBidirectional(cboxManipAttk3.valueProperty(), selectedEnemy.getManipAttacks().get(2));		
	
	}

	private void closeEnemy() {

		if (selectedEnemy == null)
			return;

		Bindings.unbindBidirectional(txtEnemyName.textProperty(), selectedEnemy.nameProperty());
		Bindings.unbindBidirectional(spnID.getEditor().textProperty(), selectedEnemy.idProperty());
		
		Bindings.unbindBidirectional(spnLevel.getEditor().textProperty(), selectedEnemy.levelProperty());
		Bindings.unbindBidirectional(spnHP.getEditor().textProperty(), selectedEnemy.hpProperty());
		Bindings.unbindBidirectional(spnMP.getEditor().textProperty(), selectedEnemy.mpProperty());

		Bindings.unbindBidirectional(spnSpeed.getEditor().textProperty(), selectedEnemy.speedProperty());
		Bindings.unbindBidirectional(spnLuck.getEditor().textProperty(), selectedEnemy.luckProperty());
		Bindings.unbindBidirectional(spnEvade.getEditor().textProperty(), selectedEnemy.evadeProperty());
		Bindings.unbindBidirectional(spnStrength.getEditor().textProperty(), selectedEnemy.strengthProperty());

		Bindings.unbindBidirectional(spnDefense.getEditor().textProperty(), selectedEnemy.defenseProperty());
		Bindings.unbindBidirectional(spnMagic.getEditor().textProperty(), selectedEnemy.magicProperty());
		Bindings.unbindBidirectional(spnMDefense.getEditor().textProperty(), selectedEnemy.magicDefenceProperty());
		Bindings.unbindBidirectional(spnBDamage.getEditor().textProperty(), selectedEnemy.backDamageMultiplierProperty());

		Bindings.unbindBidirectional(spnEXP.getEditor().textProperty(), selectedEnemy.expReceivedProperty());
		Bindings.unbindBidirectional(spnAP.getEditor().textProperty(), selectedEnemy.apReceivedProperty());
		Bindings.unbindBidirectional(spnGil.getEditor().textProperty(), selectedEnemy.gilReceivedProperty());

		for (int i = 0; i < ckbStatuses.size(); i++) {
			Bindings.unbindBidirectional(ckbStatuses.get(i).selectedProperty(), selectedEnemy.getStatuses().get(i));
		}

		for (int i = 0; i < cboxElementRates.size(); i++) {
			Bindings.unbindBidirectional(cboxElementRates.get(i).valueProperty(), selectedEnemy.getElementRates().get(i));
		}

		for (int i = 0; i < cboxElementTypes.size(); i++) {
			Bindings.unbindBidirectional(cboxElementTypes.get(i).valueProperty(), selectedEnemy.getElementTypes().get(i));
		}

		Bindings.unbindBidirectional(cboxMorphItem.valueProperty(), selectedEnemy.morphItemProperty());

		Bindings.unbindBidirectional(cboxItemDrop1.valueProperty(), selectedEnemy.getItemDropSteals().get(0));
		Bindings.unbindBidirectional(cboxItemDrop2.valueProperty(), selectedEnemy.getItemDropSteals().get(1));
		Bindings.unbindBidirectional(cboxItemDrop3.valueProperty(), selectedEnemy.getItemDropSteals().get(2));
		Bindings.unbindBidirectional(cboxItemDrop4.valueProperty(), selectedEnemy.getItemDropSteals().get(3));

		Bindings.unbindBidirectional(spnItemRate1.getEditor().textProperty(), selectedEnemy.getDropStealRates().get(0));
		Bindings.unbindBidirectional(spnItemRate2.getEditor().textProperty(), selectedEnemy.getDropStealRates().get(1));
		Bindings.unbindBidirectional(spnItemRate3.getEditor().textProperty(), selectedEnemy.getDropStealRates().get(2));
		Bindings.unbindBidirectional(spnItemRate4.getEditor().textProperty(), selectedEnemy.getDropStealRates().get(3));

		Bindings.unbindBidirectional(ckbSteal1.selectedProperty(), selectedEnemy.getDropSteals().get(0));
		Bindings.unbindBidirectional(ckbSteal2.selectedProperty(), selectedEnemy.getDropSteals().get(1));
		Bindings.unbindBidirectional(ckbSteal3.selectedProperty(), selectedEnemy.getDropSteals().get(2));
		Bindings.unbindBidirectional(ckbSteal4.selectedProperty(), selectedEnemy.getDropSteals().get(3));

		for (int i = 0; i < cboxAttacks.size(); i++) {
			Bindings.unbindBidirectional(spnAnimations.get(i).getEditor().textProperty(), selectedEnemy.getActionAnimationIndex().get(i));
			Bindings.unbindBidirectional(spnCameras.get(i).getEditor().textProperty(), selectedEnemy.getEnemyAttackCMId().get(i));
			Bindings.unbindBidirectional(cboxAttacks.get(i).valueProperty(), selectedEnemy.getAttacks().get(i));				
		}
		
		Bindings.unbindBidirectional(cboxManipAttk1.valueProperty(), selectedEnemy.getManipAttacks().get(0));		
		Bindings.unbindBidirectional(cboxManipAttk2.valueProperty(), selectedEnemy.getManipAttacks().get(1));		
		Bindings.unbindBidirectional(cboxManipAttk3.valueProperty(), selectedEnemy.getManipAttacks().get(2));	
	}

	private void openCodeSectionEnemyAI(int idx) {

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
			Dialog.showExceptionDialog("An error occurred while opening the section code.", e);
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

			scene.getEnemys()[indexEnemy].setName(enemyName);

			openCodeSectionEnemyAI(indexsCode);

		} catch (Exception e) {
			Dialog.showExceptionDialog("An error occurred while saving changes!", e);
		}
	}

	@FXML
	private void selectBackEnemy(ActionEvent event) {
		int index = cboxEnemy.getSelectionModel().getSelectedIndex();
		cboxEnemy.getSelectionModel().select(index - 1);
	}

	@FXML
	private void selectNextEnemy(ActionEvent event) {
		int index = cboxEnemy.getSelectionModel().getSelectedIndex();
		cboxEnemy.getSelectionModel().select(index + 1);
	}

	public ChoiceBox<String> getCboxEnemy() {
		return cboxEnemy;
	}

	public TextField getTxtEnemyName() {
		return txtEnemyName;
	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}

	public void setScene(Scene scene) {		
		this.scene = scene;
		closeEnemy();
		for (ComboBox<Attack> cboxAttack : cboxAttacks) {
			cboxAttack.setItems(FXCollections.observableArrayList(scene.getAttack()));
		}
		cboxManipAttk1.setItems(FXCollections.observableArrayList(scene.getAttack()));
		cboxManipAttk2.setItems(FXCollections.observableArrayList(scene.getAttack()));
		cboxManipAttk3.setItems(FXCollections.observableArrayList(scene.getAttack()));
		
	}
}

package godo.controller;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import godo.model.MainModel;
import godo.scene.AdditionalEffect;
import godo.scene.Attack;
import godo.scene.DamageConsideration;
import godo.scene.DamageFormula;
import javafx.beans.binding.Bindings;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.util.converter.IntegerStringConverter;

public class AttackController implements Initializable {

	private MainModel model = MainModel.getInstance();
	
	private Attack selectedAttack;

	@FXML
	private ListView<Attack> listvAttack;

	@FXML
	private TextField txtName;

	@FXML
	private Spinner<Integer> spnID;

	@FXML
	private Spinner<Integer> spnAttackPerc;

	@FXML
	private Spinner<Integer> spnStrengh;

	@FXML
	private Spinner<Integer> spnMPCosts;

	@FXML
	private Spinner<Integer> spnAnimationID;

	@FXML
	private Spinner<Integer> spnImpactAnimation;

	@FXML
	private Spinner<Integer> spnTargetAnimation;

	@FXML
	private Spinner<Integer> spnImpactSound;

	@FXML
	private Spinner<Integer> spnCameraSingle;

	@FXML
	private Spinner<Integer> spnCameraMultiple;

	@FXML
	private CheckBox ckbToggleMultiple;

	@FXML
	private CheckBox ckbOneRowOnly;

	@FXML
	private CheckBox ckbStartOnEnemies;

	@FXML
	private CheckBox ckbShortRange;

	@FXML
	private CheckBox ckbStartAsMultiple;

	@FXML
	private CheckBox ckbAllRows;

	@FXML
	private CheckBox ckbRandom;

	@FXML
	private CheckBox ckbEnableSelection;

	@FXML
	private CheckBox ckbElemBolt;

	@FXML
	private CheckBox ckbElemPoison;

	@FXML
	private CheckBox ckbElemHoly;

	@FXML
	private CheckBox ckbElemPunch;

	@FXML
	private CheckBox ckbElemIce;

	@FXML
	private CheckBox ckbElemHealth;

	@FXML
	private CheckBox ckbElemCut;

	@FXML
	private CheckBox ckbElemGravity;

	@FXML
	private CheckBox ckbElemWater;

	@FXML
	private CheckBox ckbElemFire;

	@FXML
	private CheckBox ckbElemShoot;

	@FXML
	private CheckBox ckbElemShout;

	@FXML
	private CheckBox ckbElemEarth;

	@FXML
	private CheckBox ckbElemWind;

	@FXML
	private CheckBox ckbElemHit;

	@FXML
	private CheckBox ckbElemHidden;

	@FXML
	private CheckBox ckbPhysical;

	@FXML
	private CheckBox ckbDrainDmg;

	@FXML
	private CheckBox ckbMissIfNotDead;

	@FXML
	private CheckBox ckbUnused1;

	@FXML
	private CheckBox ckbDummy1;

	@FXML
	private CheckBox ckbRefletable;

	@FXML
	private CheckBox ckbDefIgnoring;

	@FXML
	private CheckBox ckbDrainHPMP;

	@FXML
	private CheckBox ckbDummy2;

	@FXML
	private CheckBox ckbAffectMP;

	@FXML
	private CheckBox ckbCrtiticalHit;

	@FXML
	private CheckBox ckbUnused2;

	@FXML
	private CheckBox ckbUnused3;

	@FXML
	private CheckBox ckbIgnoreStatDef;

	@FXML
	private CheckBox ckbNoRetarget;

	@FXML
	private CheckBox ckbUnused4;

	@FXML
	private CheckBox ckbConfusion;

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
	private CheckBox ckbImprisoned;

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
	private CheckBox ckbPoison;

	@FXML
	private ToggleButton toggleTStatus;
	
	@FXML
	private ToggleButton toggleRStatus;
	
	@FXML
	private Spinner<Integer> spnChance; 

	@FXML
	private ComboBox<DamageConsideration> cboxDamageConsideration;

	@FXML
	private ComboBox<DamageFormula> cboxDamageFormula;

	@FXML
	private Spinner<Integer> spnEffectModifier;
	
	@FXML
	private ComboBox<AdditionalEffect> cboxAdditionalEffect;

	private List<CheckBox> ckbStatuses;
	private List<CheckBox> ckbElements;
	private List<CheckBox> ckbSpecials;
	private List<CheckBox> ckbTargets;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		txtName.textProperty().addListener((ob, newValue, oldValue) -> {
			listvAttack.refresh();	
		});
		
		cboxDamageConsideration.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
		if (newValue.intValue() >= 0)
			selectAccFunctionConsideration(newValue.intValue());
		});

		listvAttack.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.intValue() >= 0)
				selectAttack();
		});

		ChangeListener<? super String> nChgListener = (observable, oldValue, newValue) -> {
			newValue = newValue.replaceAll("[^0-9]", "");
			((StringProperty) observable).setValue(newValue);
		};

		
		spnID.getEditor().textProperty().addListener(nChgListener);
		spnAttackPerc.getEditor().textProperty().addListener(nChgListener);
		spnStrengh.getEditor().textProperty().addListener(nChgListener);
		spnMPCosts.getEditor().textProperty().addListener(nChgListener);
		spnAnimationID.getEditor().textProperty().addListener(nChgListener);
		spnImpactAnimation.getEditor().textProperty().addListener(nChgListener);
		spnTargetAnimation.getEditor().textProperty().addListener(nChgListener);
		spnImpactSound.getEditor().textProperty().addListener(nChgListener);
		spnCameraSingle.getEditor().textProperty().addListener(nChgListener);
		spnCameraMultiple.getEditor().textProperty().addListener(nChgListener);
		spnEffectModifier.getEditor().textProperty().addListener(nChgListener);

		spnChance.getEditor().textProperty().addListener(nChgListener);
		
		spnID.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 65535, 0));
		spnAttackPerc.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 255, 0));
		spnStrengh.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 255, 0));
		spnMPCosts.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 65535, 0));
		spnAnimationID.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 65535, 0));
		spnImpactAnimation.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 255, 0));
		spnTargetAnimation.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 255, 0));
		spnImpactSound.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 65535, 0));
		spnCameraSingle.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 65535, 0));
		spnCameraMultiple.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 65535, 0));
		spnEffectModifier.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 65535, 0));
		
		spnChance.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 63, 0));
		
		ToggleGroup tg= new ToggleGroup();
		toggleRStatus.setToggleGroup(tg);
		toggleTStatus.setToggleGroup(tg);

		ObservableList<DamageConsideration> accFuncNames = FXCollections.observableArrayList(model.listDamageConsiderations());
		cboxDamageConsideration.setItems(FXCollections.observableArrayList(accFuncNames));

		cboxAdditionalEffect.setItems(FXCollections.observableArrayList(model.listAdditionalEffects()));

		ckbStatuses = Arrays.asList(
				ckbDeath,     ckbNearDeath,  ckbSleep,     ckbPoison,
				ckbSadness,   ckbFury,       ckbConfusion, ckbSilence,
				ckbHaste,     ckbSlow,       ckbStop,	    ckbFrog, 
				ckbSmall,     ckbSlowNumb,   ckbPetrify,    ckbRegen,
				ckbBarrier,   ckbMBarrier,   ckbReflect,    ckbDual,
				ckbShield,     ckbDSentence, ckbManipulate,	ckbBerserk,	
				ckbPeerless,   ckbParalysis, ckbDarkness,	ckbDualDrain,
				ckbDeathForce, ckbResist,    ckbLuckyGirl,  ckbImprisoned);

		ckbElements = Arrays.asList(
				ckbElemFire,   ckbElemIce,     ckbElemBolt,  ckbElemEarth,
				ckbElemPoison, ckbElemGravity, ckbElemWater, ckbElemWind,
				ckbElemHoly,   ckbElemHealth,  ckbElemCut,   ckbElemHit,
				ckbElemPunch,  ckbElemShoot,   ckbElemShout, ckbElemHidden);

		ckbSpecials = Arrays.asList(
				ckbAffectMP,      ckbDummy1,       ckbPhysical,    ckbUnused1,
				ckbDrainDmg,      ckbDrainHPMP,    ckbDummy2,      ckbIgnoreStatDef,
				ckbMissIfNotDead, ckbRefletable,   ckbDefIgnoring, ckbNoRetarget,
				ckbUnused2,       ckbCrtiticalHit, ckbUnused3,     ckbUnused4);

		ckbTargets = Arrays.asList(
				ckbEnableSelection, ckbStartOnEnemies, ckbStartAsMultiple, ckbToggleMultiple,
				ckbOneRowOnly,      ckbShortRange,     ckbAllRows,         ckbRandom);

	}

	private void selectAccFunctionConsideration(int idx) {

		int selIdx = cboxDamageFormula.getSelectionModel().getSelectedIndex();

		List<DamageFormula> accFunctionFormulas;
		if ((idx >= 6 && idx <= 7)) {
			accFunctionFormulas = model.listDamageFormulas(1);
		} else if ((idx == 10)) {
			accFunctionFormulas = model.listDamageFormulas(2);
		} else
			accFunctionFormulas = model.listDamageFormulas(0);

		cboxDamageFormula.setItems(FXCollections.observableArrayList(accFunctionFormulas));

		if (selIdx < 0)
			selIdx = 15; // 0xff

		cboxDamageFormula.getSelectionModel().clearAndSelect(selIdx);

	}

	private void selectAttack() {
		closeAttack();
		selectedAttack = listvAttack.getSelectionModel().getSelectedItem();
		openAttack();

	}

	public void closeAttack() {

		if (selectedAttack == null)
			return;

		txtName.textProperty().unbindBidirectional(selectedAttack.nameProperty());
		Bindings.unbindBidirectional(spnID.getEditor().textProperty(), selectedAttack.idProperty());
		Bindings.unbindBidirectional(spnAttackPerc.getEditor().textProperty(), selectedAttack.rateProperty());
		Bindings.unbindBidirectional(spnStrengh.getEditor().textProperty(), selectedAttack.strengthAttackforDCProperty());
		Bindings.unbindBidirectional(spnMPCosts.getEditor().textProperty(), selectedAttack.castingCostProperty());
		Bindings.unbindBidirectional(spnAnimationID.getEditor().textProperty(), selectedAttack.attackEffectIdProperty());
		Bindings.unbindBidirectional(spnTargetAnimation.getEditor().textProperty(), selectedAttack.targetHurtActIndexProperty());
		Bindings.unbindBidirectional(spnImpactAnimation.getEditor().textProperty(), selectedAttack.impactEffectIdProperty());
		Bindings.unbindBidirectional(spnImpactSound.getEditor().textProperty(), selectedAttack.impactSoundProperty());
		Bindings.unbindBidirectional(spnCameraSingle.getEditor().textProperty(), selectedAttack.cameraMidSTargetProperty());
		Bindings.unbindBidirectional(spnCameraMultiple.getEditor().textProperty(), selectedAttack.cameraMidMTargetProperty());
		Bindings.unbindBidirectional(spnEffectModifier.getEditor().textProperty(), selectedAttack.effectModProperty());

		
		for (int i = 0; i < selectedAttack.getStatuses().size(); i++)
			Bindings.unbindBidirectional(ckbStatuses.get(i).selectedProperty(), selectedAttack.getStatuses().get(i));

		Bindings.unbindBidirectional(spnChance.getEditor().textProperty(), selectedAttack.statusChanceProperty());
		Bindings.unbindBidirectional(toggleTStatus.selectedProperty(), selectedAttack.toggleStatusProperty());
		Bindings.unbindBidirectional(toggleRStatus.selectedProperty(), selectedAttack.removeStatusProperty());
	
		
		for (int i = 0; i < selectedAttack.getElements().size(); i++)
			Bindings.unbindBidirectional(ckbElements.get(i).selectedProperty(), selectedAttack.getElements().get(i));

		for (int i = 0; i < selectedAttack.getSpecials().size() ; i++)
			Bindings.unbindBidirectional(ckbSpecials.get(i).selectedProperty(), selectedAttack.getSpecials().get(i));

		for (int i = 0; i < selectedAttack.getTargets().size(); i++)
			Bindings.unbindBidirectional(ckbTargets.get(i).selectedProperty(), selectedAttack.getTargets().get(i));

		Bindings.unbindBidirectional(cboxDamageConsideration.valueProperty(), selectedAttack.damageConsiderationProperty());
		Bindings.unbindBidirectional(cboxDamageFormula.valueProperty(), selectedAttack.damageDamageFormulaProperty());	
		Bindings.unbindBidirectional(cboxAdditionalEffect.valueProperty(), selectedAttack.additionalEffectProperty());	

	}

	public void openAttack() {

		txtName.textProperty().bindBidirectional(selectedAttack.nameProperty());

		Bindings.bindBidirectional(spnID.getEditor().textProperty(), selectedAttack.idProperty(), new IntegerStringConverter());
		Bindings.bindBidirectional(spnAttackPerc.getEditor().textProperty(), selectedAttack.rateProperty(), new IntegerStringConverter());
		Bindings.bindBidirectional(spnStrengh.getEditor().textProperty(), selectedAttack.strengthAttackforDCProperty(), new IntegerStringConverter());
		Bindings.bindBidirectional(spnMPCosts.getEditor().textProperty(), selectedAttack.castingCostProperty(), new IntegerStringConverter());

		Bindings.bindBidirectional(spnAnimationID.getEditor().textProperty(), selectedAttack.attackEffectIdProperty(), new IntegerStringConverter());
		Bindings.bindBidirectional(spnTargetAnimation.getEditor().textProperty(), selectedAttack.targetHurtActIndexProperty(), new IntegerStringConverter());
		Bindings.bindBidirectional(spnImpactAnimation.getEditor().textProperty(), selectedAttack.impactEffectIdProperty(), new IntegerStringConverter());

		Bindings.bindBidirectional(spnImpactSound.getEditor().textProperty(), selectedAttack.impactSoundProperty(), new IntegerStringConverter());
		Bindings.bindBidirectional(spnCameraSingle.getEditor().textProperty(), selectedAttack.cameraMidSTargetProperty(), new IntegerStringConverter());
		Bindings.bindBidirectional(spnCameraMultiple.getEditor().textProperty(), selectedAttack.cameraMidMTargetProperty(), new IntegerStringConverter());
		Bindings.bindBidirectional(spnEffectModifier.getEditor().textProperty(), selectedAttack.effectModProperty(), new IntegerStringConverter());

		
		for (int i = 0; i < selectedAttack.getStatuses().size(); i++)
			Bindings.bindBidirectional(ckbStatuses.get(i).selectedProperty(), selectedAttack.getStatuses().get(i));
		
		Bindings.bindBidirectional(spnChance.getEditor().textProperty(), selectedAttack.statusChanceProperty(), new IntegerStringConverter());
		Bindings.bindBidirectional(toggleTStatus.selectedProperty(), selectedAttack.toggleStatusProperty());
		Bindings.bindBidirectional(toggleRStatus.selectedProperty(), selectedAttack.removeStatusProperty());
			
		
		for (int i = 0; i < selectedAttack.getElements().size(); i++)
			Bindings.bindBidirectional(ckbElements.get(i).selectedProperty(), selectedAttack.getElements().get(i));

		for (int i = 0; i < selectedAttack.getSpecials().size() ; i++)
			Bindings.bindBidirectional(ckbSpecials.get(i).selectedProperty(), selectedAttack.getSpecials().get(i));

		for (int i = 0; i < selectedAttack.getTargets().size(); i++)
			Bindings.bindBidirectional(ckbTargets.get(i).selectedProperty(), selectedAttack.getTargets().get(i));

		Bindings.bindBidirectional(cboxDamageConsideration.valueProperty(), selectedAttack.damageConsiderationProperty());
		Bindings.bindBidirectional(cboxDamageFormula.valueProperty(), selectedAttack.damageDamageFormulaProperty());
		Bindings.bindBidirectional(cboxAdditionalEffect.valueProperty(), selectedAttack.additionalEffectProperty());	

	}
	
	public ListView<Attack> getListvAttack() {
		return listvAttack;
	}

	public Attack getSelectedAttack() {
		return selectedAttack;
	}

}

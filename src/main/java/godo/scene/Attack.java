package godo.scene;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Attack {

	private Property<Integer> id = new SimpleObjectProperty<>();

	private StringProperty name = new SimpleStringProperty();

	private Property<Integer> rate = new SimpleObjectProperty<>();
	private Property<Integer> impactEffectId = new SimpleObjectProperty<>();

	private Property<Integer> targetHurtActIndex = new SimpleObjectProperty<>();
	private Property<Integer> unknown = new SimpleObjectProperty<>();
	private Property<Integer> castingCost = new SimpleObjectProperty<>();
	private Property<Integer> impactSound = new SimpleObjectProperty<>();
	private Property<Integer> cameraMidSTarget = new SimpleObjectProperty<>();
	private Property<Integer> cameraMidMTarget = new SimpleObjectProperty<>();

	private Property<Integer> attackEffectId = new SimpleObjectProperty<>();

	private Property<Integer> strengthAttackforDC = new SimpleObjectProperty<>();
	private Property<Integer> conditionSubmenu = new SimpleObjectProperty<>();

	private Property<Boolean> toggleStatus = new SimpleObjectProperty<>();
	private Property<Boolean> removeStatus = new SimpleObjectProperty<>();
	private Property<Integer> statusChance = new SimpleObjectProperty<>();

	private Property<AdditionalEffect> addEffect = new SimpleObjectProperty<>();
	private Property<Integer> sEffectMod = new SimpleObjectProperty<>();

	private Property<DamageConsideration> damageConsideration = new SimpleObjectProperty<>();
	private Property<DamageFormula> damageFormula = new SimpleObjectProperty<>();

	private List<Property<Boolean>> statuses;
	private List<Property<Boolean>> elements;
	private List<Property<Boolean>> specials;
	private List<Property<Boolean>> targets;

	public Attack() {
	}

	public Attack(Integer id) {
		this.id = new SimpleObjectProperty<Integer>(id);
	}

	public Integer getId() {
		return id.getValue();
	}

	public void setId(Integer id) {
		this.id.setValue(id);
	}

	public String getName() {
		return name.getValue();
	}

	public void setName(String name) {
		this.name.setValue(name);
	}

	public Integer getRate() {
		return rate.getValue();
	}

	public void setRate(Integer rate) {
		this.rate.setValue(rate);
	}

	public Integer getImpactEffectId() {
		return impactEffectId.getValue();
	}

	public void setImpactEffectId(Integer impactEffectId) {
		this.impactEffectId.setValue(impactEffectId);
	}

	public Integer getTargetHurtActIndex() {
		return targetHurtActIndex.getValue();
	}

	public void setTargetHurtActIndex(Integer targetHurtActIndex) {
		this.targetHurtActIndex.setValue(targetHurtActIndex);
	}

	public Integer getUnknown() {
		return unknown.getValue();
	}

	public void setUnknown(Integer unknown) {
		this.unknown.setValue(unknown);
	}

	public Integer getCastingCost() {
		return castingCost.getValue();
	}

	public void setCastingCost(Integer castingCost) {
		this.castingCost.setValue(castingCost);
	}

	public Integer getImpactSound() {
		return impactSound.getValue();
	}

	public void setImpactSound(Integer impactSound) {
		this.impactSound.setValue(impactSound);
	}

	public Integer getCameraMidSTarget() {
		return cameraMidSTarget.getValue();
	}

	public void setCameraMidSTarget(Integer cameraMidSTarget) {
		this.cameraMidSTarget.setValue(cameraMidSTarget);
	}

	public Integer getCameraMidMTarget() {
		return cameraMidMTarget.getValue();
	}

	public void setCameraMidMTarget(Integer cameraMidMTarget) {
		this.cameraMidMTarget.setValue(cameraMidMTarget);
	}

	public int getTarget() {
		int target = 0;
		boolean md = true;
		int c = targets.size() - 1;
		while (c >= 0) {
			Property<Boolean> tg = targets.get(c);
			target = (target << 1) + (tg.getValue() == md ? 1 : 0);
			c--;
		}
		return target;
	}

	public void setTarget(Integer target) {
		targets = new ArrayList<Property<Boolean>>();
		Property<Boolean> el;
		int md = 1;
		for (int i = 0; i < 8; i++) {
			el = new SimpleObjectProperty<Boolean>((target >> i & 1) == md);
			targets.add(el);
		}
	}

	public Integer getAttackEffectId() {
		return attackEffectId.getValue();
	}

	public void setAttackEffectId(Integer attackEffectId) {
		this.attackEffectId.setValue(attackEffectId);
	}

	public int getDamageCalculation() {
		int code = damageConsideration.getValue().getCode();
		int code2 = damageFormula.getValue().getCode();
		return (code << 4) + code2;
	}

	public void setDamageCalculation(Integer damageCalculation) {
		damageConsideration.setValue(DamageConsideration.getByDmgCalculation(damageCalculation));
		damageFormula.setValue(DamageFormula.getByDmgCalculation(damageCalculation));
	}

	public Integer getStrengthAttackforDC() {
		return strengthAttackforDC.getValue();
	}

	public void setStrengthAttackforDC(Integer strengthAttackforDC) {
		this.strengthAttackforDC.setValue(strengthAttackforDC);
	}

	public int getConditionSubmenu() {
		return conditionSubmenu.getValue();
	}

	public void setConditionSubmenu(Integer conditionSubmenu) {
		this.conditionSubmenu.setValue(conditionSubmenu);
	}

	public Integer getStatusEffectChange() {

		int eec = ((toggleStatus.getValue() ? 1 : 0) << 7) + ((removeStatus.getValue() ? 1 : 0) << 6);

		eec += statusChance.getValue() == null ? 0 : statusChance.getValue();
		if (eec == 0)
			return null;

		return eec;
	}

	public void setStatusEffectChange(Integer eec) {
		toggleStatus = new SimpleObjectProperty<Boolean>(eec == null ? false : (eec >> 7) > 0);
		removeStatus = new SimpleObjectProperty<Boolean>(eec == null ? false : ((eec >> 6) & 1) > 0);
		statusChance = new SimpleObjectProperty<Integer>(eec == null ? null : eec & 63);
	}

	public int getAtckAddEffects() {
		return addEffect.getValue().getCode();
	}

	public void setAtckAddEffects(Integer atckAddEffects) {
		this.addEffect.setValue(AdditionalEffect.get(atckAddEffects));
	}

	public Integer getSEffectMod() {
		return sEffectMod.getValue();
	}

	public void setSEffectMod(Integer sEffectMod) {
		this.sEffectMod.setValue(sEffectMod);
	}

	public int getStatus() {

		int status = 0;
		boolean md = true;
		for (Property<Boolean> st : statuses) {
			status = (status << 1) + (st.getValue() == md ? 1 : 0);
		}
		return Integer.reverse(status);

	}

	public void setStatus(Integer status) {

		statuses = new ArrayList<Property<Boolean>>();
		Property<Boolean> st;
		int md = 1;
		for (int i = 0; i < 32; i++) {
			st = new SimpleObjectProperty<Boolean>((status >> i & 1) == md);
			statuses.add(st);
		}
	}

	public int getElement() {
		int element = 0;
		boolean md = true;
		int c = elements.size() - 1;
		while (c >= 0) {
			Property<Boolean> el = elements.get(c);
			element = (element << 1) + (el.getValue() == md ? 1 : 0);
			c--;
		}
		return element;
	}

	public void setElement(int element) {
		elements = new ArrayList<Property<Boolean>>();
		Property<Boolean> el;
		int md = 1;
		for (int i = 0; i < 16; i++) {
			el = new SimpleObjectProperty<Boolean>((element >> i & 1) == md);
			elements.add(el);
		}
	}

	public int getSpecial() {
		int special = 0;
		boolean md = false;
		int c = specials.size() - 1;
		while (c >= 0) {
			Property<Boolean> sp = specials.get(c);
			special = (special << 1) + (sp.getValue() == md ? 1 : 0);
			c--;
		}
		return special;
	}

	public void setSpecial(int special) {
		specials = new ArrayList<Property<Boolean>>();
		Property<Boolean> el;
		int md = 0;
		for (int i = 0; i < 16; i++) {
			el = new SimpleObjectProperty<Boolean>((special >> i & 1) == md);
			specials.add(el);
		}
	}

	public StringProperty nameProperty() {
		return name;
	}

	public Property<Integer> idProperty() {
		return id;
	}

	public Property<Integer> rateProperty() {
		return rate;
	}

	public Property<Integer> strengthAttackforDCProperty() {

		return strengthAttackforDC;
	}

	public Property<Integer> castingCostProperty() {

		return castingCost;
	}

	public Property<Integer> attackEffectIdProperty() {

		return attackEffectId;
	}

	public Property<Integer> targetHurtActIndexProperty() {

		return targetHurtActIndex;
	}

	public Property<Integer> impactEffectIdProperty() {

		return impactEffectId;
	}

	public Property<Integer> impactSoundProperty() {

		return impactSound;
	}

	public Property<Integer> cameraMidSTargetProperty() {

		return cameraMidSTarget;
	}

	public Property<Integer> cameraMidMTargetProperty() {

		return cameraMidMTarget;
	}

	public List<Property<Boolean>> getStatuses() {
		return statuses;
	}

	public List<Property<Boolean>> getElements() {
		return elements;
	}

	public List<Property<Boolean>> getSpecials() {
		return specials;
	}

	public List<Property<Boolean>> getTargets() {
		return targets;
	}

	public Property<DamageConsideration> damageConsiderationProperty() {
		return damageConsideration;
	}

	public Property<DamageFormula> damageDamageFormulaProperty() {
		return damageFormula;
	}

	public Property<AdditionalEffect> additionalEffectProperty() {
		return addEffect;
	}

	public Property<Integer> effectModProperty() {
		return sEffectMod;
	}

	public Property<Boolean> toggleStatusProperty() {
		return toggleStatus;
	}

	public Property<Boolean> removeStatusProperty() {
		return removeStatus;
	}

	public Property<Integer> statusChanceProperty() {
		return statusChance;
	}

	@Override
	public String toString() {
		return getName();
	}	

}

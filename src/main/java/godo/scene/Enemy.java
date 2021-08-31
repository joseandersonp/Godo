package godo.scene;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Enemy {

	private Property<Integer> id = new SimpleObjectProperty<Integer>();

	private StringProperty name = new SimpleStringProperty();

	private Property<Integer> level = new SimpleObjectProperty<Integer>();

	private Property<Integer> speed = new SimpleObjectProperty<Integer>();
	private Property<Integer> luck = new SimpleObjectProperty<Integer>();
	private Property<Integer> evade = new SimpleObjectProperty<Integer>();
	private Property<Integer> strength = new SimpleObjectProperty<Integer>();
	private Property<Integer> defense = new SimpleObjectProperty<Integer>();
	private Property<Integer> magic = new SimpleObjectProperty<Integer>();
	private Property<Integer> magicDefense = new SimpleObjectProperty<Integer>();

	private List<Property<ElementType>> elementTypes = new ArrayList<>();

	private List<Property<ElementRate>> elementRates = new ArrayList<>();

	private List<Property<Integer>> actionAnimationIndex = new ArrayList<>();

	private List<Property<Attack>> attacks = new ArrayList<Property<Attack>>();

	private List<Property<Integer>> enemyAttackCMId = new ArrayList<>();

	private List<Property<Integer>> dropStealRates = new ArrayList<>();
	private List<Property<Boolean>> dropSteal = new ArrayList<>();

	private List<Property<Item>> itemDropSteals = new ArrayList<>();

	private List<Property<Attack>> manipAttacks = new ArrayList<Property<Attack>>();

	private Property<Integer> unknown = new SimpleObjectProperty<Integer>();

	private Property<Integer> mp = new SimpleObjectProperty<Integer>();

	private Property<Integer> apReceived = new SimpleObjectProperty<Integer>();

	private Property<Item> morphItem = new SimpleObjectProperty<Item>();

	private Property<Integer> backDamageMultiplier = new SimpleObjectProperty<Integer>();

	private Property<Integer> align = new SimpleObjectProperty<Integer>();

	private Property<Integer> hp = new SimpleObjectProperty<Integer>();

	private Property<Integer> expReceived = new SimpleObjectProperty<Integer>();

	private Property<Integer> gilReceived = new SimpleObjectProperty<Integer>();

	private Property<Integer> unknown2 = new SimpleObjectProperty<Integer>();

	private List<Property<Boolean>> statuses;
	
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

	public Integer getLevel() {
		return level.getValue();
	}

	public void setLevel(Integer level) {
		this.level.setValue(level);
	}

	public Integer getSpeed() {
		return speed.getValue();
	}

	public void setSpeed(Integer speed) {
		this.speed.setValue(speed);
	}

	public Integer getLuck() {
		return luck.getValue();
	}

	public void setLuck(Integer luck) {
		this.luck.setValue(luck);
	}

	public Integer getEvade() {
		return evade.getValue();
	}

	public void setEvade(Integer evade) {
		this.evade.setValue(evade);
	}

	public Integer getStrength() {
		return strength.getValue();
	}

	public void setStrength(Integer strength) {
		this.strength.setValue(strength);
	}

	public Integer getDefense() {
		return defense.getValue();
	}

	public void setDefense(Integer defense) {
		this.defense.setValue(defense);
	}

	public Integer getMagic() {
		return magic.getValue();
	}

	public void setMagic(Integer magic) {
		this.magic.setValue(magic);
	}

	public Integer getMagicDefense() {
		return magicDefense.getValue();
	}

	public void setMagicDefense(Integer magicDefense) {
		this.magicDefense.setValue(magicDefense);
	}

	public List<Property<ElementType>> getElementTypes() {
		return elementTypes;
	}	

	public List<Property<ElementRate>> getElementRates() {
		return elementRates;
	}

	public List<Property<Integer>> getActionAnimationIndex() {
		return actionAnimationIndex;
	}

	public void setActionAnimationIndex(List<Property<Integer>> actionAnimationIndex) {
		this.actionAnimationIndex = actionAnimationIndex;
	}

	public List<Property<Attack>> getAttacks() {
		return attacks;
	}

	public List<Property<Integer>> getEnemyAttackCMId() {
		return enemyAttackCMId;
	}

	public void setEnemyAttackCMId(List<Property<Integer>> enemyAttackCMId) {
		this.enemyAttackCMId = enemyAttackCMId;
	}

	public List<Property<Integer>> getDropStealRates() {
		return dropStealRates;
	}

	public List<Property<Boolean>> getDropSteals() {
		return dropSteal;
	}

	public List<Property<Item>> getItemDropSteals() {
		return itemDropSteals;
	}

	public List<Property<Attack>> getManipAttacks() {
		return manipAttacks;
	}

	public void setManipAttacks(List<Property<Attack>> manipAttacks) {
		this.manipAttacks = manipAttacks;
	}

	public Integer getUnknown() {
		return unknown.getValue();
	}

	public void setUnknown(Integer unknown) {
		this.unknown.setValue(unknown);
	}

	public Integer getMp() {
		return mp.getValue();
	}

	public void setMp(Integer mp) {
		this.mp.setValue(mp);
	}

	public Integer getApReceived() {
		return apReceived.getValue();
	}

	public void setAp(Integer ap) {
		this.apReceived.setValue(ap);
	}

	public Item getMorphItem() {
		return morphItem.getValue();
	}

	public void setMorphItem(Item morphItem) {
		this.morphItem.setValue(morphItem);
	}

	public Integer getBackDamageMultiplier() {
		return backDamageMultiplier.getValue();
	}

	public void setBackDamageMultiplier(Integer backDamageMultiplier) {
		this.backDamageMultiplier.setValue(backDamageMultiplier);
	}

	public Integer getAlign() {
		return align.getValue();
	}

	public void setAlign(Integer align) {
		this.align.setValue(align);
	}

	public Integer getHp() {
		return hp.getValue();
	}

	public void setHp(Integer hp) {
		this.hp.setValue(hp);
	}

	public Integer getExpReceived() {
		return expReceived.getValue();
	}

	public void setExpReceived(Integer expReceived) {
		this.expReceived.setValue(expReceived);
	}

	public Integer getGilReceived() {
		return gilReceived.getValue();
	}

	public void setGilReceived(Integer gilReceived) {
		this.gilReceived.setValue(gilReceived);
	}

	public Integer getStatus() {
		int status = 0 ;
		boolean md = false;
		for (Property<Boolean> st : statuses) {		
			status = (status << 1) + (st.getValue() == md ? 1 : 0);
		}			
		return Integer.reverse(status);
	}

	public void setStatus(Integer status) {
		statuses = new ArrayList<Property<Boolean>>();	
		Property<Boolean> st;
		int md = 0;
		for (int i = 0; i < 32; i++) {
			st = new SimpleObjectProperty<Boolean>(((status >> i) & 1) == md);
			statuses.add(st);
		}
	}

	public Integer getUnknown2() {
		return unknown2.getValue();
	}

	public void setUnknown2(Integer unknown2) {
		this.unknown2.setValue(unknown2);
	}

	public StringProperty nameProperty() {
		return name;
	}

	public Property<Integer> levelProperty() {
		return level;
	}

	public Property<Integer> hpProperty() {
		return hp;
	}

	public Property<Integer> mpProperty() {
		return mp;
	}

	public Property<Integer> speedProperty() {
		return speed;
	}

	public Property<Integer> luckProperty() {
		return luck;
	}

	public Property<Integer> evadeProperty() {
		return evade;
	}

	public Property<Integer> strengthProperty() {
		return strength;
	}

	public Property<Integer> defenseProperty() {
		return defense;
	}

	public Property<Integer> magicProperty() {
		return magic;
	}

	public Property<Integer> magicDefenceProperty() {
		return magicDefense;
	}

	public Property<Integer> backDamageMultiplierProperty() {
		return backDamageMultiplier;
	}

	public Property<Integer> expReceivedProperty() {
		return expReceived;
	}

	public Property<Integer> apReceivedProperty() {
		return apReceived;
	}

	public Property<Integer> gilReceivedProperty() {
		return gilReceived;		
	}

	public Property<Item> morphItemProperty() {
		return morphItem;
	}

	public List<Property<Boolean>> getStatuses() {
		return statuses;
	}

	public void addElementType(ElementType elementType) {
		this.elementTypes.add(new SimpleObjectProperty<ElementType>(elementType));
	}

	public void addElementRate(ElementRate elementRate) {		
		this.elementRates.add(new SimpleObjectProperty<ElementRate>(elementRate));
	}

	public void addItemDropSteal(Item item) {
		itemDropSteals.add(new SimpleObjectProperty<Item>(item));
	}

	public void addDropStealRate(int rate) {		
		dropStealRates.add(new SimpleObjectProperty<Integer>(rate));		
	}

	public void addDropSteal(boolean bool) {
		dropSteal.add(new SimpleObjectProperty<Boolean>(bool));		
	}

	public void addActionAnimationIndex(Integer aai) {
		actionAnimationIndex.add(new SimpleObjectProperty<Integer>(aai));	
	}

	public void addEnemyAttackCMId(Integer eacmid) {
		enemyAttackCMId.add(new SimpleObjectProperty<Integer>(eacmid));		
	}

	public void addEnemyAttackId(Integer idattk) {
		attacks.add(new SimpleObjectProperty<Attack>(new Attack(idattk)));	
	}

	public void addEnemyManipAttackId(Integer idmattk) {
		manipAttacks.add(new SimpleObjectProperty<Attack>(new Attack(idmattk)));	
	}

	public Property<Integer> idProperty() {
		return id;
	}

}

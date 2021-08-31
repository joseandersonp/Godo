package godo.scene;

public class BattleSetup {
	
	private BattleLocation location;
	// 0x0002 2 bytes
	// Upon defeat of all opponents in current formation, begin battle with Formation ID without ending battle scene
	private int upponDefeat;
	// 0x0004 2 bytes Escape Counter
	private int scapeCounter;
	// 0x0008 4 * 2 bytes
	private int[] formationIdC = new int[4];
	// 0x0010 2 bytes Escapable Flag (Along with other flags)
	private int scapableFlag;
	// 0x0012 1 byte Battle layout type (normal, ambush, side). 0-8 types.
	private BattleLayout battleLayout;
	// 0x0013 1 byte Indexed Pre-Battle Camera position
	private int indexedPreBattle;

	public BattleLocation getLocation() {
		return location;
	}

	public void setLocation(BattleLocation location) {
		this.location = location;
	}

	public int getUpponDefeat() {
		return upponDefeat;
	}

	public void setUpponDefeat(int upponDefeat) {
		this.upponDefeat = upponDefeat;
	}

	public int getScapeCounter() {
		return scapeCounter;
	}

	public void setScapeCounter(int scapeCounter) {
		this.scapeCounter = scapeCounter;
	}

	public int[] getFormationIdC() {
		return formationIdC;
	}

	public void setFormationIdC(int[] formationIdC) {
		this.formationIdC = formationIdC;
	}

	public int getScapableFlag() {
		return scapableFlag;
	}

	public void setScapableFlag(int scapableFlag) {
		this.scapableFlag = scapableFlag;
	}

	public BattleLayout getBattleLayout() {
		return battleLayout;
	}

	public void setBattleLayout(BattleLayout battleLayout) {
		this.battleLayout = battleLayout;
	}

	public int getIndexedPreBattle() {
		return indexedPreBattle;
	}

	public void setIndexedPreBattle(int indexedPreBattle) {
		this.indexedPreBattle = indexedPreBattle;
	}

}

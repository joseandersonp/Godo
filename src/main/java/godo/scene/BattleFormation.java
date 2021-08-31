package godo.scene;

public class BattleFormation {

	private Enemy enemy;// 0x00 2 bytes Enemy ID
	private int positionX;// 0x02 2 bytes position X
	private int positionY;//// 0x04 2 bytes position Y
	private int positionZ;// 0x06 2 bytes position Z
	private int row;// 0x08 2 bytes Row
	private int coverFlags;// 0x0A 2 bytes Binary "Cover flags"
	private int initialCondition; // 0x0C 4 bytes Initial condition flags. Only last 5 bits are considered.
	
	public Enemy getEnemy() {
		return enemy;
	}
	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}
	public int getPositionX() {
		return positionX;
	}
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}
	public int getPositionY() {
		return positionY;
	}
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	public int getPositionZ() {
		return positionZ;
	}
	public void setPositionZ(int positionZ) {
		this.positionZ = positionZ;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCoverFlags() {
		return coverFlags;
	}
	public void setCoverFlags(int coverFlags) {
		this.coverFlags = coverFlags;
	}
	public int getInitialCondition() {
		return initialCondition;
	}
	public void setInitialCondition(int initialCondition) {
		this.initialCondition = initialCondition;
	}

	
}

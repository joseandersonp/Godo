package godo.scene;

public class Scene {

	private Integer id;

	private BattleSetup[] battleSetup = new BattleSetup[4];
	private CameraPlacement[] cameraPlacement = new CameraPlacement[4];
	private BattleFormation[][] battleFormation = new BattleFormation[4][6];
	private Enemy[] enemys = new Enemy[3];
	private Attack[] attack = new Attack[32];

	private AIScript[] formationAI;
	private AIScript[] enemyAI;

	public void setId(int id) {
		this.id = id;
	}

	public BattleSetup[] getBattleSetup() {
		return battleSetup;
	}

	public void setBattleSetup(BattleSetup[] battleSetup) {
		this.battleSetup = battleSetup;
	}

	public CameraPlacement[] getCameraPlacement() {
		return cameraPlacement;
	}

	public void setCameraPlacement(CameraPlacement[] cameraPlacement) {
		this.cameraPlacement = cameraPlacement;
	}

	public BattleFormation[][] getBattleFormation() {
		return battleFormation;
	}

	public void setBattleFormation(BattleFormation[][] battleFormation) {
		this.battleFormation = battleFormation;
	}

	public Integer getId() {
		return id;
	}

	public String getEnemyName1() {
		return enemys[0].getName();
	}

	public String getEnemyName2() {
		return enemys[1].getName();
	}

	public String getEnemyName3() {
		return enemys[2].getName();
	}
	
	public String getDescStringEnemyName1() {
		return getDescStringEnemyName(0);
	}
	
	public String getDescStringEnemyName2() {
		return getDescStringEnemyName(1);
	}

	public String getDescStringEnemyName3() {
		return getDescStringEnemyName(2);
	}

	private String getDescStringEnemyName(int i) {
		if (enemyAI[i] != null)
			return enemys[i].getName() + (enemyAI[i].containString() ? "*" : "");
		return enemys[i].getName();
	}

	public Enemy[] getEnemys() {
		return enemys;
	}

	public void setEnemys(Enemy[] enemys) {
		this.enemys = enemys;
	}

	public Attack[] getAttack() {
		return attack;
	}

	public void setAttack(Attack[] attack) {
		this.attack = attack;
	}

	public AIScript[] getFormationAI() {
		return formationAI;
	}

	public void setFormationAI(AIScript[] formationAI) {
		this.formationAI = formationAI;
	}

	public AIScript[] getEnemyAI() {
		return enemyAI;
	}

	public void setEnemyAI(AIScript[] enemyAI) {
		this.enemyAI = enemyAI;
	}

}

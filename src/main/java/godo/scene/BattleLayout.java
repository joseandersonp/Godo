package godo.scene;

public enum BattleLayout {
	NORMAL(0x0 , "Normal fight"),
	PREEMPTIVE(0x1 , "Preemptive"),
	BACK(0x2 , "Back attack"),
	SIDE(0x3 , "Side attack"),
	BOTH(0x4 , "Attacked from both sides (pincer attack, reverse side attack)"),
	ANOTHER_BOTH(0x5 , "Another attack from both sides battle (different maybe?)"),
	ANOTHER_SIDE(0x6 , "Another side attack"),
	THIRD_SIDE(0x7 , "A third side attack"),
	ANOTHER_NORMAL(0x8 , "Normal battle that locks you in the front row, change command is disabled"),
	
	UNKNOWN(0xFF, "Unknown");
	
	private int id;
	private String desc;

	BattleLayout(int id, String desc) {
		this.id = id;
		this.desc= desc;
	}
	
	public int getId() {
		return id;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public static BattleLayout get(int id) {
		for (BattleLayout bl : BattleLayout.values()) 
			if (bl.getId() == id)
				return bl;
		return UNKNOWN;
	}
}

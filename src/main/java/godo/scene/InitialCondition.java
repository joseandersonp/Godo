package godo.scene;

public enum InitialCondition {
	
	VISIBLE(0x0001,	"Visible"),
	DIRECTION_FACING(0x0002,"Indicates initial direction facing if players get a side attack."),
	UNKNOWN(0x0004,"Unknown"),
	TARGETABLE(0x0008, "Targetable"),
	MAIN_SCRIPT_ACTIVE(0x0010,"Main Script Active"),
	
	UNKNOWN2(0xFFFFFFFF,"Main Script Active");

	private int id;
	private String desc;

	InitialCondition(int id, String desc) {
		this.id = id;
		this.desc = desc;
	}
	
	public int getId() {
		return id;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public static InitialCondition get(int id) {
		for (InitialCondition bl : InitialCondition.values()) 
			if (bl.getId() == id)
				return bl;
		return UNKNOWN2;
	}
}

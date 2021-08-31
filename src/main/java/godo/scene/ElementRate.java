package godo.scene;

public enum ElementRate {
	
	DEATH(0x0 , "Death", "Death"),
	CANT_MISS(0x1 , "Can't miss?","Can't miss?"),
	DOUBLE_DAMAGE(0x2 , "Double Damage", "2X Damage"),
	UNKNOWN_03H(0x3 , "03h Unknown", "3 Unknown"),
	HALF_DAMAGE(0x4 , "Half Damage", "Half Dmg."),
	NULLIFY(0x5 , "Nullify Damage", "Nullify"),
	ABSORB_100(0x6 , "Absorb 100%", "Absorb"),
	FULL_CURE(0x7 , "Full Cure", "Full Cure"),
	NOTHING(0xFF , "Nothing", "Nothing");
	
	private int id;
	private String name;
	private String shortName;

	
	ElementRate(int id, String desc, String shortName) {
		this.id=id;
		this.name= desc;
		this.shortName = shortName;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getShortName() {
		return shortName;
	}

	public static ElementRate get(int id) {
		for (ElementRate en : ElementRate.values()) 
			if (en.getId() == id)
				return en;		
		return null;
	}
	
	@Override
	public String toString() {		
		return this.getName();
	}
}
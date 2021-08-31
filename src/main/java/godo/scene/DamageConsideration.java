package godo.scene;

public enum DamageConsideration {

	PHYSICAL_0X(0 , "0X-Physical, 100% hit chance"),
	PHYSICAL_1X(1 , "1X-Physical, PAcc & Crit checks"),
	 MAGICAL_2X(2 , "2X-Magical,  MAcc Check"),
	PHYSICAL_3X(3 , "3X-Physical, 100% hit chance, ignore Barrier, no crit"),
	 MAGICAL_4X(4 , "4X-Magical,  100% hit chance, ignore MBarrier"),
 	 MAGICAL_5X(5 , "5X-Magical,  100% hit chance, ignore MBarrier"),
	PHYSICAL_6X(6 , "6X-Physical, PAcc & Crit checks, special formulae, no def"),
	 MAGICAL_7X(7 , "7X-Magical,  MAcc check, special formulae, no def"),
	 MAGICAL_8X(8 , "8X-Magical,  Hit chance MOD target level = hit, no def"),
	 MAGICAL_9X(9 , "9X-Magical,  Uses Manipulate Acc"),
	PHYSICAL_AX(10, "AX-Physical, PAcc & Crit checks, Special damage multipliers"),
	PHYSICAL_BX(11, "BX-Physical, PAcc check, no Crit check"),
	 UNKNOWN_CX(12, "CX-Unknown"),
	 UNKNOWN_DX(13, "DX-Unknown"),
	 UNKNOWN_EX(14, "EX-Unknown"),
	 UNKNOWN_FX(15, "FX-Unknown");

	private String desc;
	private int code;

	private DamageConsideration(int code, String desc) {
		this.code = code;		
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}
	
	public String getDesc() {
		return desc;
	}

	public static DamageConsideration get(int value) {
		for (DamageConsideration en : DamageConsideration.values()) 
			if (en.getCode() == value)
				return en;
		return null;
	}	
	
	@Override
	public String toString() {
		return desc;
	}

	public static DamageConsideration getByDmgCalculation(int damageCalculation) {		
		return get(damageCalculation >> 4);
	}
}

package godo.scene;

public enum DamageFormula {

	FORMULA_09BX_X0(0,0,"X0-No damage"),
	FORMULA_09BX_X1(0,1,"X1-(Strength / 16) * (Atk + [(Lvl + Atk) / 32] * [(Lvl * Atk) / 32])"),
	FORMULA_09BX_X2(0,2,"X2-(Strength / 16) * ([Lvl + Atk] * 6)"),
	FORMULA_09BX_X3(0,3,"X3-HP * (Strength/32)"),
	FORMULA_09BX_X4(0,4,"X4-MHP * (Strength/32)"),
	FORMULA_09BX_X5(0,5,"X5-(Strength * 22) + ((Lvl + Atk) * 6)"),
	FORMULA_09BX_X6(0,6,"X6-Strength * 20"),
	FORMULA_09BX_X7(0,7,"X7-Strength * 16"),
	FORMULA_09BX_X8(0,8,"X8-\"Recovery\""),
	FORMULA_09BX_X9(0,9,"X9-Throw Damage"),
	FORMULA_09BX_XA(0,10,"XA-Coin Damage"),
	FORMULA_09BX_XB(0,11,"XB-Unknown"),
	FORMULA_09BX_XC(0,12,"XC-Unknown"),
	FORMULA_09BX_XD(0,13,"XD-Unknown"),
	FORMULA_09BX_XE(0,14,"XE-Unknown"),
	FORMULA_09BX_XF(0,15,"XF-Unknown"),

	FORMULA_67X_X0(1,0,"X0-Caster's HP"),
	FORMULA_67X_X1(1,1,"X1-Caster's MHP - HP"),
	FORMULA_67X_X2(1,2,"X2-Unknown"),
	FORMULA_67X_X3(1,3,"X3-Unknown"),
	FORMULA_67X_X4(1,4,"X4-Unknown"),
	FORMULA_67X_X5(1,5,"X5-Unknown"),
	FORMULA_67X_X6(1,6,"X6-Caster's H)P/2"),
	FORMULA_67X_X7(1,7,"X7-Unknown"),
	FORMULA_67X_X8(1,8,"X8-Random x 100"),
	FORMULA_67X_X9(1,9,"X9-Number of Escapes"),
	FORMULA_67X_XA(1,10,"XA-Target HP - 1"),
	FORMULA_67X_XB(1,11,"XB-Game Hour * 100 + Game Minute"),
	FORMULA_67X_XC(1,12,"XC-Target's Kills * 10"),
	FORMULA_67X_XD(1,13,"XD-Target's Materia * 1111"),
	FORMULA_67X_XE(1,14,"XE-Unknown"),
	FORMULA_67X_XF(1,15,"XF-Unknown"),

	FORMULA_AX_X0(2,0,"X0-User Status"),
	FORMULA_AX_X1(2,1,"X1-Near-Death"),
	FORMULA_AX_X2(2,2,"X2-(1+Dead Allies)"),
	FORMULA_AX_X3(2,3,"X3-(Target Level / 16)"),
	FORMULA_AX_X4(2,4,"X4-(1 + [48 * HP/MHP]) / 16"),
	FORMULA_AX_X5(2,5,"X5-(1 + [48 * MP/MMP]) / 16"),
	FORMULA_AX_X6(2,6,"X6-(1 + [AP on Weapon / 10000]) / 16"),
	FORMULA_AX_X7(2,7,"X7-(10 + [Character's Kills / 128]) / 16"),
	FORMULA_AX_X8(2,8,"X8-(1 + [Limit Level * Limit Units / 16) / 16"),
	FORMULA_AX_X9(2,9,"X9-Unknown"),
	FORMULA_AX_XA(2,10,"XA-Unknown"),
	FORMULA_AX_XB(2,11,"XB-Unknown"),
	FORMULA_AX_XC(2,12,"XC-Unknown"),
	FORMULA_AX_XD(2,13,"XD-Unknown"),
	FORMULA_AX_XE(2,14,"XE-Unknown"),
	FORMULA_AX_XF(2,15,"XF-Unknown"),
	
	FORMULA_FX_XF(15,15,"XF-Unknown");

	private String desc;
	private int code;
	private int upperCode;

	private DamageFormula(int upperCode, int code, String desc) {
		this.upperCode = upperCode;		
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}


	public String getDesc() {
		return desc;
	}
	
	public int getUpperCode() {
		return upperCode;
	}
	
	public static DamageFormula getByDmgCalculation(int dmgcalc) {
		
		int idxDC = (dmgcalc & 0xF0) >> 4;
		int idxDF = (dmgcalc & 0xF);
				
		for (DamageFormula en : DamageFormula.values()) {			
			if (en.getCode() == idxDF) {
				if ((idxDC >= 6 && idxDC <= 7)) {
					return en;
				} else if ((idxDC == 10)) {
					return en;
				}
			}
		}
		
		for (DamageFormula en : DamageFormula.values()) {			
			if (en.getCode() == idxDF) {			
				return en;
			}
		}
			
		return DamageFormula.FORMULA_FX_XF;
	}

	public static DamageFormula get(int value) {
		for (DamageFormula en : DamageFormula.values()) 
			if (en.getCode() == value)
				return en;
		return null;
	}
	
	public static DamageFormula[] values(int upperValue) {
		DamageFormula[] accFF = new  DamageFormula[16];	
		int i =0;
		for (DamageFormula DamageFormula : DamageFormula.values()) {
			if (DamageFormula.getUpperCode() == upperValue)
				accFF[i++] = DamageFormula;
		}
		return accFF;
	}

	@Override
	public String toString() {
		return desc;
	}
}

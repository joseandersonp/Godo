package godo.scene;

public enum AdditionalEffect {

	EFFECT_00(0x0,"00-Multiple [Modifier] hits"),
	EFFECT_01(0x1,"01-If all enemies are immune to statuses attempted to inflict, perform Gunge Lance"),
	EFFECT_02(0x2,"02-Randomly summon Fat-Chocobo if [Modifier] > [0..255]"),
	EFFECT_03(0x3,"03-Caster's Main Script takes control and become [Modifier]"),
	EFFECT_04(0x4,"04-Cause back-attack damage to target in row [Modifier] (Physical damage calculations only)"),
	EFFECT_05(0x5,"05-End battle, no reward"),
	EFFECT_06(0x6,"06-Steal (Level * 20) Gil from target (No affect on players' attacks)"),
	EFFECT_07(0x7,"07-Steal Item from target (No affect on players' attacks)"),
	EFFECT_08(0x8,"08-Randomly select one of the next six animation indexes to display"),
	EFFECT_09(0x9,"09-If Attacker's level = Target's level, Damage = Damage * 8"),
	EFFECT_0A(0xA,"0A-Damage multiplied based on attacker's statuses"),
	EFFECT_0B(0xB,"0B-Multiplier starts at 1\n     If attacker at or below 1/4 HP, Multiplier *2\n     If attacker inflicted with Death Sentence, Multiplier *4 [can stack for total of *8]\n     Damage multiplied by Multiplier"),
	EFFECT_0C(0xC,"0C-Damage multiplied by 1 + number of KO'd allies"),
	EFFECT_0D(0xD,"0D-Attack power becomes average of targets' levels"),
	EFFECT_0E(0xE,"0E-Resurrect Dead Allies"),
	EFFECT_0F(0xF,"0F-Bring up Slot Wheel"),
	EFFECT_10(0x10,"10-Removes other allies from battle and gives their stats to caster"),
	EFFECT_11(0x11,"11-Removes target from battle and flags as if \"Death\" (No effect on enemies)"),
	EFFECT_12(0x12,"12-Removes target from battle and flags as escaped (No effect on enemies)"),
	EFFECT_13(0x13,"13-Perform Critical Hit based on result of slot"),
	EFFECT_14(0x14,"14-Fills limit gauge of other allies (If an enemy uses this it will fill all players' bars)"),
	EFFECT_15(0x15,"15-Alters Targets' physical and magic damage and defense by [Modifier-100]% (Stackable)"),
	EFFECT_16(0x16,"16-Alter Performer's Evasion by [Modifier - 100]% per target (Stackable)"),
	EFFECT_17(0x17,"17-Alter Performer's Attack by [Modifier - 100]% per target (Stackable)"),
	EFFECT_18(0x18,"18-Perform Attack/Item [Modifier] upon completion"),
	EFFECT_19(0x19,"19-All Targets' Rows are changed (will not work on enemies)"),
	EFFECT_1A(0x1A,"1A-Perform attack [Modifier] on other row members"),
	EFFECT_1B(0x1B,"1B-Removes Caster from battle and flags as escaped (will not work on Allies)"),
	EFFECT_1C(0x1C,"1C-Alter Targets' Defenses by [Modifier - 100]% (Stackable)"),
	EFFECT_1D(0x1D,"1D-Returns Target from Escaped status"),
	EFFECT_1E(0x1E,"1E-Attack's power becomes 1 + ( ( Power * 3 * HP ) / MHP )"),
	EFFECT_1F(0x1F,"1F-Attack's power becomes 1 + ( ( Power * 3 * MP ) / MMP )"),
	EFFECT_20(0x20,"20-Attack's power becomes 1 + ( Power * (AP on weapon / 10000) / 16 )"),
	EFFECT_21(0x21,"21-Attack's power becomes 10 + ( [Character's Kills / 128] * Power) / 16 )"),
	EFFECT_22(0x22,"22-Attack's power becomes 1 + ( ( [Limit Level * Limit Units / 16] * Power ) / 16 )"),
	EFFECT_23(0x23,"23-Receive no Gil or items from enemy hit by this attack (No effect on Allies)"),
	
	EFFECT_FF(0xFF,"FF-Unknown");

	private String desc;
	private int code;


	private AdditionalEffect(int code, String desc) {

		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}


	public String getDesc() {
		return desc;
	}

	public static AdditionalEffect get(int value) {
		for (AdditionalEffect en : AdditionalEffect.values()) 
			if (en.getCode() == value)
				return en;
		return AdditionalEffect.EFFECT_FF;
	}

	@Override
	public String toString() {
		return desc;
	}
}

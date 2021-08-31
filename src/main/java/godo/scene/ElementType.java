package godo.scene;

public enum ElementType {
	FIRE(0X0, "Fire"),
	ICE(0X1, "Ice"),
	BOLT(0X2, "Bolt"),
	EARTH(0X3, "Earth"),
	BIO(0X4, "Bio"),
	GRAVITY(0X5, "Gravity"),
	WATER(0X6, "Water"),
	WIND(0X7, "Wind"),
	HOLY(0X8, "Holy"),
	HEALTH(0X9, "Health"),
	CUT(0XA, "Cut"),
	HIT(0XB, "Hit"),
	PUNCH(0XC, "Punch"),
	SHOOT(0XD, "Shoot"),
	SCREAM(0XE, "Shout"),
	HIDDEN(0XF, "Unnamed"),

	DEATH(0X20,"Death"),
	NEAR_DEATH(0X21,"Near-Death"),
	SLEEP(0X22,"Sleep"),
	POISON(0X23,"Poison"),
	SADNESS(0X24,"Sadness"),
	FURY(0X25,"Fury"),
	CONFUSE(0X26,"Confuse"),
	SILENCE(0X27,"Silence"),
	HASTE(0X28,"Haste"),
	SLOW(0X29,"Slow"),
	STOP(0X2A,"Stop"),
	FROG(0X2B,"Frog"),
	MINI(0X2C,"Mini"),
	SLOW_NUMB(0X2D, "Slow Numb"),
	PETRIFY(0X2E, "Petrify"),
	REGEN(0X2F, "Regen"),
	BARRIER(0X30, "Barrier"),
	M_BARRIER(0X31, "M.Barrier"),
	REFLECT(0X32, "Reflect"),
	DUAL(0X33, "Dual"),
	SHIELD(0X34, "Shield"),
	D_SENTENCE(0X35, "D.Sentence"),
	MANIPULATE(0X36, "Manipulate"),
	BERSERK(0X37, "Berserk"),
	PEERLESS(0X38, "Peerless"),
	PARALYSIS(0X39, "Paralysis"),
	DARKNESS(0X3A, "Darkness"),
	DUAL_DRAIN(0X3B, "Dual Drain"),
	DEATHFORCE(0X3C, "DeathForce"),
	RESIST(0X3D, "Resist"),
	LUCKY_GIRL(0X3E, "\"Lucky Girl\""),
	IMPRISONED(0X3F, "Imprisoned"),

	NOTHING(0XFF, "Nothing");

	private int id;
	private String desc;

	ElementType(int id, String desc) {
		this.id=id;
		this.desc= desc;
	}

	public int getId() {
		return id;
	}

	public String getDesc() {
		return desc;
	}

	public static ElementType get(int id) {
		for (ElementType en : ElementType.values()) 
			if (en.getId() == id)
				return en;		
		return NOTHING;
	}

	@Override
	public String toString() {
		return this.desc;
	}
}
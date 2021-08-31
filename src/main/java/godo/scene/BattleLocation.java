package godo.scene;

public enum BattleLocation {
	BLANK(0X0,"BLANK"),
	BIZARRO_BATTLE_CENTER(0x01,"Bizarro Battle Center"),
	GRASSLAND(0x02,"Grassland"),
	MT_NIBEL(0x03,"Mt Nibel"),
	FOREST(0x04,"Forest"),
	BEACH(0x05,"Beach"),
	DESERT(0x06,"Desert"),
	SNOW(0x07,"Snow"),
	SWAMP(0x08,"Swamp"),
	SECTOR_1_TRAIN_STATION(0x09,"Sector 1 Train Station"),
	REACTOR_1(0x0A,"Reactor 1"),
	REACTOR_1_CORE(0x0B,"Reactor 1 Core"),
	REACTOR_1_ENTRANCE(0x0C,"Reactor 1 Entrance"),
	SECTOR_4_SUBWAY(0x0D,"Sector 4 Subway"),
	NIBEL_CAVES_OR_AFOREST_CAVES(0x0E,"Nibel Caves or AForest Caves"),
	SHINRA_HQ(0x0F,"Shinra HQ"),
	MIDGAR_RAID_SUBWAY(0x10,"Midgar Raid Subway"),
	HOJOS_LAB(0x11,"Hojos Lab"),
	SHINRA_ELEVATORS(0x12,"Shinra Elevators"),
	SHINRA_ROOF(0x13,"Shinra Roof"),
	MIDGAR_HIGHWAY(0x14,"Midgar Highway"),
	WUTAI_PAGODA(0x15,"Wutai Pagoda"),
	CHURCH(0x16,"Church"),
	CORAL_VALLEY(0x17,"Coral Valley"),
	MIDGAR_SLUMS(0x18,"Midgar Slums"),
	SECTOR_4_CORRIDORS_OR_JUNON_PATH(0x19,"Sector 4 Corridors or Junon Path"),
	SECTOR_4_GANTRIES_OR_MIDGAR_UNDERGROUND(0x1A,"Sector 4 Gantries or Midgar Underground"),
	SECTOR_7_SUPPORT_PILLAR_STAIRWAY(0x1B,"Sector 7 Support Pillar Stairway"),
	SECTOR_7_SUPPORT_PILLAR_TOP(0x1C,"Sector 7 Support Pillar Top"),
	SECTOR_8(0x1D,"Sector 8"),
	SEWERS(0x1E,"Sewers"),
	MYTHRIL_MINES(0x1F,"Mythril Mines"),
	NORTHERN_CRATER_FLOATING_PLATFORMS(0x20,"Northern Crater Floating Platforms"),
	COREL_MOUNTAIN_PATH(0x21,"Corel Mountain Path"),
	JUNON_BEACH(0x22,"Junon Beach"),
	JUNON_CARGO_SHIP(0x23,"Junon Cargo Ship"),
	COREL_PRISON(0x24,"Corel Prison"),
	BATTLE_SQUARE(0x25,"Battle Square"),
	DA_CHAO_RAPPS_BATTLE(0x26,"Da Chao Rapps Battle"),
	CIDS_BACKYARD(0x27,"Cid's Backyard"),
	FINAL_DESCENT_TO_SEPHIROTH(0x28,"Final Descent to Sephiroth"),
	REACTOR_5_ENTRANCE(0x29,"Reactor 5 Entrance"),
	TEMPLE_OF_THE_ANCIENTS_ESCHER_ROOM(0x2A,"Temple of the Ancients Escher Room"),
	SHINRA_MANSION(0x2B,"Shinra Mansion"),
	JUNON_AIRSHIP_DOCK(0x2C,"Junon Airship Dock"),
	WHIRLWIND_MAZE(0x2D,"Whirlwind Maze"),
	JUNON_UNDERWATER_REACTOR(0x2E, "Junon Underwater Reactor"),
	GONGAGA_REACTOR(0x2F,"Gongaga Reactor"),
	GELNIKA(0x30,"Gelnika"),
	TRAIN_GRAVEYARD(0x31,"Train Graveyard"),
	GREAT_GLACIER_ICE_CAVES_GAEA_CLIFFS_INSIDE(0x32,"Great Glacier Ice Caves & Gaea Cliffs Inside"),
	SISTER_RAY(0x33,"Sister Ray"),
	SISTER_RAY_BASE(0x34,"Sister Ray Base"),
	FORGOTTEN_CITY_ALTAR(0x35,"Forgotten City Altar"),
	NORTHERN_CRATER_INITIAL_DESCENT(0x36,"Northern Crater Initial Descent"),
	NORTHERN_CRATER_HATCHERY(0x37,"Northern Crater Hatchery"),
	NORTHERN_CRATER_WATER_AREA(0x38,"Northern Crater Water Area"),
	SAFER_BATTLE(0x39,"Safer Battle"),
	KALM_FLASHBACK_DRAGON_BATTLE(0x3A,"Kalm Flashback Dragon Battle"),
	JUNON_UNDERWATER_PIPE(0x3B,"Junon Underwater Pipe"),
	BLANK_1(0x3C,"Blank"),
	COREL_RAILWAY_CANYON(0x3D,"Corel Railway Canyon"),
	WHIRLWIND_MAZE_CRATER(0x3E,"Whirlwind Maze Crater"),
	COREL_RAILWAY_ROLLERCOASTER(0x3F,"Corel Railway Rollercoaster"),
	WOODEN_BRIDGE(0x40,"Wooden Bridge"),
	DA_CHAO(0x41,"Da Chao"),
	FORT_CONDOR(0x42,"Fort Condor"),
	DIRT_WASTELAND(0x43,"Dirt Wasteland"),
	BIZARRO_BATTLE_RIGHT_SIDE(0x44,"Bizarro Battle Right Side"),
	BIZARRO_BATTLE_LEFT_SIDE(0x45,"Bizarro Battle Left Side"),
	JENOVA_SYNTHESIS_BATTLE(0x46,"Jenova*SYNTHESIS Battle"),
	COREL_TRAIN_BATTLE(0x47,"Corel Train Battle"),
	COSMO_CANYON(0x48,"Cosmo Canyon"),
	CAVERNS_OF_THE_GI(0x49,"Caverns of the Gi"),
	NIBELHEIM_MANSION_BASEMENT(0x4A,"Nibelheim Mansion Basement"),
	TEMPLE_OF_THE_ANCIENTS_DEMONS_GATE(0x4B,"Temple of the Ancients Demons Gate"),
	TEMPLE_OF_THE_ANCIENTS_MURAL_ROOM(0x4C,"Temple of the Ancients Mural Room"),
	TEMPLE_OF_THE_ANCIENTS_CLOCK_PASSAGE(0x4D,"Temple of the Ancients Clock Passage"),
	FINAL_BATTLE_SEPHIROTH(0x4E,"Final Battle Sephiroth"),
	JUNGLE(0x4F,"Jungle"),
	ULTIMATE_WEAPON_BATTLE_ON_HIGHWIND(0x50,"Ultimate Weapon Battle on Highwind"),
	COREL_REACTOR(0x51,"Corel Reactor"),
	UNUSED(0x52,"Unused"),
	DON_CORNEOS_MANSION(0x53,"Don Corneo's Mansion"),
	EMERALD_WEAPON_BATTLE(0x54,"Emerald Weapon Battle"),
	REACTOR_5(0x55,"Reactor 5"),
	SHINRA_HQ_ESCAPE(0x56,"Shinra HQ Escape"),
	ULTIMATE_WEAPON_GONGAGA_REACTOR(0x57,"Ultimate Weapon Gongaga Reactor"),
	COREL_PRISON_DYNE_BATTLE(0x58,"Corel Prison Dyne Battle"),
	ULTIMATE_WEAPON_FOREST(0x59,"Ultimate Weapon Forest"),
	
	UNKNOWN(0xFFFF,"Unknown");

	private int id;
	private String desc;

	BattleLocation(int id, String desc) {
		this.id= id;
		this.desc = desc;
	}

	public int getId() {
		return id;
	}

	public String getDesc() {
		return desc;
	}

	public static BattleLocation get(int id) {
		for (BattleLocation bl : BattleLocation.values()) 
			if (bl.getId() == id)
				return bl;
		return UNKNOWN;
	}
}

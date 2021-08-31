package godo.scene;

public enum AIOpcode {

	PUSHAD(0x0F, 2, "PUSHAD"),
	PUSHWR(0x1F, 2, "PUSHWR"),

	ADD(0x30, 0, "ADD"),
	SUB(0x31, 0, "SUB"),
	MUL(0x32, 0, "MUL"),
	DIV(0x33, 0, "DIV"),
	MOD(0x34, 0, "MOD"),
	AND(0x35, 0, "AND"),
	ORR(0x36, 0, "ORR"),
	NOT(0x37, 0, "NOT"),

	EQU(0x40, 0, "EQU"),
	NEQ(0x41, 0, "NEQ"),
	GEQ(0x42, 0, "GEQ"),
	LEQ(0x43, 0, "LEQ"),
	GRTN(0x44, 0, "GRTN"),
	LSTN(0x45, 0, "LSTN"),

	LAND(0x50, 0, "LAND"),
	LOR(0x51, 0, "LOR"),
	LNOT(0x52, 0, "LNOT"),

	PUSHC1(0x60, 1, "PUSHC1"),
	PUSHC2(0x61, 2, "PUSHC2"),
	PUSHC3(0x62, 3, "PUSHC3"),

	JUMPZ(0x70, 2, "JUMPZ"),
	JNEQ(0x71, 2, "JNEQ"),
	JUMP(0x72, 2, "JUMP"),
	END (0x73, 0, "END"),
	POP74(0x74, 0, "POP74"),
	LINK(0x75, 0, "LINK"),	

	MASK(0x80, 0, "MASK"),	
	RWRD(0x81, 0, "RWRD"),	
	RBYT(0x82, 0, "RBYT"),	
	CNTB(0x83, 0, "CNTB"),	
	HMSK(0x84, 0, "HMSK"),	
	LMSK(0x85, 0, "LMSK"),	
	MPCT(0x86, 0, "MPCT"),	
	TBIT(0x87, 0, "TBIT"),	

	STRA(0x90, 0, "STRA"),
	POP(0x91, 0, "POP"),
	ATTK(0x92, 0, "ATTK"),
	DSTR(0x93, 0, "DSTR"),
	COPY(0x94, 0, "COPY"),
	GLOB(0x95, 0, "GLOB"),
	EDEF(0x96, 0, "EDEF"),

	DDEBUG(0xA0, 0, "DDEBUG"),
	POP2(0xA1, 0, "POP2");

	private int code;
	private int valSize;
	private String name;

	AIOpcode(int code, int valSize, String name) {
		this.code = code;
		this.valSize = valSize;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public int getValSize() {
		return valSize;
	}

	public String getName() {
		return name;
	}

	public static AIOpcode get(int opvalue) {

		for (AIOpcode op : AIOpcode.values()) {
			if (op.getCode() == opvalue)
				return op;
		}		
		if ((opvalue >> 4) == 0)
			return AIOpcode.PUSHAD;
		if ((opvalue >> 4) == 1)
			return AIOpcode.PUSHWR;

		return null;

	}

	public static AIOpcode get(String opcode) {
		for (AIOpcode op : AIOpcode.values()) {
			if (op.getName().equals(opcode)){
				return op;

			}		
		}
		return null;
	}
}

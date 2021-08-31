package godo.scene;

public class Opcode {

	private AIOpcode opcode;
	private Integer type;
	private byte[] value;

	public AIOpcode getOpcode() {
		return opcode;
	}

	public void setOpcode(AIOpcode opcode) {
		this.opcode = opcode;
	}

	public Integer getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public byte[] getValue() {
		return value;
	}

	public void setValue(byte[] value) {
		this.value = value;
	}
	
	public int getIntValue() {	
		int b0 = (value[0] & 0xFF);
		int b1 = (value[1] & 0xFF);
		return ((b1 << 8) + b0);
	}

	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();
		if (opcode != null) {
			sb.append(opcode.getName());
			if (value != null)
				for (int i = 0; i < value.length; i++) {
					sb.append(String.format(" %X", value[i]));
				}
			if (type != null)
				sb.append(String.format(" TYPE %X", type));
			return sb.toString();
		}
		return super.toString();
	}

}

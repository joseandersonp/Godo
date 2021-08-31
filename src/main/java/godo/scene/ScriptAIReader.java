package godo.scene;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import godo.util.ArrayUtil;

public class ScriptAIReader {

	public static AIScript[] read(byte[] dataScript, int listSize) throws Exception {

		AIScript[] aiScripts = new AIScript[listSize];

		for (int i = 0; i < listSize; i++) {

			int offsetDs = dataScript[i * 2] & 0xFF;
			offsetDs += ((dataScript[i * 2 + 1] & 0XFF) << 8);

			if (offsetDs == 0xFFFF)
				continue;

			AIScript aiScript = new AIScript();
			aiScripts[i] = aiScript;

			int j = offsetDs;
			int header = offsetDs + 32;
			int k = 0;
			while (j < header) {

				int offsetSc = dataScript[j++] & 0xFF;
				offsetSc += ((dataScript[j++] & 0XFF) << 8);

				if (offsetSc == 0xFFFF) {
					k++;
					continue;
				}

				List<Opcode> opcodes = new ArrayList<Opcode>();
				aiScript.getSections().set(k++, opcodes);

				offsetSc += offsetDs;

				while (true) {

					int opValue = dataScript[offsetSc] & 0xFF;
					offsetSc++;

					AIOpcode aiOpcode = AIOpcode.get(opValue);
					Opcode op = new Opcode();
					op.setOpcode(aiOpcode);
					opcodes.add(op);

					if (aiOpcode.equals(AIOpcode.END)) 
						break;
					

					if (aiOpcode == AIOpcode.PUSHWR || aiOpcode == AIOpcode.PUSHAD)
						op.setType(opValue & 0x0F);

					if (aiOpcode.getValSize() > 0) {
						byte[] bValue = new byte[aiOpcode.getValSize()];
						int c = 0;
						while (c < bValue.length) {
							bValue[c++] = dataScript[offsetSc++];
						}
						op.setValue(bValue);
					} else if (AIOpcode.DSTR.equals(aiOpcode) || 
							AIOpcode.DDEBUG.equals(aiOpcode)) {
						
						int endChar = AIOpcode.DSTR.equals(aiOpcode)? -1 : 0;
												
						int c = offsetSc;
						while (dataScript[c] != endChar)
							c++;
						c++;

						byte[] bValue = new byte[c - offsetSc];
						c = 0;
						while (c < bValue.length) {
							bValue[c++] = dataScript[offsetSc++];
						}

						op.setValue(bValue);
					}

				}
			}
		}

		return aiScripts;
	}

	public static byte[] write(AIScript[] aiScripts, int bufferSize) throws Exception {

		// 4096;
		byte[] bufferSc = new byte[bufferSize];
		Arrays.fill(bufferSc, (byte)0xFF);
		
		int offsetAIEnemy = aiScripts.length * 2;
		for (int i = 0; i < aiScripts.length; i++) {

			AIScript aiScript = aiScripts[i];
			if (aiScript == null) {
				ArrayUtil.writeUnsignedShortL(bufferSc, i * 2, 0xFFFF);
				continue;
			}
			
			ArrayUtil.writeUnsignedShortL(bufferSc, i * 2, offsetAIEnemy);

			int offsetData = offsetAIEnemy + 0x20;

			for (int j = 0; j < 16; j++) {

				List<Opcode> opcodes = aiScript.getSections().get(j);
				
				if (opcodes == null || opcodes.size() == 0) {
					ArrayUtil.writeUnsignedShortL(bufferSc, offsetAIEnemy + j * 2, 0xFFFF);
					continue;
				}		
				
				ArrayUtil.writeUnsignedShortL(bufferSc, offsetAIEnemy + j * 2, offsetData - offsetAIEnemy);

				for (Opcode opcode : opcodes) {

					switch (opcode.getOpcode()) {
					case PUSHAD:
					case PUSHWR:

						int code = opcode.getOpcode().getCode();
						code = (code & 0xF0) + opcode.getType();
						bufferSc[offsetData++] = (byte) code;
						ArrayUtil.write(opcode.getValue(), bufferSc, offsetData);
						offsetData += opcode.getValue().length;
						break;

					case PUSHC1:
					case PUSHC2:
					case PUSHC3:
					case JUMP:
					case JNEQ:
					case JUMPZ:
					case DDEBUG:
					case DSTR:

						code = opcode.getOpcode().getCode();
						bufferSc[offsetData++] = (byte) code;
						ArrayUtil.write(opcode.getValue(), bufferSc, offsetData);
						offsetData += opcode.getValue().length;
						break;

					default:

						code = opcode.getOpcode().getCode();
						bufferSc[offsetData++] = (byte) code;
						break;

					}

					if (opcode.getOpcode().equals(AIOpcode.END)) 
						break;
					
				}
			}
			
			if (offsetData % 2 != 0) 
				bufferSc[offsetData++] = (byte)0xFF;
			
			offsetAIEnemy = offsetData;
		}	

		return bufferSc;
	}

}
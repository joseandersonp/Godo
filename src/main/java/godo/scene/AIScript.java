package godo.scene;

import java.io.BufferedReader;
import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import godo.script.Table;

public class AIScript {

	List<List<Opcode>> sections = new ArrayList<List<Opcode>>(Collections.nCopies(16, (List<Opcode>) null));

	public List<List<Opcode>> getSections() {
		return sections;
	}
	
	public boolean containString() {
		for (List<Opcode> list : sections)
			if (list != null)
				for (Opcode opcode : list) 
					if(opcode.getOpcode().equals(AIOpcode.DDEBUG)||
							opcode.getOpcode().equals(AIOpcode.DSTR))
						return true;		
		return false;
	}

	public void setSections(List<List<Opcode>> sections) {
		this.sections = sections;
	}

	public static String format(List<Opcode> section) throws Exception {

		Table table = new Table(new File("table.txt"), false);

		StringBuffer sb = new StringBuffer();

		int lc = 0;

		ArrayList<Integer> offsetJumps = new ArrayList<Integer>(0);

		for (Opcode opcode : section) {

			if (offsetJumps.contains(lc)) {
				sb.append(String.format("%-8S", "LABEL"));
				sb.append(String.format("%04X%n", lc));
			}

			// sb.append(String.format("%04X: ", lc));
			if (opcode.getValue() != null) {

				sb.append(String.format("%-8S", opcode.getOpcode().getName()));

				switch (opcode.getOpcode()) {

				case DSTR:
					sb.append("\"");
					sb.append(table.format(opcode.getValue()).replaceAll("\\{END\\}", ""));
					sb.append("\"");
					break;
					
				case DDEBUG:
					sb.append("\"");
					sb.append(new String(opcode.getValue()));
					sb.append("\"");
					break;

				case JUMPZ:
				case JNEQ:
				case JUMP:

					int b0 = (opcode.getValue()[0] & 0xFF);
					int b1 = (opcode.getValue()[1] & 0xFF);
					offsetJumps.add((b1 << 8) + b0);

				default:

					int length = opcode.getValue().length;
					while (length > 0)
						sb.append(String.format("%02X", opcode.getValue()[--length]));

					if (opcode.getOpcode().equals(AIOpcode.PUSHAD) || opcode.getOpcode().equals(AIOpcode.PUSHWR)) {
						sb.append(String.format("   TYPE %X", opcode.getType()));
					}
					break;
				}
				lc += opcode.getValue().length + 1;
			} else {
				sb.append(opcode.getOpcode().getName());
				lc++;
			}
			sb.append("\n");

		}
		return sb.toString();
	}

	public static List<Opcode> parse(String text) throws Exception {

		Table table = new Table(new File("table.txt"), false);

		BufferedReader br = new BufferedReader(new StringReader(text));

		List<Opcode> opcodes = new ArrayList<Opcode>();

		Map<Integer, Integer> labels = new HashMap<Integer, Integer>();
		List<Opcode> opJumps = new ArrayList<Opcode>();

		String pPush = "^(PUSH(AD|WR))\\s+([0-9A-F]+)\\s+TYPE\\s+([0-9A-F]+)";
		String pOther = "^([A-Z1-3]+)\\s+([0-9A-F]+)";
		String pString = "^(DSTR|DDEBUG) \\s+\\\"(.+)\\\"";

		Pattern pattern;
		Matcher matcher;
		String line;
		int c = 0;
		while ((line = br.readLine()) != null) {

			// line = line.substring(6);

			pattern = Pattern.compile(pPush, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(line);
			if (matcher.lookingAt()) {

				String sOpcode = matcher.group(1);
				String sValue = matcher.group(3);
				String sType = matcher.group(4);

				int value = Integer.parseInt(sValue, 16);
				int type = Integer.parseInt(sType, 16);

				Opcode opcode = new Opcode();
				opcode.setOpcode(AIOpcode.get(sOpcode));
				byte[] bValue = new byte[2];
				bValue[0] = (byte) (value & 0xFF);
				bValue[1] = (byte) ((value & 0xFF00) >> 8);
				opcode.setType(type);
				opcode.setValue(bValue);

				System.out.println(String.format("%X %S", c, opcode));
				c += 3;
				opcodes.add(opcode);
				continue;
			}

			pattern = Pattern.compile(pOther, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(line);
			if (matcher.lookingAt()) {

				String sOpcode = matcher.group(1);
				String sValue = matcher.group(2);

				int value = Integer.parseInt(sValue, 16);

				if (sOpcode.equalsIgnoreCase("LABEL")) {
					if (value != c) {
						System.out.println("----------------");
						System.out.println(String.format("LNE: %X, %X", c, value));
						System.out.println("----------------");
					}
					labels.put(value, c);
					continue;
				}

				AIOpcode aiOpcode = AIOpcode.get(sOpcode);

				Opcode opcode = new Opcode();
				opcode.setOpcode(aiOpcode);

				int lenght = aiOpcode.getValSize();
				byte[] bValue = new byte[lenght];
				for (int i = 0; i < lenght; i++) {
					bValue[i] = (byte) (value >> 8 * i & 0x0000FF);
				}
				opcode.setValue(bValue);

				if (aiOpcode.equals(AIOpcode.JUMP) || aiOpcode.equals(AIOpcode.JUMPZ) || aiOpcode.equals(AIOpcode.JNEQ)) {
					opJumps.add(opcode);
				}

				System.out.println(String.format("%X %S", c, opcode));
				c += aiOpcode.getValSize() + 1;
				opcodes.add(opcode);

				continue;
			}

			pattern = Pattern.compile(pString, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(line);
			if (matcher.lookingAt()) {

				String sOpcode = matcher.group(1);
				String string = matcher.group(2);

				
				Opcode opcode = new Opcode();
				opcode.setOpcode(AIOpcode.get(sOpcode));
				
				byte[] value;
				if (opcode.getOpcode().equals(AIOpcode.DSTR))				
					value = table.parseBytes(string);
				else {
					
					byte cargs = 0;
					byte[] bytesString = string.getBytes();
					value = new byte[string.length() + 2];
					for (int i = 0; i < string.length() ; i++) {
						if (string.charAt(i) == '%')
							cargs++;
						value[i+1] = bytesString[i];
					}
					value[0] = cargs;
					
				}
				opcode.setValue(value);

				System.out.println(String.format("%X %S", c, opcode));
				c += value.length + 1;
				opcodes.add(opcode);
				continue;

			}

			Opcode opcode = new Opcode();
			opcode.setOpcode(AIOpcode.get(line));
			opcodes.add(opcode);
			System.out.println(String.format("%X %S", c, opcode));
			c++;

		}

		for (Opcode opcode : opJumps) {
			if (labels.containsKey(opcode.getIntValue())) {
				Integer offset = labels.get(opcode.getIntValue());
				byte[] bValue = new byte[2];
				bValue[0] = (byte) (offset & 0xFF);
				bValue[1] = (byte) ((offset & 0xFF00) >> 8);
				opcode.setValue(bValue);
			}
		}

		return opcodes;

	}
}
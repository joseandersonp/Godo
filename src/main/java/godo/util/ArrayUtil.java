package godo.util;
public class ArrayUtil {

	private ArrayUtil(){}
	
	public static void readUnsignetdhortL(byte buf[], int idx, int value) {
		readNumberL(buf, idx, 2);
	}
	
	public static void readUnsignedIntL(byte buf[], int idx, int value) {
		readNumberL(buf, idx, 4);
	}

	public static void writeUnsignedShortL(byte buf[], int idx, int value) {
		writeNumberL(buf, idx, value, 2);
	}
	
	public static void writeUnsignedIntL(byte buf[], int idx, int value) {
		writeNumberL(buf, idx, value, 4);
	}
	
	public static long readNumberL(byte buf[], int idx, int lenght) {		
		long value = 0;
		for (int i = 0; i < lenght; i++) {
			value += (buf[i+idx] & 0xFF) << 8*i;
		}
		return value;
	}
	
	public static void writeNumberL(byte buf[], int idx, long value, int lenght) {		
		for (int i = 0; i < lenght; i++) {
			buf[i+idx] = (byte)(value >> 8*i & 0xFF);
		}	
	}
	
	public static void write(byte buffrom[],byte bufto[], int idxto) {
		write(buffrom, bufto, 0, idxto, buffrom.length);
	}
	
	public static void write(byte buffrom[],byte bufto[], int idxfrom, int idxto, int lenght) {
		while (lenght > 0) {
			bufto[idxto++] = buffrom[idxfrom++];
			lenght--;
		}
	}
	
}

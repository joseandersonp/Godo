package godo.scene;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import godo.compression.Gzip;
import godo.util.ArrayUtil;
import godo.util.MemBuffer;

public class SceneFileIO {

	private static int fileCount = 0;

	public static SceneDataContainer read(File fileScene) throws Exception {

		try (FileInputStream inStream = new FileInputStream(fileScene)) {
			byte[] data = new byte[inStream.available()];
			inStream.read(data);
			return read(data);
		}		
	}

	public static void write(SceneDataContainer sceneData, File sceneFile) throws IOException {
		FileOutputStream out = new FileOutputStream(sceneFile);
		write(sceneData, out);
		out.close();
	}

	public static void write(SceneDataContainer sceneData, OutputStream out) throws IOException {

		ByteArrayInputStream bin;
		ByteArrayOutputStream bout;
		byte[][] sectionComps = new byte[16][];
		int totalSizeComps = 64;
		int idxComp = 0;

		for (int i = 0; i < sceneData.size(); i++) {

			bin = new ByteArrayInputStream(sceneData.get(i));
			bout = new ByteArrayOutputStream();

			Gzip.encode(bin, bout);

			int rest = bout.toByteArray().length % 4;
			int padff = rest > 0 ? 4 - rest : 0;
			while (padff > 0) {
				bout.write(0xFF);
				padff--;
			}

			bin.close();
			bout.close();

			byte[] sectionComp = bout.toByteArray();
			sectionComp[9] = 3;
			sectionComp[8] = 2;
			totalSizeComps += sectionComp.length;

			if (i == sceneData.size() - 1) {
				sectionComps[idxComp++] = sectionComp;
			}

			if (totalSizeComps > 0x2000 || i == sceneData.size() - 1) {

				byte[] pointers = new byte[64];
				Arrays.fill(pointers, (byte) 0xFF);

				int pointer = 64;
				for (int j = 0; j < idxComp; j++) {
					ArrayUtil.writeUnsignedIntL(pointers, j * 4, pointer / 4);
					pointer += sectionComps[j].length;
				}
				out.write(pointers);
				for (int j = 0; j < idxComp; j++) {
					out.write(sectionComps[j]);
				}

				int countPad = 0x2000 - pointer;
				while (countPad > 0) {
					out.write(0xFF);
					countPad--;
				}

				totalSizeComps = 64;
				totalSizeComps += sectionComp.length;
				idxComp = 0;

			}
			sectionComps[idxComp++] = sectionComp;
		}

	}

	public static SceneDataContainer read(byte[] data) throws Exception {

		SceneDataContainer sceneData = new SceneDataContainer();
		MemBuffer buffer = MemBuffer.wrap(data);

		fileCount = 0;
		byte[] sectionData = new byte[0x2000]; // 8192bytes
		while (buffer.get(sectionData) == sectionData.length) {
			readSection(sectionData, sceneData);
		}		
		return sceneData;
	}

	private static void readSection(byte[] sectionData, SceneDataContainer sceneData) throws IOException {

		MemBuffer buffer = MemBuffer.wrap(sectionData);

		ByteArrayInputStream bin;
		ByteArrayOutputStream bout;

		int[] pointers = new int[16];

		for (int i = 0; i < pointers.length; i++) {
			pointers[i] = buffer.getInt() * 4;
		}

		int pBegin = 0;
		int pEnd = 0;
		for (int i = 0; i < pointers.length; i++) {

			pBegin = pointers[i];
			if (pBegin == -4)
				break;

			pEnd = 0;
			if (i + 1 == pointers.length || pointers[i + 1] == -4)
				pEnd = 0x2000;
			else
				pEnd = pointers[i + 1];

			byte[] bufferC = new byte[pEnd - pBegin];
			buffer.get(bufferC);

			bin = new ByteArrayInputStream(bufferC);
			bout = new ByteArrayOutputStream();
			Gzip.decode(bin, bout);
			sceneData.put(fileCount, bout.toByteArray());
			fileCount++;
			bin.close();
			bout.close();
		}

	}

}

package godo.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import godo.scene.Scene;
import godo.scene.SceneDataContainer;
import godo.scene.SceneFileIO;
import godo.scene.SceneIO;
import godo.scene.ScriptAIReader;
import godo.script.Table;
import godo.util.ArrayUtil;

public class SceneEditorModel {

	private static SceneEditorModel instance;

	private File sceneFile;
	private SceneDataContainer sceneData;
	private List<Scene> scenes;

	public void openSceneFile(File file) throws Exception {

		sceneFile = file;
		sceneData = SceneFileIO.read(sceneFile);
		scenes = new ArrayList<Scene>();

		for (int i = 0; i < sceneData.size(); i++) {

			Scene scene = SceneIO.decode(sceneData.get(i));
			scene.setId(i + 1);
			scenes.add(scene);

		}
	}

	public void saveSceneFile(File file) throws Exception {

		if (file != null) {
			this.sceneFile = file;
		}
		
		// FileOutputStream out;
		ByteArrayOutputStream bout;
		// String path = "C:\\FFVII\\Teste\\SceneGodo\\scene";
		Table table = new Table(new File("table.txt"), false);
		for (int i = 0; i < sceneData.size(); i++) {

			byte[] bufferScene = sceneData.get(i);

			byte[] bEnemyName1 = table.parseBytes(scenes.get(i).getEnemyName1());
			byte[] bEnemyName2 = table.parseBytes(scenes.get(i).getEnemyName2());
			byte[] bEnemyName3 = table.parseBytes(scenes.get(i).getEnemyName3());

			ArrayUtil.write(bEnemyName1, bufferScene, 664);
			ArrayUtil.write(bEnemyName2, bufferScene, 848);
			ArrayUtil.write(bEnemyName3, bufferScene, 1032);

			// out = new FileOutputStream(path + (i+1));
			bout = new ByteArrayOutputStream();
			// out.write(bufferScene, 0, 3712);
			bout.write(bufferScene, 0, 3712);

			byte[] bufferEnemyAI = ScriptAIReader.write(scenes.get(i).getEnemyAI(), 4096);
			// out.write(bufferEnemyAI);
			bout.write(bufferEnemyAI);

			sceneData.put(i, bout.toByteArray());

			bout.close();
			// out.close();
		}

		SceneFileIO.write(sceneData, sceneFile);
	}

	public SceneDataContainer getSceneData() {
		return sceneData;
	}

	private SceneEditorModel() {
	}

	public static SceneEditorModel getInstance() {
		if (instance == null)
			instance = new SceneEditorModel();
		return instance;
	}

	public List<Scene> getScenes() {
		return scenes;
	}

	public List<String> listCodeSectionNames() {

		ArrayList<String> sectionNames = new ArrayList<String>(Arrays.asList("Initialize", "Main", "General Counter",
				"Death Counter", "Physical Counter", "Magical Counter", "Battle End", "Pre-Action Setup",
				"Custom Event 1", "Custom Event 2", "Custom Event 3", "Custom Event 4", "Custom Event 5",
				"Custom Event 6", "Custom Event 7", "Custom Event 8"));

		return sectionNames;

	}

	public File getSceneFile() {
		return sceneFile;
	}

	public void setSceneFile(File sceneFile) {
		this.sceneFile = sceneFile;
	}

}

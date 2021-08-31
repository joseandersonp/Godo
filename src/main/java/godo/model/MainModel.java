package godo.model;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import godo.scene.AdditionalEffect;
import godo.scene.DamageConsideration;
import godo.scene.DamageFormula;
import godo.scene.ElementRate;
import godo.scene.ElementType;
import godo.scene.Item;
import godo.scene.Scene;
import godo.scene.SceneDataContainer;
import godo.scene.SceneFileIO;
import godo.scene.SceneIO;

public class MainModel {

	private static MainModel instance;

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

		if (file != null)
			this.sceneFile = file;

		for (int i = 0; i < scenes.size(); i++) {
			byte[] buffer = SceneIO.encode(scenes.get(i));
			sceneData.put(i, buffer);
		}
		SceneFileIO.write(sceneData, sceneFile);
	}

	public void saveAllScenes(File sceneDir) throws Exception {

		for (int i = 0; i < scenes.size(); i++) {

			String path = sceneDir.getAbsolutePath() + "\\scene" + (i + 1);
			FileOutputStream out = new FileOutputStream(path);
			byte[] buffer = SceneIO.encode(scenes.get(i));
			out.write(buffer);
			out.flush();
			out.close();

		}
	}

	public SceneDataContainer getSceneData() {
		return sceneData;
	}

	private MainModel() {
	}

	public static MainModel getInstance() {
		if (instance == null)
			instance = new MainModel();
		return instance;
	}

	public List<Scene> getScenes() {
		return scenes;
	}

	public List<String> listCodeSectionNames() {

		ArrayList<String> list = new ArrayList<String>(Arrays.asList("Initialize", "Main", "General Counter",
				"Death Counter", "Physical Counter", "Magical Counter", "Battle End", "Pre-Action Setup",
				"Custom Event 1", "Custom Event 2", "Custom Event 3", "Custom Event 4", "Custom Event 5",
				"Custom Event 6", "Custom Event 7", "Custom Event 8"));

		return list;

	}

	public List<DamageConsideration> listDamageConsiderations() {
		return Arrays.asList(DamageConsideration.values());
	}

	public List<DamageFormula> listDamageFormulas(int idx) {
		return Arrays.asList(DamageFormula.values(idx));
	}

	public List<ElementType> listElementType() {
		return Arrays.asList(ElementType.values());
	}

	public List<ElementRate> listElementRate() {
		return Arrays.asList(ElementRate.values());
	}

	public List<AdditionalEffect> listAdditionalEffects() {
		return Arrays.asList(AdditionalEffect.values());
	}

	public List<Item> listItem() {
		return Arrays.asList(Item.values());
	}

	public File getSceneFile() {
		return sceneFile;
	}

	public void setSceneFile(File sceneFile) {
		this.sceneFile = sceneFile;
	}

}

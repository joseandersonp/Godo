package godo.scene;

public class SceneDataContainer {

	private byte[][] scenes = new byte[256][];

	public byte[] get(int idx) {
		return scenes[idx];
	}

	public void put(int idx, byte[] sceneData) {
		scenes[idx] = sceneData;
	}

	public int size() {
		return scenes.length;
	}

}

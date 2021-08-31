package godo.scene;

import java.io.File;
import java.util.Arrays;

import godo.script.Table;
import godo.util.MemBuffer;
import javafx.beans.property.Property;

public class SceneIO {

	public static Scene decode(byte[] data) throws Exception {

		Table table = new Table(new File("table.txt"));

		MemBuffer lin = MemBuffer.wrap(data);

		Scene scene = new Scene();

		for (int i = 0; i < 3; i++) {
			Enemy enemy = new Enemy();
			enemy.setId(toNullIfMaxShort(lin.getUShort()));
			scene.getEnemys()[i] = enemy;
		}
		lin.skip(2);

		for (int i = 0; i < 4; i++) {

			BattleSetup battleS = new BattleSetup();
			battleS.setLocation(BattleLocation.get(lin.getUShort()));
			battleS.setUpponDefeat(lin.getUShort());
			battleS.setScapeCounter(lin.getUShort());
			lin.skip(2);

			for (int j = 0; j < 4; j++) {
				battleS.getFormationIdC()[j] = lin.getUShort();
			}

			battleS.setScapableFlag(lin.getUShort());
			battleS.setBattleLayout(BattleLayout.get(lin.getU()));
			battleS.setIndexedPreBattle(lin.getU());

			scene.getBattleSetup()[i] = battleS;

		}

		for (int i = 0; i < 4; i++) {

			CameraPlacement cameraP = new CameraPlacement();

			for (int j = 0; j < 3; j++) {

				cameraP.getPositionX()[j] = lin.getUShort();
				cameraP.getPositionY()[j] = lin.getUShort();
				cameraP.getPositionZ()[j] = lin.getUShort();
				cameraP.getDirectionX()[j] = lin.getUShort();
				cameraP.getDirectionY()[j] = lin.getUShort();
				cameraP.getDirectionZ()[j] = lin.getUShort();

			}
			lin.skip(12);
			scene.getCameraPlacement()[i] = cameraP;
		}

		for (int i = 0; i < 4; i++) {

			for (int j = 0; j < 6; j++) {

				BattleFormation battleF = new BattleFormation();
				int c = 0;
				int id = lin.getUShort();
				while (c < 3) {
					Integer id2 = scene.getEnemys()[c].getId();
					if (id2 != null && id2 == id)
						battleF.setEnemy(scene.getEnemys()[c]);
					c++;
				}

				battleF.setPositionX(lin.getUShort());
				battleF.setPositionY(lin.getUShort());
				battleF.setPositionZ(lin.getUShort());
				battleF.setRow(lin.getUShort());
				battleF.setCoverFlags(lin.getUShort());

				battleF.setInitialCondition(lin.getInt());

				scene.getBattleFormation()[i][j] = battleF;
			}
		}

		for (int i = 0; i < 3; i++) {

			Enemy enemy = scene.getEnemys()[i];

			byte[] buffer = new byte[32];
			lin.get(buffer);
			enemy.setName(getString32(buffer, table));
			enemy.setLevel(toNullIfMaxByte(lin.getU()));
			enemy.setSpeed(toNullIfMaxByte(lin.getU()));
			enemy.setLuck(toNullIfMaxByte(lin.getU()));
			enemy.setEvade(toNullIfMaxByte(lin.getU()));
			enemy.setStrength(toNullIfMaxByte(lin.getU()));
			enemy.setDefense(toNullIfMaxByte(lin.getU()));
			enemy.setMagic(toNullIfMaxByte(lin.getU()));
			enemy.setMagicDefense(toNullIfMaxByte(lin.getU()));

			for (int j = 0; j < 8; j++)
				enemy.addElementType(ElementType.get(lin.getU()));

			for (int j = 0; j < 8; j++)
				enemy.addElementRate(ElementRate.get(lin.getU()));

			for (int j = 0; j < 16; j++)
				enemy.addActionAnimationIndex(toNullIfMaxByte(lin.getU()));

			for (int j = 0; j < 16; j++)
				enemy.addEnemyAttackId(lin.getUShort());

			for (int j = 0; j < 16; j++)
				enemy.addEnemyAttackCMId(toNullIfMaxShort(lin.getUShort()));

			for (int j = 0; j < 4; j++) {
				int rate = lin.getU();
				enemy.addDropSteal(rate > 0x80);
				rate = rate > 0x80 ? rate - 0x80 : rate;
				enemy.addDropStealRate(rate);
			}

			for (int j = 0; j < 4; j++)
				enemy.addItemDropSteal(Item.get(lin.getUShort()));

			for (int j = 0; j < 3; j++)
				enemy.addEnemyManipAttackId(lin.getUShort());

			enemy.setUnknown(lin.getUShort());
			enemy.setMp(toNullIfMaxShort(lin.getUShort()));
			enemy.setAp(toNullIfMaxShort(lin.getUShort()));
			enemy.setMorphItem(Item.get(lin.getUShort()));

			enemy.setBackDamageMultiplier(toNullIfMaxByte(lin.getU()));
			enemy.setAlign(lin.getU());

			enemy.setHp(toNullIfMaxInt(lin.getInt()));
			enemy.setExpReceived(toNullIfMaxInt(lin.getInt()));
			enemy.setGilReceived(toNullIfMaxInt(lin.getInt()));
			enemy.setStatus(lin.getInt());
			enemy.setUnknown2(lin.getInt());
		}

		for (int i = 0; i < 32; i++) {

			Attack attack = new Attack();

			attack.setRate(toNullIfMaxByte(lin.getU()));
			attack.setImpactEffectId(toNullIfMaxByte(lin.getU()));
			attack.setTargetHurtActIndex(toNullIfMaxByte(lin.getU()));
			attack.setUnknown(lin.getU());

			attack.setCastingCost(toNullIfMaxShort(lin.getUShort()));
			attack.setImpactSound(toNullIfMaxShort(lin.getUShort()));
			attack.setCameraMidSTarget(toNullIfMaxShort(lin.getUShort()));
			attack.setCameraMidMTarget(toNullIfMaxShort(lin.getUShort()));

			attack.setTarget(lin.getU());
			attack.setAttackEffectId(toNullIfMaxByte(lin.getU()));
			attack.setDamageCalculation(lin.getU());
			attack.setStrengthAttackforDC(toNullIfMaxByte(lin.getU()));

			attack.setConditionSubmenu(lin.getU());
			attack.setStatusEffectChange(toNullIfMaxByte(lin.getU()));

			attack.setAtckAddEffects(lin.getU());
			attack.setSEffectMod(toNullIfMaxByte(lin.getU()));

			attack.setStatus(toZeroIfMaxInt(lin.getInt()));

			attack.setElement(lin.getUShort());
			attack.setSpecial(lin.getUShort());

			scene.getAttack()[i] = attack;

		}

		for (int i = 0; i < 32; i++)
			scene.getAttack()[i].setId(toNullIfMaxShort(lin.getUShort()));

		byte[] buffer = new byte[32];
		for (int i = 0; i < 32; i++) {
			lin.get(buffer);
			scene.getAttack()[i].setName(getString32(buffer, table));
		}

		buffer = new byte[0xE80 - lin.getOffset()];
		lin.get(buffer);
		AIScript[] formationAIScript = ScriptAIReader.read(buffer, 4);
		scene.setFormationAI(formationAIScript);

		buffer = new byte[lin.getOffset()];
		lin.get(buffer);
		AIScript[] enemyAIScript = ScriptAIReader.read(buffer, 3);
		scene.setEnemyAI(enemyAIScript);

		for (Enemy enemy : scene.getEnemys()) {
			for (int c = 0; c < enemy.getAttacks().size(); c++) {
				int id = enemy.getAttacks().get(c).getValue().getId();
				if (id == 0xFFFF)
					continue;

				for (Attack attack : scene.getAttack()) {
					if (attack.getId() == id) {
						enemy.getAttacks().get(c).setValue(attack);
						break;
					}
				}
			}
		}

		for (Enemy enemy : scene.getEnemys()) {
			for (int c = 0; c < enemy.getManipAttacks().size(); c++) {

				Integer id = enemy.getManipAttacks().get(c).getValue().getId();
				if (id == null)
					continue;

				for (Attack attack : scene.getAttack()) {
					if (attack.getId() == id) {
						enemy.getManipAttacks().get(c).setValue(attack);
						break;
					}
				}
			}
		}
		
		return scene;
	}

	public static byte[] encode(Scene scene) throws Exception {

		Table table = new Table(new File("table.txt"), false);
		MemBuffer lout = MemBuffer.allocate(0x1E80);

		lout.putShort(toMaxShortIfNull(scene.getEnemys()[0].getId()));
		lout.putShort(toMaxShortIfNull(scene.getEnemys()[1].getId()));
		lout.putShort(toMaxShortIfNull(scene.getEnemys()[2].getId()));
		lout.putShort(0xFFFF);

		for (int i = 0; i < scene.getBattleSetup().length; i++) {

			BattleSetup battleS = scene.getBattleSetup()[i];

			lout.putShort(battleS.getLocation().getId());
			lout.putShort(battleS.getUpponDefeat());
			lout.putShort(battleS.getScapeCounter());

			lout.putShort(0xFFFF);

			for (int j = 0; j < battleS.getFormationIdC().length; j++) {
				lout.putShort(battleS.getFormationIdC()[j]);
			}

			lout.putShort(battleS.getScapableFlag());
			lout.put(battleS.getBattleLayout().getId());
			lout.put(battleS.getIndexedPreBattle());
		}

		for (int i = 0; i < scene.getCameraPlacement().length; i++) {

			CameraPlacement cameraP = scene.getCameraPlacement()[i];

			for (int j = 0; j < 3; j++) {
				lout.putShort(cameraP.getPositionX()[j]);
				lout.putShort(cameraP.getPositionY()[j]);
				lout.putShort(cameraP.getPositionZ()[j]);
				;
				lout.putShort(cameraP.getDirectionX()[j]);
				lout.putShort(cameraP.getDirectionY()[j]);
				lout.putShort(cameraP.getDirectionZ()[j]);
			}
			lout.putInt(0xFFFFFFFF);
			lout.putInt(0xFFFFFFFF);
			lout.putInt(0xFFFFFFFF);

		}

		for (int i = 0; i < scene.getBattleFormation().length; i++) {

			for (int j = 0; j < scene.getBattleFormation()[i].length; j++) {

				BattleFormation battleF = scene.getBattleFormation()[i][j];

				if (battleF.getEnemy() == null)
					lout.putShort(0xFFFF);
				else
					lout.putShort(battleF.getEnemy().getId());

				lout.putShort(battleF.getPositionX());
				lout.putShort(battleF.getPositionY());
				lout.putShort(battleF.getPositionZ());
				lout.putShort(battleF.getRow());
				lout.putShort(battleF.getCoverFlags());

				lout.putInt(battleF.getInitialCondition());
			}
		}

		for (int i = 0; i < scene.getEnemys().length; i++) {

			Enemy enemy = scene.getEnemys()[i];

			byte[] buffer = table.parseBytes(enemy.getName());
			byte[] buffer2 = new byte[32];
			Arrays.fill(buffer2, (byte) 0xFF);
			for (int j = 0; j < buffer2.length - 1; j++) {
				if (j == buffer.length)
					break;
				buffer2[j] = buffer[j];
			}
			lout.put(buffer2);

			lout.put(toMaxByteIfNull(enemy.getLevel()));
			lout.put(toMaxByteIfNull(enemy.getSpeed()));
			lout.put(toMaxByteIfNull(enemy.getLuck()));
			lout.put(toMaxByteIfNull(enemy.getEvade()));
			lout.put(toMaxByteIfNull(enemy.getStrength()));
			lout.put(toMaxByteIfNull(enemy.getDefense()));
			lout.put(toMaxByteIfNull(enemy.getMagic()));
			lout.put(toMaxByteIfNull(enemy.getMagicDefense()));

			for (Property<ElementType> elem : enemy.getElementTypes())
				lout.put(elem.getValue().getId());

			for (Property<ElementRate> elem : enemy.getElementRates())
				lout.put(elem.getValue().getId());

			for (Property<Integer> aai : enemy.getActionAnimationIndex())
				lout.put(toMaxByteIfNull(aai.getValue()));

			for (Property<Attack> attk : enemy.getAttacks())
				lout.putShort(attk.getValue().getId());

			for (Property<Integer> eacmid : enemy.getEnemyAttackCMId())
				lout.putShort(toMaxShortIfNull(eacmid.getValue()));

			for (int j = 0; j < enemy.getItemDropSteals().size(); j++) {
				Item item = enemy.getItemDropSteals().get(j).getValue();
				if (Item.NOTHING.equals(item))
					lout.put(0xFF);
				else {
					Boolean dropSteal = enemy.getDropSteals().get(j).getValue();
					Integer rate = enemy.getDropStealRates().get(j).getValue();
					if (dropSteal)
						rate = rate + 0x80;
					lout.put(rate);
				}
			}

			for (Property<Item> itemp : enemy.getItemDropSteals())
				lout.putShort(itemp.getValue().getId());

			for (Property<Attack> valu : enemy.getManipAttacks())
				lout.putShort(valu.getValue().getId());

			lout.putShort(enemy.getUnknown());
			lout.putShort(toMaxShortIfNull(enemy.getMp()));
			lout.putShort(toMaxShortIfNull(enemy.getApReceived()));
			lout.putShort(enemy.getMorphItem().getId());

			lout.put(toMaxByteIfNull(enemy.getBackDamageMultiplier()));
			lout.put(enemy.getAlign());

			lout.putInt(toMaxIntIfNull(enemy.getHp()));
			lout.putInt(toMaxIntIfNull(enemy.getExpReceived()));
			lout.putInt(toMaxIntIfNull(enemy.getGilReceived()));
			lout.putInt(enemy.getStatus());
			lout.putInt(enemy.getUnknown2());
		}

		for (int i = 0; i < scene.getAttack().length; i++) {

			Attack attack = scene.getAttack()[i];

			lout.put(toMaxByteIfNull(attack.getRate()));
			lout.put(toMaxByteIfNull(attack.getImpactEffectId()));
			lout.put(toMaxByteIfNull(attack.getTargetHurtActIndex()));
			lout.put(toMaxByteIfNull(attack.getUnknown()));

			lout.putShort(toMaxShortIfNull(attack.getCastingCost()));
			lout.putShort(toMaxShortIfNull(attack.getImpactSound()));
			lout.putShort(toMaxShortIfNull(attack.getCameraMidSTarget()));
			lout.putShort(toMaxShortIfNull(attack.getCameraMidMTarget()));

			lout.put(attack.getTarget());
			lout.put(toMaxByteIfNull(attack.getAttackEffectId()));
			lout.put(attack.getDamageCalculation());
			lout.put(toMaxByteIfNull(attack.getStrengthAttackforDC()));

			lout.put(attack.getConditionSubmenu());
			lout.put(toMaxByteIfNull(attack.getStatusEffectChange()));
			lout.put(attack.getAtckAddEffects());
			lout.put(toMaxByteIfNull(attack.getSEffectMod()));

			lout.putInt(toMaxIfZero(attack.getStatus()));
			lout.putShort(attack.getElement());
			lout.putShort(attack.getSpecial());

		}

		for (int i = 0; i < scene.getAttack().length; i++)
			lout.putShort(toMaxShortIfNull(scene.getAttack()[i].getId()));

		for (int j = 0; j < scene.getAttack().length; j++) {
			lout.put(parseBytes32(scene.getAttack()[j].getName(), table));
		}

		byte[] bufferAI = ScriptAIReader.write(scene.getFormationAI(), 512);
		lout.put(bufferAI);

		bufferAI = ScriptAIReader.write(scene.getEnemyAI(), 4096);
		lout.put(bufferAI);
		
		return lout.array();

	}

	public static String getString32(byte[] buffer, Table table) {
		int j = 0;
		while (j < buffer.length) {
			if (buffer[j] == -1)
				break;
			j++;
		}
		return table.format(buffer, 0, j);
	}

	private static Integer toNullIfMaxByte(Integer value) {
		return value == 255 ? null : value; // if 0xFF return null;
	}

	private static Integer toZeroIfMaxInt(Integer value) {
		return toNullIfMaxInt(value) == null ? 0 : value;
	}

	private static Integer toNullIfMaxInt(Integer value) {
		return value == -1 ? null : value; // if 0xFFFFFFFF return null;
	}
	
	private static Integer toMaxIntIfNull(Integer value) {		
		return value == null ? -1 : value;
	}
	
	private static Integer toMaxByteIfNull(Integer value) {
		return value == null ? 0XFF : value;
	}
	
	private static Integer toMaxShortIfNull(Integer value) {
		return value == null ? 0XFFFF : value;
	}
	
	private static Integer toNullIfMaxShort(int value) {
		return value == 0XFFFF ? null : value;
	}

	private static Integer toMaxIfZero(Integer value) {
		return value == 0 ? -1 : value;
	}

	private static byte[] parseBytes32(String string, Table table) {
		byte[] buffer = table.parseBytes(string);
		byte[] buffer32 = new byte[32];
		Arrays.fill(buffer32, (byte) 0xFF);
		for (int j = 0; j < buffer32.length - 1; j++) {
			if (j == buffer.length)
				break;
			buffer32[j] = buffer[j];
		}
		return buffer32;
	}

}

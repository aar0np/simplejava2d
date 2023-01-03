package game2d;

import entity.OldMan;
import monster.GreenSlime;

//import object.Boots;
//import object.Chest;
//import object.Door;
//import object.Key;

public class ObjectFactory {

	GamePanel gp;
	int tileSize;
	
	public ObjectFactory(GamePanel gamePanel) {
		this.gp = gamePanel;
		tileSize = gp.getTileSize();
	}
	
	// RPG version generateObjects
	//
	public void generateObjects() {
	
	}
	
	public void generateNPCs() {
		
		gp.npcs[0] = new OldMan(gp);
		gp.npcs[0].setWorldX(tileSize * 21);
		gp.npcs[0].setWorldY(tileSize * 21);		
	}
	
	public void generateMonsters() {
		gp.monsters[0] = new GreenSlime(gp);
		gp.monsters[0].setWorldX(tileSize * 23);
		gp.monsters[0].setWorldY(tileSize * 36);

		gp.monsters[1] = new GreenSlime(gp);
		gp.monsters[1].setWorldX(tileSize * 23);
		gp.monsters[1].setWorldY(tileSize * 37);
	}
	
// treasure hunting verison generateObjects
//
// 	public void generateObjects() {
//
//		// keys
//		gp.getObjects()[0] = new Key(tileSize);
//		gp.getObjects()[0].setWorldX(23 * tileSize);
//		gp.getObjects()[0].setWorldY(7 * tileSize);
//		
//		gp.getObjects()[1] = new Key(tileSize);
//		gp.getObjects()[1].setWorldX(23 * tileSize);
//		gp.getObjects()[1].setWorldY(40 * tileSize);
//
//		gp.getObjects()[2] = new Key(tileSize);
//		gp.getObjects()[2].setWorldX(38 * tileSize);
//		gp.getObjects()[2].setWorldY(8 * tileSize);
//
//		// doors
//		gp.getObjects()[3] = new Door(tileSize);
//		gp.getObjects()[3].setWorldX(10 * tileSize);
//		gp.getObjects()[3].setWorldY(11 * tileSize);
//
//		gp.getObjects()[4] = new Door(tileSize);
//		gp.getObjects()[4].setWorldX(8 * tileSize);
//		gp.getObjects()[4].setWorldY(28 * tileSize);
//
//		gp.getObjects()[5] = new Door(tileSize);
//		gp.getObjects()[5].setWorldX(12 * tileSize);
//		gp.getObjects()[5].setWorldY(22 * tileSize);
//
//		// chests
//		gp.getObjects()[6] = new Chest(tileSize);
//		gp.getObjects()[6].setWorldX(10 * tileSize);
//		gp.getObjects()[6].setWorldY(7 * tileSize);
//		
//		// boots
//		gp.getObjects()[7] = new Boots(tileSize);
//		gp.getObjects()[7].setWorldX(37 * tileSize);
//		gp.getObjects()[7].setWorldY(42 * tileSize);
//	}
}

package game2d;

import object.Boots;
import object.Chest;
import object.Door;
import object.Key;

public class ObjectFactory {

	GamePanel gp;
	int tileSize;
	
	public ObjectFactory(GamePanel gamePanel) {
		this.gp = gamePanel;
		tileSize = gp.getTileSize();
	}
	
	public void generateObjects() {
		
		// keys
		gp.getObjects()[0] = new Key();
		gp.getObjects()[0].setWorldX(23 * tileSize);
		gp.getObjects()[0].setWorldY(7 * tileSize);
		
		gp.getObjects()[1] = new Key();
		gp.getObjects()[1].setWorldX(23 * tileSize);
		gp.getObjects()[1].setWorldY(40 * tileSize);

		gp.getObjects()[2] = new Key();
		gp.getObjects()[2].setWorldX(38 * tileSize);
		gp.getObjects()[2].setWorldY(8 * tileSize);

		// doors
		gp.getObjects()[3] = new Door();
		gp.getObjects()[3].setWorldX(10 * tileSize);
		gp.getObjects()[3].setWorldY(11 * tileSize);

		gp.getObjects()[4] = new Door();
		gp.getObjects()[4].setWorldX(8 * tileSize);
		gp.getObjects()[4].setWorldY(28 * tileSize);

		gp.getObjects()[5] = new Door();
		gp.getObjects()[5].setWorldX(12 * tileSize);
		gp.getObjects()[5].setWorldY(22 * tileSize);

		// chests
		gp.getObjects()[6] = new Chest();
		gp.getObjects()[6].setWorldX(10 * tileSize);
		gp.getObjects()[6].setWorldY(7 * tileSize);
		
		// boots
		gp.getObjects()[7] = new Boots();
		gp.getObjects()[7].setWorldX(37 * tileSize);
		gp.getObjects()[7].setWorldY(42 * tileSize);
	}
}

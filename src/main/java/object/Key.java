package object;

import entity.Entity;
import game2d.GamePanel;

public class Key extends Entity {

	public Key(GamePanel gp) {
		super(gp);
		
		name = "Key";
		description = "[" + name + "]\nIt opens a door.";
		down1 = setupEntityImage("/objects/key.png", tileSize, tileSize);
	}
}

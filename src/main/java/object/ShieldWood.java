package object;

import entity.Entity;
import game2d.GamePanel;

public class ShieldWood extends Entity {

	public ShieldWood(GamePanel gp) {
		super(gp);

		name = "Wooden Shield";
		type = SHIELD;
		description = "[" + name + "]\nA wooden shield.";
		down1 = setupEntityImage("/objects/shield_wood.png", tileSize, tileSize);
		defenseValue = 1;
	}

}

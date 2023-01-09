package object;

import entity.Entity;
import game2d.GamePanel;

public class BlueShield extends Entity {

	public BlueShield(GamePanel gp) {
		super(gp);

		name = "Blue Shield";
		type = SHIELD;
		description = "[" + name + "]\nThe Blue Shield.";
		down1 = setupEntityImage("/objects/shield_blue.png", tileSize, tileSize);
		defenseValue = 2;
	}
}

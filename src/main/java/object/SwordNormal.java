package object;

import entity.Entity;
import game2d.GamePanel;

public class SwordNormal extends Entity {

	public SwordNormal(GamePanel gp) {
		super(gp);

		name = "Sword";
		type = SWORD;
		description = "[" + name + "]\nAn old sword.";
		down1 = setupEntityImage("/objects/sword_normal.png", tileSize, tileSize);
		attackArea.width = 36;
		attackArea.height = 36;
		attackValue = 1;
	}

}

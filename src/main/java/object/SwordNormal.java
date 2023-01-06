package object;

import entity.Entity;
import game2d.GamePanel;

public class SwordNormal extends Entity {

	public SwordNormal(GamePanel gp) {
		super(gp);

		name = "Sword";
		down1 = setupEntityImage("/objects/sword_normal.png", tileSize, tileSize);
		attackValue = 1;
	}

}

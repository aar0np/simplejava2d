package object;

import entity.Entity;
import game2d.GamePanel;

public class Axe extends Entity {

	public Axe (GamePanel gp) {
		super(gp);
		
		name = "Woodcutter's Axe";
		type = AXE;
		description = "[" + name + "]\nAn axe";
		down1 = setupEntityImage("/objects/axe.png", tileSize, tileSize);
		attackArea.width = 30;
		attackArea.height = 30;
		attackValue = 2;
	}
}

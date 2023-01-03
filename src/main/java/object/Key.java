package object;

import entity.Entity;
import game2d.GamePanel;

public class Key extends Entity {

	public Key(GamePanel gp) {
		super(gp);
		
		name = "Key";
		down1 = setupEntityImage("/objects/key.png");
	}
}

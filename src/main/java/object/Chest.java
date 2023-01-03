package object;

import entity.Entity;
import game2d.GamePanel;

public class Chest extends Entity {

	public Chest(GamePanel gp) {
		super(gp);
		
		name = "Chest";
		down1 = setupEntityImage("/objects/chest.png");
	}
}

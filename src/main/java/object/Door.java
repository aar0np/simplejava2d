package object;

import entity.Entity;
import game2d.GamePanel;

public class Door extends Entity {

	public Door(GamePanel gp) {
		super(gp);
		
		name = "Door";
		
		collision = true;
		
		down1 = setupEntityImage("/objects/door.png", tileSize, tileSize);
	}
}
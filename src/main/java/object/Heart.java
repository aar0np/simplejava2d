package object;

import entity.Entity;
import game2d.GamePanel;

public class Heart extends Entity {

	public Heart(GamePanel gp) {
		super(gp);
		
		name = "Heart";
		
		image = setupEntityImage("/objects/heart_full.png", tileSize, tileSize);
		image2 = setupEntityImage("/objects/heart_half.png", tileSize, tileSize);
		image3 = setupEntityImage("/objects/heart_blank.png", tileSize, tileSize);
	}
}

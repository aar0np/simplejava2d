package object;

import entity.Entity;
import game2d.GamePanel;

public class Boots extends Entity {

	public Boots(GamePanel gp) {
		super(gp);
		
		name = "Boots";
		down1 = setupEntityImage("/objects/boots.png");
	}
}

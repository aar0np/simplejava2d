package object;

import entity.Entity;
import game2d.GamePanel;

public class ManaCrystal extends Entity {
	
	int manaValue;
	
	public ManaCrystal(GamePanel gp) {
		super(gp);

		name = "Mana Crystal";
		type = REGULAR_ITEM;
		manaValue = 1;
		image = setupEntityImage("/objects/manacrystal_full.png", tileSize, tileSize);
		image2 = setupEntityImage("/objects/manacrystal_blank.png", tileSize, tileSize);
		
		down1 = image;
	}

	public void use(Entity user) {
		gp.playSoundEffect(2);
		gp.getGameUI().addMessage("Mana +" + manaValue);
		user.increaseMana(manaValue);
	}
}

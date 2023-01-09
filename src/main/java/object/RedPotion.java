package object;

import entity.Entity;
import game2d.GamePanel;

public class RedPotion extends Entity {

	int healingValue = 5;
	
	public RedPotion(GamePanel gp) {
		super(gp);
		
		type = CONSUMABLE;
		name = "Red Potion";
		down1 = setupEntityImage("/objects/potion_red.png", tileSize, tileSize);
		description = "[" + name + "] gives\n" + healingValue + " health points.";
		
	}
	
	public void use(Entity entity) {
		gp.setGameState(gp.DIALOG_STATE);
		gp.getGameUI().setCurrentDialog("Your health has been recovered by\n" + healingValue + " points.");
		entity.increaseHealth(healingValue);
		gp.playSoundEffect(2);
	}
	
	public int getHealingValue() {
		return this.healingValue;
	}
}

package object;

import entity.Entity;
import game2d.GamePanel;

public class BronzeCoin extends Entity {

	int monetaryValue;
	
	public BronzeCoin(GamePanel gp) {
		super(gp);
	
		type = REGULAR_ITEM;
		name = "Bronze coin";
		down1 = setupEntityImage("/objects/coin_bronze.png", tileSize, tileSize);
		monetaryValue = 1;
	}

	public void use(Entity user) {
		gp.playSoundEffect(1);
		gp.getGameUI().addMessage("Coin +" + monetaryValue);
		user.incrementCoin(monetaryValue);
	}
}

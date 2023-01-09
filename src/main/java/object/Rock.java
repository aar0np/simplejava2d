package object;

import entity.Entity;
import entity.Projectile;
import game2d.GamePanel;

public class Rock extends Projectile {
	
	GamePanel gp;
	
	public Rock(GamePanel gp) {
		super(gp);

		this.gp = gp;
		name = "Rock";
		speed = 8;
		maxHealth = 80;
		currentHealth = maxHealth;
		attack = 2;
		useCost = 0;
		alive = false;
		
		getImages();
	}

	public void getImages() {
		up1 = setupEntityImage("/projectiles/rock_down_1.png", tileSize, tileSize);
		up2 = setupEntityImage("/projectiles/rock_down_1.png", tileSize, tileSize);
		down1 = setupEntityImage("/projectiles/rock_down_1.png", tileSize, tileSize);
		down2 = setupEntityImage("/projectiles/rock_down_1.png", tileSize, tileSize);
		right1 = setupEntityImage("/projectiles/rock_down_1.png", tileSize, tileSize);
		right2 = setupEntityImage("/projectiles/rock_down_1.png", tileSize, tileSize);
		left1 = setupEntityImage("/projectiles/rock_down_1.png", tileSize, tileSize);
		left2 = setupEntityImage("/projectiles/rock_down_1.png", tileSize, tileSize);
	}
	
	public boolean hasResource(Entity user) {
		
		boolean returnVal = false;
		
		if (user.getAmmunition() >= useCost) {
			returnVal = true;
		}
		
		return returnVal;
	}
	
	public void decreaseResource(Entity user) {
		
		user.decreaseAmmunition(useCost);
	}
}

package monster;

import java.util.Random;

import entity.Entity;
import game2d.GamePanel;
import object.BronzeCoin;
import object.Heart;
import object.ManaCrystal;
import object.RedPotion;
import object.Rock;

public class GreenSlime extends Entity {

	public GreenSlime(GamePanel gp) {
		super(gp);
		
		name = "Green Slime";
		type = MONSTER;
		speed = 1;
		maxHealth = 4;
		currentHealth = maxHealth;
		attack = 5;
		defense = 0;
		projectile = new Rock(gp);
		experiencePoints = 2;
		
		solidArea.x = 3;
		solidArea.y = 18;
		solidArea.width = 42;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getGreenSlimeImages();
	}
	
	public void getGreenSlimeImages() {
		up1 = setupEntityImage("/monsters/greenslime_down_1.png", tileSize, tileSize);
		up2 = setupEntityImage("/monsters/greenslime_down_2.png", tileSize, tileSize);
		down1 = setupEntityImage("/monsters/greenslime_down_1.png", tileSize, tileSize);
		down2 = setupEntityImage("/monsters/greenslime_down_2.png", tileSize, tileSize);
		right1 = setupEntityImage("/monsters/greenslime_down_1.png", tileSize, tileSize);
		right2 = setupEntityImage("/monsters/greenslime_down_2.png", tileSize, tileSize);
		left1 = setupEntityImage("/monsters/greenslime_down_1.png", tileSize, tileSize);
		left2 = setupEntityImage("/monsters/greenslime_down_2.png", tileSize, tileSize);
	}

	public void setAction() {
		//Green Slime AI
		actionLockCounter++;
		Random random = new Random();
		
		// only let the green slime move every 120 frames (2 seconds)
		if (actionLockCounter >= 120) {
			int rndNum = random.nextInt(100) + 1;
			
			if (rndNum <= 25) {
				direction = "up";
			} else if (rndNum > 25 && rndNum <= 50) {
				direction = "down";
			} else if (rndNum > 50 && rndNum <= 75) {
				direction = "left";
			} else {
				direction = "right";
			}
			
			actionLockCounter = 0;					
		}
		
		// throw/shoot rock
		int rndShotNum = random.nextInt(100) + 1;

		if (rndShotNum > 99 && !projectile.isAlive() && shotAvailableCounter >= 30) {
			projectile.set(worldX, worldY, direction, true, this);
			gp.getProjectiles().add(projectile);
			shotAvailableCounter = 0;
		}		
	}
	
	public void damageReaction() {

		actionLockCounter = 0;
		direction = gp.getPlayer().getDirection();
	}
	
	public void checkDrop() {
		int randomNum = new Random().nextInt(100) + 1;
		
		// 30-49 == nothing
		if (randomNum < 5) {
			dropItem(new RedPotion(gp));
		} else if (randomNum >= 5 && randomNum < 30) {
			dropItem(new BronzeCoin(gp));
		} else if (randomNum >= 50 && randomNum < 75) {
			dropItem(new Heart(gp));
		} else if (randomNum >= 75) {
			dropItem(new ManaCrystal(gp));
		}
	}
}

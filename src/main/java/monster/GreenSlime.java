package monster;

import java.util.Random;

import entity.Entity;
import game2d.GamePanel;

public class GreenSlime extends Entity {

	public GreenSlime(GamePanel gp) {
		super(gp);
		
		name = "Green Slime";
		speed = 1;
		int maxHealth = 4;
		int currentHealth = maxHealth;
		type = MONSTER;
		
		solidArea.x = 3;
		solidArea.y = 18;
		solidArea.width = 42;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getGreenSlimeImages();
	}
	
	public void getGreenSlimeImages() {
		up1 = setupEntityImage("/monsters/greenslime_down_1.png");
		up2 = setupEntityImage("/monsters/greenslime_down_2.png");
		down1 = setupEntityImage("/monsters/greenslime_down_1.png");
		down2 = setupEntityImage("/monsters/greenslime_down_2.png");
		right1 = setupEntityImage("/monsters/greenslime_down_1.png");
		right2 = setupEntityImage("/monsters/greenslime_down_2.png");
		left1 = setupEntityImage("/monsters/greenslime_down_1.png");
		left2 = setupEntityImage("/monsters/greenslime_down_2.png");
	}

	public void setAction() {
		//Green Slime AI
		actionLockCounter++;
		
		// only let the green slime move every 120 frames (2 seconds)
		if (actionLockCounter >= 120) {
			Random random = new Random();
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
	}
}

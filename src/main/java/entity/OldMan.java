package entity;

import java.util.Random;

import game2d.GamePanel;

public class OldMan extends Entity {

	int tileSize;
	
	public OldMan(GamePanel gp) {
		super(gp);
		tileSize = gp.getTileSize();
		
		name = "Old Man";
		direction = "down";
		speed = 1;
		dialogIndex = 0;
		type = NPC;
		
		getOldManImage();
		setDialog();
	}

	public void getOldManImage() {
		
		up1 = setupEntityImage("/npc/oldman_up_1.png");
		up2 = setupEntityImage("/npc/oldman_up_2.png");
		down1 = setupEntityImage("/npc/oldman_down_1.png");
		down2 = setupEntityImage("/npc/oldman_down_2.png");
		right1 = setupEntityImage("/npc/oldman_right_1.png");
		right2 = setupEntityImage("/npc/oldman_right_2.png");
		left1 = setupEntityImage("/npc/oldman_left_1.png");
		left2 = setupEntityImage("/npc/oldman_left_2.png");
	}

	public void setDialog() {

		dialogs[0] = "Hello, lad!";
		dialogs[1] = "So you've come to this island to\nfind the treasure.";
		dialogs[2] = "I used to be a great wizard, but now\nI'm a bit too old for adventure.";
		dialogs[3] = "Good luck!";
	}
	
	public void setAction() {
		//Old Man AI
		actionLockCounter++;
		
		// only let the old man move every 120 frames (2 seconds)
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
	
	public void speak() {
		super.speak();
	}
	
}

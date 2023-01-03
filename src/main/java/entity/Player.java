package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game2d.GamePanel;
import game2d.KeyHandler;

public class Player extends Entity {
	private int tileSize;
	
	KeyHandler keyHandler;
	
	final int screenX;
	final int screenY;
	// int hasKey = 0;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);
		
		this.keyHandler = keyH;
		
		tileSize = gp.getTileSize();
		
		// computing the middle of the screen
		screenX = (gp.getScreenWidth() / 2) - (tileSize / 2);
		screenY = (gp.getScreenHeight() / 2) - (tileSize / 2);
		
		// solid pixel area of player starts at 8,16 and is a 32x32 square
		solidArea = new Rectangle(8, 16, 32, 32);
		
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	private void setDefaultValues() {
		worldX = gp.getTileSize() * 23;
		worldY = gp.getTileSize() * 21;
		speed = 4;
		direction = "down";
		maxHealth = 6;
		currentHealth = maxHealth;
	}
	
	public void getPlayerImage() {
		
		up1 = setupEntityImage("/player/boy_up_1.png");
		up2 = setupEntityImage("/player/boy_up_2.png");
		down1 = setupEntityImage("/player/boy_down_1.png");
		down2 = setupEntityImage("/player/boy_down_2.png");
		right1 = setupEntityImage("/player/boy_right_1.png");
		right2 = setupEntityImage("/player/boy_right_2.png");
		left1 = setupEntityImage("/player/boy_left_1.png");
		left2 = setupEntityImage("/player/boy_left_2.png");
	}
	
	public void update() {
		
		if (keyHandler.isUpPressed() || keyHandler.isDownPressed()
				|| keyHandler.isRightPressed() || keyHandler.isLeftPressed()) {

			// This used to be one big if/else, but I separated them
			// to allow diagonal movement.
			if (keyHandler.isUpPressed()) {
				direction = "up";
			} else if (keyHandler.isDownPressed()) {
				direction = "down";
			}

			// check tile collision
			collisionOn = false;
			gp.getCollisionChecker().checkTile(this);

			// check object collision
			int objectIndex = gp.getCollisionChecker().checkObject(this, true);
			pickUpObject(objectIndex);

			// check NPC collision
			int npcIndex = gp.getCollisionChecker().checkEntity(this, gp.getNPCs());
			interactWNPC(npcIndex);

			// check Monster collision
			int monsterIndex = gp.getCollisionChecker().checkEntity(this, gp.getMonsters());
			contactMonster(monsterIndex);
			
			if (!collisionOn) {
				switch (direction) {
					case "up":
						worldY -= speed;
						break;
					case "down":
						worldY += speed;
						break;
				}
			}
			
			// now check left/right for collision
			if (keyHandler.isLeftPressed()) {
				direction = "left";
			} else if (keyHandler.isRightPressed()) {
				direction = "right";
			}

			// check tile collision again
			collisionOn = false;
			gp.getCollisionChecker().checkTile(this);

			// check object collision again
			objectIndex = gp.getCollisionChecker().checkObject(this, true);
			pickUpObject(objectIndex);

			// check NPC collision again
			npcIndex = gp.getCollisionChecker().checkEntity(this, gp.getNPCs());
			interactWNPC(npcIndex);

			// check Monster collision
			monsterIndex = gp.getCollisionChecker().checkEntity(this, gp.getMonsters());
			contactMonster(monsterIndex);

			// check events (should only need to do this once
			// ...maybe?
			gp.getEventHandler().checkEvent();
						
			if (!collisionOn) {
				switch (direction) {
					case "left":
						worldX -= speed;
						break;
					case "right":
						worldX += speed;
						break;						
				}
			}
			
			// how to influence the walking animation
			spriteCounter++;
			if (spriteCounter > 12) {
				if (spriteNum == 1) {
					spriteNum = 2;
				// } else if ( spriteNum == 2) {
				} else {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
		
		if (invincible) {
			invincibleCounter++;
			if (invincibleCounter >= 60) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
	}
	
// RPG version of pickUpObject
//
	public void pickUpObject(int index) {

		if (index != 999) {
			
		}
		
	}
	
	public void interactWNPC(int index) {
		
		if (index != 999) {
			if (gp.getKeyHandler().isEnterPressed()) {
				gp.setGameState(gp.DIALOG_STATE);
				gp.getNPCs()[index].speak();
			}
		}
	}
	
	public void contactMonster(int index) {
		if (index != 999) {
			if (!invincible) {
				decreaseHealth(1);
				gp.playSoundEffect(10);
				invincible = true;
			}
		}
	}
	
// Treasure hunt version pickUpObject	
//	
//	public void pickUpObject(int index) {
//		
//		if (index != 999) {
//			String objName = gp.getObjects()[index].getName();
//			
//			switch (objName) {
//				case "Key":
//					hasKey++;
//					gp.playSoundEffect(1);
//					gp.getObjects()[index] = null;
//					gp.getGameUI().showMessage("Key found!");
//					break;
//				case "Door":
//					if (hasKey > 0) {
//						gp.playSoundEffect(3);
//						gp.getObjects()[index] = null;
//						gp.getGameUI().showMessage("Door opened!");
//						hasKey--;
//					} else {
//						gp.getGameUI().showMessage("Door requires a key!");
//						// gp.playSoundEffect(5);
//					}
//					break;
//				case "Boots":
//					speed += 2;
//					gp.playSoundEffect(2);
//					gp.getObjects()[index] = null;
//					gp.getGameUI().showMessage("Boots of speed acquired!");
//					break;
//				case "Chest":
//					gp.getGameUI().setIsGameFinished(true);
//					gp.stopMusic();
//					gp.playSoundEffect(4);
//					break;
//			}
//		}
//	}
	
	public void draw(Graphics2D g2) {

		// testing
		//g2.setColor(Color.white);
		//g2.fillRect(x, y, tileSize, tileSize);
		
		BufferedImage image = null;
		
		switch(direction) {
			case "up":
				if (spriteNum == 1) {
					image = up1;
				} else {
					image = up2;
				}
				break;
				
			case "down":
				if (spriteNum == 1) {
					image = down1;
				} else {
					image = down2;
				}
				break;
				
			case "left":
				if (spriteNum == 1) {
					image = left1;
				} else {
					image = left2;
				}
				break;
				
			case "right":
				if (spriteNum == 1) {
					image = right1;
				} else {
					image = right2;
				}
				break;
		}
		
		if (invincible) {
			// while invincible, make slightly transparent
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		}
		
		g2.drawImage(image, screenX, screenY, null);
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}
	
	public int getScreenX() {
		return this.screenX;
	}
	
	public int getScreenY() {
		return this.screenY;
	}
	
//	public int getKeys() {
//		return hasKey;
//	}
	
	public int getMaxHealth() {
		return this.maxHealth;
	}
	
	public int getCurrentHealth() {
		return this.currentHealth;
	}
	
	public boolean isInvincible() {
		return this.invincible;
	}
	
	public void setInvincible(boolean invincible) {
		this.invincible = invincible;
	}
 }

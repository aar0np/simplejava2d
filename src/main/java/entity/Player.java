package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game2d.GamePanel;
import game2d.KeyHandler;

public class Player extends Entity {
	GamePanel gp;
	KeyHandler keyHandler;
	
	final int screenX;
	final int screenY;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyHandler = keyH;
		
		// setting the middle of the screen
		screenX = (gp.getScreenWidth() / 2) - (gp.getTileSize());
		screenY = (gp.getScreenHeight() / 2) - (gp.getTileSize());
		
		// solid pixel area of player starts at 8,16 and is a 32x32 square
		solidArea = new Rectangle(8, 16, 32, 32);
		
		setDefaultValues();
		getPlayerImage();
	}
	
	private void setDefaultValues() {
		worldX = gp.getTileSize() * 23;
		worldY = gp.getTileSize() * 21;
		speed = 4;
		direction = "down";
	}
	
	public void getPlayerImage() {
		
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
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

			// check collision
			collisionOn = false;
			gp.getCollisionChecker().checkTile(this);

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

			// check collision again
			collisionOn = false;
			gp.getCollisionChecker().checkTile(this);

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
	}
	public void draw(Graphics2D g2) {

		int tileSize = gp.getTileSize();
		
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
		
		g2.drawImage(image, screenX, screenY, tileSize, tileSize, null);
	}
	
	public int getScreenX() {
		return screenX;
	}
	
	public int getScreenY() {
		return screenY;
	}
	
}

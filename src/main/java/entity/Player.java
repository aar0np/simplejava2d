package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game2d.GamePanel;
import game2d.KeyHandler;

public class Player extends Entity {
	GamePanel gp;
	KeyHandler keyHandler;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyHandler = keyH;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	private void setDefaultValues() {
		x = 100;
		y = 100;
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
				y -= speed;	
			} else if (keyHandler.isDownPressed()) {
				direction = "down";
				y += speed;
			}
			
			if (keyHandler.isLeftPressed()) {
				direction = "left";
				x -= speed;
			} else if (keyHandler.isRightPressed()) {
				direction = "right";
				x += speed;
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
		
		g2.drawImage(image, x, y, tileSize, tileSize, null);
	}
}

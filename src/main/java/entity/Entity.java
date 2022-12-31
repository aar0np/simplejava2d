package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
	protected int worldX;
	protected int worldY;
	protected int speed;
	
	protected BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	protected String direction;
	
	protected int spriteCounter = 0;
	protected int spriteNum = 1;
	
	protected Rectangle solidArea;
	protected boolean collisionOn = false;
	
	public int getWorldX() {
		return worldX;
	}

	public int getWorldY() {
		return worldY;
	}
	
	public int getSpeed() {
		return speed;
	}

	public Rectangle getSolidArea() {
		return solidArea;
	}

	public boolean isCollisionOn() {
		return collisionOn;
	}

	public void setCollisionOn(boolean collisionOn) {
		this.collisionOn = collisionOn;
	}
	
	public String getDirection() {
		return direction;
	}
}

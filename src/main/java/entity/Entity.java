package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game2d.GamePanel;
import game2d.GraphicsTools;

public class Entity {
	protected int worldX;
	protected int worldY;
	protected int speed;
	
	protected BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	protected String direction = "down";
	
	protected int spriteCounter = 0;
	protected int spriteNum = 1;
	
	protected Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	protected boolean collisionOn = false;
	
	protected int solidAreaDefaultX;
	protected int solidAreaDefaultY;
	
	protected GamePanel gp;
	
	protected int tileSize;
	protected int actionLockCounter = 0;
	int dialogIndex = 0;
	int maxHealth;
	int currentHealth;
	String dialogs[] = new String[20];
	
	protected BufferedImage image;
	protected BufferedImage image2;
	protected BufferedImage image3;
	protected String name;
	protected boolean collision = false;
	protected boolean invincible = false;
	protected int invincibleCounter = 0;
	protected int type;
	protected final int NPC = 1;
	protected final int MONSTER = 2;
	
	public Entity(GamePanel gp) {
		this.gp = gp;
		tileSize = gp.getTileSize();
	}
	
	public void setAction() {
		
	}
	
	public void speak() {
		if (dialogs[dialogIndex] == null) {
			dialogIndex = 0;
		}
		
		gp.getGameUI().setCurrentDialog(dialogs[dialogIndex]);
		dialogIndex++;
		
		switch (gp.getPlayer().getDirection()) {
		case "up":
			direction = "down";
			break;
		case "down":
			direction = "up";			
			break;
		case "left":
			direction = "right";
			break;
		case "right":
			direction = "left";
			break;
		}		
	}
	
	public void update() {
		setAction();
		
		collisionOn = false;
		gp.getCollisionChecker().checkTile(this);
		gp.getCollisionChecker().checkObject(this, false);
		gp.getCollisionChecker().checkEntity(this, gp.getNPCs());
		gp.getCollisionChecker().checkEntity(this, gp.getMonsters());
		boolean contactPlayer = gp.getCollisionChecker().checkPlayer(this);
		
		if (this.type == MONSTER && contactPlayer) {
			if (!gp.getPlayer().isInvincible()) {
				gp.getPlayer().decreaseHealth(1);
				gp.playSoundEffect(10);
				gp.getPlayer().setInvincible(true);
			}
		}
		
		if (!collisionOn) {
			switch (direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;						
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
	
	protected BufferedImage setupEntityImage(String imagePath) {
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath));
			// scale player tile
			image = GraphicsTools.scaleTile(image, tileSize);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return image;
	}
	
	public void draw(Graphics2D g2) {
	
		BufferedImage image = null;
		int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
		int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();
		
		// if check so that we only draw tiles which are visible.
		if (worldX + tileSize > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() &&
			worldX - tileSize < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() &&
			worldY + tileSize > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() &&
			worldY - tileSize < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY()) {

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
	}
	
	public int getWorldX() {
		return worldX;
	}

	public void setWorldX(int coordX) {
		this.worldX = coordX;
	}
	
	public int getWorldY() {
		return worldY;
	}
	
	public void setWorldY(int coordY) {
		this.worldY = coordY;
	}
	
	public int getSpeed() {
		return speed;
	}

	public Rectangle getSolidArea() {
		return solidArea;
	}

	public int getSolidAreaDefaultX() {
		return solidAreaDefaultX;
	}
	
	public int getSolidAreaDefaultY() {
		return solidAreaDefaultY;
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
	
	public BufferedImage getEntityImage() {
		return down1;
	}
	
	public void increaseHealth(int healthPoints) {
		if (currentHealth < maxHealth) {
			this.currentHealth += healthPoints;
			
			if (currentHealth > maxHealth) {
				// currentHealth cannot exceed the maximum
				currentHealth = maxHealth;
			}
		}
	}
	
	public void decreaseHealth(int healthPoints) {
		if (currentHealth > 0) {
			currentHealth -= healthPoints;
			
			if (currentHealth < 0) {
				// currentHealth cannot go below zero
				currentHealth = 0;
			}
		}
	}
	
	public void replenishHealth() {
		this.currentHealth = this.maxHealth;
	}
	
	public BufferedImage getImage() {
		return this.image;
	}
	
	public BufferedImage getImage2() {
		return this.image2;
	}

	public BufferedImage getImage3() {
		return this.image3;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isCollision() {
		return collision;
	}

	public void setCollision(boolean collisionOn) {
		this.collision = collisionOn;
	}
	
	public int getType() {
		return this.type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
}

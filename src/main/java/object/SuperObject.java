package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game2d.GamePanel;

public class SuperObject {

	protected BufferedImage image;
	protected String name;
	protected boolean collision = false;
	protected int worldX;
	protected int worldY;
	protected Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	protected int solidAreaDefaultX = 0;
	protected int solidAreaDefaultY = 0;
	
	public void draw(Graphics2D g2, GamePanel gp) {
		int tileSize = gp.getTileSize();
		
		int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
		int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();
		
		// if check so that we only draw tiles which are visible.
		// added the * 2 because we were still short on the right and bottom.
		if (worldX + (tileSize * 2) > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() &&
			worldX - (tileSize * 2) < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() &&
			worldY + (tileSize * 2) > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() &&
			worldY - (tileSize * 2) < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY()) {

			g2.drawImage(image, screenX, screenY,
					tileSize, tileSize, null);
		}
	}
	
	public int getWorldX() {
		return worldX;
	}

	public void setWorldX(int worldX) {
		this.worldX = worldX;
	}
	
	public int getWorldY() {
		return worldY;
	}

	public void setWorldY(int worldY) {
		this.worldY = worldY;
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
	
	public Rectangle getSolidArea() {
		return this.solidArea;
	}
	
	public int getSolidAreaDefaultX() {
		return this.solidAreaDefaultX;
	}

	public int getSolidAreaDefaultY() {
		return this.solidAreaDefaultY;
	}
}

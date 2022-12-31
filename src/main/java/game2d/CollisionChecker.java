package game2d;

import entity.Entity;

public class CollisionChecker {

	GamePanel gp;
	int tileSize;
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
		tileSize = gp.getTileSize();
	}
	
	public void checkTile(Entity entity) {

		int entityLeftWorldX = entity.getWorldX() + entity.getSolidArea().x;
		int entityRightWorldX = entity.getWorldX() + entity.getSolidArea().x
			+ entity.getSolidArea().width;
		int entityTopWorldY = entity.getWorldY() + entity.getSolidArea().y;
		int entityBottomWorldY = entity.getWorldY() + entity.getSolidArea().y
			+ entity.getSolidArea().height;
		
		int entityLeftCol = entityLeftWorldX / tileSize;
		int entityRightCol = entityRightWorldX / tileSize;
		int entityTopRow = entityTopWorldY / tileSize;
		int entityBottomRow = entityBottomWorldY / tileSize;
		
		int tileNum1, tileNum2;
		
		// check separately, so that we can detect collisions at angle movements		
		switch (entity.getDirection()) {
			case "up":
				entityTopRow = (entityTopWorldY - entity.getSpeed()) / tileSize;
				tileNum1 = gp.getTileManager().getMapTileCodes()[entityLeftCol][entityTopRow];
				tileNum2 = gp.getTileManager().getMapTileCodes()[entityRightCol][entityTopRow];
				
				if (gp.getTileManager().getTiles()[tileNum1].isCollision() ||
					gp.getTileManager().getTiles()[tileNum2].isCollision()) {
					entity.setCollisionOn(true);
				}
				break;
			case "down":
				entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / tileSize;
				tileNum1 = gp.getTileManager().getMapTileCodes()[entityLeftCol][entityBottomRow];
				tileNum2 = gp.getTileManager().getMapTileCodes()[entityRightCol][entityBottomRow];
				
				if (gp.getTileManager().getTiles()[tileNum1].isCollision() ||
					gp.getTileManager().getTiles()[tileNum2].isCollision()) {
					entity.setCollisionOn(true);
				}
				break;
		}
				
		switch (entity.getDirection()) {
			case "left":
				entityLeftCol = (entityLeftWorldX - entity.getSpeed()) / tileSize;
				tileNum1 = gp.getTileManager().getMapTileCodes()[entityLeftCol][entityTopRow];
				tileNum2 = gp.getTileManager().getMapTileCodes()[entityLeftCol][entityBottomRow];
				
				if (gp.getTileManager().getTiles()[tileNum1].isCollision() ||
					gp.getTileManager().getTiles()[tileNum2].isCollision()) {
					entity.setCollisionOn(true);
				}
				break;
			case "right":
				entityRightCol = (entityRightWorldX + entity.getSpeed()) / tileSize;
				tileNum1 = gp.getTileManager().getMapTileCodes()[entityRightCol][entityTopRow];
				tileNum2 = gp.getTileManager().getMapTileCodes()[entityRightCol][entityBottomRow];
				
				if (gp.getTileManager().getTiles()[tileNum1].isCollision() ||
					gp.getTileManager().getTiles()[tileNum2].isCollision()) {
					entity.setCollisionOn(true);
				}
				break;
			}
	}
}

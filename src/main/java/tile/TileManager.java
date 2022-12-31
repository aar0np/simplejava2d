package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import game2d.GamePanel;

public class TileManager {

	GamePanel gp;
	int tileSize;
	Tile[] tiles;
	int mapTileCodes[][];
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		tileSize = gp.getTileSize();
		tiles = new Tile[10];
		mapTileCodes = new int[gp.getMaxWorldColumn()][gp.getMaxWorldRow()];
		
		getTileImage();
		loadMap("/maps/world01.txt");
	}
	
	private void getTileImage() {
		
		try {
			// water
			tiles[2] = new Tile();
			tiles[2].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/018.png")));
			tiles[2].setCollision(true);

			// wall
			tiles[1] = new Tile();
			tiles[1].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/032.png")));
			tiles[1].setCollision(true);

			// grass
			tiles[0] = new Tile();
			tiles[0].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/001.png")));

			// earth
			tiles[3] = new Tile();
			tiles[3].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/017.png")));

			// tree
			tiles[4] = new Tile();
			tiles[4].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/016.png")));
			tiles[4].setCollision(true);

			// sand
			tiles[5] = new Tile();
			tiles[5].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/003.png")));

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	private void loadMap(String mapFile) {
		
		try {
			InputStream inputStream = getClass().getResourceAsStream(mapFile);
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			
			int col = 0;
			int row = 0;
			
			while (col < gp.getMaxWorldColumn() && row < gp.getMaxWorldRow()) {
				
				String inputLine = br.readLine();
				
				while (col < gp.getMaxWorldColumn()) {
					String tileCodes[] = inputLine.split(" ");
					
					int tileCode = Integer.parseInt(tileCodes[col]);
					mapTileCodes[col][row] = tileCode;
					col++;
				}
				
				if (col >= gp.getMaxWorldColumn()) {
					col = 0;
					row++;
				}
			}
			
			br.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	public void draw(Graphics2D g2) {
		// test background
		//g2.drawImage(tiles[2].image, 0, 0, gp.getTileSize(), gp.getTileSize(), null);
		//g2.drawImage(tiles[0].image, 48, 0, gp.getTileSize(), gp.getTileSize(), null);
		//g2.drawImage(tiles[1].image, 96, 0, gp.getTileSize(), gp.getTileSize(), null);
		
		int worldCol = 0;
		int worldRow = 0;

		
		while (worldCol < gp.getMaxWorldColumn() && worldRow < gp.getMaxWorldRow()) {

			int tileNum = mapTileCodes[worldCol][worldRow];
			
			int worldX = worldCol * tileSize;
			int worldY = worldRow * tileSize;
			int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
			int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();
			
			// if check so that we only draw tiles which are visible.
			// added the * 2 because we were still short on the right and bottom.
			if (worldX + (tileSize * 2) > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() &&
				worldX - (tileSize * 2) < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() &&
				worldY + (tileSize * 2) > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() &&
				worldY - (tileSize * 2) < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY()) {

				g2.drawImage(tiles[tileNum].getImage(), screenX, screenY,
						tileSize, tileSize, null);
			}
			
			worldCol++;
			
			if (worldCol >= gp.getMaxWorldColumn()) {
				worldCol = 0;
				worldRow++;
			}
		}
	}
	
	public int[][] getMapTileCodes() {
		return mapTileCodes;
	}
	
	public Tile[] getTiles() {
		return tiles;
	}
}

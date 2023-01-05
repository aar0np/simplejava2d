package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import game2d.GamePanel;
import game2d.GraphicsTools;

public class TileManager {

	GamePanel gp;
	int tileSize;
	Tile[] tiles;
	int mapTileCodes[][];
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		tileSize = gp.getTileSize();
		tiles = new Tile[50];
		mapTileCodes = new int[gp.getMaxWorldColumn()][gp.getMaxWorldRow()];
		
		getTileImage();
		// loadMap("/maps/world01.txt"); // original tiles 0-9
		loadMap("/maps/worldv2.txt");    // new tiles 10-41
	}
	
	private void getTileImage() {

// ORIGINAL TILES
//		// grass
//		setupTile(0, "001.png", false);
//
//		// wall
//		setupTile(1, "032.png", true);
//
//		// water
//		setupTile(2, "018.png", true);
//
//		// earth
//		setupTile(3, "017.png", false);
//
//		// tree
//		setupTile(4, "016.png", true);
//
//		// sand
//		setupTile(5, "003.png", false);

		// "Placeholders
		setupTile(0, "001.png", false);
		setupTile(1, "032.png", true);
		setupTile(2, "018.png", true);
		setupTile(3, "017.png", false);
		setupTile(4, "016.png", true);
		setupTile(5, "003.png", false);
		setupTile(6, "000.png", false);
		setupTile(7, "000.png", false);
		setupTile(8, "000.png", false);
		setupTile(9, "000.png", false);
		// grass
		setupTile(10, "001.png", false);
		setupTile(11, "002.png", false);
		// water
		setupTile(12, "018.png", true);
		setupTile(13, "019.png", true);
		setupTile(14, "020.png", true);
		setupTile(15, "021.png", true);
		setupTile(16, "022.png", true);
		setupTile(17, "023.png", true);
		setupTile(18, "024.png", true);
		setupTile(19, "025.png", true);
		setupTile(20, "026.png", true);
		setupTile(21, "027.png", true);
		setupTile(22, "028.png", true);
		setupTile(23, "029.png", true);
		setupTile(24, "030.png", true);
		setupTile(25, "031.png", true);		
		// road
		setupTile(26, "003.png", false);
		setupTile(27, "004.png", false);
		setupTile(28, "005.png", false);
		setupTile(29, "006.png", false);
		setupTile(30, "007.png", false);
		setupTile(31, "008.png", false);
		setupTile(32, "009.png", false);
		setupTile(33, "010.png", false);
		setupTile(34, "011.png", false);
		setupTile(35, "012.png", false);
		setupTile(36, "013.png", false);
		setupTile(37, "014.png", false);
		setupTile(38, "015.png", false);
		// earth
		setupTile(39, "017.png", false);
		// wall
		setupTile(40, "032.png", true);
		// tree
		setupTile(41, "016.png", true);
	}
	
	private void setupTile(int tileIndex, String imagePath, boolean collision) {

		try {
			tiles[tileIndex] = new Tile();
			BufferedImage scaledImage = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imagePath));
			scaledImage = GraphicsTools.scaleTile(scaledImage, tileSize, tileSize);
			tiles[tileIndex].setImage(scaledImage);
			tiles[tileIndex].setCollision(collision);
			
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
			if (worldX + tileSize > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() &&
				worldX - tileSize < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() &&
				worldY + tileSize > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() &&
				worldY - tileSize < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY()) {

				g2.drawImage(tiles[tileNum].getImage(), screenX, screenY, null);
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

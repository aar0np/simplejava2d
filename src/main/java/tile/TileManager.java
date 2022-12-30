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
	Tile[] tiles;
	int mapTileCodes[][];
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		tiles = new Tile[10];
		mapTileCodes = new int[gp.getMaxScreenColumn()][gp.getMaxScreenRow()];
		
		getTileImage();
		loadMap("/maps/map01.txt");
	}
	
	private void getTileImage() {
		
		try {
			// grass
			tiles[0] = new Tile();
			tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/001.png"));

			// wall
			tiles[1] = new Tile();
			tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/032.png"));

			// water
			tiles[2] = new Tile();
			tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/018.png"));

			// earth
			tiles[3] = new Tile();
			tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/017.png"));

			// tree
			tiles[4] = new Tile();
			tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/016.png"));

			// sand
			tiles[5] = new Tile();
			tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/003.png"));

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
			
			while (col < gp.getMaxScreenColumn() && row < gp.getMaxScreenRow()) {
				
				String inputLine = br.readLine();
				
				while (col < gp.getMaxScreenColumn()) {
					String tileCodes[] = inputLine.split(" ");
					
					int tileCode = Integer.parseInt(tileCodes[col]);
					mapTileCodes[col][row] = tileCode;
					col++;
				}
				
				if (col >= gp.getMaxScreenColumn()) {
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
		// draw background
		//g2.drawImage(tiles[2].image, 0, 0, gp.getTileSize(), gp.getTileSize(), null);
		//g2.drawImage(tiles[0].image, 48, 0, gp.getTileSize(), gp.getTileSize(), null);
		//g2.drawImage(tiles[1].image, 96, 0, gp.getTileSize(), gp.getTileSize(), null);
		
		int col = 0;
		int row = 0;
		int xCoord = 0;
		int yCoord = 0;
		
		while (col < gp.getMaxScreenColumn() && row < gp.getMaxScreenRow()) {
			int tileNum = mapTileCodes[col][row];
			
			g2.drawImage(tiles[tileNum].image, xCoord, yCoord,
				gp.getTileSize(), gp.getTileSize(), null);
			col++;
			xCoord += gp.getTileSize();
			
			if (col >= gp.getMaxScreenColumn()) {
				col = 0;
				xCoord = 0;
				row++;
				yCoord += gp.getTileSize();
			}
		}
	}
}

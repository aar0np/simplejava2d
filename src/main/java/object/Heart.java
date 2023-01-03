package object;

import javax.imageio.ImageIO;

import game2d.GamePanel;
import game2d.GraphicsTools;

public class Heart extends SuperObject {
	GamePanel gp;
	
	public Heart(GamePanel gp) {
		name = "Heart";
		this.gp = gp;
		int tileSize = gp.getTileSize();
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png"));
			image = GraphicsTools.scaleTile(image, tileSize);

			image2 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_half.png"));
			image2 = GraphicsTools.scaleTile(image2, tileSize);

			image3 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_blank.png"));
			image3 = GraphicsTools.scaleTile(image3, tileSize);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

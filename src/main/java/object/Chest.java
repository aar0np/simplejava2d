package object;

import javax.imageio.ImageIO;

import game2d.GraphicsTools;

public class Chest extends SuperObject {

	public Chest(int tileSize) {
		name = "Chest";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
			image = GraphicsTools.scaleTile(image, tileSize);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

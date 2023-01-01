package object;

import javax.imageio.ImageIO;

import game2d.GraphicsTools;

public class Key extends SuperObject {

	public Key(int tileSize) {
		name = "Key";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
			image = GraphicsTools.scaleTile(image, tileSize);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

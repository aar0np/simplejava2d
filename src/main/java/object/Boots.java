package object;

import javax.imageio.ImageIO;

import game2d.GraphicsTools;

public class Boots extends SuperObject {

	public Boots(int tileSize) {
		name = "Boots";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
			image = GraphicsTools.scaleTile(image, tileSize);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

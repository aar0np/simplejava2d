package object;

import javax.imageio.ImageIO;

import game2d.GraphicsTools;

public class Door extends SuperObject {

	public Door(int tileSize) {
		name = "Door";
		collision = true;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
			image = GraphicsTools.scaleTile(image, tileSize);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
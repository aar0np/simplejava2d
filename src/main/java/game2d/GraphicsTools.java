package game2d;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class GraphicsTools {

	public static BufferedImage scaleTile(BufferedImage smallImage, int tileSize) {

		BufferedImage scaledImage = new BufferedImage(tileSize, tileSize, smallImage.getType());
		Graphics2D g2 = scaledImage.createGraphics();
		g2.drawImage(smallImage, 0, 0, tileSize, tileSize, null);
		g2.dispose();
		
		return scaledImage;
	}
}

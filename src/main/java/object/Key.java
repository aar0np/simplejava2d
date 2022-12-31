package object;

import javax.imageio.ImageIO;

public class Key extends SuperObject {

	public Key() {
		name = "Key";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

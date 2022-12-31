package object;

import javax.imageio.ImageIO;

public class Boots extends SuperObject {

	public Boots() {
		name = "Boots";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

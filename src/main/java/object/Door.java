package object;

import javax.imageio.ImageIO;

public class Door extends SuperObject {

	public Door() {
		name = "Door";
		collision = true;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
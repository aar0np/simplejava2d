package game2d;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.Key;

public class GameUserInterface {
	private boolean messageOn = false;
	private boolean gameFinished = false;
	private String message;
	private int messageCounter = 0;
	private double playTime;
	DecimalFormat dFormat = new DecimalFormat("#0.0");
	
	GamePanel gp;
	Font arialP40 = new Font("Arial", Font.PLAIN, 40);
	Font arialB80 = new Font("Arial", Font.BOLD, 80);
	BufferedImage keyImage;
	int tileSize;

	public GameUserInterface(GamePanel gp) {
		this.gp = gp;
		
		tileSize = gp.getTileSize();
		
		Key key = new Key();
		keyImage = key.getImage();
		key = null;
	}
	
	public void draw(Graphics2D g2) {
		
		if (!gameFinished) {
			g2.setFont(arialP40);
			g2.setColor(Color.white);
			g2.drawImage(keyImage, tileSize / 2, tileSize / 2, tileSize, tileSize, null);
			g2.drawString("x " + gp.getPlayer().getKeys(), 74, 65);
			
			// Time
			playTime += (double) 1/60;
			g2.drawString("Time: " + dFormat.format(playTime), tileSize + 11, 65);
			
			if (messageOn) {
				g2.setFont(g2.getFont().deriveFont(30F));
				g2.drawString(message, tileSize / 2, tileSize * 5);
				
				messageCounter++;
				
				// make text disappear after 2 seconds
				if (messageCounter > 120) {
					messageCounter = 0;
					messageOn = false;
				}
			}
		} else {
			g2.setFont(arialP40);
			g2.setColor(Color.white);

			String text = "You found the treasure!";
			int textSize = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			// show finish message in center of screen
			int textX = (gp.getScreenWidth() / 2) - (textSize / 2);
			int textY = (gp.getScreenHeight() / 2) - (tileSize * 3);

			// show time
			text = "Time: " + dFormat.format(playTime);
			textSize = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			textX = (gp.getScreenWidth() / 2) - (textSize / 2);
			textY = (gp.getScreenHeight() / 2) + (tileSize * 4);

			g2.setFont(g2.getFont().deriveFont(40));
			g2.drawString(text, textX, textY);
			
			// Congratulations
			g2.setFont(arialB80);
			g2.setColor(Color.cyan);
		
			text = "Congratulations!";
			textSize = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			
			textX = (gp.getScreenWidth() / 2) - (textSize / 2);
			textY = (gp.getScreenHeight() / 2) + (tileSize * 2);
			g2.drawString(text, textX, textY);
			
			gp.stopGame();
		}
	}

	public void showMessage(String text) {
		this.message = text;
		messageOn = true;
	}
	
	public boolean getIsGameFinished() {
		return this.gameFinished;
	}
	
	public void setIsGameFinished(boolean isFinished) {
		this.gameFinished = isFinished;
	}
}

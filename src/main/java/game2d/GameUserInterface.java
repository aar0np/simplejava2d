package game2d;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
//import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.Heart;
import object.SuperObject;

//import object.Key;

public class GameUserInterface {
	private boolean messageOn = false;
	private boolean gameFinished = false;
	private String message;
	private int messageCounter = 0;
	
	GamePanel gp;
	Graphics2D g2;
	Font arialP40 = new Font("Arial", Font.PLAIN, 40);
	Font arialB80 = new Font("Arial", Font.BOLD, 80);
	Font tron;
	BufferedImage heartFull;
	BufferedImage heartHalf;
	BufferedImage heartBlank;
	int tileSize;
	int commandNum = 0;
	String currentDialog = "";

	public GameUserInterface(GamePanel gp) {
		this.gp = gp;
		
		tileSize = gp.getTileSize();
		
		try {
			InputStream fontIS = getClass().getResourceAsStream("/fonts/TRON.TTF");
			tron = Font.createFont(Font.TRUETYPE_FONT, fontIS);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
		// Key key = new Key(tileSize);
		// keyImage = key.getImage();
		// key = null;
		
		SuperObject heart = new Heart(gp);
		heartFull = heart.getImage();
		heartHalf = heart.getImage2();
		heartBlank = heart.getImage3();
	}
	
// RPG version of draw	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		
		g2.setFont(arialB80);
		//g2.setFont(tron);
		g2.setColor(Color.white);
		

		if (gp.getGameState() == gp.PLAY_STATE) {
			// Play state
			drawPlayerHearts();
		} else if (gp.getGameState() == gp.TITLE_STATE) {
			// title screen
			drawTitleScreen();
	    } else if (gp.getGameState() == gp.PAUSE_STATE) {
			// Pause state
			drawPauseScreen();
		} else if (gp.getGameState() == gp.DIALOG_STATE) {
			drawDialogScreen();
		}
	}
	
	public void drawPlayerHearts() {
		
		// draw max life
		int heartX = tileSize / 2;
		int heartY = tileSize / 2;
		
		for (int counter = 0; counter < gp.getPlayer().getMaxHealth() / 2; counter++) {
			g2.drawImage(heartBlank, heartX, heartY, null);
			heartX += tileSize;
		}

		// draw current life
		heartX = tileSize / 2;
		heartY = tileSize / 2;
		int heartCounter = 0;
		
		while (heartCounter < gp.getPlayer().getCurrentHealth()) {
			
			if (heartCounter + 1 < gp.getPlayer().getCurrentHealth()) {
				g2.drawImage(heartFull, heartX, heartY, null);
				heartCounter+=2;
			} else {
				g2.drawImage(heartHalf, heartX, heartY, null);
				heartCounter++;
			}
			
			heartX += tileSize;
		}
	}
	
	public void drawTitleScreen() {
		
		// background
		g2.setColor(new Color(0, 0, 120));
		g2.fillRect(0,  0, gp.getScreenWidth(), gp.getScreenHeight());
		
		// title
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,72F));
		String text = "Blue Boy Adventure";
		int titleX = getXForCenteredText(text);
		int titleY = tileSize * 3;
		
		// shadow
		g2.setColor(Color.darkGray);
		g2.drawString(text, titleX + 5, titleY + 5);
		
		// main color
		g2.setColor(Color.white);
		g2.drawString(text, titleX, titleY);
		
		// Blue Boy image
		int bbX = gp.getScreenWidth() / 2;
		int bbY = tileSize * 5;
		g2.drawImage(gp.getPlayer().getEntityImage(), bbX - tileSize, bbY, tileSize *2, tileSize *2, null);
		
		// menu
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,36F));
		
		String newGame = "New Game";
		int ngX = getXForCenteredText(newGame);
		int ngY = tileSize * 9;
		g2.drawString(newGame, ngX, ngY);
		
		String loadGame = "Load Game";
		int lgX = getXForCenteredText(loadGame);
		int lgY = tileSize * 10;
		g2.drawString(loadGame, lgX, lgY);
		
		String quitGame = "Quit";
		int qgX = getXForCenteredText(quitGame);
		int qgY = tileSize * 11;
		g2.drawString(quitGame, qgX, qgY);
		
		// arrow on menu
		if (commandNum == 0) {
			g2.drawString(">", ngX - tileSize, ngY);
		} else if (commandNum == 1) {
			g2.drawString(">", lgX - tileSize, lgY);
		} else if (commandNum == 2) {
			g2.drawString(">", qgX - tileSize, qgY);
		}
		
	}
	
	public void drawPauseScreen() {
		
		String text = "PAUSED";
		int textX = getXForCenteredText(text);
		int textY = gp.getScreenHeight() / 2;
		
		g2.drawString(text, textX, textY);	
	}
	
	public void drawDialogScreen() {
		
		int windowX = tileSize * 2;
		int windowY = tileSize / 2;
		int windowWidth = gp.getScreenWidth() - (tileSize * 4);
		int windowHeight = tileSize * 4;	
	
		drawSubWindow(windowX, windowY, windowWidth, windowHeight);
		
		// text
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,28F));
		int textX = windowX + tileSize;
		int textY = windowY + tileSize;
		
		
		for (String line : currentDialog.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
			
	}
	
	public void drawSubWindow(int swX, int swY, int swWidth, int swHeight) {

		Color c = new Color(0, 0, 0, 200);
		g2.setColor(c);
		g2.fillRoundRect(swX, swY, swWidth, swHeight, 35, 35);
		
		c = new Color(255, 255, 255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5)); // pixel width
		g2.drawRoundRect(swX + 5, swY + 5, swWidth - 10, swHeight - 10, 25, 25);
	}
	
	private int getXForCenteredText(String text) {
		int textSize = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		return (gp.getScreenWidth() / 2) - (textSize / 2);
	}
	
// Treasure Hunt version of draw
//
//	public void draw(Graphics2D g2) {
//		
//		if (!gameFinished) {
//			g2.setFont(arialP40);
//			g2.setColor(Color.white);
//			g2.drawImage(keyImage, tileSize / 2, tileSize / 2, tileSize, tileSize, null);
//			g2.drawString("x " + gp.getPlayer().getKeys(), 74, 65);
//			
//			// Time
//			playTime += (double) 1/60;
//			g2.drawString("Time: " + dFormat.format(playTime), tileSize * 11, 65);
//			
//			if (messageOn) {
//				g2.setFont(g2.getFont().deriveFont(30F));
//				g2.drawString(message, tileSize / 2, tileSize * 5);
//				
//				messageCounter++;
//				
//				// make text disappear after 2 seconds
//				if (messageCounter > 120) {
//					messageCounter = 0;
//					messageOn = false;
//				}
//			}
//		} else {
//			g2.setFont(arialP40);
//			g2.setColor(Color.white);
//
//			String text = "You found the treasure!";
//			int textSize = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//			// show finish message in center of screen
//			int textX = (gp.getScreenWidth() / 2) - (textSize / 2);
//			int textY = (gp.getScreenHeight() / 2) - (tileSize * 3);
//
//			// show time
//			text = "Time: " + dFormat.format(playTime);
//			textSize = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//			textX = (gp.getScreenWidth() / 2) - (textSize / 2);
//			textY = (gp.getScreenHeight() / 2) + (tileSize * 4);
//
//			g2.setFont(g2.getFont().deriveFont(40));
//			g2.drawString(text, textX, textY);
//			
//			// Congratulations
//			g2.setFont(arialB80);
//			g2.setColor(Color.cyan);
//		
//			text = "Congratulations!";
//			textSize = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//			
//			textX = (gp.getScreenWidth() / 2) - (textSize / 2);
//			textY = (gp.getScreenHeight() / 2) + (tileSize * 2);
//			g2.drawString(text, textX, textY);
//			
//			gp.stopGame();
//		}
//	}

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
	
	public String getCurrentDialog() {
		return this.currentDialog;
	}
	
	public void setCurrentDialog(String message) {
		this.currentDialog = message;
	}
	
	public int getCommandNum() {
		return this.commandNum;
	}
	
	public void decrementCommandNum() {
		if (commandNum > 0) {
			commandNum--;
		}
	}
	
	public void incrementCommandNum() {
		if (commandNum < 2) {
			commandNum++;
		}
	}
}

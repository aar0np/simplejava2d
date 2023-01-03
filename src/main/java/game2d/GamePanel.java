package game2d;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 8734664941941553022L;

	final int originalTileSize = 16;
	final int scale = 3;

	// screen settings
	private final int tileSize = originalTileSize * scale;  // 48x48 by default
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCol; // 768x
	final int screenHeight = tileSize * maxScreenRow; // 576
	
	// world map settings
	final int maxWorldCol = 50;
	final int maxWorldRow = 50;
	
	// frames per second
	final int fPS = 60;
	
	// Game engine
	private TileManager tileMgr = new TileManager(this);
	private Thread gameThread;
	KeyHandler keyHandler = new KeyHandler(this);
	Sound sound = new Sound(false);
	Sound music = new Sound(true);
	CollisionChecker cChecker = new CollisionChecker(this);
	ObjectFactory oFactory = new ObjectFactory(this);
	GameUserInterface gameUI = new GameUserInterface(this);
	
	// Entities and objects
	Player player = new Player(this,keyHandler);
	SuperObject objects[] = new SuperObject[10];
	Entity npcs[] = new Entity[10];
	
	private int gameState;
	public final int TITLE_STATE = 0;
	public final int PLAY_STATE = 1;
	public final int PAUSE_STATE = 2;
	public final int DIALOG_STATE = 3;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
	}

	public void setupGame() {
		oFactory.generateObjects();
		oFactory.generateNPCs();
		//playMusic(0);
		//stopMusic();
		gameState = TITLE_STATE;
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void stopGame() {
		gameThread = null;
	}
	
	public void run() {
		
		double drawInterval = 1000000000/fPS;
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while (gameThread != null) {
			// testing
			// System.out.println("Game loop is running");
			
			update();
			
			repaint();
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime / 1000000;
				
				if (remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long)remainingTime);
				nextDrawTime += drawInterval;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update() {
		
		if (gameState == PLAY_STATE) {
			player.update();
			
			for (Entity npc : npcs) {
				if (npc != null) {
					npc.update();
				}
			}
		} else if (gameState == PAUSE_STATE) {
			
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		//long drawStart = System.currentTimeMillis();
		
		if (gameState == TITLE_STATE) {
			// title screen 
			gameUI.draw(g2);
			
		} else {	
			// play state
			
			// tiles
			tileMgr.draw(g2);
			
			// objects
			for (SuperObject obj : objects) {
				if (obj != null) {
					obj.draw(g2, this);
				}
			}
			
			// NPCs
			for (Entity npc : npcs) {
				if (npc != null) {
					npc.draw(g2);
				}
			}
			
			// player
			player.draw(g2);

			// UI
			gameUI.draw(g2);
			
		}

		//long drawEnd = System.currentTimeMillis();
		//long drawTime = drawEnd - drawStart;
		
		//System.out.println("draw time = " + drawTime);
		// getting 200-300ms initially	

		g2.dispose();
	}
	
	public void playMusic(int index) {
		music.setFile(index);
		music.play();
		music.loop();
	}
	
	public void stopMusic() {
		music.stop();
	}
	
	public void playSoundEffect(int index) {
		sound.setFile(index);
		sound.play();
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public CollisionChecker getCollisionChecker() {
		return this.cChecker;
	}
	
	public TileManager getTileManager() {
		return this.tileMgr;
	}

	public KeyHandler getKeyHandler() {
		return this.keyHandler;
	}
	
	public SuperObject[] getObjects() {
		return this.objects;
	}
	
	public Entity[] getNPCs() {
		return this.npcs;
	}
	
	public GameUserInterface getGameUI() {
		return this.gameUI;
	}
	
	public int getTileSize() {
		return tileSize;
	}
	
	public int getMaxScreenColumn() {
		return maxScreenCol;
	}
	
	public int getMaxScreenRow() {
		return maxScreenRow;
	}
	
	public int getScreenWidth() {
		return screenWidth;
	}
	
	public int getScreenHeight() {
		return screenHeight;
	}
	
	public int getMaxWorldColumn() {
		return maxWorldCol;
	}
	
	public int getMaxWorldRow() {
		return maxWorldRow;
	}
	
	public int getGameState() {
		return this.gameState;
	}
	
	public void setGameState(int newState) {
		this.gameState = newState;
	}
}

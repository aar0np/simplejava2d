package game2d;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	Clip clip;
	URL soundURL[] = new URL[30];
	
	public Sound (boolean musicOnly) {

		if (musicOnly) {
			soundURL[0] = getClass().getResource("/sounds/BlueBoyAdventure.wav");
		} else {
			soundURL[0] = null;
			soundURL[1] = getClass().getResource("/sounds/coin.wav");
			soundURL[2] = getClass().getResource("/sounds/powerup.wav");
			soundURL[3] = getClass().getResource("/sounds/unlock.wav");
			soundURL[4] = getClass().getResource("/sounds/fanfare.wav");
			soundURL[5] = getClass().getResource("/sounds/blocked.wav");
		}
	}
	
	public void setFile(int index) {
		try {
			
			AudioInputStream audioInStream = AudioSystem.getAudioInputStream(soundURL[index]);
			clip = AudioSystem.getClip();
			clip.open(audioInStream);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void play() {
		clip.start();
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		clip.stop();
	}
}

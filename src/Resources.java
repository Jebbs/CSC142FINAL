import uwcse.graphics.GWindow;
import uwcse.io.AudioPlayer;
import uwcse.io.Sound;

import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;



/**
 * A class that loads and stores the resources used in the game.
 * 
 * I didn't have time to fix this, but most of these don't need to be arrays.
 * It looks like this from earlier when I was trying something different that
 * ended up not working.
 */
public class Resources {

	// Ship Images
	public static Image[] playerShip;
	public static Image[] escapePod;
	public static Image[] shuttle;
	public static Image[] defender;
	public static Image[] boss;

	// Bullets
	public static Image[] playerBullet;
	public static Image[] defenderBullet;
	public static Image[] bossBullet;

	// Effects
	public static Image[] explosion;

	// Music
	private static Sound backgroundMusic;

	private static AudioPlayer player;

	// makes it so that all the assets are loaded only once
	private static boolean hasLoaded = false;

	public static void load(GWindow window) 
	{
		if (!hasLoaded) 
		{
			playerShip = new Image[1];
			playerShip[0] = window.getImageFromURL("http://jebbs.github.io/CSC142FINAL/res/PlayerShip.png");

			escapePod = new Image[1];
			escapePod[0] = window.getImageFromURL("http://jebbs.github.io/CSC142FINAL/res/EscapePod.png");

			shuttle = new Image[2];
			shuttle[1] = window.getImageFromURL("http://jebbs.github.io/CSC142FINAL/res/Shuttle.png");
			shuttle[0] = window.getImageFromURL("http://jebbs.github.io/CSC142FINAL/res/ShuttleHit.png");

			defender = new Image[3];
			defender[2] = window.getImageFromURL("http://jebbs.github.io/CSC142FINAL/res/Defender.png");
			defender[1] = window.getImageFromURL("http://jebbs.github.io/CSC142FINAL/res/DefenderHit1.png");
			defender[0] = window.getImageFromURL("http://jebbs.github.io/CSC142FINAL/res/DefenderHit2.png");

			boss = new Image[3];
			boss[0] = window.getImageFromURL("http://jebbs.github.io/CSC142FINAL/res/Boss.png");
			boss[1] = window.getImageFromURL("http://jebbs.github.io/CSC142FINAL/res/BossDamage1.png");
			boss[2] = window.getImageFromURL("http://jebbs.github.io/CSC142FINAL/res/BossDamage2.png");

			playerBullet = new Image[1];
			playerBullet[0] = window.getImageFromURL("http://jebbs.github.io/CSC142FINAL/res/PlayerBullet.png");

			defenderBullet = new Image[1];
			defenderBullet[0] = window.getImageFromURL("http://jebbs.github.io/CSC142FINAL/res/DefenderBullet.png");

			bossBullet = new Image[1];
			bossBullet[0] = window.getImageFromURL("http://jebbs.github.io/CSC142FINAL/res/BossBullet.png");

			explosion = new Image[17];
			for (int i = 0; i < explosion.length; i++) 
			{
				explosion[i] = window.getImageFromURL("http://jebbs.github.io/CSC142FINAL/res/explosion/explosion"+ (i + 1) + ".png");
			}

			try 
			{
				backgroundMusic = new Sound(new URL(
						"http://jebbs.github.io/CSC142FINAL/res/music.wav"));
			} 
			catch (MalformedURLException e) 
			{
				e.printStackTrace();
			}

			player = new AudioPlayer();

			player.loop(backgroundMusic);
			
			
			hasLoaded = true;
		}

	}

}

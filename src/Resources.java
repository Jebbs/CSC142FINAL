
import uwcse.graphics.*;

/**
 * A class that loads and stores the resources used in the game.
 *
 */
public class Resources 
{
	private static String playerShipUrl, escapePodUrl, shuttleUrl, shuttleHitUrl, 
	defenderUrl, defenderHit1Url, defenderHit2Url, bossUrl, bossDamage1Url,
	bossDamage2Url;
	
	//Ship Images
	public static Shape[] playerShip;
	public static Shape[] escapePod;
	public static Shape[] shuttle;
	public static Shape[] defender;
	public static Shape[] boss;
	
	//Bullets
	public static Shape[] playerBullet;
	public static Shape[] defenderBullet;
	public static Shape[] bossBullet;
	
	//Effects
	public static Shape[] explosion;
	
	//Sounds
	//git clone public static Shape[] playerBullet;
	
	
	//Music
	
	
	
	
	
	public static void load(GWindow window)
	{
		playerShip = new Shape[1];
		playerShip[0] = new ImageShape(window.getImageFromURL("https://github.com/Jebbs/CSC142FINAL/blob/master/res/PlayerShip.png"),0,0);
		
		escapePod = new Shape[1];
		escapePod[0] = new ImageShape(window.getImageFromURL("https://github.com/Jebbs/CSC142FINAL/blob/master/res/EscapePod.png"),0,0);
		
		shuttle = new Shape[2];
		shuttle[0] = new ImageShape(window.getImageFromURL("https://github.com/Jebbs/CSC142FINAL/blob/master/res/Shuttle.png"),0,0);
		shuttle[1] = new ImageShape(window.getImageFromURL("https://github.com/Jebbs/CSC142FINAL/blob/master/res/ShuttleHit.png"),0,0);
		
		defender = new Shape[3];
		defender[0] = new ImageShape(window.getImageFromURL("https://github.com/Jebbs/CSC142FINAL/blob/master/res/Defender.png"),0,0);
		defender[1] = new ImageShape(window.getImageFromURL("https://github.com/Jebbs/CSC142FINAL/blob/master/res/DefenderHit1.png"),0,0);
		defender[2] = new ImageShape(window.getImageFromURL("https://github.com/Jebbs/CSC142FINAL/blob/master/res/DefenderHit2.png"),0,0);
		
		boss = new Shape[3];
		boss[0] = new ImageShape(window.getImageFromURL("https://github.com/Jebbs/CSC142FINAL/blob/master/res/Boss.png"),0,0);
		boss[1] = new ImageShape(window.getImageFromURL("https://github.com/Jebbs/CSC142FINAL/blob/master/res/BossDamage1.png"),0,0);
		boss[2] = new ImageShape(window.getImageFromURL("https://github.com/Jebbs/CSC142FINAL/blob/master/res/BossDamage2.png"),0,0);
		
		
		playerBullet = new Shape[1];
		playerBullet[0] = new ImageShape(window.getImageFromURL("https://github.com/Jebbs/CSC142FINAL/blob/master/res/PlayerBullet.png"),0,0);
		
		defenderBullet = new Shape[1];
		defenderBullet[0] = new ImageShape(window.getImageFromURL("https://github.com/Jebbs/CSC142FINAL/blob/master/res/DefenderBullet.png"),0,0);
		
		bossBullet = new Shape[1];
		bossBullet[0] = new ImageShape(window.getImageFromURL("https://github.com/Jebbs/CSC142FINAL/blob/master/res/BossBullet.png"),0,0);
		
		
	}
	
	
}

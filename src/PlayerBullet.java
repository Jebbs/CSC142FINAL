import java.awt.Point;

import uwcse.graphics.GWindow;

/**
 * The bullet the player fires.
 */
public class PlayerBullet extends Bullet
{
	/**
	 * Construct the bullet.
	 */
	public PlayerBullet(GWindow window, Point center) {
		super(window, center);
		bulletImage = Resources.playerBullet[0];
		this.draw();
	}
	
	/**
	 * Moves the bullet.
	 */
	public void move() 
	{
		center.y -= 8;
		
		this.erase();
		this.draw();
	}


	
	
	
}

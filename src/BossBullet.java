import java.awt.Point;

import uwcse.graphics.GWindow;


/**
 * The bullet that the boss fires.
 */
public class BossBullet extends Bullet
{
	/**
	 * Construct the bullet.
	 */
	public BossBullet(GWindow window, Point center) {
		super(window, center);
		bulletImage = Resources.bossBullet[0];
		this.draw();
	}

	/**
	 * Move the bullet.
	 */
	public void move() 
	{
		center.y += 5;
		
		this.erase();
		this.draw();
		
	}



}

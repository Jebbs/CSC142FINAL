import java.awt.Point;

import uwcse.graphics.GWindow;


/**
 * The bullet that the defender fires.
 */
public class DefenderBullet extends Bullet
{
	/**
	 * Construct the bullet.
	 */
	public DefenderBullet(GWindow window, Point center) {
		super(window, center);
		bulletImage = Resources.defenderBullet[0];
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

import java.awt.Image;
import java.awt.Point;

import uwcse.graphics.*;

/**
 * 
 * A bullet that has been fired from a ship.
 *
 */
public abstract class Bullet extends MovingObject
{
	protected static final int WIDTH = 10;
	protected static final int HEIGHT = 16;
	
	protected Image bulletImage;
	
	/**
	 * Construct the bullet.
	 */
	public Bullet(GWindow window, Point center)
	{
		super(window, center);
	}
	
	/**
	 * Remove the bullet from the screen.
	 */
	public void remove()
	{
		erase();
	}
	
	/**
	 * Move the bullet!
	 */
	public abstract void move();

	/**
	 * Draw the bullet.
	 */
	protected void draw() 
	{
		this.shapes = new Shape[1];
		
		this.shapes[0] = new ImageShape(bulletImage,this.center.x - 10 / 2,
				this.center.y - 16 / 2);

		this.boundingBox = this.shapes[0].getBoundingBox();
		
		this.window.add(this.shapes[0]);
		
		this.window.doRepaint();
	}
}

import java.awt.Point;

import uwcse.graphics.GWindow;
import uwcse.graphics.ImageShape;
import uwcse.graphics.Shape;

/**
 * 
 * This alien ship is a shuttle. It has a little more health than a escape pod.
 *
 */
public class ShuttleAlien extends Alien
{

	/**
	 * Construct the Shuttle.
	 */
	public ShuttleAlien(GWindow window, Point center) {
		super(window, center);
		
		this.lives = 2;
		
		yMovement = 5;
		
		WIDTH = 68;
		HEIGHT = 86;
	}
	
	/**
	 * Move the ship.
	 */
	public void move()
	{
		
		center.y += yMovement;
		
		this.erase();
		
		if(!this.isDead())
		{
			this.draw();
		}
	}
	
	/**
	 * Draw the ship.
	 */
	protected void draw()
	{
		
		this.shapes = new Shape[1];
		
		this.shapes[0] = new ImageShape(Resources.shuttle[lives-1],this.center.x - WIDTH / 2,
				this.center.y - HEIGHT / 2);
		
		this.boundingBox = this.shapes[0].getBoundingBox();
		
		this.window.add(this.shapes[0]);
		
		this.window.doRepaint();
	}
}

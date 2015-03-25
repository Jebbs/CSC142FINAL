import java.awt.Point;
import java.util.Random;

import uwcse.graphics.GWindow;
import uwcse.graphics.ImageShape;
import uwcse.graphics.Shape;

/**
 * 
 * This alien ship is an escape pod fleeing your wrath!
 *
 */
public class EscapePodAlien extends Alien
{
	/**
	 * Construct the ship.
	 */
	public EscapePodAlien(GWindow window, Point center) {
		super(window, center);
		
		this.lives = 1;
		WIDTH = 60;
		HEIGHT = 60;
		
		
		yMovement = 5;
		
		//work out the trajectory
		Random rand = new Random();
		
		//left third
		if(center.x <= (window.getWindowWidth()/3))
		{
			xMovement = rand.nextInt(6);
		}
		//center third
		else if ((center.x > window.getWindowWidth()/3)&&(center.x<= 2*window.getWindowWidth()/3))
		{
			xMovement = rand.nextInt(6)-3;
		}
		//right third
		else
		{
			xMovement = rand.nextInt(6)-6;
		}
		
		this.draw();
		
	}
	
	/**
	 * Move the ship.
	 */
	public void move()
	{
		center.x += xMovement;
		center.y += yMovement;
		
		this.erase();
		this.draw();
		
	}
	
	/**
	 * Draw the ship.
	 */
	protected void draw()
	{
		this.shapes = new Shape[1];
		
		this.shapes[0] = new ImageShape(Resources.escapePod[0],this.center.x - WIDTH / 2,
				this.center.y - HEIGHT / 2);
		
		this.boundingBox = this.shapes[0].getBoundingBox();
		
		this.window.add(this.shapes[0]);
		
		this.window.doRepaint();
	}
}

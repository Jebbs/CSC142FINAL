import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import uwcse.graphics.GWindow;
import uwcse.graphics.ImageShape;
import uwcse.graphics.Shape;

/**
 * 
 * This alien ship is a strong one. And it fights back!
 *
 */
public class DefenderAlien extends Alien
{
	private boolean isMovingLeft;
	
	private int bulletTimer;
	private int bulletTime;
	
	public DefenderAlien(GWindow window, Point center, int bulletTime) {
		super(window, center);
		// TODO Auto-generated constructor stub
		
		Random rand = new Random();
		
		isMovingLeft = rand.nextBoolean();
		
		this.lives = 3;
		
		yMovement = 3;
		
		WIDTH = 86;
		HEIGHT = 92;
		
		this.bulletTime = bulletTime;
		
		//cap the shooting time.
		if(bulletTime < 500)
		{
			bulletTimer = 500;
		}
		
	}
	
	/**
	 * Move the ship.
	 */
	public void move()
	{
		//movement
		if(isMovingLeft)
		{
			xMovement = -5;
		}
		else
		{
			xMovement = 5;
		}
		
		
		center.x += xMovement;
		center.y += yMovement;
		
		if(center.x - WIDTH/2 <= 0)
		{
			isMovingLeft = false;
			
			center.x = WIDTH/2;
		}
		
		if(center.x+WIDTH/2 >= window.getWindowWidth())
		{
			isMovingLeft = true;
			
			center.x = window.getWindowWidth()-WIDTH/2;
					
		}
		
		//Add 100 ms to the timer
		bulletTimer +=100;
		
		
		this.erase();
		this.draw();
		
	}
	
	/**
	 * Draw the ship.
	 */
	protected void draw()
	{
		this.shapes = new Shape[1];
		
		this.shapes[0] = new ImageShape(Resources.defender[lives-1],this.center.x - WIDTH / 2,
				this.center.y - HEIGHT / 2);
		
		this.boundingBox = this.shapes[0].getBoundingBox();
		
		this.window.add(this.shapes[0]);
		
		this.window.doRepaint();
	}
	
	/**
	 * Returns if the alien is shooting at the player.
	 */
	public boolean isShooting()
	{
		
		if(this.bulletTimer < this.bulletTime)
		{
			return false;
		}
		else
		{
			bulletTimer = 0;
			return true;
		}
	}
	/**
	 * Adds bullets to the list of bullets if the alien is shooting.
	 * 
	 * @param alienBullets The list of bullets to add to.
	 */
	public void shoot(ArrayList<Bullet> alienBullets)
	{
		alienBullets.add(new DefenderBullet(window, new Point(center.x, center.y + HEIGHT/2)));
	}

}

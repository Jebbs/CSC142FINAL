import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import uwcse.graphics.GWindow;
import uwcse.graphics.ImageShape;
import uwcse.graphics.Shape;

/**
 * 
 *The alien boss! You have to beat this one to advance to the next level!
 *
 */
public class Boss extends Alien
{
	private boolean isMovingLeft;
	private boolean usingOutsideGuns;
	
	//timers for the guns
	private int bulletTimer;
	private int bulletTime;
	
	private int damageLevel = 0;

	public Boss(GWindow window, Point center, int bulletTime) {
		super(window, center);
		
		Random rand = new Random();
		
		isMovingLeft = rand.nextBoolean();
		usingOutsideGuns = rand.nextBoolean();
		
		this.lives = 10;
		
		WIDTH = 192;
		HEIGHT = 88;
		
		yMovement = 3;
		
		this.bulletTime = bulletTime;
		//cap the shooting time.
		if(bulletTime < 500)
		{
			bulletTimer = 500;
		}
	}
	
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
		if(center.y > 50)//after it moves down a ways it stays at a single y position until it dies
		{
			yMovement = 0;
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
	
	protected void draw()
	{
		this.shapes = new Shape[1];
		
		this.shapes[0] = new ImageShape(Resources.boss[damageLevel],this.center.x - WIDTH / 2,
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
		if(usingOutsideGuns)
		{
			alienBullets.add(new BossBullet(window, new Point(center.x-58, center.y + HEIGHT/2)));
			alienBullets.add(new BossBullet(window, new Point(center.x+58, center.y + HEIGHT/2)));
			usingOutsideGuns = false;
		}
		else
		{
			alienBullets.add(new BossBullet(window, new Point(center.x-18, center.y + HEIGHT/2)));
			alienBullets.add(new BossBullet(window, new Point(center.x+18, center.y + HEIGHT/2)));
			usingOutsideGuns = true;
		}
	}
	/**
	 * The alien is being shot Decrement its number of lives and erase it from
	 * the graphics window if it is dead.
	 */
	public void isShot() {
		super.isShot();
		
		if(lives<6)
		{
			damageLevel = 1;
		}
		if(lives < 4)
		{
			damageLevel = 2;
		}
		
		
	}
	
	/**
	 * Returns true if this ship is the boss ship.
	 */
	public boolean isTheBoss()
	{
		return true;
	}
}

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import uwcse.graphics.GWindow;
import uwcse.graphics.ImageShape;
import uwcse.graphics.Rectangle;
import uwcse.graphics.Shape;
import uwcse.graphics.TextShape;

/**
 * The space ship
 */
public class SpaceShip extends MovingObject {
	/** Height of a space ship */
	public static final int HEIGHT = 68;

	/** Width of a space ship */
	public static final int WIDTH = 76;
	
	private TextShape healthText;
	private Rectangle healthBar;
	
	private int health;

	/**
	 * Construct this SpaceShip
	 */
	public SpaceShip(GWindow window, Point center) {
		super(window, center);
		this.direction = MovingObject.LEFT;	
		
		healthText = new TextShape("Health", 0, window.getWindowHeight() - 30, Color.GREEN);
		
		health = 10;
		
		// Draw this SpaceShip
		this.draw();
	}

	/**
	 * Move this SpaceShip. The space ship should be constantly moving. Select a
	 * new direction if the space ship can't move in the current direction of
	 * motion.
	 */
	public void move() {
		// A space ship moves left or right
		if (this.direction != MovingObject.LEFT
				&& this.direction != MovingObject.RIGHT)
			throw new IllegalArgumentException("Invalid space ship direction");

		// move this SpaceShip
		boolean isMoveOK;
		// Distance covered by the space ship in one step
		int step = this.boundingBox.getWidth() / 4;

		do {
			int x = this.center.x;
			switch (this.direction) {
			case LEFT:
				x -= step;
				break;
			case RIGHT:
				x += step;
				break;
			}

			// Is the new position in the window?
			if (x + this.boundingBox.getWidth() / 2 >= this.window
					.getWindowWidth())
			// past the right edge
			{
				isMoveOK = false;
				this.direction = MovingObject.LEFT;
			} else if (x - this.boundingBox.getWidth() / 2 <= 0) // past the
			// left edge
			{
				isMoveOK = false;
				this.direction = MovingObject.RIGHT;
			} else // it is in the window
			{
				isMoveOK = true;
				this.center.x = x;
			}
		} while (!isMoveOK);

		// Show the new location of this SpaceShip
		this.erase();
		this.draw();
	}

	/**
	 * Shoot at the aliens If an alien is hit, decrease its number of lives or
	 * remove it from the array list if it is dead.
	 * 
	 * @param aliens
	 *            the ArrayList of aliens
	 */
	public void shoot(ArrayList<Bullet> playerBullets) 
	{
		playerBullets.add(new PlayerBullet(window, new Point(center.x, center.y - HEIGHT/2)));
	}

	/**
	 * Draw this SpaceShip in the graphics window
	 */
	protected void draw() {

		
		this.shapes = new Shape[1];
		
		this.shapes[0] = new ImageShape(Resources.playerShip[0],this.center.x - WIDTH / 2,
				this.center.y - HEIGHT / 2);
		

		// The bounding box of this SpaceShip
		this.boundingBox = this.shapes[0].getBoundingBox();

		healthBar = new Rectangle(50,window.getWindowHeight() - 27, 10*health, 10, Color.GREEN, true);
		
		this.window.add(this.shapes[0]);
		
		this.window.add(healthText);
		
		this.window.add(healthBar);

		this.window.doRepaint();
	}
	
	/**
	 * Erases your ship from the screen.
	 */
	protected void erase()
	{
		super.erase();
		this.window.remove(healthText);
		this.window.remove(healthBar);
	}
	
	/**
	 * Called when your ship has been hit. 
	 * 
	 * It decreases your health and erases the ship when you die.
	 */
	public void isShot()
	{
		health -= 1;
		
		if(isDead())
		{
			erase();
		}
	}
	
	/**
	 * Returns true if your ship has exploded!
	 */
	public boolean isDead()
	{
		if(health ==0)
		{
			return true;
		}
		return false;
	}

	
}

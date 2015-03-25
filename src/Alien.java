import java.awt.Point;
import java.util.ArrayList;

import uwcse.graphics.GWindow;


/**
 * The representation and display of an Alien
 */

public abstract class Alien extends MovingObject {
	
	public static final int RADIUS = 5;
	
	
	// Number of lives in this Alien
	// When 0, this Alien is dead
	protected int lives;

	protected int xMovement;
	protected int yMovement;
	
	protected int WIDTH;
	protected int HEIGHT;
	
	/**
	 * Create an alien in the graphics window
	 * 
	 * @param window
	 *            the GWindow this Alien belongs to
	 * @param center
	 *            the center Point of this Alien
	 */
	public Alien(GWindow window, Point center) {
		super(window, center);
		//this.lives = (int) (Math.random() * 3 + 1);

		// Display this Alien
		//this.draw();
	}

	/**
	 * The alien is being shot Decrement its number of lives and erase it from
	 * the graphics window if it is dead.
	 */
	public void isShot() {
		lives -=1;
		if(isDead())
		{
			this.erase();
		}
	}

	/**
	 * Is this Alien dead?
	 */
	public boolean isDead() {
		return this.lives == 0;
	}

	/**
	 * Return the location of this Alien
	 */
	public Point getLocation() {
		return this.center;
	}

	/**
	 * Move this Alien As a start make all of the aliens move downward. If an
	 * alien reaches the bottom of the screen, it reappears at the top.
	 */
	public abstract void move();

	/**
	 * Display this Alien in the graphics window
	 */
	protected abstract void draw();
	
	/**
	 * Returns if the alien is shooting at the player.
	 */
	public boolean isShooting()
	{
		return false;
	}
	
	/**
	 * Adds bullets to the list of bullets if the alien is shooting.
	 * 
	 * @param alienBullets The list of bullets to add to.
	 */
	public void shoot(ArrayList<Bullet> alienBullets)
	{
		//do nothing usually
	}
	
	/**
	 * Returns the width of the alien ship.
	 */
	public int getWidth()
	{
		return WIDTH;
	}
	
	/**
	 * Returns the height of the alien ship.
	 */
	public int getHeight()
	{
		return HEIGHT;
	}
	
	/**
	 * Returns true if this ship is the boss ship.
	 */
	public boolean isTheBoss()
	{
		return false;
	}
}

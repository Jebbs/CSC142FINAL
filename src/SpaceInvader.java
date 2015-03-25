// Write your compliance statement here:
// What are your 4 extra features?
// How is your new alien different from the one described by the Alien class?
//

/*
 * I thought my joke in class about you being the Alien Invader amusing, so that is the theme for the game.
 * You are trying to conquer the alien planet, and most of the aliens are trying to escape. Because the assets
 * are downloaded from the internet it will take a few seconds to load for the first level. I did all the art 
 * for the ships, but the explosion and music are from online.
 * 
 * The minimum requirements for the assignment are met: The ship is able to be controlled left and right,
 * the basic aliens change based on how many times they are hit (although I have the ships get damaged
 * instead of change colors), and you are prompted for another round if you win or lose. 
 * 
 * The extra features included are increased difficulty (but only if you win the level), random
 * alien ships are spawned as time goes on, a boss is always the last ship to be spawned which is harder to kill, 
 * each alien ship has different moving patterns, each ship (including your own) will explode when destroyed,
 * and some of the ships will also fire back at you for invading their home.
 * 
 * Instead of just one new kind of alien, each alien ship is different. There is an escape pod which is destroyed in one shot,
 * but will fly at an angle to get away easily, the shuttle which takes two hits but moves straight down, and the defender ship
 * which takes three hits, moves side to side and it goes down, and will fire every few seconds. There is also a boss
 * that takes 10 hits and fires several shots at once every few seconds. It has four cannons along the side and alternates
 * firing between the outermost cannons and the innermost cannons.
 */

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JOptionPane;

import uwcse.graphics.GWindow;
import uwcse.graphics.GWindowEvent;
import uwcse.graphics.GWindowEventAdapter;


/**
 * A SpaceInvader displays a fleet of alien ships and a space ship. The player
 * directs the moves of the spaceship and can shoot at the aliens.
 */

public class SpaceInvader extends GWindowEventAdapter {
	// Possible actions from the keyboard
	/** No action */
	public static final int DO_NOTHING = 0;

	/** Steer the space ship */
	public static final int SET_SPACESHIP_DIRECTION = 1;

	/** To shoot at the aliens */
	public static final int SHOOT = 2;

	// Period of the animation (in ms)
	// (the smaller the value, the faster the animation)
	private int animationPeriod = 100;

	// Current action from the keyboard
	private int action;

	// Game window
	private GWindow window;

	//The moving background
	Background background;
	
	// The space ship
	private SpaceShip spaceShip;

	// Direction of motion given by the player
	private int dirFromKeyboard = MovingObject.LEFT;

	// The aliens
	private ArrayList<Alien> aliens;
	
	private ArrayList<Bullet> alienBullets;
	
	private ArrayList<Bullet> playerBullets;
	
	private ArrayList<Explosion> explosions;
	
	private int difficulty = 0;
	
	private int alienSpawnTimer, alienSpawnTime;
	
	//timer to allow the last explosion to take place
	private int endTimer = 2000;
	
	private int numberOfAliens;
	
	private boolean bossDefeated;
	
	private boolean bossSpawned;


	/**
	 * Construct a space invader game
	 */
	public SpaceInvader() {
		this.window = new GWindow("Space Invader", 500, 500);
		this.window.setExitOnClose();
		this.window.addEventHandler(this); // this SpaceInvader handles all of
		// the events fired by the graphics
		// window

		// Display the game rules
		String rulesOfTheGame = "Conquer the alien scum! Make it past the Boss and you win!\n"
				+ "To move left, press '<'.\n"
				+ "To move right, press '>'.\n"
				+ "To shoot, press the space bar.\n" + "To quit, press 'Q'.";
		JOptionPane.showMessageDialog(null, rulesOfTheGame, "Space Invader",
				JOptionPane.INFORMATION_MESSAGE);
		this.initializeGame();
	}

	/**
	 * Initialize the game (draw the background, aliens, and space ship)
	 */
	private void initializeGame() {
		// Clear the window
		this.window.erase();
		
		Resources.load(window);

		background = new Background(window);

		// ArrayList of aliens
		this.aliens = new ArrayList<Alien>();
		this.alienBullets = new ArrayList<Bullet>();
		this.playerBullets = new ArrayList<Bullet>();
		this.explosions = new ArrayList<Explosion>();
		
		//set up the game based on the difficulty
		alienSpawnTime = 3000 - (300 * difficulty);
		//cap how fast the aliens can spawn
		if(alienSpawnTime < 1000)
		{
			alienSpawnTime = 1000;
		}
		alienSpawnTimer = alienSpawnTime;
		
		numberOfAliens = 12 + (8 * difficulty);
		
		bossDefeated = false;
		bossSpawned = false;
		
		endTimer = 2000;

		// Create the space ship at the bottom of the window
		int x = this.window.getWindowWidth() / 2;
		int y = this.window.getWindowHeight() - SpaceShip.HEIGHT / 2;
		this.spaceShip = new SpaceShip(this.window, new Point(x, y));

		// start timer events
		this.window.startTimerEvents(this.animationPeriod);
	}

	/**
	 * Move the objects within the graphics window every time the timer fires an
	 * event
	 */
	public void timerExpired(GWindowEvent we) {
		// Perform the action requested by the user?
		switch (this.action) {
		case SpaceInvader.SET_SPACESHIP_DIRECTION:
			this.spaceShip.setDirection(this.dirFromKeyboard);
			break;
		case SpaceInvader.SHOOT:
			this.spaceShip.shoot(playerBullets);
			break;
		}

		this.action = SpaceInvader.DO_NOTHING; // Don't do the same action
		// twice

		// Show the new locations of the objects
		this.updateGame();
	}

	/**
	 * Select the action requested by the pressed key
	 */
	public void keyPressed(GWindowEvent e) {
		// Don't perform the actions (such as shoot) directly in this method.
		// Do the actions in timerExpired, so that the alien ArrayList can't be
		// modified at the same time by two methods (keyPressed and timerExpired
		// run in different threads).

		switch (Character.toLowerCase(e.getKey())) // not case sensitive
		{
		// Put here the code to move the space ship with the < and > keys

		case ' ': // shoot at the aliens
		{
			this.action = SpaceInvader.SHOOT;
			break;
		}
		case ',':
		{
			this.action = SpaceInvader.SET_SPACESHIP_DIRECTION;
			this.dirFromKeyboard = SpaceShip.LEFT;
			break;
		}
		case '.':
		{
			this.action = SpaceInvader.SET_SPACESHIP_DIRECTION;
			this.dirFromKeyboard = SpaceShip.RIGHT;
			break;
		}	
		case 'q': // quit the game (BlueJ might not like that one)
		{
			System.exit(0);
		}

		default: // no new action
			this.action = SpaceInvader.DO_NOTHING;
			break;
		}
	}

	/**
	 * Update the game (Move aliens + space ship)
	 */
	private void updateGame() {
		
		endUpdate();

		spawnUpdate();
		
		this.window.suspendRepaints(); // to speed up the drawing

		background.update();
		
		alienUpdate();
		
		bulletUpdate();
		
		effectsUpdate();
		
		// Move the space ship
		if(!spaceShip.isDead())
		{
			this.spaceShip.move();
		}

		// Display it all
		this.window.resumeRepaints();
	}
	
	/**
	 * All the updates related the the ending of a level.
	 */
	private void endUpdate()
	{
		//you won!
				if(aliens.size() == 0 && bossDefeated == true)
				{			
					if(endTimer <=0)
					{
						if(anotherGame("Good work! You conquered the alien's home planet! " +
							"\nDo you want to conquer the next one?"))
						{
							difficulty ++;
							initializeGame();
						}
						else
						{
							System.exit(0);
						}
					}
					else
					{
						endTimer -=100;
					}
				}
				
				//you lost!
				if(spaceShip.isDead())
				{
					if(endTimer <=0)
					{
						if(anotherGame("Too bad! You failed your conquest." +
								"\nDo you want to try again?"))
						{
							initializeGame();
						}
						else
						{
							System.exit(0);
						}
					}
					else
					{
						endTimer -=100;
					}
				}
	}
	
	/**
	 * Updates for spawning new alien ships.
	 */
	private void spawnUpdate()
	{
		alienSpawnTimer += animationPeriod;
		if(alienSpawnTimer >= alienSpawnTime)
		{
			if(numberOfAliens >0)
			{
				spawnAlien();
				numberOfAliens --;
			}
			else
			{
				if(!bossSpawned)
				{
					spawnBoss();
					bossSpawned = true;
				}
			}
			alienSpawnTimer = 0;
		}
	}
	
	/**
	 * Spawn a random alien.
	 */
	private void spawnAlien()
	{
		Random rand = new Random();
		
		switch(rand.nextInt(3))
		{
			case 0:
			{
				//add new escape pod
				
				//magic numbers are half width and with of the escape pod
				int x = rand.nextInt(window.getWindowWidth()-(60)) + 30;
				
				//move it above the screen
				int y = -60;
				
				aliens.add(new EscapePodAlien(window, new Point(x,y)));
				
				
				break;
			}
			case 1:
			{
				//add new shuttle
				
				//magic numbers are half width and with of the shuttle
				int x = rand.nextInt(window.getWindowWidth()-(68)) + 34;
				
				//move it above the screen
				int y = -86;
				
				aliens.add(new ShuttleAlien(window, new Point(x,y)));
				break;
			}
			case 2:
			{
				//add new defender
				
				//magic numbers are half width and with of the defender
				int x = rand.nextInt(window.getWindowWidth()-(86)) + 43;
				
				//move it above the screen
				int y = -92;
				
				aliens.add(new DefenderAlien(window, new Point(x,y), 4000 - (100*difficulty)));
				break;
			}
		}
	}
	
	/**
	 * Spawn the boss.
	 */
	private void spawnBoss()
	{
		Random rand = new Random();
		
		//magic numbers are half width and with of the boss
		int x = rand.nextInt(window.getWindowWidth()-(192)) + 96;
		
		//move it above the screen
		int y = -88;
		
		aliens.add(new Boss(window, new Point(x,y), 4000 - (100*difficulty)));
	}
	
	/**
	 * Update all the aliens.
	 */
	private void alienUpdate()
	{
		for(Iterator<Alien> it = aliens.iterator(); it.hasNext();)
		{
			Alien a = it.next();
			a.move();
			if(a.isShooting())
			{
				a.shoot(alienBullets);
			}
			if(a.center.y > (window.getWindowWidth()+ a.getWidth()/2))
			{
				it.remove();
			}
		}
	}
	
	/**
	 * All the updates for bullets and collision.
	 */
	private void bulletUpdate()
	{
		for(Iterator<Bullet> it = alienBullets.iterator(); it.hasNext();)
		{
			Bullet b = it.next();
			b.move();
			
			if(spaceShip.boundingBox.intersects(b.boundingBox))
			{
				if(!spaceShip.isDead())
				{
					spaceShip.isShot();
					b.remove();
					it.remove();
					
					if(spaceShip.isDead())
					{
						explosions.add(new Explosion(window, spaceShip.center));
					}					
					break;
				}
				
				
				
			}
			
			else if(b.center.y >window.getWindowHeight())
			{
				b.remove();
				it.remove();
			}
		}
		
		for(Iterator<Bullet> it = playerBullets.iterator(); it.hasNext();)
		{
			Bullet b = it.next();
			b.move();
			
			if(b.center.y < 0)
			{
				b.remove();
				it.remove();
				continue;
			}
			
			for(Iterator<Alien> iter = aliens.iterator(); iter.hasNext();)
			{
				Alien a = iter.next();
				if(a.boundingBox.intersects(b.boundingBox))
				{
				
					a.isShot();
					if(a.isDead())
					{
						explosions.add(new Explosion(window, a.center));
						
						if(!bossDefeated)
						{
							bossDefeated = a.isTheBoss();
						}
						iter.remove();
					}
					b.remove();
					it.remove();
					break;
				}
			}
			
			
		}
	}
	
	private void effectsUpdate()
	{
		for(Iterator<Explosion> it = explosions.iterator(); it.hasNext();)
		{
			Explosion e = it.next();
			
			e.move();
			
			if(e.isFinished())
			{
				it.remove();
			}
		}
	}
	
	/**
	 * Does the player want to play again?
	 */
	public boolean anotherGame(String s) {
		// this method is useful at the end of a game if you want to prompt the
		// user
		// for another game (s would be a String describing the outcome of the
		// game
		// that just ended, e.g. "Congratulations, you saved the Earth!")
		int choice = JOptionPane.showConfirmDialog(null, s, "Game over",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		return (choice == JOptionPane.YES_OPTION);
	}

	
	/**
	 * Starts the application
	 */
 	public static void main(String[] args) {
		new SpaceInvader();
	}
}

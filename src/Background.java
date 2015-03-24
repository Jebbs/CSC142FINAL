import java.awt.Color;
import java.awt.Point;
import java.util.Random;

import uwcse.graphics.GWindow;
import uwcse.graphics.Line;
import uwcse.graphics.Oval;
import uwcse.graphics.Rectangle;
import uwcse.graphics.Shape;


/**
 * The background for the Space Invader game.
 * 
 * This background consists of moving stars.
 *
 */
public class Background 
{
	private GWindow window;
	
	private Rectangle space; 
	
	private Shape[] farStars, midStars, nearStars;
	
	private int farSpeed = 1, midSpeed = 3, nearSpeed = 8;
	
	
	
	public Background(GWindow window)
	{
		this.window = window;
		
		space = new Rectangle(0,0,window.getWindowWidth(), window.getWindowHeight(), Color.BLACK, true);
		this.window.add(space);
		
		this.farStars = new Shape[30];
		this.midStars = new Shape[20];
		this.nearStars = new Shape[10];
		
		//fill the arrays with stars!
		
		Random rnd = new Random();
		
		int radius = 1;
		for(int i = 0; i < farStars.length; i++)
		{
			int x = rnd.nextInt(this.window.getWindowWidth() - 2 * radius);
			int y = rnd.nextInt(this.window.getWindowHeight() - 2 * radius);
			
			Oval star = new Oval(x, y, 2 * radius, 2 * radius, Color.white,true);
			window.add(star);
			farStars[i] = star;
			
		}
		
		radius = 2;
		for(int i = 0; i < midStars.length; i++)
		{
			int x = rnd.nextInt(this.window.getWindowWidth() - 2 * radius);
			int y = rnd.nextInt(this.window.getWindowHeight() - 2 * radius);
			
			Oval star = new Oval(x, y, 2 * radius, 2 * radius, Color.white,true);
			window.add(star);
			midStars[i] = star;
		}
		
		radius = 3;
		for(int i = 0; i < nearStars.length; i++)
		{
			int x = rnd.nextInt(this.window.getWindowWidth() - 2 * radius);
			int y = rnd.nextInt(this.window.getWindowHeight() - 2 * radius);
			
			Oval star = new Oval(x, y, 2 * radius, 2 * radius, Color.white,true);
			window.add(star);
			nearStars[i] = star;
		}
		
		
		/*
		
		for (int i = 0; i < 50; i++) 
		{
			// Random radius between 1 and 3
			int radius = rnd.nextInt(3) + 1;
			// Random location (within the window)
			// Make sure that the full circle is visible in the window
			
			this.window.add(new Oval(x, y, 2 * radius, 2 * radius, Color.white,
					true));
		}*/
		
		
	}
	
	public void update()
	{
		//update those stars!
		
		for(int i = 0; i < farStars.length; i++)
		{
			farStars[i].moveBy(0, farSpeed);
			if(farStars[i].getY()>window.getWindowHeight())
			{
				farStars[i].moveTo(farStars[i].getX(), -farStars[i].getHeight());
			}
			
		}
		for(int i = 0; i < midStars.length; i++)
		{
			midStars[i].moveBy(0, midSpeed);
			if(midStars[i].getY()>window.getWindowHeight())
			{
				midStars[i].moveTo(midStars[i].getX(), -midStars[i].getHeight());
			}
		}
		for(int i = 0; i < nearStars.length; i++)
		{
			nearStars[i].moveBy(0, nearSpeed);
			if(nearStars[i].getY()>window.getWindowHeight())
			{
				nearStars[i].moveTo(nearStars[i].getX(), -nearStars[i].getHeight());
			}
		}
	}
	public void draw()
	{
		this.window.add(space);
		for(int i = 0; i < farStars.length; i++)
		{
			window.add(farStars[i]);
		}
		for(int i = 0; i < midStars.length; i++)
		{
			window.add(midStars[i]);
		}
		for(int i = 0; i < nearStars.length; i++)
		{
			window.add(nearStars[i]);
		}
	}
	
	
	
	
}

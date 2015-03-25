import java.awt.Point;

import uwcse.graphics.GWindow;
import uwcse.graphics.ImageShape;
import uwcse.graphics.Shape;


public class Explosion extends MovingObject
{
	private int frame = 0;
	private boolean finishedAnimating;

	public Explosion(GWindow window, Point center) {
		super(window, center);
	}

	/**
	 * "Move" the explosion.
	 */
	public void move() {
		//not actually moving, but acting like an update method
		frame++;
		
		if(frame == 17)
		{
			frame --;
			finishedAnimating = true;
		}
		
		
		erase();
		draw();
	}
	
	/**
	 * Draw the explosion.
	 */
	protected void draw() 
	{
		this.shapes = new Shape[1];
		
		this.shapes[0] = new ImageShape(Resources.explosion[frame],this.center.x - 50,
				this.center.y - 50);	
		
		
		
		this.window.add(this.shapes[0]);
		
		this.window.doRepaint();
		
	}
	
	/**
	 * Returns if the explosion has finished animating.
	 * 
	 * This method should be called each frame as it removes the explosion 
	 * if it is finished.
	 */
	public boolean isFinished()
	{
		if(finishedAnimating)
		{
			erase();
		}
		
		return finishedAnimating;
	}
	
	
}

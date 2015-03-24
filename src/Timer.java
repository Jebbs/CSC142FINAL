/**
 * A little timer class for helping to fix the game's timestep
 */
public class Timer 
{
	private static long nanoTime;
	
	/**
	 * Get's the elapsed amount of time since last checking
	 * 
	 * @return time in milliseconds
	 */
	public static float getElapsedTime()
	{
		long systemTime = System.nanoTime();
		long lastTime = nanoTime;
		
		nanoTime = systemTime;
		
		return (systemTime - lastTime)/1000000f;
	}
}

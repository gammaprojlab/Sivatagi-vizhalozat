package sivatagiVizhalozat;

/**
 * Interface for the Observers
 */
public interface IObserver {
	
	/**
	 * 
	 * @param x Y coordinate of the mouseClick
	 * @param y Y coordinate of the mouseClick
	 * @return If user clicked on object it returns itself, otherwise null
	 */
	public FieldElement Clicked(int x, int y);
	
	/**
	 * 
	 * @param x Y coordinate of the mouseClick
	 * @param y Y coordinate of the mouseClick
	 */
	public void Move(int x, int y);
	
	/**
	 * Updates the view of the object
	 */
	public void Update();
}

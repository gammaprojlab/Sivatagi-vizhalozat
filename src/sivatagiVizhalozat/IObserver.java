package sivatagiVizhalozat;

import java.awt.Graphics;
import java.awt.Point;

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
	 * Updates the view of the object.
	 * @param graphics the graphics used for drawing.
	 */
	public void Update(Graphics graphics);
	
	/** Returns the position of the object, that the observer is linked to.
	  * @return Point the Position of the Object.
	*/
	public Point getPosition();
	
	/** Sets the position of the object, that the observer is linked to.
	  * @param pos the Position of the Object.
	*/
	public void setPosition(Point pos);
	
	/** Sets Object selected attribute to the value recieved as a parameter.
	  * @param s The value, taht represents if the object was selected or not.
	*/
	public void setSelected(boolean s);
}

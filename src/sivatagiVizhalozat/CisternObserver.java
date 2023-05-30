package sivatagiVizhalozat;

/**
* The observer class for the Cistern type Fields. Its liability is
* to draw out the Cister on the gamePanel.
*/
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class CisternObserver extends NodeObserver {

	private static final long serialVersionUID = 4509179881867490339L;
	protected Cistern cistern;		/** The Cistern object, of which this observer is liable for.*/
	static double epsilon = 10;     /** The radius of the object's hitbox.*/
	
	/** The constructor of the observer.
	  *  @param n The Cistern wich this observer is going to be linked to.
	*/
	public CisternObserver(Cistern n) {
		super(20, new Color(0,162,232), new Color(125,125,125));
		cistern = n;
	}
	
	/** The method, that handles wether the mouse click was within the hitbox of the object.
	  *  @param x The x coordinate of the mouseclick.
	  *  @param y The y coordinate of the mouseclick.
	  *  @return FieldElement The Cistern, that's hitbox was clicked.
	*/
	@Override
	public FieldElement Clicked(int x, int y) {
		if(position.distance(x, y) < radius + epsilon) {
			selected = true;
			return cistern;
		}
		return null;
	}
	/** The method, that draws the Cistern object to the right position.
	  *  @param g The graphics instance, that will be executing the drawing.
	*/
	public void Update(Graphics g) {
		super.Update(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.black);
		g2d.setFont(new Font("Hack", Font.BOLD, 18));
		g2d.drawString("C" + cistern.getId(), position.x-radius/2, position.y+7);
	}
}

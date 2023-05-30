package sivatagiVizhalozat;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * The observer class for the Spring type Fields. Its liability is
 * to draw out the Spring on the gamePanel.
 */
public class SpringObserver extends NodeObserver {
	private static final long serialVersionUID = 7256532664932191508L;
	static JPanel panel;		/** The panel onto which the Spring will be drawn */
	private Spring spring;		/** The Spring to which the observer is linked to */
	
	/** The constructor of the observer.
	  *  @param n The Cistern which this observer is going to be linked to.
	*/
	public SpringObserver(Spring n) {
		super(20, new Color(153,217,234), new Color(0,162,232));
		spring = n;
	}
	
	@Override
	public FieldElement Clicked(int x, int y) {
		if(position.distance(x, y) < radius + epsilon) {
			selected = true;
			return spring;
		}
		return null;
	}
	
	@Override
	public void Update(Graphics g) {
		super.Update(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.black);
		g2d.setFont(new Font("Hack", Font.BOLD, 18));
		g2d.drawString("S" + spring.getId(), position.x-radius/2, position.y+7);
	}
}

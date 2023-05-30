package sivatagiVizhalozat;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.Serializable;

/**
 * The Parent observer class for most of the FieldElements
 */
public class NodeObserver implements IObserver, Serializable {

	private static final long serialVersionUID = -9001566757956546469L;
	protected Point position;		/** The position of the Node */
	protected int radius;			/** The radius of the Node */
	protected Color innerColor;		/** The color of the inside of the Node */
	protected Color outerColor;		/** The color of the outside of the Node */
	protected boolean selected;		/** The selection of the Node */
	static double epsilon = 10;		/** The radius of the hitbox of the Node */
	
	/**
	 * Default constructor
	 */
	public NodeObserver() {
		radius = 20;
		selected = false;
	}
	
	/**
	 * 3 parameter constructor
	 * @param r The radius of the Object
	 * @param iC The innerColor
	 * @param oC The outerColor
	 */
	public NodeObserver(int r, Color iC, Color oC) {
		radius = r;
		innerColor = iC;
		outerColor = oC;
		selected = false;
	}
	
	
	
	@Override
	public FieldElement Clicked(int x, int y) {
		return null;
	}
	
	@Override
	public void Update(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		int rad = radius*2;
		if(selected){
			g2d.setColor(Color.BLACK); 
			g2d.setStroke(new BasicStroke(15));
			g2d.drawOval(position.x-radius-3, position.y-radius-3, rad+6, rad+6);
		}
		//Make border
		g2d.setColor(outerColor); 

		g2d.setStroke(new BasicStroke(15));
		g2d.drawOval(position.x-radius, position.y-radius, rad, rad);

		//Fill circle
		g2d.setColor(innerColor);
        g2d.fillOval(position.x-radius, position.y-radius, rad, rad);
	}

	@Override
	public void setSelected(boolean s) {
		selected = s;
	}

	@Override
	public Point getPosition() {
		return position;
	}

	@Override
	public void setPosition(Point pos) {
		position = pos;
	}
}

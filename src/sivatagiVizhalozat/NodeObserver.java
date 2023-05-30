package sivatagiVizhalozat;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.Serializable;
import javax.swing.JPanel;

public class NodeObserver implements IObserver, Serializable {

	protected Point position;
	protected int radius;
	protected Color innerColor;
	protected Color outerColor;
	protected boolean selected;
	static double epsilon = 10;
	
	/**
	 * 5 parameter constructor
	 * @param n The object
	 * @param p The position of the Object
	 * @param r The radius of the Object
	 * @param iC The innerColor
	 * @param oC The outerColor
	 */
	public NodeObserver(Point p, int r, Color iC, Color oC) {
		position = p;
		radius = r;
		innerColor = iC;
		outerColor = oC;
		selected = false;
	}
	
	public NodeObserver(int r, Color iC, Color oC) {
		radius = r;
		innerColor = iC;
		outerColor = oC;
		selected = false;
	}
	
	public NodeObserver() {
		radius = 20;
		selected = false;
	}
	
	@Override
	public FieldElement Clicked(int x, int y) {
		return null;
	}

	@Override
	public void Move(int x, int y) { }

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

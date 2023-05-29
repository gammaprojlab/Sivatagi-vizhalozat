package sivatagiVizhalozat;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;
import javax.swing.JPanel;

public class NodeObserver implements IObserver, Serializable {

	protected Point position;
	protected int radius;
	protected Color innerColor;
	protected Color outerColor;
	protected boolean selected;
	
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
		// TODO Implement
	}

	@Override
	public void setSelected(Boolean s) {
		// TODO Auto-generated method stub
		selected = s;
	}

	@Override
	public Point getPosition() {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public void setPosition(Point pos) {
		position = pos;
	}
}

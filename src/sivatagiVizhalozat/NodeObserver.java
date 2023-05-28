package sivatagiVizhalozat;

import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;

public class NodeObserver implements IObserver, Serializable {

	protected Point position;
	protected double radius;
	protected Color innerColor;
	protected Color outerColor;
	protected boolean clicked;
	
	/**
	 * 5 parameter constructor
	 * @param n The object
	 * @param p The position of the Object
	 * @param r The radius of the Object
	 * @param iC The innerColor
	 * @param oC The outerColor
	 */
	public NodeObserver(Point p, double r, Color iC, Color oC) {
		position = p;
		radius = r;
		innerColor = iC;
		outerColor = oC;
		clicked = false;
	}
	
	@Override
	public FieldElement Clicked(int x, int y) {
		return null;
	}

	@Override
	public void Move(int x, int y) { }

	@Override
	public void Update() {
		// TODO Implement
	}
}

package sivatagiVizhalozat;

import java.awt.Color;
import java.awt.Point;

public class PumpObserver extends NodeObserver {

	protected Pump pump;
	static double epsilon = 1e-6;
	
	public PumpObserver(Pump n, Point p, double r, Color iC, Color oC) {
		super(p, r, iC, oC);
		pump = n;
	}
	
	@Override
	public FieldElement Clicked(int x, int y) {
		if(position.distance(x, y) < radius + epsilon) {
			clicked = true;
			Update(); // ??? TODO
			return pump;
		}
		return null;
	}
	
	@Override
	public void Move(int x, int y) {
		if(clicked && position.distance(x, y) > radius + epsilon) {
			position = new Point(x,y);
			Update();
		}
	}
	
	@Override
	public void Update() {
		// TODO Implement
	}
}

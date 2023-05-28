package sivatagiVizhalozat;

import java.awt.Color;
import java.awt.Point;

public class CisternObserver extends NodeObserver {

	private Cistern cistern;
	static double epsilon = 1e-6;
	
	public CisternObserver(Cistern n, Point p, double r, Color iC, Color oC) {
		super(p, r, iC, oC);
		cistern = n;
	}
	
	@Override
	public FieldElement Clicked(int x, int y) {
		if(position.distance(x, y) < radius + epsilon) {
			clicked = true;
			Update(); // ??? TODO
			return cistern;
		}
		return null;
	}
	
	@Override
	public void Update() {
		// TODO Implement
	}
}

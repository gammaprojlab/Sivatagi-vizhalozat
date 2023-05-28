package sivatagiVizhalozat;

import java.awt.Color;
import java.awt.Point;


public class SpringObserver extends NodeObserver {
	
	private Spring spring;
	static double epsilon = 1e-6;
	
	public SpringObserver(Spring n) {
		super(new Point(0,0), 5, new Color(0, 0, 0), new Color(0, 0, 0));
		spring = n;
	}
	
	@Override
	public FieldElement Clicked(int x, int y) {
		if(position.distance(x, y) < radius + epsilon) {
			clicked = true;
			Update(); // ??? TODO
			return spring;
		}
		return null;
	}
	
	@Override
	public void Update() {
		// TODO Implement
	}
}

package sivatagiVizhalozat;

import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;

public class PipeObserver implements IObserver, Serializable {

	protected Pipe pipe;
	private Point end1;
	private Point end2;
	private Color mainColor;
	private Color secondColor;
	
	static double epsilon = 1e-6;
	
	public PipeObserver(Pipe n, Point e1, Point e2, Color m, Color s) {
		pipe = n;
		end1 = e1;
		end2 = e2;
		mainColor = m;
		secondColor = s;
	}
	
	@Override
	public FieldElement Clicked(int x, int y) {
		int a = end1.y-end2.y;
		int b = -(end1.x-end2.x);
		int c = -(end1.x * a + end1.y * b);
		double dist = Math.abs(a*x + b*y + c) / Math.sqrt(a*a + b*b);
		if(dist < 2+epsilon) {
			return pipe;
		}
		return null;
	}

	@Override
	public void Move(int x, int y) { }

	@Override
	public void Update() {
		// TODO Implement
	}
	
}

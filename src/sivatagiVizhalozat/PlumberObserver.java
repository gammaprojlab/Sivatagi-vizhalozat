package sivatagiVizhalozat;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.Serializable;

public class PlumberObserver implements IObserver, Serializable {
	
	private static final long serialVersionUID = -645375937634172512L;
	/**
	 * The Observed class.
	 */
	protected Plumber plumber;
	
	public PlumberObserver(Plumber p) {
		plumber = p;
	}
	
	/**
	 * Doesn't do anything
	 */
	@Override
	public FieldElement Clicked(int x, int y) {
		return null;
	}

	/**
	 * Updates the Player.
	 */
	@Override
	public void Update(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Point pos = plumber.location.getObserver().getPosition();
		int s = 20;
		int[] xs = new int[3]; xs[0] = pos.x; xs[1] = pos.x + s; xs[2] = pos.x - s;
		int[] ys = new int[3]; ys[0] = pos.y - s - 5; ys[1] = pos.y + s - 5; ys[2] = pos.y + s - 5;
		g2d.setColor(new Color(239,43,255));
		g2d.setStroke(new BasicStroke(5));
		g2d.drawPolygon(xs, ys, 3);
	}
	
	@Override
	public void setSelected(boolean s) { }

	@Override
	public Point getPosition() {
		return null;
	}

	@Override
	public void setPosition(Point pos) {
	}

}

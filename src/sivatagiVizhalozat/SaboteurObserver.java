package sivatagiVizhalozat;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.Serializable;

public class SaboteurObserver implements IObserver, Serializable {
	private static final long serialVersionUID = -8836075036697933923L;
	/**
	 * The Observed class.
	 */
	private Saboteur saboteur;
	
	public SaboteurObserver(Saboteur s) {
		saboteur = s;
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
		Point pos = saboteur.location.getObserver().getPosition();
		int s = 30;
		g2d.setColor(new Color(0, 0, 128));
		g2d.setStroke(new BasicStroke(5));
		g2d.drawRect(pos.x-s/2, pos.y-s/2, s, s);
	}
	
	@Override
	public void setSelected(boolean s) { }

	@Override
	public Point getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPosition(Point pos) {
		// TODO Auto-generated method stub
		
	}

}

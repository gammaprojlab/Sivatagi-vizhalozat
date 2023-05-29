package sivatagiVizhalozat;

import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;
import javax.swing.JPanel;

public class SaboteurObserver implements IObserver, Serializable {
	/**
	 * The Observed class.
	 */
	private Saboteur saboteur;
	
	private Boolean selected = false;
	
	/**
	 * Doesn't do anything
	 */
	@Override
	public FieldElement Clicked(int x, int y) {
		return null;
	}

	/**
	 * Doesn't do anything
	 */
	@Override
	public void Move(int x, int y) {
	}

	/**
	 * Updates the UIController.
	 */
	@Override
	public void Update(Graphics g) {
		
	}
	
	@Override
	public void setSelected(Boolean s) {
		// TODO Auto-generated method stub
		selected = s;
	}

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

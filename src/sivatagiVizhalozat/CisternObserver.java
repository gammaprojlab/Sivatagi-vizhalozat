package sivatagiVizhalozat;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import java.awt.Point;

public class CisternObserver extends NodeObserver {

	private Cistern cistern;
	static double epsilon = 10;
	
	public CisternObserver(Cistern n) {
		super(20, new Color(0,162,232), new Color(125,125,125));
		cistern = n;
	}
	
	@Override
	public FieldElement Clicked(int x, int y) {
		if(position.distance(x, y) < radius + epsilon) {
			selected = true;
			return cistern;
		}
		return null;
	}
	
	public void Update(Graphics g) {
		super.Update(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.black);
		g2d.setFont(new Font("Hack", Font.BOLD, 18));
		g2d.drawString("C" + cistern.getId(), position.x-radius/2, position.y+7);
	}
}

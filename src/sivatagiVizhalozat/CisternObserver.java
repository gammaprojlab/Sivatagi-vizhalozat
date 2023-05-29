package sivatagiVizhalozat;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import java.awt.Point;

public class CisternObserver extends NodeObserver {

	private Cistern cistern;
	static double epsilon = 1e-6;
	
	public CisternObserver(Cistern n) {
		cistern = n;
	}
	
	@Override
	public FieldElement Clicked(int x, int y) {
		System.out.println(x + " " + y + " " + position.x + " " + position.y);
		if(position.distance(x, y) < radius + epsilon) {
			selected = true;
			return cistern;
		}
		return null;
	}
	
	@Override
	public void Update(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		int rad = radius*2;
		if(selected){
			g2d.setColor(Color.BLACK); 
			g2d.setStroke(new BasicStroke(15));
			g2d.drawOval(position.x-radius-3, position.y-radius-3, rad+6, rad+6);
		}
		//Make border
		g2d.setColor(new Color(0,162,232)); 

		g2d.setStroke(new BasicStroke(15));
		g2d.drawOval(position.x-radius, position.y-radius, rad, rad);

		//Fill circle
		g2d.setColor(new Color(125,125,125));
        g2d.fillOval(position.x-radius, position.y-radius, rad, rad);
	}
}

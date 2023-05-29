package sivatagiVizhalozat;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JPanel;

public class PumpObserver extends NodeObserver {

	static JPanel panel;
	protected Pump pump;
	static double epsilon = 1e-6;
	private Boolean selected = false;
	
	public PumpObserver(Pump n) {
		pump = n;
	}
	
	@Override
	public FieldElement Clicked(int x, int y) {
		if(position.distance(x, y) < radius + epsilon) {
			selected = true;
			return pump;
		}
		selected = false;
		return null;
	}
	
	@Override
	public void Move(int x, int y) {
		if(selected && position.distance(x, y) > radius + epsilon) {
			position = new Point(x,y);
		}
	}
	
	@Override
	public void Update(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		int rad = radius*2;
		if(selected){
			g2d.setColor(Color.BLACK); 
			g2d.setStroke(new BasicStroke(15));
			g2d.drawOval(position.x-radius-3, position.y-radius-3, rad+6, rad+6);;
		}
		
		//Make border
		if(pump.getIsWorking()){
			g2d.setColor(new Color(125,125,125)); 
		}
		else g2d.setColor(Color.RED); 

		g2d.setStroke(new BasicStroke(15));
		g2d.drawOval(position.x-radius, position.y-radius, rad, rad);

		//Fill circle
		g2d.setColor(new Color(195,195,195));
		g2d.fillOval(position.x-radius, position.y-radius, rad, rad);

		
	}
}

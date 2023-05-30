package sivatagiVizhalozat;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JPanel;

public class PumpObserver extends NodeObserver {

	static JPanel panel;
	protected Pump pump;
	
	public PumpObserver(Pump n) {
		super(20, new Color(195,195,195), new Color(125,125,125));
		pump = n;
	}
	
	@Override
	public FieldElement Clicked(int x, int y) {
		if(position.distance(x, y) < radius + epsilon) {
			selected = true;
			return pump;
		}
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
		if(pump.GetNeighbor().size() <= 0 && pump.getPlayers().size() <= 0) return;
		Graphics2D g2d = (Graphics2D) g;
		
		int rad = radius*2;
		if(selected){
			g2d.setColor(Color.BLACK); 
			g2d.setStroke(new BasicStroke(15));
			g2d.drawOval(position.x-radius-3, position.y-radius-3, rad+6, rad+6);;
		}
		
		//Make border
		if(pump.getIsWorking()){
			g2d.setColor(outerColor); 
		}
		else g2d.setColor(Color.RED); 

		g2d.setStroke(new BasicStroke(15));
		g2d.drawOval(position.x-radius, position.y-radius, rad, rad);

		//Fill circle
		g2d.setColor(innerColor);
		g2d.fillOval(position.x-radius, position.y-radius, rad, rad);
		
		g2d.setColor(Color.black);
		g2d.setFont(new Font("Hack", Font.BOLD, 18));
		g2d.drawString("P" + pump.getId(), position.x-radius/2, position.y+7);
	}
}

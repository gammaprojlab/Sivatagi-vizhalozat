package sivatagiVizhalozat;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import java.awt.Point;


public class SpringObserver extends NodeObserver {
	static JPanel panel;
	private Spring spring;
	
	public SpringObserver(Spring n) {
		super(20, new Color(153,217,234), new Color(0,162,232));
		spring = n;
	}
	
	@Override
	public FieldElement Clicked(int x, int y) {
		if(position.distance(x, y) < radius + epsilon) {
			selected = true;
			return spring;
		}
		return null;
	}
	
	public void Update(Graphics g) {
		super.Update(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.black);
		g2d.setFont(new Font("Hack", Font.BOLD, 18));
		g2d.drawString("S" + spring.getId(), position.x-radius/2, position.y+7);
	}
}

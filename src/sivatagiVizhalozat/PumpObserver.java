package sivatagiVizhalozat;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
* The observer class for the Pump type Fields. Its liability is
* to draw out the Pump on the gamePanel.
*/
public class PumpObserver extends NodeObserver {

	private static final long serialVersionUID = 1185529468250815409L;
	static JPanel panel;		/** The panel onto which the Pump will be drawn */
	protected Pump pump;		/** The Pump to which the observer is linked */
	/**
	 * The constructor 
	 * @param n The pump, that the observer is linked to
	 */
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

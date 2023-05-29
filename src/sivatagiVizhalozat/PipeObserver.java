package sivatagiVizhalozat;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.Serializable;

import javax.swing.JPanel;

public class PipeObserver implements IObserver, Serializable {

	protected Pipe pipe;
	private Point end1;
	private Point end2;
	private Color mainColor;
	private Color secondColor;
	private Boolean selected = false;

	static double epsilon = 1e-6;
	
	public PipeObserver(Pipe n) {
		pipe = n;
	}
	
	@Override
	public FieldElement Clicked(int x, int y) {
		int a = end1.y-end2.y;
		int b = -(end1.x-end2.x);
		int c = -(end1.x * a + end1.y * b);
		double dist = Math.abs(a*x + b*y + c) / Math.sqrt(a*a + b*b);
		if(dist < 7+epsilon) {
			if(end1.distance(x, y) > 20 - epsilon && end2.distance(x, y) > 20 - epsilon) {
				selected = true;
				return pipe;
			}
		}
		selected = false;
		return null;
	}

	@Override
	public void Move(int x, int y) { }

	@Override
	public void Update(Graphics g) {
		Graphics2D g2d = (Graphics2D) g; // ha neighbour nem letezik, akkor v iranyba x tavolsagra
		end1 = pipe.GetNeighbor().get(0).getObserver().getPosition();
        end2 = pipe.GetNeighbor().get(1).getObserver().getPosition();
		
		//Outline if selected
		if(selected){
			g2d.setColor(Color.BLACK);
			g2d.setStroke(new BasicStroke(12));
			g2d.drawLine(end1.x, end1.y, end2.x, end2.y);
		}
		//Make line empty
		g2d.setColor(new Color(199,184,135));
		g2d.setStroke(new BasicStroke(8));
		g2d.drawLine(end1.x, end1.y, end2.x, end2.y);


		g2d.setColor(new Color(153,217,234));					//LIGHTBLUE
		g2d.setStroke(new BasicStroke(8));						//Line thickness
		
		if (pipe.getState().name().equals("Sticky"))
			g2d.setColor(new Color(26,121,31));					//GREEN
		else if (pipe.getState().name().equals("Slippery"))
			g2d.setColor(new Color(255,245,0));					//YELLOW
		
		else if(pipe.getIsPunctured())
			g2d.setColor(Color.RED);							//RED
		else if (pipe.getIsGrabbed()){
			g2d.setStroke(new BasicStroke(8, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{5}, 0));		//DASHED LINE
		}		

		
        g2d.drawLine(end1.x, end1.y, end2.x, end2.y);

		
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

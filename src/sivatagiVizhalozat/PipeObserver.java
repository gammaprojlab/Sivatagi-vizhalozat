package sivatagiVizhalozat;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.Serializable;

/**
 * The observer class for the Pipe type Fields. Its liability is
 * to draw out the Pipe on the gamePanel.
 */
public class PipeObserver implements IObserver, Serializable {

	private static final long serialVersionUID = 4259464217506115323L;
	protected Pipe pipe;				/**The Pipe to which the observer is connected */ 
	private Point end1;					/**The end of the line*/
	private Point end2;					/**The other end of the line */
	private boolean selected = false;	/**The selection of the observer */
	
	/**The constructor of the Pipe observer.
	 * @param n The Pipe to which the observer is going to be linked.
	 */
	public PipeObserver(Pipe n) {
		pipe = n;
	}
	
	@Override
	public FieldElement Clicked(int x, int y) {
		if(end1 == null && end2 == null) return null;
		int a = end1.y-end2.y;
		int b = -(end1.x-end2.x);
		int c = -(end1.x * a + end1.y * b);
		double dist = Math.abs(a*x + b*y + c) / Math.sqrt(a*a + b*b);
		if(dist < 7) { // Point distance from line
			if(end1.x == end2.x) {
				if((end1.x + 7 - x) * (end2.x - 7 - x) <= 0 && (end1.y - y) * (end2.y-y) <= 0) {
					selected = true;
					return pipe;
				}
			}
			else if(end1.y == end2.y) {
				if((end1.x - x) * (end2.x-x) <= 0 && (end1.y + 7 - y) * (end2.y - 7 - y) <= 0) {
					selected = true;
					return pipe;
				}
			}
			else if((end1.x - x) * (end2.x-x) <= 0 && (end1.y - y) * (end2.y-y) <= 0) {
				selected = true;
				return pipe;
			}
		}
		return null;
	}

	@Override
	public void Update(Graphics g) {
		if(pipe.GetNeighbor().size() <= 0) return;
		Graphics2D g2d = (Graphics2D) g; // ha neighbour nem letezik, akkor v iranyba x tavolsagra
		end1 = pipe.GetNeighbor().get(0).getObserver().getPosition();
		if(pipe.GetNeighbor().size() < 2) {
			if(pipe.getIsGrabbed()) {
				for(Plumber p : pipe.getGame().getPlumbers()) {
					if(p.getHeldPipe() == pipe) end2 = p.getLocation().getObserver().getPosition();
				}
			}
			else {
				end2 = new Point(end1.x-25, end1.y-25);
			}
		}
		else {
			end2 = pipe.GetNeighbor().get(1).getObserver().getPosition();
		}
		
		//Outline if selected
		if(selected){
			g2d.setColor(Color.BLACK);
			g2d.setStroke(new BasicStroke(15));
			g2d.drawLine(end1.x, end1.y, end2.x, end2.y);
		}
		//Make line empty
		g2d.setColor(new Color(199,184,135));
		g2d.setStroke(new BasicStroke(8));
		g2d.drawLine(end1.x, end1.y, end2.x, end2.y);


		g2d.setColor(new Color(153,217,234));					//LIGHTBLUE
		g2d.setStroke(new BasicStroke(8));						//Line thickness
		
		if  (pipe.getIsGrabbed())
			g2d.setStroke(new BasicStroke(8, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{10}, 0));		//DASHED LINE
		if (pipe.getIsPunctured()){
			g2d.setColor(Color.RED);							//RED
			g2d.drawLine(end1.x, end1.y, end2.x, end2.y);
			g2d.setStroke(new BasicStroke(8, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{5}, 0));
		}
		if (pipe.getState().name().equals("Sticky")) {
			g2d.setColor(new Color(26,121,31));					//GREEN		
		}		
		if (pipe.getState().name().equals("Slippery")){
			g2d.setColor(new Color(255,245,0));					//YELLOW
		}
		g2d.drawLine(end1.x, end1.y, end2.x, end2.y);
			
		g2d.setColor(Color.black);
		g2d.setFont(new Font("Hack", Font.BOLD, 18));
		g2d.drawString("P" + pipe.getId(), getPosition().x-10, getPosition().y+7);
	}
	
	@Override
	public void setSelected(boolean s) {
		selected = s;
	}

	@Override
	public Point getPosition() {
		if(end1 != null && end2 != null)
			return new Point((end1.x+end2.x) / 2, (end1.y+end2.y) / 2);
		return null;
	}

	@Override
	public void setPosition(Point pos) {
	}
}

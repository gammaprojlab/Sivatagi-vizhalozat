package sivatagiVizhalozat;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import  java.time.LocalTime;

public class GamePanel extends JPanel implements MouseListener, MouseMotionListener {
	ArrayList<IObserver> observers;
	private FieldElement selectedObject;
	private IObserver ui;
	
	public void setSelectedObject(FieldElement obj) {
		selectedObject = obj;
	}
	
	public FieldElement getSelectedObject() {
		return selectedObject;
	}
	
	public GamePanel(ArrayList<IObserver> os) {
		observers = os;
    }
	
	public GamePanel(IObserver u) {
		ui = u;
		observers = new ArrayList<IObserver>();
    }
	
	public void Add(IObserver o) {
		if(!observers.contains(o)) {
			if(o.getPosition() == null) {
				FieldElement obj = null;
				Point newPos = null;
				do {
					obj = null;
					newPos = new Point((int)(Math.random() * 600) + 20, (int)(Math.random() * 600) + 20);
					obj = Clicked(newPos.x,newPos.y);
					if(obj != null) {
						obj.getObserver().setSelected(false);
					}
				} while(obj != null);
				o.setPosition(newPos);
			}
			observers.add(o);
		}
	}

	public void Clear() {
		if(observers != null) observers.clear();
	}
	
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(IObserver observer : observers) {
        	paintObject(observer, g);
        }
    }
    
    protected void paintObject(IObserver observer, Graphics g) {
    	observer.Update(g);
    }
    
    private FieldElement Clicked(int x, int y) {
    	FieldElement obj = null;
		for(IObserver observer : observers) {
        	obj = observer.Clicked(x, y);
        	if(obj != null) break;
        }
		return obj;
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		FieldElement obj = Clicked(e.getX(), e.getY());
		if(obj != null) {
			if(obj != selectedObject) {
				selectedObject.getObserver().setSelected(false);
				selectedObject = obj;
				ui.Update(getGraphics());
			}
		}
	}

	FieldElement dragged;
	@Override
	public void mousePressed(MouseEvent e) {
		FieldElement obj = Clicked(e.getX(), e.getY());
		if(obj != null) {
			dragged = obj;
			if(!selectedObject.toString().equals(obj.toString())) obj.getObserver().setSelected(false);
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		dragged = null;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	long lastTime = System.currentTimeMillis();
	@Override
	public void mouseDragged(MouseEvent e) {
		if(dragged == null) return;
		if(System.currentTimeMillis() - lastTime > 30) {
			dragged.getObserver().setPosition(getMousePosition());
			ui.Update(getGraphics());
			lastTime = System.currentTimeMillis();
		}
	}
}

package sivatagiVizhalozat;

/**
 * A JPanel, that shows the Current state of the game, and handles the mouse actions
 * When an element on the map is being moved from one place to another.
 * It also draws calls the observer nad invokes their update method with which
 * the elements become visible.
 */
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements MouseListener, MouseMotionListener {
	private static final long serialVersionUID = -3298141573244923130L;
	ArrayList<IObserver> observers; /** All the objects observers, that are in the current game. */
	private FieldElement selectedObject; /** The FieldElement, that is currently selected by the current player. */
	private IObserver ui;	/** The observer of the user interface. */			
	
	/** Setter of the Selected object.
	  * @param obj The Selected object. 
	*/
	public void setSelectedObject(FieldElement obj) {
		selectedObject = obj;
	}
	
	/** Getter of the Selected object.
	  * @return FieldElement The Selected object. 
	*/
	public FieldElement getSelectedObject() {
		return selectedObject;
	}
	/** The constructor of the GamePanel.
	  * @param os The List of observers. 
	*/
	public GamePanel(ArrayList<IObserver> os) {
		observers = os;
    }
	
	/** The constructor of the GamePanel.
	  * @param u The observer of the user interface. 
	*/
	public GamePanel(IObserver u) {
		ui = u;
		observers = new ArrayList<IObserver>();
    }
	
	/** The method, that adds the set observer to the list of the observers in the current game instance.
	  * @param o The observer, that has to be added to the list of observers.
	*/
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

	/** Clears all the observers
	*/
	public void Clear() {
		if(observers != null) observers.clear();
	}
	
	/** Draws all the elements of the game onto the map.
	  * @param g The Graphic, that will be executing the drawing.
	*/
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(IObserver observer : observers) {
        	paintObject(observer, g);
        }
    }
    
	/** Draws an element of the game onto the map.
	  * @param observer The observer of the GamePanel.
	  * @param g The Graphic, that will be executing the drawing.
	*/
    public void paintObject(IObserver observer, Graphics g) {
    	observer.Update(g);
    }
    
	/** The method, that returns the object, that was clicked on in the GamePanel.
	  * @param x The x coordinate of the click.
	  * @param y The y coordinate of the click.
	  * @return
	*/
    private FieldElement Clicked(int x, int y) {
    	FieldElement obj = null;
    	for(int i = observers.size(); i > 0; i--) {
    		obj = observers.get(i-1).Clicked(x, y);
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
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {	
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

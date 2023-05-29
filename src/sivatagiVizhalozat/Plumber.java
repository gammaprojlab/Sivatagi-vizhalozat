package sivatagiVizhalozat;
//

//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Plumber.java
//  @ Date : 03/04/2023
//  @ Author : 
//
//

import java.util.ArrayList;

/**
 * The class responsible for the mechanic character's activities.
 * With the help of this, players can repair a pump, repair a pipe,
 * disconnect and connect pipes and pick up pumps from cisterns and then put
 * them down.
 */
public class Plumber extends Player {
	
	/**
	 * This class's Observer.
	 */
	private PlumberObserver Observer;
	
	/**
	 * 
	 * @return Observer This class's observer.
	 */
	public PlumberObserver GetObserver() {
		return Observer;
	}
	
	/**
	 * Updates the state of this class on the screen.
	 */
	public void Update() {
		
	}
	
	/**
	 * The next Plumber's id
	 */
	private static int nextId = 1;

	/**
	 * This tells you which pipe the mechanic is currently holding, if he has one.
	 */
	private Pipe heldPipe;

	/**
	 * This tells you which pump the mechanic is currently holding, if he has one.
	 */
	private Pump heldPump;

	/**
	 * Sets the Pipe.
	 * 
	 * @param p The pipe what will be put in the inventory of the Plumber.
	 */
	public void setHeldPipe(Pipe p) {
		heldPipe = p;
	}

	/**
	 * Gets the Pipe what the Plumber has in is inventory.
	 * 
	 * @return heldPipe The pipe what the Plumber has in his inventory.
	 */
	public Pipe getHeldPipe() {
		return heldPipe;
	}

	/**
	 * Sets the Pump.
	 * 
	 * @param p The pump what will be put in the inventory of the Plumber.
	 */
	public void setHeldPump(Pump p) {
		heldPump = p;
	}

	/**
	 * Gets the Pump what the Plumber has in is inventory.
	 * 
	 * @return heldPump The pump what the Plumber has in his inventory.
	 */
	public Pump getHeldPump() {
		return heldPump;
	}

	/**
	 * Konstruktor of the Plumber class, without params.
	 */
	public Plumber() {
		super();
		id = nextId++;
		heldPipe = null;
		heldPump = null;
	}

	/**
	 * One parameter constructor.
	 * 
	 * @param g Game object
	 */
	public Plumber(Game g) {
		super(g);
		id = nextId++;
		heldPipe = null;
		heldPump = null;
		g.addPlumber(this);
	}

	/**
	 * Konstruktor of te Plumber class, with params.
	 * 
	 * @param pu The Pump what the Player as in is inventory.
	 * @param pi The Pipe what the Player as in is inventory.
	 * @param id The identification of the player.
	 * @param n  The player's name.
	 * @param f  The FieldElement where the Player starts.
	 * @param g  The game, where the Player playes.
	 */
	public Plumber(Pump pu, Pipe pi, int id, String n, FieldElement f, Game g) {
		super(n, f, g);
		heldPipe = pi;
		heldPump = pu;
		g.addPlumber(this);
	}

	/**
	 * Repairs the location.
	 * 
	 * @return
	 */
	public boolean Repair() {
		if (location != null)
			return location.Repair();
		return false;
	}

	/**
	 * Connects the Pipe to the FieldElement.
	 * 
	 * @return
	 */
	public boolean ConnectPipe() {
		if (location.Connect(heldPipe)) {
			heldPipe = null;
			return true;
		}
		return false;
	}

	/**
	 * Disconnects the Pipe from the FieldElement.
	 * Can only be done when the Plumber holds no Pipe.
	 * After disconnecting the Pipe the Plumber adds it as its held pipe
	 * 
	 * @param p The pipe what the player wants to disconnect.
	 * @return
	 */
	public boolean DisconnectPipe(int p) {
		if (heldPipe == null) {
			heldPipe = location.Disconnect(p);
			return true;
		}
		return false;
	}

	/**
	 * Takes the pump from the Cistern.
	 * 
	 * @return
	 */
	public boolean TakePump() {
		if (heldPump == null) {
			Pump p = location.ProvidePump();
			if (p != null) {
				setHeldPump(p);
				return true;
			}
		}
		return false;
	}

	/**
	 * Places the Pump to the pipe, which splits the pipe.
	 * 
	 * @return
	 */
	public boolean PlacePump() {
		if (heldPump != null && location.GetNeighbor().size() == 2) {
			if (location.Split(heldPump)) {
				if (this.PlayerMove(heldPump)) {
					setHeldPump(null);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Pick up the connected Pipe element.
	 * 
	 * @return
	 */
	public boolean GrabPipe() {
		if (heldPipe == null) {
			Pipe p = location.Grab();
			if (p != null) {
				setHeldPipe(p);
				return true;
			}
		}
		return false;
	}

	/**
	 * The implementation of the Step2 function of the Steppable interface
	 */
	@Override
	public void Step2() {
	}

	/**
	 * Returns a string containing the data of the object
	 * 
	 * @return A string containing the data of the object
	 */
	public String List() {
		String ret = super.List();

		ret += "\nheldPipe: ";
		if (heldPipe != null)
			ret += heldPipe.getClass().getSimpleName() + heldPipe.getId();
		else
			ret += "null";
		ret += "\nheldPump: ";
		if (heldPump != null)
			ret += heldPump.getClass().getSimpleName() + heldPump.getId();
		else
			ret += "null";

		return ret;
	}

	/**
	 * Get the value of the nextId variable
	 * 
	 * @return The identification of the saboteur
	 */
	public static int getNextId() {
		return nextId;
	}

	/**
	 * Sets the value of nextId
	 * 
	 * @param: The value to be set
	 **/
	public static void setNextId(int id) {
		nextId = id;

	}
}

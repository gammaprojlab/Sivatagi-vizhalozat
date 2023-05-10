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
 * disconnect and connect pipes and pick up pumps from cisterns and then put them down.
 * */
public class Plumber extends Player {
	/** 
	 * The next Plumber's id
	 * */
	private static int nextId = 1;
	
	/** 
	 * This tells you which pipe the mechanic is currently holding, if he has one.
	 * */
	private Pipe heldPipe;
	
	/**
	 * This tells you which pump the mechanic is currently holding, if he has one.
	 *  */
	private Pump heldPump;
	
	/**
	 * Sets the Pipe.
	 * @param p The pipe what will be put in the inventory of the Plumber.
	 *  */
	public void setHeldPipe(Pipe p) {
		heldPipe = p;
	}
	
	/**
	 * Gets the Pipe what the Plumber has in is inventory.
	 * @return heldPipe The pipe what the Plumber has in his inventory.
	 *  */
	public Pipe getHeldPipe() {
		return heldPipe;
	}
	
	/** 
	 * Sets the Pump.
	 * @param p The pump what will be put in the inventory of the Plumber.
	 * */
	public void setHeldPump(Pump p) {
		heldPump = p;
	}
	
	/**
	 * Gets the Pump what the Plumber has in is inventory.
	 * @return heldPump The pump what the Plumber has in his inventory.
	 *  */
	public Pump getHeldPump(){
		return heldPump;
	}
	
	/**
	 * Konstruktor of the Plumber class, without params.
	 *  */
	public Plumber() {
		super();
		id = nextId++;
		heldPipe = null;
		heldPump = null;
	}
	
	/**
	 * Konstruktor of te Plumber class, with params.
	 * @param pu The Pump what the Player as in is inventory.
	 * @param pi The Pipe what the Player as in is inventory.
	 * @param id The identification of the player.
	 * @param n The player's name.
	 * @param f The FieldElement where the Player starts.
	 * @param g The game, where the Player playes.
	 *  */
	public Plumber(Pump pu, Pipe pi, int id, String n, FieldElement f, Game g){
		super(n, id, f, g);
		heldPipe = pi;
		heldPump = pu;
	}
	
	/**
	 * Repairs the location.
	 *  */
	public void Repair() {
		location.Repair();
	}
	
	/**
	 * Connects the Pipe to the FieldElement.
	 *  */
	public void ConnectPipe() {
		if(location.Connect(heldPipe)) {
			heldPipe = null;
		}
	}
	
	/**
	 * Disconnects the Pipe from the FieldElement.
	 * @param p The pipe what the player wants to disconnect.
	 *  */
	public void DisconnectPipe(int p) {
		location.Disconnect(p);
	}
	
	/**
	 * Takes the pump from the Cistern.
	 *  */
	public void TakePump() {
		if(getHeldPump() == null) {
			Pump p = location.ProvidePump();
			setHeldPump(p);
		}
	}
	
	/**
	 * Places the Pump to the pipe, which splits the pipe.
	 *  */
	public void PlacePump() {
		if(heldPump != null && location.GetNeighbor().size() == 2) {
			if(location.Split(heldPump)) {
				game.addSteppable(heldPump);
				setHeldPump(null);
				this.PlayerMove(heldPump);
			}
		}
	}
	
	/**
	 * Pick up the connected Pipe element.
	 *  */
	public void GrabPipe() {
		if (heldPipe == null) {
			Pipe p = location.Grab();
			if(p != null)
				setHeldPipe(p);
		}
	}

	/** 
	 * The implementation of the Step2 function of the Steppable interface
	*/
	@Override
	public void Step2() {
	}
	
	/**
	 * Returns a string containing the data of the object
	 * @return A string containing the data of the object
	 */
	public String toString() {
		String ret = super.toString();
		
		ret += "\nheldPipe: ";
		if(heldPipe != null)
			ret += heldPipe.getClass().getSimpleName();
		else
			ret += "null";
		ret += "\nheldPump: ";
		if(heldPump != null)
				ret += heldPump.getClass().getSimpleName();
		else
			ret += "null";
		
		return ret;
	}

	public static int nextId() {
		return nextId;
	}

	public static void setNextId(int id) {
		nextId = id;
		
	}
}

package sivatagiVizhalozat;

import java.util.ArrayList;
//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Sivatagi vizhalozat
//  @ File Name : FieldElement.java
//  @ Date : 03/04/2023
//  @ Author : 
//
//

import java.io.Serializable;

/**
 * FieldElement:
 * An abstract class for all the placed elements on the board.
 * The main use is for the players to interact with other fields.
 * It fulfills an important role in finding the neighbouring elements.
 * To simulate water flow, it implements the Steppable interface
 */
public abstract class FieldElement implements Steppable, Serializable {

	/**
	 * It stores how many connections a this element can have
	 */
	protected int maxConnections;

	/**
	 * Stores the id of the object
	 */
	protected int id;

	/**
	 * It returns the value of the id
	 * @return The id of the object
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Set the objects id
	 * @param i The new id of the object
	 */
	public void setId(int i) {
		if(i > 0) id = i;
	}

	/**
	 * Set the maxConnections variable
	 * 
	 * @param c The new value
	 */
	public void setMaxConnections(int c) {
		if (c >= 0)
			maxConnections = c;
	}

	/**
	 * Get the value of maxConnections
	 * 
	 * @return The number of connections this element can have
	 */
	public int getMaxConnections() {
		return maxConnections;
	}

	/**
	 * It stores the fields that are connected to this element
	 */
	protected ArrayList<FieldElement> connections;

	/**
	 * It sets the ArrayList for connections
	 * 
	 * @param c The new ArrayList to be set for connections
	 */
	public void setConnections(ArrayList<FieldElement> c) {
		if (c != null)
			connections = c;
	}

	/**
	 * Get the ArrayList containing all the connections of this element
	 * 
	 * @return A FieldElement ArrayList containing all the fields that are connected
	 *         to this element
	 */
	public ArrayList<FieldElement> GetNeighbor() {
		if (connections != null) {
			return connections;
		}
		return null;
	}

	/**
	 * It stores the game pbject
	 */
	protected Game game;

	/**
	 * It sets the value for the game variable
	 * 
	 * @param g The value for the game variable
	 */
	public void setGame(Game g) {
		if (g != null)
			game = g;
	}

	/**
	 * 
	 * @return The Game object
	 */
	public Game getGame() {
		if (game != null) {
			return game;
		}
		return null;
	}

	/**
	 * It stores players standing on this element
	 */
	protected ArrayList<Player> players;

	/**
	 * It sets the ArrayList for the players variable
	 * 
	 * @param p The new ArrayList to be set
	 */
	public void setPlayers(ArrayList<Player> p) {
		if (p != null) {
			players = p;
		}
	}

	/**
	 * Get the ArrayList containing all the players who are standing on this element
	 * 
	 * @return The ArrayList of players
	 */
	public ArrayList<Player> getPlayers() {
		if (players != null) {
			return players;
		}
		return null;
	}

	/**
	 * Default constructor
	 */
	public FieldElement() {
		players = new ArrayList<Player>();
		connections = new ArrayList<FieldElement>();
		maxConnections = 5;
		game = null;
	}

	/**
	 * Two parameter constructor
	 * 
	 * @param mc The maximum number of connections this element can have
	 * @param g  The Game object where this element is being used
	 */
	public FieldElement(int mc, Game g) {
		players = new ArrayList<Player>();
		connections = new ArrayList<FieldElement>();
		if (mc >= 0 && g != null) {
			maxConnections = mc;
			game = g;
			game.getMap().addFieldElement(this.getClass().getSimpleName(), this);
		} else {
			maxConnections = 5;
			game = null;
		}
	}


	/**
	 * Add a new field to connections
	 * 
	 * @param field The field to be added
	 * @return The successfulness of the command, if it was able to add the new
	 *         field to it's connections
	 */
	public boolean Add(FieldElement field) {
		if (field != null) {
			if (!connections.contains(field) && connections.size() < maxConnections) {
				connections.add(field);
				return true;
			}
		}
		return false;
	}

	/**
	 * Remove a field from connections
	 * @param field The field to be removed
	 */
	public boolean Remove(FieldElement field) {
		if (field != null) {
			if (connections.contains(field)) {
				connections.remove(field);
				return true;
			}
		}
		return false;
	}

	/**
	 * @param p A player that tries to step on this field
	 * @return The successfulness of the player stepping on this field
	 */
	public boolean StepOn(Player p) {
		if (p != null && !players.contains(p)) {
			if(p.getLocation() != null)
				p.getLocation().StepOff(p);
			players.add(p);
			p.setLocation(this);
			return true;
		}
		return false;
	}

	/**
	 * @param p A player that tries to leave this field
	 * @return The successfulness of the player leaving this field
	 */
	public boolean StepOff(Player p) {
		if (p != null) {
			if (players.contains(p)) { // if players is on this element
				players.remove(p);
				return true;
			}
		}
		return false;
	}

	/**
	 * @param p A pipe object that we want to connect to this field
	 * @return The successfulness of connecting the pipe to this field
	 */
	public boolean Connect(Pipe p) {
		if (p != null && p.getClass() != this.getClass()) {
			ArrayList<FieldElement> pipeConnections = p.GetNeighbor();
			if (pipeConnections.contains(this))
				return false;
			if (Add(p) && p.Add(this)) {
				return true;
			}
			Remove(p);
			p.Remove(this);
		}
		return false;
	}

	/**
	 * @param f The id of the pipe the player wants to disconnect from this field
	 * @return The disconnected pipe
	 */
	public Pipe Disconnect(int f) { 
		for (FieldElement p : connections) {
			if(p.getId() == f) {
				Pipe ret = p.Disconnect(p.GetNeighbor().indexOf(this));
				if(ret != null) {
					return ret;
				}
			}
		}
		return null;
	}

	/**
	 * The player tries to puncture the pipe it is standing on
	 * 
	 * @return The successfulness of the command, if it was able to puncture the
	 *         pipe
	 */
	public boolean Puncture() {
		return false;
	}

	/**
	 * @param p The pump the player will place between the pipe it is standing on
	 * @return The successfulness of the command, if it was able to split the pipe
	 *         and place the pump in-between the pipes
	 */
	public boolean Split(Pump p) {
		return false;
	}

	/**
	 * It tries to grab a pipe it can from connections
	 * 
	 * @return The grabbed pipe
	 */
	public Pipe Grab() {
		for (var connection : connections) {
			Pipe ret = connection.Grab();
			if (ret != null) {
				return ret;
			}
		}
		return null;
	}

	/**
	 * It repairs this field if it was broken
	 * 
	 * @return The successfulness of the command, if it was able to repair this
	 *         field
	 */
	public boolean Repair() {
		return false;
	}

	/**
	 * @param water The amount of water an element can pump water into a pipe
	 * @return The amount of water the pipe was able to accept
	 */
	public int SuckWater(int water) {
		return 0;
	}

	/**
	 * @param water
	 * @return The amount of water the pipe was able to pump into the element
	 */
	public int PumpWater(int water) {
		return 0;
	}

	/**
	 * If the cistern can create a new pump it creates one
	 * 
	 * @return The created pump
	 */
	public Pump ProvidePump() {
		return null;
	}

	/**
	 * @param p1 The id of the pipe the player wants to set as input
	 * @param p2 The id of the pipe the player wants to set as output
	 * @return The successfulness of the command, if the player was able to change
	 *         the direction
	 */
	public boolean ChangeDirection(int p1, int p2) {
		return false;
	}
	
	public boolean setState(PipeSurfaceState s)
	{
		return false;
	}

	/**
	 * The implementation of the Step1 function of the Steppable interface
	 */
	public void Step1() {
	}

	/**
	 * The implementation of the Step2 function of the Steppable interface
	 */
	public void Step2() {
	}

	/**
	 * Used for testing
	 */
	public String toString() {
		return "";
	}

	public boolean addPlayer(Player player) {
		players.add(player);
		return true;
	}
}

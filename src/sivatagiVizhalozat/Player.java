package sivatagiVizhalozat;
//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Player.java
//  @ Date : 03/04/2023
//  @ Author : 
//
//

import java.util.ArrayList;
import java.io.Serializable;

/** 
 * The Player class is the parent class of Plumber and Saboteur.
 * These will be the caracters what the person/people can control.
 * It implements the Steppable class because it will be Stepping and moving,
 * because of the Steppable's Step function.
 * */
public abstract class Player implements Steppable, Serializable {
	
	private static final long serialVersionUID = -1488293927263051669L;

	public IObserver getObserver() {
		return null;
	}
	
	/** 
	 * Tells how long the Player cannot move
	 * */
	private int immobile;
	
	/** 
	 * Name of the player
	 * */
	private String name;
	
	/** 
	 * The identification of the player
	 * */
	protected int id;
	
	/** 
	 * The FieldElement, which the Player is on in the game.
	 * */
	protected FieldElement location;
	
	/**
	 * It is the Game that the Player belongs to.
	 *  */
	protected Game game;

	/** 
	 * Sets the value of Immobile
	 * @param: The value to be set
	 * */
	public void setImmobile(int b) {
		immobile = b;
	}
	
	/**
	 * Get the value of the Immobile variable
	 * @return How long the Player cannot move
	 */
	public int getImmobile() {
		return immobile;
	}
	
	/** 
	 * Sets the value of Name
	 * @param: The value to be set
	 * */
	public void setName(String n) {
		name = n;
	}
	
	/**
	 * Get the value of the Name variable
	 * @return The name of the player
	 */
	public String getName() {
		return name;
	}
	
	/** 
	 * Sets the value of id
	 * @param: The value to be set
	 * */
	public void setId(int szam) {
		id = szam;
	}
	
	/**
	 * Get the value of the id variable
	 * @return The identification of the player
	 */
	public int getId() {
		return id;
	}

	/** 
	 * Sets the Players location, and also adds the player to the locations player list
	 * @param: f Where the Player is.
	 * */
	public void setLocation(FieldElement f) {
		location = f;
	}
	
	/** 
	 * Gets the Players game.
	 * @return: game In what game is the Player in.
	 * */
	public Game getGame() {
		return game;
	}
	
	/** 
	 * Sets the Players game.
	 * @param: g In what game is the Player in.
	 * */
	public void setGame(Game g) {
		game = g;
	}
	
	/**
	 * Player class's konstruktor without parameters.
	 */
	public Player() {
		immobile = 0;
		name = "Sanyi";
		location = null;
		game = null;
		
	}
	
	/**
	 * One parameter constructor.
	 * @param g Game object
	 */
	public Player(Game g) {
		immobile = 0;
		name = "Sanyi";
		location = null;
		game = g;
		g.addSteppable(this);
	}
	
	/**
	 * Player class's konstruktor with parameters.
	 * @param n The player's name
	 * @param id The identification of the player
	 * @param location The location where the player starts from
	 * @param g The Game object where this element is created
	 **/
	public Player(String n, FieldElement location, Game g) {
		immobile = 0;
		name = n;
		game = g;
		this.location = location;
		g.addSteppable(this);
	}
	
	/**
	 * Moves the Player to a specific FieldElement.
	 * @param: f The FieldElement where the Player will be put.
	 * @return if movement was successful
	 *  */
	public boolean PlayerMove(FieldElement f) {
		ArrayList<FieldElement> fields = location.GetNeighbor();
		if (immobile <= 0 && fields.contains(f) && f.StepOn(this)) 
			return true;
		return false;
	}
	
	/** 
	 * The FieldElement can be gotten with this function.
	 * @return: Returns with the players location.
	 * */
	public FieldElement getLocation() {
		return location;
	}

	/**
	 * Changes the direction, from where and to where the Pump will pump the water.
	 * @param: input From where the Pump suck the water out.
	 * @param: output To where the Pump pumps the water.
	 * @return if direction change was successful
	 *  */
	public boolean PumpDirection(int input, int output) {
		return location.ChangeDirection(input, output);
	}
	
	/**
     * Punctures the location, where the player is standing.
	 * @return if punctureing was successful
     **/
    public boolean PuncturePipe() {
    	return location.Puncture();
	}
    
    
    /**
     * It makes the pipe sticky.
     * @return 
     **/
    public boolean MakeSticky() {
    	return location.setState(PipeSurfaceState.Sticky);
    }
	
	/**
	 * The implementation of the Step1 function of the Steppable interface
	*/
	public void Step1() {
		if(immobile > 0) {
			immobile--;
		}
	}
	
	/**
	 * Returns a string containing the data of the object
	 * @return A string containing the data of the object
	 */
	public String List() {
		String ret = "id: " + id
		+ "\nname: " + name 
		+ "\nimmobile: " + immobile 
		+ "\nlocation: ";
		if(location != null)
			ret += location.getClass().getSimpleName() + location.getId();
		else
			ret+= "null";
		
		return ret;
	}
	
	public String toString() {
		return this.getClass().getSimpleName() + this.getId();
	}
}

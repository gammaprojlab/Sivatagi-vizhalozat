package sivatagiVizhalozat;
//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Saboteur.java
//  @ Date : 03/04/2023
//  @ Author : 
//
//




/**
 * The department responsible for the activities of the saboteur character.
 * With the help of this, the saboteur players can pierce the pipes.
 *  */
public class Saboteur extends Player {
	/** 
	 * The next Saboteur's id
	 * */
	private static int nextId = 1;
	
	/** 
	 * Sets the value of nextId
	 * @param: The value to be set
	 * */
	public static void setNextId(int id) {
		nextId = id;
	}
	
	/**
	 * Get the value of the nextId variable
	 * @return The identification of the saboteur
	 */
	public int getNextId() {
		return nextId;
	}
	
	/**
	 * Konstruktor of the Saboteur without params.
	 **/
	public Saboteur() {
		super();
		id = nextId++;
	}
	
	/**
	 * One parameter constructor.
	 * @param g Game object
	 */
	public Saboteur(Game g) {
		super(g);
		id = nextId++;
	}
	
	/**
	 * Konstruktor of the Saboteur with params.
	 * @param n The player's name
	 * @param id The identification of the player
	 * @param f The location where the player starts from
	 * @param g The Game object where this element is created
	 **/
	public Saboteur(String n, FieldElement f, Game g) {
		super(n, f, g);
		id = nextId++;
	}
	
	
	/**
     * Makes the pipe slippery.
	 * @return 
     **/
	public boolean MakeSlippery() {
		return location.setState(PipeSurfaceState.Slippery);
	}

	/**
	 * The implementation of the Step2 function of the Steppable interface
	 *  */
	@Override
	public void Step2() {

	}
	
	/**
	 * Returns a string containing the data of the object
	 * @return A string containing the data of the object
	 */
	public String toString() {
		return super.toString();
	}

	public static int nextId() {
		return nextId;
	}
}

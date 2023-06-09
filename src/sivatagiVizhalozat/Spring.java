package sivatagiVizhalozat;
//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Sivatagi Vizhalozat 
//  @ File Name : Spring.java
//  @ Date : 03/04/2023
//  @ Author : 
//
//

/** */
public class Spring extends FieldElement {

	private static final long serialVersionUID = -6649080444290565176L;
	private static int nextId = 1;	/** The Id of the next created Spring */
	private final IObserver observer = new SpringObserver(this);	/** The observer of the Spring object */
	/**
	 * The getter of the observer
	 * @return IObserver The observer of this Spring
	 */
	public IObserver getObserver() {
		return observer;
	}
	
	/**
	 * Sets the value of the nextId variable
	 * @param value The value to be set
	 */
	public static void setNextId(int i) {
		nextId = i;
	}

	/**
	 * Get the value of the nextId variable
	 * @return What's going to be the next id of this class
	 */
	public static int getNextId() {
		return nextId;
	}

	/**
	 * Default constructor
	 */
	public Spring() {
		super();
		id = nextId++;
	}

	/**
	 * One parameter constructor
	 * @param g The Game object where this element is being used
	 */
	public Spring(Game g) {
		super(Integer.MAX_VALUE, g);
		id = nextId++;
	}

	/**
	 * Two parameter constructor
	 * @param mc The maximum number of connections this element can have
	 * @param g The Game object where this element is being used
	 */
	public Spring(int mc, Game g) {
		super(mc, g);
		id = nextId++;
	}
	
	/** 
	 * The implementation of the Step2 function of the Steppable interface
	*/
	public void Step2() {
		for (var connection : connections) {
			connection.PumpWater(Integer.MAX_VALUE);
		}
	}

	/**
	 * Returns a string containing the data of the object
	 * @return A string containing the data of the object
	 */
	public String List() {
		String ret = "id: " + getId()
		+ "\nmaxConnections: " + ((maxConnections < Integer.MAX_VALUE) ? maxConnections : "infinite")  
		+ "\nconnections:";
		for (FieldElement neighbour : connections) {
			ret = ret.concat("\n");
			ret = ret.concat(neighbour.getClass().getSimpleName());
			ret = ret.concat(Integer.toString(neighbour.getId()));
		}
		ret = ret.concat("\nplayers:");
		for (Player player : players) {
			ret = ret.concat("\n");
			ret = ret.concat(player.getClass().getSimpleName());
			ret = ret.concat(Integer.toString(player.getId()));
		}
		return ret;
	}

}

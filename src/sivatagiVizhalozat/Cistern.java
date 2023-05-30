package sivatagiVizhalozat;
//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Sivatagi Vizhalozat
//  @ File Name : Cistern.java
//  @ Date : 03/04/2023
//  @ Author : 
//
//

/** 
 * The field or element of the game that collects water for the citites
*/
public class Cistern extends FieldElement {

	private static final long serialVersionUID = 2192456259983085350L;
	private final IObserver observer = new CisternObserver(this);

	public IObserver getObserver() {
		return observer;
	}
	/**
	 * Stores the id of the next created Cistern
	 */
	private static int nextId = 1;

	/**
	 * Default constructor;
	 */
	public Cistern() {
		super();
		id = nextId++;
		maxConnections = Integer.MAX_VALUE;
	}

	/**
	 * One parameter constructor
	 * @param g The Game object where this element is being used
	 */
	public Cistern(Game g) {
		super(Integer.MAX_VALUE, g);
		id = nextId++;
	}

	/**
	 * Two parameter constructor
	 * @param mc The maximum number of connections this element can have
	 * @param g The Game object where this element is being used
	 */
	public Cistern(int mc, Game g) {
		super(mc, g);
		id = nextId++;
	}

	/**
	 * Sets the value of the nextId variable
	 * @param id The value to be set
	 */
	public static void setNextId(int id) {
		nextId = id;
	}

	/**
	 * Get the value of the nextId variable
	 * @return What's going to be the next id of this class
	 */
	public static int getNextId() {
		return nextId;
	}

	/**
	 * It creates a new pump and returns it
	 * @return The newly created pump
	 */
	public Pump ProvidePump() {
		Pump ret = new Pump(game);
		return ret;
	}
	
	/** 
	 * The implementation of the Step1 function of the Steppable interface
	*/
	public void Step1() {
		if(connections != null) {
			for (var neighbour : connections) {
				game.addWaterCollected(neighbour.SuckWater(Integer.MAX_VALUE));
			}
		}
		if(game.getRandom()*100 > 85 && connections.size() < maxConnections) GeneratePipe();
	}

	/**
	 * If cistern has less than 10 pipes with one free end
	 * then create a new one and add it to it's connections
	 */
	private void GeneratePipe() {
		int free = 0;
		for(FieldElement p : connections) {
			if(p.connections.size() == 1) free++;
		}
		if(free < 10) {
			Pipe p = new Pipe(game);
			p.Add(this); this.Add(p);
			game.getMap().addFieldElement(p.getClass().getSimpleName(), p);
			game.addSteppable(p);
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

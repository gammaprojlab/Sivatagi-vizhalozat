package sivatagiVizhalozat;
//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Sivatagi Vizhalozat
//  @ File Name : Pipe.java
//  @ Date : 03/04/2023
//  @ Author : 
//
//


/** 
 * This class manages the pipe. It handles all of it is functions like puncturing it.
 * It also deals with new pumps being placed in the middle of it.
*/
public class Pipe extends FieldElement {

	/**
	 * Stores the state of the Pipe if it is punctured or not
	*/
	private boolean isPunctured;

	/**
	 * Stores the state of the Pipe if it is grabbed or not
	*/
	private boolean isGrabbed;
	
	/**
	 * Stores the maximum amount of water the Pipe can keep
	*/
	private int capacity;
	
	/**
	 * Stores how much water is in the Pipe
	*/
	private int water;

	/**
	 * Sets the value of the isPunctured variable
	 * @param value The value to be set
	 */
	public void setIsPunctured(boolean value) {
		Skeleton.Println(this.toString()+"setIsPunctured("+boolean.class.getSimpleName()+" "+value+")");
		isPunctured = value;
	}

	/**
	 * Get the value of the isPunctured variable
	 * @return True or false depending if it is punctured or not
	 */
	public boolean getIsPunctured() {
		Skeleton.Println(this.toString()+"getIsPunctured()");
		Skeleton.Println("return " + ((isPunctured) ? "true" : "false"));
		return this.isPunctured;
	}

	/**
	 * Sets the value of the isGrabbed variable
	 * @param value The value to be set
	 */
	public void setIsGrabbed(boolean value) {
		Skeleton.Println(this.toString()+"setIsGrabbed("+boolean.class.getSimpleName()+" "+value+")");
		isGrabbed = value;
	}

	/**
	 * Get the value of the usGrabbed variable
	 * @return True or false depemding if it is grabbed or not
	 */
	public boolean getIsGrabbed() {
		Skeleton.Println(this.toString() + "getIsGrabbed()");
		Skeleton.Println("return " + ((isGrabbed) ? "true" : "false"));
		return isGrabbed;
	}

	/**
	 * Returns how much water is in the Pipe currently
	 * @return The amount of water that's in the pipe
	 */
	public int getWater() {
		Skeleton.Println(this.toString() + "getWater()");
		Skeleton.Println("return " + water);
		return water;
	}

	/**
	 * Set how much water is in the Pipe 
	 * @param w The value of how much water is in the pipe
	 */
	public void setWater(int w) {
		Skeleton.Println(this.toString()+"setWater("+int.class.getSimpleName()+" "+w+")");
		water = w;
	}

	/**
	 * Set how much water can be in this pipe
	 * @param c The amount of water the pipe can hold
	 */
	public void setCapacity(int c) {
		Skeleton.Println(this.toString() + "setCapacity(" + int.class.getSimpleName() + c + ")");
		capacity = c;
	}

	/**
	 * Get how much water can be in this pipe
	 * @return The amount of water this pipe can hold
	 */
	public int getCapacity() {
		Skeleton.Println(this.toString() + "getCapacity()");
		Skeleton.Println("return " + capacity);
		return capacity;
	}

	
	/**
	 * Default constructor
	 */
	public Pipe() {
		super();
		Skeleton.Println(this.toString()+"Pipe()");
		isPunctured = false;
		isGrabbed = false;
		water = 0;
		capacity = 0;
		maxConnections = 2;
	}

	/**
	 * Two parameter constructor
	 * @param mc The maximum number of connections this element can have
	 * @param g The Game object where this element is being used
	 */
	public Pipe(int mc, Game g) {
		super(mc, g);
		Skeleton.Println(this.toString()+"Pipe("+ int.class.getSimpleName() + " " + mc + ", " + Game.class.getSimpleName() + " " + g +")");
		isPunctured = false;
		isGrabbed = false;
		water = 0;
		capacity = 0;
	}

	/**
	 * Two parameter constructor
	 * @param g The Game object where this element is being used
	 * @param c The maximum amount of water that the pipe can hold
	 */
	public Pipe(Game g, int c) {
		super(2, g);
		Skeleton.Println(this.toString()+"Pipe("+ Game.class.getSimpleName() + " " + g + ", " + int.class.getSimpleName() + " " + c + ")");
		isPunctured = false;
		isGrabbed = false;
		capacity = c;
		water = 0;
	}
	/**
	 * Three parameter constructor
	 * @param g The Game object where this element is being used
	 * @param c The maximum amount of water that the pipe can hold
	 * @param w The amount of water in the Pipe
	 */
	public Pipe(Game g, int c, int w) {
		super(2, g);
		Skeleton.Println(this.toString()+"Pipe("+ Game.class.getSimpleName() + " " + g + ", " + int.class.getSimpleName() + " " + c + ", " + int.class.getSimpleName() + " " + w + ")");
		isPunctured = false;
		isGrabbed = false;
		capacity = c;
		water = w;
	}

	/**
	 * Four parameter constructor
	 * @param mc The maximum number of connections this element can have
	 * @param g The Game object where this element is being used
	 * @param c The maximum amount of water that the pipe can hold
	 * @param w The amount of water in the Pipe
	 */
	public Pipe(int mc, Game g, int c, int w) {
		super(mc, g);
		Skeleton.Println(this.toString()+"Pipe("+ int.class.getSimpleName() + " " + mc + ", " + Game.class.getSimpleName() + " " + g + ", " + int.class.getSimpleName() + " " + c + ", " + int.class.getSimpleName() + " " + w + ")");
		isPunctured = false;
		isGrabbed = false;
		capacity = c;
		water = w;
	}

	/**
	 * The player tries to puncture the pipe it is standing on
	 * @return The successfulness of the command, if it was able to puncture the pipe
	*/
	public boolean Puncture() {
		Skeleton.Println(this.toString()+"Puncture()");
		if(!isPunctured) {
			isPunctured(true);	
			Skeleton.Println("return true");
			return true;
		}
		Skeleton.Println("return false");
		return false;
	}
	
	/**
	 * @param p The pump the player will place between the pipe it is standing on
	 * @return The successfulness of the command, if it was able to split the pipe and place the pump in-between the pipes
	*/
	public boolean Split(Pump p) {
		Skeleton.Println(this.toString()+"Split("+Pump.class.getSimpleName()+" "+p+")");
		Skeleton.indentation++;
		if(p != null && connections.size() == 2 && !isGrabbed) {
			Pipe newPipe = new Pipe(game, capacity, water/2);
			FieldElement a = connections.get(0);
			if(a.Remove(this) && Remove(a)) {
				p.Add(newPipe); p.Add(this);
				a.Add(newPipe);
				newPipe.Add(a); newPipe.Add(p);
				water = water/2;
				game.AddSteppable(p);
				Skeleton.indentation--;
				Skeleton.Println("return true");
				return true;
			}
			a.Add(this); Add(this);
		}
		Skeleton.indentation--;
		Skeleton.Println("return false");
		return false;
	}
	
	/**
	 * If this pipe can be grabbed it returns itself to be Grabbed,
	 * @return The grabbed pipe
	*/
	public Pipe Grab() {
		Skeleton.Println(this.toString()+"Grab()");
		Skeleton.indentation++;
		if(!isGrabbed && connections.size() == 1 && players.size() == 0) {
			isGrabbed = true;
			Skeleton.indentation--;
			Skeleton.Println("return " + this);
			return this;
		}
		Skeleton.indentation--;
		Skeleton.Println("return null");
		return null;
	}
	
	/**
	 * If the Pipe is punctured it repairs it
	 * @return The successfulness of the command, if it was able to repair this Pipe
	*/
	public boolean Repair() {
		Skeleton.Println(this.toString()+"Repair()");
		if(isPunctured && !isGrabbed) {
			isPunctured = false;
			Skeleton.Println("return true");
			return true;
		}
		Skeleton.Println("return false");
		return false;
	}
	
	/**
	 * @param w The amount of water the element is able to receive
	 * @return The amount of water the pipe was able to pump into the element
	*/
	public int SuckWater(int w) {
		Skeleton.Println(this.toString()+"SuckWater("+int.class.getSimpleName()+" "+w+")");
		if(water > 0) {
			if(w >= water) {
				int ret = water;
				water = 0;
				Skeleton.Println("return " + ret);
				return ret;
			}
			// The water that the element can accept is less than what's in the pipe
			else {
				water =- w;
				Skeleton.Println("return " + w);
				return w;
			}
		}
		Skeleton.Println("return 0");
		return 0;
	}
	
	/**
	 * @param w The amount of water an element can pump water into a pipe
	 * @return The amount of water the pipe was able to accept 
	*/
	public int PumpWater(int w) {
		Skeleton.Println(this.toString()+"PumpWater("+int.class.getSimpleName()+" "+w+")");
		if(w > 0 && capacity > water) {
			if(w >= (capacity - water)) {
				int ret = capacity-water;
				water += ret;
				Skeleton.Println("return " + ret);
				return ret;
			}
			else {
				water += w;
				Skeleton.Println("return " + w);
				return w;
			}
		}
		Skeleton.Println("return 0");
		return 0;
	}
	
	/**
	 * The implementation of the Step1 function of the Steppable interface
	*/
	public void Step1() {
		Skeleton.Println(this.toString()+"Step1()");
		Skeleton.indentation++;
		if(game != null && !isGrabbed && (isPunctured || connections.size() == 1)) {
			game.WaterSpilled(water);
			water = 0;
		}
		Skeleton.indentation--;
	}
	
	/**
	 * @param p A player that tries to step on this field
	 * @return The successfulness of the player stepping on this field
	*/
	public boolean StepOn(Player p) {
		Skeleton.Println(this.toString()+"StepOn("+Player.class.getSimpleName()+" "+p+")");
		if(p != null) {
			if(players.size() == 0) {
				players.add(p);
				Skeleton.Println("return true");
				return true;
			}
		}
		Skeleton.Println("return false");
		return false;
	}
	
	/**
	 * @param f The index of the connected element the player wants to remove the pipe from
	 * @return The disconnected pipe
	*/
	public Pipe Disconnect(int f) {
		Skeleton.Println(this.toString()+"Disconnect("+int.class.getSimpleName()+" "+f+")");
		Skeleton.indentation++;
		if(players.get(0).GetLocation() != this) {
			if(f >= 0 && f < connections.size() && connections.size() == 2) {
				Remove(connections.get(f));
				Skeleton.indentation--;
				Skeleton.Println("return " + this);
				return this;
			}
		}
		Skeleton.indentation--;
		Skeleton.Println("return null");
		return null;
	}
}

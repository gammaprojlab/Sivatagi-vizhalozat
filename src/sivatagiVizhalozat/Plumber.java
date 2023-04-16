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

import java.nio.channels.Pipe;

/** 
 * The class responsible for the mechanic character's activities.
 * With the help of this, players can repair a pump, repair a pipe,
 * disconnect and connect pipes and pick up pumps from cisterns and then put them down.
 * */
public class Plumber extends Player {
	
	/** 
	 * This tells you which pipe the mechanic is currently holding, if he has one.
	 * */
	private Pipe heldPipe;
	
	/**
	 * Gets the Pipe what the Plumber has in is inventory.
	 * @return heldPipe The pipe what te Plumber has in his inventory.
	 *  */
	public Pipe GetPipe() {
		Skeleton.Println(this.toString()+"GetPipe()");
		return heldPipe;
	}
	
	/**
	 * This tells you which pump the mechanic is currently holding, if he has one.
	 *  */
	private Pump heldPump;
	
	/**
	 * Gets the Pump what the Plumber has in is inventory.
	 * @return heldPump The pump what te Plumber has in his inventory.
	 *  */
	public Pump GetPump(){
		Skeleton.Println(this.toString()+"GetPump()");
		return heldPump;
	}
	
	/**
	 * Konstruktor of the Plumber class.
	 *  */
	public Plumber() {
		super();
		Skeleton.Println(this.toString()+"Plumber()");
		heldPipe = null;
		heldPump = null;
	}
	
	/**
	 * Konstruktor of te Plumber class, with params.
	 * @param g The game, where the Player playes.
	 * @param f The FieldElement where the Player starts.
	 * @param pi The Pipe what the Player as in is inventory.
	 * @param pu The Pump what the Player as in is inventory.
	 *  */
	public Plumber(Game g, FieldElement f, Pipe pi, Pump pu){
		super(g, f);
		Skeleton.Println(this.toString()+"Plumber(" + Game.class.getSimpleName() + " " + g + ", " + FieldElement.class.getSimpleName() + " " + f + ", " + Pipe.class.getSimpleName() + " " + pi + ", " + Pump.class.getSimpleName() + " " + pu +")");
		heldPipe = pi;
		heldPump = pu;
	}
	
	/**
	 * Repairs the location.
	 *  */
	public void Repair() {
		Skeleton.Println(this.toString()+"Repair()");
		Skeleton.identation++;
		if (location.Repair()) {
			Skeleton.Println("The FieldElement is repaired.");
		}
		else {
			Skeleton.Println("The FieldElement cannot be repaired.");
		}
		Skeleton.identation--;
	}
	
	/**
	 * Connects the Pipe to the FieldElement.
	 *  */
	public void ConnectPipe() {
		Skeleton.Println(this.toString()+"ConnectPipe()");
		if(location.Connect(heldPipe)) {
			heldPipe = null;
		}
	}
	
	/**
	 * Disconnects the Pipe from the FieldElement.
	 * @param p The pipe what the player wants to disconnect.
	 *  */
	public void DisconnectPipe(int p) {
		Skeleton.Println(this.toString()+"Disconnect("+ int.class.getSimpleName() + " " + p +")");
		Skeleton.identation++;
		if (GetPipe() == null) {
			pi = location.Disconnect(p);
			setHeldPipe(pi);
		}
		Skeleton.identation--;
	}
	
	/**
	 * Takes the pump from the Cistern.
	 *  */
	public void TakePump() {
		Skeleton.Println(this.toString()+"TakePump()");
		Skeleton.identation++;
		if(GetPump() == null) {
			p = location.ProvidePump();
			setHeldPump(p);
		}
		Skeleton.identation--;
	}
	
	/**
	 * Places the Pump to te FieldElement.
	 *  */
	public void PlacePump() {
		Skeleton.Println(this.toString()+"PlacePump()");
		Skeleton.identation++;
		if(GetPump() == null && location.getNeigbours().size() == 2) {
			location.Split(heldPump);
			game.addSteppable(heldPump);
			this.PlayerMove(heldPump);
			setHeldPump(null);
		}
		Skeleton.identation--;
	}
	
	/**
	 * Grabs the Pipe.
	 *  */
	public void GrabPipe() {
		Skeleton.Println(this.toString()+"GrabPipe()");
		ArrayList<FieldElement> elements = location.GetNeighbour();
		Skeleton.identation++;
		for(FieldElement element: elements) {
			if (GetPipe() == null) {
				Pipe p = location.Grab();
				setHeldPipe(p);
			}
		}
		Skeleton.identation--;
	}
	
	/**
	 * Sets the Pipe.
	 * @param p The pipe what will be put in the inventory of the Plumber.
	 *  */
	public void setHeldPipe(Pipe p) {
		Skeleton.Println(this.toString()+"setHeldPipe("+ Pipe.class.getSimpleName() + " " + p +")");
		heldPipe = p;
	}
	
	/** 
	 * Sets the Pump.
	 * @param p The pump what will be put in the inventory of the Plumber.
	 * */
	public void setHeldPump(Pump p) {
		Skeleton.Println(this.toString()+"setHeldPump("+ Pump.class.getSimpleName() + " " + p +")");
		heldPump = p;
	}

	/** 
	 * The implementation of the Step1 function of the Steppable interface
	*/
	@Override
	public void Step1() {
		Skeleton.Println(this.toString()+"Step1()");
	}

	/** 
	 * The implementation of the Step2 function of the Steppable interface
	*/
	@Override
	public void Step2() {
		Skeleton.Println(this.toString()+"Step2()");
	}
	
	/**
	 * Used for testing
	 */
	public String toString() {
		return this.getClass().getSimpleName()+"'"+Integer.toHexString(this.hashCode())+"'"+"."; 
	}
}

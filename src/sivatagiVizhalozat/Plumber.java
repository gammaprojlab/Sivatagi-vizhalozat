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

/** */
public class Plumber extends Player {
	
	/** */
	private Pipe heldPipe;
	
	public Pipe GetPipe() {
		Skeleton.Println(this.toString()+"GetPipe()");
		return heldPipe;
	}
	
	/** */
	private Pump heldPump;
	
	/** */
	public Pump GetPump(){
		Skeleton.Println(this.toString()+"GetPump()");
		return heldPump;
	}
	
	/** */
	public Plumber() {
		super();
		Skeleton.Println(this.toString()+"Plumber()");
		heldPipe = null;
		heldPump = null;
	}
	
	/** */
	public Plumber(Game g, FieldElement f, Pipe pi, Pump pu){
		super(g, f);
		Skeleton.Println(this.toString()+"Plumber(" + Game.class.getSimpleName() + " " + g + ", " + FieldElement.class.getSimpleName() + " " + f + ", " + Pipe.class.getSimpleName() + " " + pi + ", " + Pump.class.getSimpleName() + " " + pu +")");
		heldPipe = pi;
		heldPump = pu;
	}
	
	/** */
	public void Repair() {
		Skeleton.Println(this.toString()+"Repair()");
		if (location.Repair()) {
			Skeleton.Println("The FieldElement is repaired.");
		}
		else {
			Skeleton.Println("The FieldElement cannot be repaired.");
		}
	}
	
	/** */
	public void ConnectPipe() {
		Skeleton.Println(this.toString()+"ConnectPipe()");
		if(location.Connect(heldPipe)) {
			heldPipe = null;
		}
	}
	
	/** */
	public void DisconnectPipe(int p) {
		Skeleton.Println(this.toString()+"Disconnect("+ int.class.getSimpleName() + " " + p +")");
		ArrayList<FieldElement> neighbours = location.GetNeighbor();
		Pipe pi = location.Disconnect(p);
		setHeldPipe(pi);
	}
	
	/** */
	public void TakePump() {
		Skeleton.Println(this.toString()+"TakePump()");
		if(GetPump() == null) {
			Pump p = location.ProvidePump();
			setHeldPump(p);
		}
	}
	
	/** */
	public void PlacePump() {
		Skeleton.Println(this.toString()+"PlacePump()");
		if(GetPump() == null && location.GetNeighbor().size() == 2) {
			location.Split(heldPump);
			game.AddSteppable(heldPump);
			this.PlayerMove(heldPump);
			setHeldPump(null);
		}
	}
	
	/** */
	public void GrabPipe() {
		Skeleton.Println(this.toString()+"GrabPipe()");
		ArrayList<FieldElement> elements = location.GetNeighbor();
		for(FieldElement element: elements) {
			if (GetPipe() == null) {
				Pipe p = location.Grab();
				setHeldPipe(p);
			}
		}
	}
	
	/** */
	public void setHeldPipe(Pipe p) {
		Skeleton.Println(this.toString()+"setHeldPipe("+ Pipe.class.getSimpleName() + " " + p +")");
		heldPipe = p;
	}
	
	/** */
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
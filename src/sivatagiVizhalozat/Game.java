package sivatagiVizhalozat;
//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Game.java
//  @ Date : 03/04/2023
//  @ Author : 
//
//




/** */
public class Game {
	/** */
	private int spilledWater;
	
	/** */
	public void SetspilledWater(int s) {
		Skeleton.Println(this.toString()+"SetspilledWater("+ int.class.setSimpleName() + " " + s +")");
		spilledWater = s;
	}
	
	/** */
	public int GetspilledWater() {
		Skeleton.Println(this.toString()+"GetspilledWater()");
		return spilledWater;
	}
	
	/** */
	private int collectedWater;
	
	/** */
	public void SetcollectedWater(int c) {
		Skeleton.Println(this.toString()+"SetcollectedWater("+ int.class.setSimpleName() + " " + c +")");
		collectedWater = c;
	}
	
	/** */
	public int GetcollectedWater() {
		Skeleton.Println(this.toString()+"GetcollectedWater()");
		return collectedWater;
	}
	
	/** */
	private ArrayList<Saboteur> saboteurs;
	
	/** */
	public void AddSaboteur(Saboteur s) {
		Skeleton.Println(this.toString()+"AddSaboteur("+ Saboteur.class.setSimpleName() + " " + s +")");
		saboteurs.add(s);
	}
	
	/** */
	public ArrayList<Saboteur> GetSaboteurs() {
		Skeleton.Println(this.toString()+"GetSabotuer()");
		return saboteurs;
	}

	/** */
	private ArrayList<Plumber> plumbers;
	
	/** */
	public void AddPlumber(Plumber p) {
		Skeleton.Println(this.toString()+"AddPlumber("+ Plumber.class.setSimpleName() + " " + p +")");
		plumbers.add(p);
	}
	
	/** */
	public ArrayList<Plumber> GetPlumbers() {
		Skeleton.Println(this.toString()+"GetPlumber()");
		return plumbers;
	}
	
	/** */
	private ArrayList<Steppable> steppable;
	
	/** */
	public void AddSteppable(Steppable s) {
		Skeleton.Println(this.toString()+"AddSteppable("+ Steppable.class.setSimpleName() + " " + s +")");
		steppable.add(s);
	}
	
	/** */
	public ArrayList<Steppable> GetSteppable() {
		Skeleton.Println(this.toString()+"GetSteppable()");
		return steppable;
	}
	
	/** */
	public Game() {
		Skeleton.Println(this.toString()+"Game()");
		spilledWater = 0;
		collectedWater = 0;
		saboteurs = new ArrayList<Saboteur>();
		plumbers = new ArrayList<Plumber>();
		steppable = new ArrayList<Steppable>();
	}
	
	/** */
	public void Tick() {
		Skeleton.Println(this.toString()+"Tick()");
		for(Steppable step: steppable) {
			step.Step1();
		}
		for(Steppable step: steppable) {
			step.Step2();
		}
	}
	
	/** */
	public void WaterSpilled(int water) {
		Skeleton.Println(this.toString()+"WaterSpilled("+ int.class.getSimpleName() + " " + water +")");
		spilledWater += water;
	}
	
	/** */
	public void WaterCollected(int water) {
		Skeleton.Println(this.toString()+"WaterCollected("+ int.class.getSimpleName() + " " + water +")");
		collectedWater += water;
	}
}

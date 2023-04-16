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




/** 
 * The class that implements the Game functionality.
 * It stores and controls the mechanics, saboteurs and field elements present on the Playground.
 * Scores during the game are also credited to the teams by this department.
 * */
public class Game {
	
	/**
	 * Saboteurs' points, as many spilled into the desert.
	 * */
	private int spilledWater;
	
	/**
	 * Sets the Point of the Saboteurs.
	 * @param s Points to what the Saboteurs point will be set.
	 *  */
	public void SetspilledWater(int s) {
		Skeleton.Println(this.toString()+"SetspilledWater("+ int.class.setSimpleName() + " " + s +")");
		spilledWater = s;
	}
	
	/**
	 * Gets the points of the Saboteurs.
	 * @return spilledWater Points of the Saboteurs.
	 *  */
	public int GetspilledWater() {
		Skeleton.Println(this.toString()+"GetspilledWater()");
		return spilledWater;
	}
	
	/**
	 * Points of the Plumbers.
	 *  */
	private int collectedWater;
	
	/**
	 * Sets the Point of the Plumbers.
	 * @param c Points to what the Plumbers point will be set.
	 *  */
	public void SetcollectedWater(int c) {
		Skeleton.Println(this.toString()+"SetcollectedWater("+ int.class.setSimpleName() + " " + c +")");
		collectedWater = c;
	}
	
	/**
	 * Gets the points of the Plumbers.
	 * @return collectedWater Points of the Plumbers.
	 *  */
	public int GetcollectedWater() {
		Skeleton.Println(this.toString()+"GetcollectedWater()");
		return collectedWater;
	}
	
	/**
	 * Saboteurs who play the game.
	 *  */
	private ArrayList<Saboteur> saboteurs;
	
	/**
	 * Adds a Saboteur to the game.
	 * @param s Saboteur who will be playing.
	 *  */
	public void AddSaboteur(Saboteur s) {
		Skeleton.Println(this.toString()+"AddSaboteur("+ Saboteur.class.setSimpleName() + " " + s +")");
		saboteurs.add(s);
	}
	
	/**
	 * Gets the Saboteurs who play the game.
	 * @return saboteurs Saboteurs who play.
	 *  */
	public ArrayList<Saboteur> GetSaboteurs() {
		Skeleton.Println(this.toString()+"GetSabotuer()");
		return saboteurs;
	}

	/**
	 * Plumbers who play the game.
	 *  */
	private ArrayList<Plumber> plumbers;
	
	/** 
	 * Adds a Plumber to the game.
	 * @param p Plumber who will be playing.
	 * */
	public void AddPlumber(Plumber p) {
		Skeleton.Println(this.toString()+"AddPlumber("+ Plumber.class.setSimpleName() + " " + p +")");
		plumbers.add(p);
	}
	
	/**
	 * Gets the Plumbers who play the game.
	 * @return plumbers Plumbers who play.
	 *  */
	public ArrayList<Plumber> GetPlumbers() {
		Skeleton.Println(this.toString()+"GetPlumber()");
		return plumbers;
	}
	
	/**
	 * Fields of the map.
	 *  */
	private ArrayList<Steppable> steppable;
	
	/**
	 * Adds to the Fields.
	 * @param s New tiel to the map.
	 *  */
	public void AddSteppable(Steppable s) {
		Skeleton.Println(this.toString()+"AddSteppable("+ Steppable.class.setSimpleName() + " " + s +")");
		steppable.add(s);
	}
	
	/**
	 * Gets the fields of the map.
	 * @return steppable Fields of the map.
	 *  */
	public ArrayList<Steppable> GetSteppable() {
		Skeleton.Println(this.toString()+"GetSteppable()");
		return steppable;
	}
	
	/**
	 * Konstruktor of the Game class.
	 *  */
	public Game() {
		Skeleton.Println(this.toString()+"Game()");
		spilledWater = 0;
		collectedWater = 0;
		saboteurs = new ArrayList<Saboteur>();
		plumbers = new ArrayList<Plumber>();
		steppable = new ArrayList<Steppable>();
	}
	
	/**
	 * Imitates the flow of the water.
	 *  */
	public void Tick() {
		Skeleton.Println(this.toString()+"Tick()");
		for(Steppable step: steppable) {
			step.Step1();
		}
		for(Steppable step: steppable) {
			step.Step2();
		}
	}
	
	/**
	 * Adds points to the Saboteurs.
	 * @param water The points what will be added.
	 *  */
	public void WaterSpilled(int water) {
		Skeleton.Println(this.toString()+"WaterSpilled("+ int.class.getSimpleName() + " " + water +")");
		spilledWater += water;
	}
	
	/**
	 * Adds points to the Plumbers.
	 * @param water The points what will be added.
	 *  */
	public void WaterCollected(int water) {
		Skeleton.Println(this.toString()+"WaterCollected("+ int.class.getSimpleName() + " " + water +")");
		collectedWater += water;
	}
}

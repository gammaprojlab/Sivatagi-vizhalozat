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

import java.io.Console;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The class that implements the Game functionality.
 * It stores and controls the mechanics, saboteurs and field elements present on
 * the Playground.
 * Scores during the game are also credited to the teams by this department.
 */
public class Game implements Serializable {

	/**
	 * The flag that signs whether the game is running or not.
	 */
	private boolean isRunning;

	/**
	 * Saboteurs' points, as many spilled into the desert.
	 */
	private int spilledWater;

	/**
	 * Points of the Plumbers.
	 */
	private int collectedWater;

	/**
	 * The mode of the current Game.
	 * If the value is -1, then the game will take all random values to be truly
	 * random.
	 * If the value is 0, then the game will take all random values to be 0.
	 * If the value is 1, then the game will take all random values to be 1.
	 */
	private int tester;

	/**
	 * The nextIds of the game elements for each class.
	 */
	private int[] classIdArray;

	/**
	 * Saboteurs who play the game.
	 */
	private ArrayList<Saboteur> saboteurs;

	/**
	 * Plumbers who play the game.
	 */
	private ArrayList<Plumber> plumbers;

	/**
	 * The map instance.
	 */
	private Map map = new Map();

	/**
	 * Fields of the map.
	 */
	private ArrayList<Steppable> steppable;

	/**
	 * The number of rounds left till the end of the game.
	 */
	private int remainingRounds = 0;

	/**
	 * The currently active player. This player is performing the actions currently.
	 */
	private Player activePlayer = null;

	/**
	 * The getter of the activePlayer attribute.
	 * 
	 * @return Player The currently active player.
	 */
	public Player getActivePlayer() {
		return activePlayer;
	}

	/**
	 * The setter of the isRunning attribute.
	 * 
	 * @param b The value we want to set for the isRunning attribute.
	 */
	public void setIsRunning(boolean b) {
		isRunning = b;
	}

	/**
	 * Gets the private isRunning value.
	 */
	public boolean getIsRunning() {
		return isRunning;
	}

	/**
	 * Gets the map instance, that the game is currently being played on.
	 * 
	 * @return The map instance
	 */
	public Map getMap() {
		return map;
	}

	/**
	 * sets the Point of the Saboteurs.
	 * 
	 * @param s Points to what the Saboteurs point will be set.
	 */
	public void setSpilledWater(int s) {
		spilledWater = s;
	}

	/**
	 * Gets the points of the Saboteurs.
	 * 
	 * @return spilledWater Points of the Saboteurs.
	 */
	public int getSpilledWater() {
		return spilledWater;
	}

	/**
	 * Gets the mode of the game.
	 * 
	 * @return The value of the mode of the game.
	 */
	public int getTester() {
		return tester;
	}

	/**
	 * Sets the mode of the game.
	 * 
	 * @param The value of the mode of the game.
	 */
	public void setTester(int t) {
		tester = t;
	}

	/**
	 * Gives the classIdArray back
	 * 
	 * @return The classIdArray.
	 */
	public int[] getClassIdArray() {
		int[] array = null;
		array = classIdArray;
		return array;
	}

	/**
	 * Adds a value to the classIdArray.
	 * 
	 * @return The classIdArray.
	 */
	public void addClassIdArray(int i) {
		classIdArray[classIdArray.length] = i;
	}

	/**
	 * sets the Point of the Plumbers.
	 * 
	 * @param c Points to what the Plumbers point will be set.
	 */
	public void setCollectedWater(int c) {
		collectedWater = c;
	}

	/**
	 * Gets the points of the Plumbers.
	 * 
	 * @return collectedWater Points of the Plumbers.
	 */
	public int getCollectedWater() {
		return collectedWater;
	}

	/**
	 * Adds a Saboteur to the game.
	 * 
	 * @param s Saboteur who will be playing.
	 */
	public void addSaboteur(Saboteur s) {
		saboteurs.add(s);
	}

	/**
	 * Gets the Saboteurs who play the game.
	 * 
	 * @return saboteurs Saboteurs who play.
	 */
	public ArrayList<Saboteur> getSaboteurs() {
		return saboteurs;
	}

	/**
	 * Adds a Plumber to the game.
	 * 
	 * @param p Plumber who will be playing.
	 */
	public void addPlumber(Plumber p) {
		plumbers.add(p);
	}

	/**
	 * Gets the Plumbers who play the game.
	 * 
	 * @return plumbers Plumbers who play.
	 */
	public ArrayList<Plumber> getPlumbers() {
		return plumbers;
	}

	/**
	 * Adds to the Fields.
	 * 
	 * @param s New tiel to the map.
	 */
	public void addSteppable(Steppable s) {
		steppable.add(s);
	}

	/**
	 * Gets the fields of the map.
	 * 
	 * @return steppable Fields of the map.
	 */
	public ArrayList<Steppable> getSteppable() {
		return steppable;
	}

	/**
	 * Constructor of the Game class.
	 */
	public Game() {
		tester = -1;
		spilledWater = 0;
		collectedWater = 0;
		saboteurs = new ArrayList<Saboteur>();
		plumbers = new ArrayList<Plumber>();
		steppable = new ArrayList<Steppable>();
		classIdArray = new int[6];
		map = new Map();
		remainingRounds = 40;
	}

	/**
	 * 8 parametered constructor of the Game class.
	 * 
	 * @param spilled   The amount of water spilled.
	 * @param collected The amount of water collected.
	 * @param t         The mode of the game.
	 * @param pl        The array list of plumbers playing the game.
	 * @param sa        The array list of saboteurs playing the game.
	 * @param st        The array list of all game elements in the current game.
	 * @param classId   The array of the next id values of all game elements.
	 * @param maxrounds The max amount of round the game will be having. At 0 the
	 *                  game stops.
	 */
	public Game(int spilled, int collected, int t, ArrayList<Plumber> pl, ArrayList<Saboteur> sa,
			ArrayList<Steppable> st, int[] classId, int maxrounds) {
		tester = t;
		spilledWater = spilled;
		collectedWater = collected;
		saboteurs = sa;
		plumbers = pl;
		steppable = st;
		classIdArray = classId;
		map = new Map();
		remainingRounds = maxrounds;
		activePlayer = null;
	}

	/**
	 * Set activePlayer to the next Player. Plumbers start and the next player is
	 * selected in a zig-zag pattern between the two player types. And invokes the
	 * tick method.
	 */
	public void NextPlayer() {
		if (activePlayer == null) {
			activePlayer = plumbers.get(0);
			return;
		}
		if (plumbers.contains(activePlayer)) {
			activePlayer = saboteurs.get(activePlayer.getId());
		} else if (saboteurs.contains(activePlayer) && activePlayer.getId() + 1 < Plumber.getNextId()) {
			activePlayer = plumbers.get(activePlayer.getId() + 1);
		} else {
			activePlayer = plumbers.get(0);
			remainingRounds -= 1;
			Tick();
		}	
	}

	/**
	 * Imitates the flow of the water.
	 */
	public void Tick() {
		ArrayList<Steppable> steppables = new ArrayList<>(steppable);
		for (Steppable step : steppables) {
			step.Step1();
		}
		for (Steppable step : steppables) {
			step.Step2();
		}
		if (remainingRounds <= 0) {
			EndGame();
		}
	}

	/**
	 * Adds points to the Saboteurs.
	 * 
	 * @param water The points what will be added.
	 */
	public void addWaterSpilled(int water) {
		spilledWater += water;
	}

	/**
	 * Adds points to the Plumbers.
	 * 
	 * @param water The points what will be added.
	 */
	public void addWaterCollected(int water) {
		collectedWater += water;
	}

	/**
	 * Used for testing
	 */
	public String toString() {
		String data = "";
		data += "spilledWater: " + spilledWater + "\n";
		data += "collectedWater: " + collectedWater;
		return data;
	}

	/**
	 * Receive a random number (or fix number dependent of the tester's value).
	 * 
	 * @return A random value
	 */
	public double getRandom() {
		switch (tester) {
			case -1:
				return Math.random();
			case 0:
				return 0;
			case 1:
				return 1;
			default:
				return Math.random();
		}
	}

	/**
	 * Set the IdArray to the received parameters value.
	 * 
	 * @param array The array we want to IdArray to be.
	 */
	public void setIdArray(int[] array) {
		classIdArray = array;
	}

	/**
	 * Get the array of ids of the elements that are present in the game.
	 * 
	 * @return int[] The array of the class ids.
	 */
	public int[] getIdArray() {
		return classIdArray;
	}

	/**
	 * Find and return the Plumber with the received id.
	 * 
	 * @param id The id of the Plumber we are looking for.
	 * @return The needed Plumber.
	 */
	public Plumber getPlumber(int id) {
		for (Plumber plumber : plumbers) {
			if (plumber.getId() == id)
				return plumber;
		}
		return null;
	}

	/**
	 * Find and return the Saboteur with the received id.
	 * 
	 * @param id The id of the Saboteur we are looking for.
	 * @return The needed Saboteur.
	 */
	public Saboteur getSaboteur(int id) {
		for (Saboteur saboteur : saboteurs) {
			if (saboteur.getId() == id)
				return saboteur;
		}
		return null;
	}

	/**
	 * Set the remaining rounds value.
	 */
	public void setRemainingRounds(int remaining) {
		remainingRounds = remaining;
	}

	/**
	 * If the remaining rounds have reached 0, then call this function.
	 * This handles the end of the game and writes out the scores of the
	 * 2 opposing groups.
	 */
	public void EndGame() {
		System.out.println("THE GAME HAS ENDED!");

		if (collectedWater > spilledWater)
			System.out.println("The plumbers have won the game");
		else if (collectedWater > spilledWater)
			System.out.println("The saboteurs have won the game");
		else if (collectedWater == spilledWater)
			System.out.println("The game is a tie");

		System.out.println("The plumbers have collected:     " + collectedWater + " unit water");
		System.out.println("The saboteurs have spilled:     " + spilledWater + " unit water\n");
		System.out.println("Press any key to continue!");

		isRunning = false;

		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
	}
}

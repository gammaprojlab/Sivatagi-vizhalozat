package sivatagiVizhalozat;

/**
 * @class The TakePumpTest class, that is responsible for testing if 
 * 			when a plumber tries to take a Pump from a Cistern it
 * 			gets allocated to him.
 */
public class TakePumpTest extends Test{

	/**
	 * The Constructor of the TakePumpTest class
	 * @param name The name of the test
	 */
	TakePumpTest(String name) {
		super(name);
	}
	
	/**
	 * The function that executes the test with all the setup it needs.
	 */
	public void Run()
	{
		//Setting up test elements
		Game game = new Game();
		
		Cistern cistern = new Cistern(2,game);
		
		//Place player on pipe
		Plumber plumber = new Plumber();
		plumber.SetLocation(cistern);
		plumber.SetGame(game);
		
		Skeleton.Println("");
		Skeleton.Println("SETUP COMPLETE");
		Skeleton.Println("");
		
		//Take pump
		plumber.TakePump();

	}
}

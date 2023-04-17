package sivatagiVizhalozat;

/**
 * @class The FixPumpTest class, that is responsible for testing if 
 * 			when the Pump stops working and a plumber tries to fix it
 * 			it gets fixed and starts working again.
 */
public class FixPumpTest extends Test{
	
	/**
	 * The Constructor of the FixPumpTest class
	 * @param name The name of the test
	 */
	FixPumpTest(String name){
		super(name);
	}
	
	/**
	 * The function that executes the test with all the setup it needs.
	 */
	public void Run()
	{
		//Setting up test elements
		Game game = new Game();
		Pump pump = new Pump();
		
		//Place player on pump
		Plumber plumber = new Plumber();		
		plumber.SetLocation(pump);
		plumber.SetGame(game);
		pump.StopWorking();
		
		Skeleton.Println("");
		Skeleton.Println("SETUP COMPLETE");
		Skeleton.Println("");
		
		
		
		//The functional part of the test
			//Mess with the pump
		
		
		if(!plumber.location.Repair())
			Skeleton.Println("FAILURE! Pump couldn't get fixed.");
		else
			Skeleton.Println("SUCCES! Pump got fixed.");
	}

}

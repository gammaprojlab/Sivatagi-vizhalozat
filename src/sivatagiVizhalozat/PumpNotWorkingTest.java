package sivatagiVizhalozat;

/**
 * @class The TimerTest class, that is responsible for testing if 
 * 			when time passes does all Steppable entity's step methods
 * 			get called. And also do they work according to expectations
 */
public class PumpNotWorkingTest extends Test{
	
	/**
	 * The Constructor of the PumpNotWorkingTest class
	 * @param name The name of the test
	 */
	PumpNotWorkingTest(String name){
		super(name);
	}
	
	/**
	 * The function that executes the test with all the setup it needs.
	 */
	public void Run()
	{
		//Setting up test elements
		//Game game = new Game();
		Pump pump = new Pump();
		
		/*
		//Place player on pump
		Plumber plumber = new Plumber(pump,game);
		*/
		
		Skeleton.Println("");
		Skeleton.Println("SETUP COMPLETE");
		Skeleton.Println("");
		
		Skeleton.indentation++;
		Skeleton.indentation++;
		
		//The functional part of the test
			//Mess with the pump
		pump.StopWorking();
		
		if(pump.getIsWorking())
			Skeleton.Println("FAILURE! Pump is still working.");
		else
			Skeleton.Println("SUCCES! Pump is not working anymore.");
	}
	
}

package sivatagiVizhalozat;

/**
 * @class The TimerTest class, that is responsible for testing if 
 * 			when time passes does all Steppable entity's step methods
 * 			get called. And also do they work according to expectations
 */
public class TimerTest extends Test{

	/**
	 * The Constructor of the TimerTest class
	 * @param name The name of the test
	 */
	TimerTest(String name) {
		super(name);
	}
	
	/**
	 * The function that executes the test with all the setup it needs.
	 */
	public void Run()
	{
		
		//Setting up test elements
		Game game = new Game();
		
		Spring spring = new Spring(2,game);
		
		Pipe pipe1 = new Pipe(2,game);
		
		Pump pump = new Pump(5,game); 
		
		Pipe pipe2 = new Pipe(2,game);
		
		Pipe pipe3 = new Pipe(2,game);
		
		Cistern cistern = new Cistern(2,game);
	
		game.AddSteppable(cistern);
		game.AddSteppable(pipe1);
		game.AddSteppable(pipe2);
		game.AddSteppable(pipe3);
		game.AddSteppable(pump);
		game.AddSteppable(spring);
		
		//Fixate pipe
		spring.Connect(pipe1);
		pump.Connect(pipe1);
		pump.Connect(pipe3);
		pump.Connect(pipe2);
		cistern.Connect(pipe2);
		
		Skeleton.Println("");
		Skeleton.Println("SETUP COMPLETE");
		Skeleton.Println("");
		
		game.Tick();

		
		
	}

}

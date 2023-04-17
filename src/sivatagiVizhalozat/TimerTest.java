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
		
		Pump pump = new Pump(2,game); 
		
		Pipe pipe2 = new Pipe(2,game);
		
		Cistern cistern = new Cistern(2,game);
	
		
		//Fixate pipe
		spring.Connect(pipe1);
		pump.Connect(pipe1);
		pump.Connect(pipe2);
		cistern.Connect(pipe2);
		
		//Place player on cistern
		Plumber plumber = new Plumber();
		plumber.SetLocation(cistern);
		plumber.SetGame(game);
		
		
		Skeleton.Println("");
		Skeleton.Println("SETUP COMPLETE");
		Skeleton.Println("");
		
		Skeleton.Println("Select from where you want to disconnect the pipe:");
		Skeleton.Println("1. Spring");
		Skeleton.Println("2. Pump");
		Skeleton.Println("3. Cistern");
		
		Skeleton.indentation++;
		game.Tick();
		Skeleton.indentation--;
	}

}

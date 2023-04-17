package sivatagiVizhalozat;

import java.util.Scanner;

/**
 * @class The DisconnectPipeTest class, that is responsible for testing if a 
 * 		  pipe can be disconnected from a pump or cistern or spring.
 */

public class DisconnectPipeTest extends Test{
	/**
	 * The Constructor of the DisconnectPipeTest class
	 * @param name The name of the test
	 */
	DisconnectPipeTest(String name) {
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
		
		Scanner in = new Scanner(System.in);
		 
        int choice = in.nextInt();
        
        in.close();
		
        Skeleton.indentation++;
		
		switch(choice)
		{	case 1:
				plumber.SetLocation(spring);
				plumber.DisconnectPipe(0);
				Skeleton.Println("SUCCES!");
				break;
			case 2:
				plumber.SetLocation(pump);
				plumber.DisconnectPipe(0);
				Skeleton.Println("SUCCES!");
				break;
			case 3:
				plumber.SetLocation(cistern);
				plumber.DisconnectPipe(0);
				Skeleton.Println("SUCCES!");
				break;
			default:
				Skeleton.Println("FAILURE");
		}
		
		Skeleton.indentation--;
	}
}

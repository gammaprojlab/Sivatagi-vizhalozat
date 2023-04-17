package sivatagiVizhalozat;

import java.io.Console;
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
		
		
		Boolean quit = false;
		
		while(!quit)
		{
			
			
			
			Skeleton.indentation++;
			Skeleton.Println("0 to quit");
			Skeleton.Println("Cistern: 1,");
			Skeleton.Println("Pump: 2,");
			Skeleton.Println("Spring: 3");
			Skeleton.Println("Chose test scenario:");
			Skeleton.indentation--;
			
			Scanner sc = new Scanner(System.in);
			String str = sc.nextLine();
			try 
			{
			int choice = Integer.parseInt(str);
			if(!str.isBlank())
			{
				switch(choice)
				{	
					case 0:
						quit = true;
						break;
							
					case 1:
						plumber.SetLocation(spring);
						Skeleton.Println("");
						Skeleton.Println("SETUP COMPLETE");
						Skeleton.Println("");
						plumber.DisconnectPipe(0);
						Skeleton.Println("");
						Skeleton.Println("SUCCES!");
						Skeleton.Println("");
						quit = true;
						break;
					case 2:
						plumber.SetLocation(pump);
						Skeleton.Println("");
						Skeleton.Println("SETUP COMPLETE");
						Skeleton.Println("");
						plumber.DisconnectPipe(0);
						Skeleton.Println("");
						Skeleton.Println("SUCCES!");
						Skeleton.Println("");
						quit = true;
						break;
					case 3:
						plumber.SetLocation(cistern);
						Skeleton.Println("");
						Skeleton.Println("SETUP COMPLETE");
						Skeleton.Println("");
						plumber.DisconnectPipe(0);
						Skeleton.Println("");
						Skeleton.Println("SUCCES!");
						Skeleton.Println("");
						quit = true;
						break;
					default:
						Skeleton.Println("FAILURE");
				}
			}
		}
			catch (Exception ex)
			{
				ex.printStackTrace();
				System.out.println("Something ain't right!");
			}
			finally {}
		}
	}
}

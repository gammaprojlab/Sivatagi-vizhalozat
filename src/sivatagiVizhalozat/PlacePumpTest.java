package sivatagiVizhalozat;

/**
 * @class The PlacePumpTest class, that is responsible for testing if 
 * 			when placing a pump the Pipe it is getting placed on is
 * 			splitted into 2 parts and those get connected to the
 * 			corresponding pumps. To which the parent pipe used to be
 * 			Connected too.
 */
public class PlacePumpTest extends Test{

	/**
	 * The Constructor of the PlacePumpTest class
	 * @param name The name of the test
	 */
	PlacePumpTest(String name) {
		super(name);
	}
	
	/**
	 * The function that executes the test with all the setup it needs.
	 */
	public void Run()
	{
		//Setting up test elements
		Game game = new Game();
		
		Pipe pipe1 = new Pipe(2,game);
		Pipe pipe2 = new Pipe(2,game);
		
		Cistern cistern = new Cistern(2,game);
		
		Pump pump1 = new Pump(2,game); 
		Pump pump2 = new Pump(2,game);
		
		//Fixate pipe
		pump1.Connect(pipe1);
		pump2.Connect(pipe1);
		
		pump2.Connect(pipe2);
		cistern.Connect(pipe2);
		
		//Place player on cistern
		Plumber plumber = new Plumber();
		plumber.SetLocation(cistern);
		plumber.SetGame(game);
		
		Skeleton.Println("");
		Skeleton.Println("SETUP COMPLETE");
		Skeleton.Println("");
		
		//Take pump
		plumber.TakePump();
		
		//Move to pipe1  P=X=P===C
		plumber.PlayerMove(pipe2);
		plumber.PlayerMove(pump2);
		plumber.PlayerMove(pipe1);
		
		//Place pump
		plumber.PlacePump();
		
		//Check if the placed pump has the correct neighbors. PIPES WHEN A PUMP IS PLACED ARE FIRST CONNECTED TO ALREADY EXISTING PUMPS AND THEN TO THE NEWLY PLACED ONE
		if(pump1.GetNeighbor().get(0).GetNeighbor().get(1)==pump2.GetNeighbor().get(0).GetNeighbor().get(1)){
			Skeleton.Println("SUCCES! Between pump1 and pump2 there is another pump. It is also connected to pump1 and pump2 with 1-1 pipes.");
			Skeleton.indentation++;
				Skeleton.Println("The id of the pump: "+pump1.GetNeighbor().get(0).GetNeighbor().get(1));
		}
		else
			Skeleton.Println("FAILURE! The pump didn't get placed where it should be.");
	}
}

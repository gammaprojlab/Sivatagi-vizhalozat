package sivatagiVizhalozat;

public class FixPumpTest extends Test{
	
	FixPumpTest(String name){
		super(name);
	}
	
	public void Run()
	{
		//Setting up test elements
		Game game = new Game();
		Pump pump = new Pump();
		
		//Place player on pump
		Plumber plumber = new Plumber(pump,game);		
		
		Skeleton.Println("");
		Skeleton.Println("SETUP COMPLETE");
		Skeleton.Println("");
		
		Skeleton.indentation++;
		Skeleton.indentation++;
		
		//The functional part of the test
			//Mess with the pump
		pump.StopWorking();
		
		if(!plumber.location.Repair())
			Skeleton.Println("FAILURE! Pump couldn't get fixed.");
		else
			Skeleton.Println("SUCCES! Pump got fixed.");
	}

}

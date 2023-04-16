package sivatagiVizhalozat;

import java.util.Scanner;

public class PlayerMoveTest extends Test {
	PlayerMoveTest(String name) {
		super(name);
	}
	
	public void ToField(FieldElement f) {
		Plumber plumber = new Plumber();
		Pipe pipe1 = new Pipe();			//Create the pipe where he moves from
		Pump pump = new Pump();
		
		pump.Connect(pipe1);
		f.Connect(pipe1);
		plumber.SetLocation(pipe1);			//The player stands on the pipe
		pipe1.StepOn(plumber);				//Step the player on the pipe
		plumber.PlayerMove(f);				//The player moves to the fieldElement
		
		Skeleton.Println("");
		Skeleton.Println("SETUP COMPLETE");
		Skeleton.Println("");
		
		if(plumber.GetLocation() == f) {
			Skeleton.Println("");
			Skeleton.Println("SUCCES!");
			Skeleton.Println("");
		} else {
			Skeleton.Println("");
			Skeleton.Println("FAILURE!");
			Skeleton.Println("");
		}
	}
	
	public void ToPipe() {
		Plumber plumber = new Plumber();	//The Player moves to the pipe
		Pump pump1 = new Pump();			//Create the location where he moves from
		Pipe pipe1 = new Pipe();			//Create the pipe where he moves to
		Pump pump2 = new Pump();
		
		pump1.Connect(pipe1);
		pump2.Connect(pipe1);
		plumber.SetLocation(pump1);			//The player stands on the pump
		pump1.StepOn(plumber);				//Step the player on the pump
		plumber.PlayerMove(pipe1);			//The player moves to the pipe
		
		Skeleton.Println("");
		Skeleton.Println("SETUP COMPLETE");
		Skeleton.Println("");
		
		if(plumber.GetLocation() == pipe1) {
			Skeleton.Println("");
			Skeleton.Println("SUCCESS!");
			Skeleton.Println("");
		} else {
			Skeleton.Println("");
			Skeleton.Println("FAILURE!");
			Skeleton.Println("");
		}
	}
	
	public void To_Pipe() {
		Plumber plumber1 = new Plumber();	//The Player moves to the pipe
		Pump pump1 = new Pump();			//Create the location where he moves from
		Pipe pipe1 = new Pipe();			//Create the pipe where he moves to
		Pump pump2 = new Pump();
		Plumber plumber2 = new Plumber();
		
		pump1.Connect(pipe1);
		pump2.Connect(pipe1);
		plumber1.SetLocation(pump1);		//The player stands on the pump
		pump1.StepOn(plumber1);				//Step the player on the pump
		plumber2.SetLocation(pipe1);		//The player stands on the pipe
		pipe1.StepOn(plumber2);				//Step the player on the pipe
		plumber1.PlayerMove(pipe1);			//The player moves to the pipe
		
		Skeleton.Println("");
		Skeleton.Println("SETUP COMPLETE");
		Skeleton.Println("");
		
		if(plumber1.GetLocation() != pipe1) {
			Skeleton.Println("");
			Skeleton.Println("SUCCESS!");
			Skeleton.Println("");
		} else {
			Skeleton.Println("");
			Skeleton.Println("FAILURE!");
			Skeleton.Println("");
		}
	}
	
	public void Run() {
		boolean quit = false;
		while(!quit)
		{
			//Subscenarios
			Skeleton.Println("0 to quit");
			Skeleton.Println("Cistern: 1,");
			Skeleton.Println("Pump: 2,");
			Skeleton.Println("Spring: 3");
			Skeleton.Println("Pipe : 4");
			Skeleton.Println("Pipe with a player: 5");
			Skeleton.Println("Chose test scenario:");
			Scanner sc = new Scanner(System.in);
			String str = sc.nextLine();
			try 
			{
				int choice = Integer.parseInt(str)-1;
				if(!str.isBlank())
				{
					if(choice == -1)
					{
						quit = true;
					}
					else
					{
						if(choice == 0) {
							//Test Cistern
							Cistern c = new Cistern();
							ToField(c);
						} else if(choice == 1) {
							//Test pump
							Pump p = new Pump();
							ToField(p);
						} else if(choice == 2) {
							//Test Spring
							Spring s = new Spring();
							ToField(s);
						} else if(choice == 3) {
							//Test pipe
							ToPipe();
						} else if(choice == 4) {
							//Test stepping on the pipe with a player standing on it
							To_Pipe();
						}
					}
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				Skeleton.Println("Something ain't right! Check your input");
			}
		}
	}
}

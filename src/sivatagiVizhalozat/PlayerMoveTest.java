package sivatagiVizhalozat;

public class PlayerMoveTest extends Test {
	PlayerMoveTest(String name) {
		super(name);
	}
	
	public void ToField(FieldElement f) {
		Plumber plumber = new Plumber();
		Pipe pipe1 = new Pipe();			//Create the pipe where he moves from
		
		f.Connect(pipe1);
		plumber.SetLocation(pipe1);			//The player stands on the pipe
		plumber.PlayerMove(f);				//The player moves to the fieldElement
		
		Skeleton.Println("");
		Skeleton.Println("SETUP COMPLETE");
		Skeleton.Println("");
		
		if(plumber.GetLocation() == f) {
			Skeleton.Println("SUCCES!");
		} else {
			Skeleton.Println("FAILURE!");
		}
	}
	
	public void ToPipe() {
		Plumber plumber = new Plumber();	//The Player moves to the pipe
		Pump pump1 = new Pump();			//Create the location where he moves from
		Pipe pipe1 = new Pipe();			//Create the pipe where he moves to
		
		pump1.Connect(pipe1);
		plumber.SetLocation(pump1);			//The player stands on the pump
		plumber.PlayerMove(pipe1);			//The player moves to the pipe
		
		Skeleton.Println("");
		Skeleton.Println("SETUP COMPLETE");
		Skeleton.Println("");
		
		if(plumber.GetLocation() == pipe1) {
			Skeleton.Println("SUCCES!");
		} else {
			Skeleton.Println("FAILURE!");
		}
	}
	
	public void Run() {
		//Test pipe
		ToPipe();
		
		//Test pump
		Pump p = new Pump();
		ToField(p);
		
		//Test Cistern
		Cistern c = new Cistern();
		ToField(c);
		
		//Test Spring
		Spring s = new Spring();
		ToField(s);
	}
}

package sivatagiVizhalozat;

public class PumpDirectionTest extends Test {
	PumpDirectionTest(String name) {
		super(name);
	}
	
	public void Run() {
		Pipe pipe1 = new Pipe();			//Create the new input pipe
		Pipe pipe2 = new Pipe();			//Create the new output pipe
		Pipe pipe3 = new Pipe();			//The pump must have at least one connection
		Pump pump = new Pump();				//Create the pump
		Plumber p = new Plumber();			//The plumber changes the direction of the pipe
		
		p.SetLocation(pump);
		pump.Connect(pipe1);
		pump.Connect(pipe2);
		pump.Connect(pipe3);
		
		Skeleton.Println("");
		Skeleton.Println("SETUP COMPLETE");
		Skeleton.Println("");
		
		pump.ChangeDirection(1, 2);			//Change the input to the first neighbor (pipe1) and the output to the second neighbor (pipe2)
		
		if(pump.getInput() == 1 && pump.getOutput() == 2) {
			Skeleton.Println("SUCCES!");
		} else {
			Skeleton.Println("FAILURE!");
		}
	}
}

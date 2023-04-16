package sivatagiVizhalozat;

public class RepairPipeTest extends Test {
	RepairPipeTest(String name) {
		super(name);
	}
	
	public void Run() {
		Pipe pipe = new Pipe();
		Plumber plumber = new Plumber();//Only plumber can repair a pipe
		
		plumber.SetLocation(pipe);		//The plumber is repairing the pipe on which he is standing
		pipe.Puncture();
		
		Skeleton.Println("");
		Skeleton.Println("SETUP COMPLETE");
		Skeleton.Println("");
		
		plumber.Repair();				//Repair the punctured pipe

		if(!pipe.getIsPunctured()) {
			Skeleton.Println("");
			Skeleton.Println("SUCCESS! Pipe is repaired.");
			Skeleton.Println("");
		} else {
			Skeleton.Println("");
			Skeleton.Println("FAILURE! Pipe is not repaired.");
			Skeleton.Println("");
		}
	}
}

package sivatagiVizhalozat;

public class PuncturePipeTest extends Test {
	PuncturePipeTest(String name) {
		super(name);
	}
	
	public void Run() {
		Pipe p = new Pipe();	//Pipe to puncture
		Saboteur s = new Saboteur();	//Saboteur punctures the pipe
		
		s.SetLocation(p);		//The Saboteur is puncturing the pipe on which he is standing
		
		Skeleton.Println("");
		Skeleton.Println("SETUP COMPLETE");
		Skeleton.Println("");
		
		s.PuncturePipe();
		
		if(p.getIsPunctured()) {
			Skeleton.Println("");
			Skeleton.Println("SUCCESS! It is punctured.");
			Skeleton.Println("");
		} else {
			Skeleton.Println("");
			Skeleton.Println("FAILURE! It is not punctured.");
			Skeleton.Println("");
		}
	}
}

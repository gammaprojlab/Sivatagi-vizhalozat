package sivatagiVizhalozat;

public class GeneratePipeTest extends Test{

	GeneratePipeTest(String name) {
		super(name);
	}
	
	public void Run()
	{
		//Setting up test elements
		Game g = new Game();
		Cistern c = new Cistern(2,g);
		Pipe p = new Pipe(2,g);
		
		Skeleton.Println("");
		Skeleton.Println("SETUP COMPLETE");
		Skeleton.Println("");
		
		//The functional part of the test
			//Is the p Pipe connected to the c Cistern?
		if(c.Connect(p))
			Skeleton.Println("The Pipe is connected to the Cistern");
			//Checking
		if(c.GetNeighbor().size()==1) {
			Skeleton.Println("SUCCES! There really is a Pipe connected to this Cistern");
			Skeleton.indentation++;
				Skeleton.Println("The id of the pipe: "+c.GetNeighbor().get(0));
			Skeleton.indentation--;
		}
		else
			Skeleton.Println("FAILURE! The pipe didn't get connected to the ");
	}
}

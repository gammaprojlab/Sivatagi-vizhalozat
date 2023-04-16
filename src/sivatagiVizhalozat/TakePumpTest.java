package sivatagiVizhalozat;

public class TakePumpTest extends Test{

	TakePumpTest(String name) {
		super(name);
	}
	
	public void Run()
	{
		//Setting up test elements
		Game game = new Game();
		
		Cistern cistern = new Cistern(2,game);
		
		//Place player on pipe
		Plumber plumber = new Plumber(cistern,game);
		
		Skeleton.Println("");
		Skeleton.Println("SETUP COMPLETE");
		Skeleton.Println("");
		
		//Take pump
		plumber.TakePump();

	}
}

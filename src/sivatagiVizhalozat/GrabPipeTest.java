package sivatagiVizhalozat;

public class GrabPipeTest extends Test {
	GrabPipeTest(String name) {
		super(name);
	}
	
	public void OnField(FieldElement f) {
		Plumber plumber = new Plumber();//Only a plumber can grab a pipe
		Pipe pipe1 = new Pipe();		//The pipe, which will be grabbed by a plumber
		Pipe pipe2 = new Pipe();		//Can't grab the last pipe
		
		f.Connect(pipe1);
		f.Connect(pipe2);
		plumber.SetLocation(f);			//The player stands on a fieldelement
		
		Skeleton.Println("");
		Skeleton.Println("SETUP COMPLETE");
		Skeleton.Println("");
		
		plumber.GrabPipe();
		
		if(plumber.setHeldPipe() == pipe1 || plumber.setHeldPipe() == pipe2) {
			Skeleton.Println("SUCCES!");
		} else {
			Skeleton.Println("FAILURE!");
		}
	}
	
	public void Run() {
		//Test pump
		Pump p = new Pump();
		OnField(p);
		
		//Test cistern
		Cistern c = new Cistern();
		OnField(c);
		
		//Test spring
		Spring s = new Spring();
		OnField(s);
	}
}

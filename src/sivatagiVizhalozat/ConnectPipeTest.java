package sivatagiVizhalozat;

public class ConnectPipeTest extends Test {
	ConnectPipeTest(String name) {
		super(name);
	}
	
	public void ToField(FieldElement f) {
		Spring s = new Spring();
		Pipe p1 = new Pipe();		//This will be connected
		Pipe p2 = new Pipe();		//This connects the fieldelement and an other element
		Plumber plumber = new Plumber();
		
		f.Connect(p2);
		s.Connect(p1);				//Connected to a pipe which is held by the plumber
		s.Connect(p2);
		plumber.SetLocation(f);		//The plumber connects the pipe to this fieldelement
		plumber.setHeldPipe(p1);
		
		Skeleton.Println("");
		Skeleton.Println("SETUP COMPLETE");
		Skeleton.Println("");
		
		plumber.ConnectPipe();
		
		if(f.getMaxConnections() == 2) {
			Skeleton.Println("SUCCES! FieldElement is connected.");
		} else {
			Skeleton.Println("FAILURE! FieldElement is not connected.");
		}
	}
	
	public void Run() {
		//Test cistern
		Cistern c = new Cistern();
		ToField(c);
		
		//Test pump
		Pump p = new Pump();
		ToField(p);
		
		//Test spring
		Spring s = new Spring();
		ToField(s);
		
	}
}

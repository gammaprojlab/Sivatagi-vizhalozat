package sivatagiVizhalozat;

import java.util.Scanner;

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
		
		if(f.GetNeighbor().get(1) == p1) {
			Skeleton.Println("");
			Skeleton.Println("SUCCESS! FieldElement is connected.\n");
			Skeleton.Println("");
		} else {
			Skeleton.Println("");
			Skeleton.Println("FAILURE! FieldElement is not connected.\n");
			Skeleton.Println("");
		}
	}
	
	public void Run() {
		boolean quit = false;
		while(!quit)
		{
			Skeleton.Println("Chose test scenario:");
			Skeleton.Println("0 to quit");
			Skeleton.Println("Cistern: 1,");
			Skeleton.Println("Pump: 2,");
			Skeleton.Println("Spring: 3");
			
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
							//Test cistern
							Cistern c = new Cistern();
							ToField(c);
						} else if(choice == 1) {
							//Test pump
							Pump p = new Pump();
							ToField(p);
						} else if(choice == 2) {
							//Test spring
							Spring s = new Spring();
							ToField(s);
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

package sivatagiVizhalozat;

import java.util.Scanner;

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
		
		if(plumber.GetPipe() == pipe1 || plumber.GetPipe() == pipe2) {
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
			
			Skeleton.Println("0 to quit");
			Skeleton.Println("Cistern: 1,");
			Skeleton.Println("Pump: 2,");
			Skeleton.Println("Spring: 3");
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
							//Test cistern
							Cistern c = new Cistern();
							OnField(c);
						} else if(choice == 1) {
							//Test pump
							Pump p = new Pump();
							OnField(p);
						} else if(choice == 2) {
							//Test spring
							Spring s = new Spring();
							OnField(s);
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

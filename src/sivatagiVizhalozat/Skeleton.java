package sivatagiVizhalozat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Skeleton Class
 * Responsible for the control of the tests.
 */

public class Skeleton {
	static int indentation = 0;
	private  List<Test> tests = new ArrayList<Test>();
	private Boolean quit = false;
	
	Skeleton()
	{
		
	}

	public void Run() {
		tests.add(new ConnectPipeTest("ConnectPipeTest"));
		tests.add(new DisconnectPipeTest("DisconnectPipeTest"));
		tests.add(new FixPumpTest("FixPumpTest"));
		tests.add(new GeneratePipeTest("GeneratePipeTest"));
		tests.add(new GrabPipeTest("GrabPipeTest"));
		tests.add(new PlacePumpTest("PlacePumpTest"));
		tests.add(new PumpDirectionTest("PumpDirectionTest"));
		tests.add(new PumpNotWorkingTest("PumpNotWorkingTest"));
		tests.add(new PuncturePipeTest("PuncturePipeTest"));
		tests.add(new RepairPipeTest("RepairPipeTest"));
		tests.add(new TakePumpTest("TakePumpTest"));
		tests.add(new TimerTest("TimerTest"));

		while(!quit)
		{
			
			System.out.println("Chose test scenario:");
			int number = 1;
			indentation++;
			for(Test t : tests)
			{
				Println(number+". "+t.GetName());
				number++;
			}
			indentation--;
			System.out.println("Enter 0 to quit");
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
						if(choice < tests.size() && choice > -1)
						{
							tests.get(choice).Run();
							System.out.println();
							System.out.println("Test finished");
							System.out.println();	
						}
						else
						{
							Println("Invalid number");
						}
						
					}
				}
				else
				{
					Println("TypeStuff");
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				System.out.println("Something ain't right!");
			}
			finally
			{
				
			}
		}
		System.out.println("Program terminated");
	}
	
	public static void Println(String str)
	{
		for (int i = 0; i < indentation; i++)
		{
			System.out.print("|   ");
		}
		System.out.println(str);
	}
}

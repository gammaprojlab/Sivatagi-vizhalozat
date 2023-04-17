package sivatagiVizhalozat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Class Skeleton.
 */

public class Skeleton {
	
	/** The current ammount of indentation */
	static int indentation = 0;
	
	/** The list of tests */
	private  List<Test> tests = new ArrayList<Test>();
	
	/** Tells the skeleton when it is done*/
	private Boolean quit = false;
	
	/**
	 * Instantiates a new skeleton.
	 */
	Skeleton()
	{
		
	}

	/**
	 * Runs the test program.
	 */
	public void Run() {
		//adds the tests to the list
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
			//writes out menu
			for(Test t : tests)
			{
				Println(number+". "+t.GetName());
				number++;
			}
			indentation--;
			//scans for input
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
							//runs test
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
	
	/**
	 * Println.
	 *
	 * @param str the str
	 */
	public static void Println(String str)
	{
		for (int i = 0; i < indentation; i++)
		{
			System.out.print("|   ");
		}
		System.out.println(str);
	}
}

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

	public void run() {
		tests.add(new SkeletonTest1("realTest"));
		tests.add(new Test("test2"));
		tests.add(new Test("test3"));
		tests.add(new Test("test4"));
		tests.add(new Test("test5"));
		
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
						if(choice < tests.size())
						{
							tests.get(choice).Run();
							System.out.println();
							System.out.println("Test finished");
							System.out.println();
							
						}
						else
						{
							Println("Írjál ide valamit");
						}
					}
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				System.out.println("Something ain't right! Check your input");
			}
			finally
			{
				
			}
		}
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

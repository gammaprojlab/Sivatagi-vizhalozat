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
	
	
	Skeleton()
	{
		
	}

	public void run() {
		tests.add(new SkeletonTest1("realTest"));
		tests.add(new Test("test2"));
		tests.add(new Test("test3"));
		tests.add(new Test("test4"));
		tests.add(new Test("test5"));
		
		System.out.println("Chose test scenario:");
		int number = 1;
		indentation++;
		for(Test t : tests)
		{
			Println(number+". "+t.GetName());
			number++;
		}
		indentation--;
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		
		if(!str.isBlank() && Integer.parseInt(str)-1 < tests.size())
		{
			tests.get(Integer.parseInt(str)-1).Run();
		}
		else
		{
			Println("Írjál ide valamit");
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

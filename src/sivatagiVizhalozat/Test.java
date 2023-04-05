package sivatagiVizhalozat;


/**
 * The Test class.
 * Contains the tests name and a run function to run the test
 */
public  class Test {
	
	private String name;
	
	
	Test(String name)
	{
		this.name = name;
	}
	
	public String GetName()
	{
		return name;
	}
	
	public void Run()
	{
		System.out.println(name);
	}
}

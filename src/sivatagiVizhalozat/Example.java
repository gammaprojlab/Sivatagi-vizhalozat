package sivatagiVizhalozat;

import java.util.Scanner;

/**
 * The Class Example.
 * An example of how a class should look to work with Skeleton tests
 */
public class Example
{
	
	/**
	 * Instantiates a new example.
	 *
	 *
	 */
	
	Example()
	{

		Skeleton.Println(this.toString()+"Create()");
		Skeleton.indentation++;
		Skeleton.indentation--;
	}
	

	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString()
	{
		return this.getClass().getSimpleName()+"'"+Integer.toHexString(this.hashCode())+"'"+"."; 
	}
	
	/**
	 * A.
	 */
	public int A(int i)
	{
		Skeleton.Println(this.toString()+"A("+int.class.getSimpleName()+" "+i+")");
		Skeleton.indentation++;
		int a = i*2;
		B();
		C();
		D();
		Skeleton.indentation--;
		Skeleton.Println("return "+a);
		return a;
	}



	/**
	 * B.
	 */
	private int B()
	{
		Skeleton.Println(this.toString()+"B()");
		Skeleton.indentation++;
		String str = C();
		Skeleton.indentation--;
		Skeleton.Println("return "+str.length());
		return str.length();
	}
	
	/**
	 * C.
	 */
	private String C() {
		Skeleton.Println(this.toString()+"C()");
		Skeleton.indentation++;
		System.out.println("What should I return?");
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		Skeleton.indentation--;
		Skeleton.Println("return "+str);
		return str;
	}
	
	/**
	 * D.
	 */
	private void D() {
		Skeleton.Println(this.toString()+"D()");
		Skeleton.indentation++;
		Skeleton.indentation--;
	}

}

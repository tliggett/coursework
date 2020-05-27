package homework3;

//TJ Liggett
//Homework 3
//Class Demo2 prints a user-specified amount (1-5) of random integers in the range [0..100)
//9_19_18
import java.util.InputMismatchException;
//Demo.java
import java.util.Scanner; 
public class Demo2
{
	//Desc: Display up to 5 random integers
	//Input: A number n entered via the keyboard indicating how many random integers
	//Output: n random integers in the range [0..100) displayed on the screen
	public static void main(String[] args)
	{
		int[] numbers = new int[5];
		int n=0;
		Scanner input = new Scanner(System.in);	
		while (true)
		{
			try 	
			{
				n=fillNumbers(numbers,  input);
				showNumbers(numbers, n);
				break;
		    }
			catch(InputMismatchException err)
			{
				System.out.println("***Must enter an integer!");
				input.nextLine();
			}
			catch(negativeNumberException err)
			{
				System.out.println("***negativeNumberException: Negative number is not good!");
			}
			catch(zeroException err)
			{
				System.out.println("***zeroException: 0 is not good!");
			}
			catch(tooBigException err)
			{
				System.out.println("***tooBigException: Value greater than 5 is not good!");
			}
			
		}
	}
	
	//Desc: Prompts the user for an amount of random numbers, reads input and checks to make 
	//		sure number is valid, fills numbers array with said amount of random numbers, 
	//		and returns the amount.
	//Input: The amount of random numbers to be displayed, 1-5
	//Output: User prompt for amount of numbers
	//Return: Amount of random numbers to display
	public static int fillNumbers(int[] numbers, Scanner input) throws negativeNumberException, zeroException, tooBigException 
	{
		System.out.print("How many random numbers you want to see? ");
		int n = input.nextInt();
		if (n<0) throw new negativeNumberException();
		else if (n==0) throw new zeroException();
		else if (n>5) throw new tooBigException();
		for (int i = 0; i < n; ++i)
			numbers[i] = (int)(Math.random()*100);
		return n;
	}
	
	//Desc: Displays 'n' random integers from array 'numbers'
	//Output: User specified amount of random numbers in format:
	//			a	b	c	d	e
	public static void showNumbers(int[] numbers, int n)
	{
		String s="";
		for(int i = 0; i < n; ++i)
		{
			s += numbers[i];
			s += "  ";
		}
		System.out.println(s);
	}

}

//Class negativeNumberException extends Exception and is thrown if input is negative.
class negativeNumberException extends Exception
{
	public negativeNumberException()
	{
		super("Input is negative number.");
	}
}

//Class tooBigException extends Exception and is thrown if input is greater than 5
class tooBigException extends Exception
{
	public tooBigException()
	{
		super("Input exceeds upper bound.");
	}
}

//Class zeroException extends Exception and is thrown if input is zero.
class zeroException extends Exception
{
	public zeroException()
	{
		super("Input is zero.");
	}
}


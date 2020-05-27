package homework3;

//TJ Liggett
//Homework 3
//Class Demo sums two integer values for the user.
//9_19_18


import java.util.Scanner;
import java.util.InputMismatchException;	



class Demo				
{
	//Desc: Takes two user inputed integers, sums them, and outputs result
	//Input: Two integers to sum
	//Output: User prompts, error messages, the resulting sum
	public static void main(String[] args)				
 	{	
		Scanner keyboard=new Scanner(System.in);
		int n1, n2;
		boolean check = false;
		System.out.println("Enter 2 integers to sum, one on each line: ");	
		do {
			try
			{
				n1= keyboard.nextInt();					
				break;								
			}
			catch(InputMismatchException e)
			{
				System.out.println("****Input Error.  Repeat Input.");
				System.out.println("Enter 2 integers to sum, one on each line: ");	
				keyboard.nextLine();
			}
		} while (true);
		
		do {
			try
			{	
				n2= keyboard.nextInt();				
				break;								
			}
			catch(InputMismatchException e)
			{
				System.out.println("****Input Error.  Repeat Input.");
				System.out.println("Enter the second integer: ");	
				check = true;
				keyboard.nextLine();
				
			}
		} while (true);
		
		if(check)
			System.out.println(n1 + " + " + n2 + " = "+(n1+n2));
		else System.out.println("Sum is "+(n1+n2));				
	}
}

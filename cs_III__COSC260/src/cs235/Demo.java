import java.util.Scanner;


//TJ Liggett
//Homework 10
//Class Demo clears a desired bit from an integer
//11_13_2018
//Demo.java


public class Demo {
	
	public static void main(String[] args) 
	{
		Scanner Keyboard=new Scanner(System.in);
   		int mask=1;
   		System.out.print("Enter an integer:");
   		int num=Keyboard.nextInt();					
   		BitWise.printbit(num);			
   		System.out.print("Enter the bit position (0-31) from the right:");
   		int i=Keyboard.nextInt();					
   		mask=mask<<i;	 
   		mask=~mask;
   		num=num&mask;				
   		BitWise.printbit(num);			
	}

}

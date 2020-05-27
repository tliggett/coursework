package homework9;

import java.util.Scanner;

//TJ Liggett
//Homework 9
//IntToEng.java
//Class IntToEng converts a positive integer to English 


public class IntToEng {
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Enter positive integer: ");
		System.out.println(intToEnglish(input.nextInt()));
		input.close();
	}
	
	//Usage:	public static String intToEnglish (int n)
	//Desc:	Converts a positive integer to English.  
	//Pre:	n must be positive.  
	//Return:	A string consists of English words of n. For example, 
	//		if n=372, intToEnglish returns the String "three seven two".
	public static String intToEnglish(int i)
	{
		if(i < 10)
			switch(i)
			{
			case 0: return "zero ";
			case 1: return "one ";
			case 2: return "two ";
			case 3: return "three ";
			case 4: return "four ";
			case 5: return "five ";
			case 6: return "six ";
			case 7: return "seven ";
			case 8: return "eight ";
			case 9: return "nine ";
			}
		return intToEnglish(i/10) + intToEnglish(i%10);
	}
}

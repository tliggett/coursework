package homework8;

import java.util.Scanner;

public class Recursion {
	public static void main (String[] args)
	{
		Scanner keyboard=new Scanner(System.in);
		System.out.print("Enter a base 10 number: ");	
		int n=keyboard.nextInt();				
		System.out.println("Binary: "+ convert(n, 2));
		System.out.println("Octal: "+ convert(n, 8));
		System.out.println("Hex: "+ convert(n, 16));
		
		}							
			//Desc: convert n to base b
			//Pre: n>0, 16>=b>1
			//Post: the string that represents n in base b
			public static String convert (int n, int b)
			{
				String bit = "";
				if(n%b < 10) bit += n%b;
				else switch(n%b)
				{
					case 10: bit += "A";
					break;
					case 11: bit += "B";
					break;
					case 12: bit += "C";
					break;
					case 13: bit += "D";
					break;
					case 14: bit += "E";
					break;
					case 15: bit += "F";
					break;
				}
				if(n/b == 0) return bit;
				return convert(n/b, b) + bit;

			}

	
}

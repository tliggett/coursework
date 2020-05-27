package scannerFiles;
//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class - 
//Lab  -

import static java.lang.System.*;

import java.util.Scanner;

public class TheLine
{
   private String line;

   

   public TheLine(String s)
   {
	   line = s;
   }

	public int getLargest()
	{
		int largest = Integer.MIN_VALUE;
		Scanner scan = new Scanner(line);
		while(scan.hasNextInt()){
			int i = scan.nextInt();
			if(i>largest){
				largest = i;
				
			}
			
		}
		return largest;
	}

	public String toString( )
	{
		return "" + line + " - Largest == " + getLargest();
	}
}
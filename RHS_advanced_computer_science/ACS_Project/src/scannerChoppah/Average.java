package scannerChoppah;
//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class - 
//Lab  -

import java.util.Scanner;
import static java.lang.System.*;

public class Average 
{
  
	String line; 
	
   public Average()
   {
	   setLine("");
   }

   public Average(String s)
   {
	   setLine(s);
   }

	public void setLine(String s)
	{
		line = s;
	}

	public int getCount()
	{	
		String[] nums = line.split(" ");
		
		return nums.length;
	}


	public int getSum()
	{
		int total = 0;
		String[] ray = line.split(" ");
		for(String st : ray){
			int i = Integer.parseInt(st);
			total += i;
		}
		return total;
	}

	public double getAverage()
	{
		double average=0.0;
		double sum=0.0;
		double count=0.0;
		sum = getSum();
		count = getCount();
		average = sum/count;

		return average;
	}

	public String getLine()
	{
		return line;
	}

	public String toString()
	{
		return getLine() + "\nAverage: " + getAverage();
	}
}